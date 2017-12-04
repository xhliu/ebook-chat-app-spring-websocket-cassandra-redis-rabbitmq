package br.com.jorgeacetozi.ebookChat.chatroom.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;

@Component
public class WebSocketEvents {

	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
		List<String> toUsers = headers.getNativeHeader("toUser");
		// only put into session if set in header
		if (toUsers != null) {
			String toUser = toUsers.get(0);
			headers.getSessionAttributes().put("toUser", toUser);
		}
	}

	@EventListener
	private void handleSessionDisconnect(SessionDisconnectEvent event) {
//		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//		String chatRoomId = headers.getSessionAttributes().get("chatRoomId").toString();
//		ChatRoomUser leavingUser = new ChatRoomUser(event.getUser().getName());
//
//		chatRoomService.leave(leavingUser, chatRoomService.findById(chatRoomId));
	}
}
