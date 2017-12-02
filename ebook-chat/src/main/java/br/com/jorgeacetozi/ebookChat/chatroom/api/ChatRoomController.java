package br.com.jorgeacetozi.ebookChat.chatroom.api;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.ChatRoom;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.ChatRoomUser;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.InstantMessage;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.service.ChatRoomService;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.service.InstantMessageService;

@Controller
public class ChatRoomController {

	@Autowired
	private ChatRoomService chatRoomService;

	@Autowired
	private InstantMessageService instantMessageService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/chatroom", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
		return chatRoomService.save(chatRoom);
	}

	@RequestMapping("/chatroom/{toUser}")
	public ModelAndView join(@PathVariable String toUser, Principal principal) {
		ModelAndView modelAndView = new ModelAndView("chatp2p");
		modelAndView.addObject("toUser", toUser);
		return modelAndView;
	}

	@SubscribeMapping("/old.messages")
	public List<InstantMessage> listOldMessagesFromUserOnSubscribe(Principal principal,
			SimpMessageHeaderAccessor headerAccessor) {
		String toUser = headerAccessor.getSessionAttributes().get("toUser").toString();
		return instantMessageService.findAllInstantMessagesFor(principal.getName(), toUser);
	}

	@MessageMapping("/send.message")
	public void sendMessage(@Payload InstantMessage instantMessage, Principal principal,
			SimpMessageHeaderAccessor headerAccessor) {
		String toUser = headerAccessor.getSessionAttributes().get("toUser").toString();
		instantMessage.setToUser(toUser);
		instantMessage.setFromUser(principal.getName());
		chatRoomService.sendPrivateMessage(instantMessage);
	}
}
