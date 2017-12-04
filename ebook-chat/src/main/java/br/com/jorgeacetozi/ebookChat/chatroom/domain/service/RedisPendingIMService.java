package br.com.jorgeacetozi.ebookChat.chatroom.domain.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.PendingIM;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.repository.PendingIMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.ChatRoom;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.ChatRoomUser;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.InstantMessage;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.repository.ChatRoomRepository;
import br.com.jorgeacetozi.ebookChat.utils.Destinations;

@Service
public class RedisPendingIMService implements PendingIMService {

	@Autowired
	private SimpMessagingTemplate webSocketMessagingTemplate;

	@Autowired
	private PendingIMRepository pendingIMRepository;

	@Override
	public Set<String> find(String user) {
		PendingIM pendingIM = pendingIMRepository.findOne(user);
		if (pendingIM != null) {
			return pendingIM.getPendingFromUsers();
		} else {
			return new HashSet<>();
		}
	}

	@Override
	public void add(String user, String fromUser) {
		PendingIM pendingIM;
		if (pendingIMRepository.exists(user)) {
			pendingIM = pendingIMRepository.findOne(user);
		} else {
			pendingIM = new PendingIM(user);
		}
		pendingIM.addPendingUser(fromUser);
		pendingIMRepository.save(pendingIM);
		sendPendingIMToUser(user, pendingIM.getPendingFromUsers());
	}

	@Override
	public void remove(String user, String fromUser) {
		if (!pendingIMRepository.exists(user)) {
			return;
		}
		PendingIM pendingIM = pendingIMRepository.findOne(user);
		pendingIM.removePendingUser(fromUser);
		pendingIMRepository.save(pendingIM);
		sendPendingIMToUser(user, pendingIM.getPendingFromUsers());
	}

	@Override
	public void removeAll(String user) {
		if (!pendingIMRepository.exists(user)) {
			return;
		}
		PendingIM pendingIM = pendingIMRepository.findOne(user);
		pendingIM.removeAllPendingUsers();
		pendingIMRepository.save(pendingIM);
		assert pendingIM.getPendingFromUsers().isEmpty();
		sendPendingIMToUser(user, pendingIM.getPendingFromUsers());
	}

	private void sendPendingIMToUser(String user, Set<String> pendingFromUsers) {
		webSocketMessagingTemplate.convertAndSendToUser(
				user,
				Destinations.ChatRoom.pendingMessages(),
				pendingFromUsers);
	}
}
