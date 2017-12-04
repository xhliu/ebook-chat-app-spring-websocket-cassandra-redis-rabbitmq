package br.com.jorgeacetozi.ebookChat.chatroom.domain.service;

import java.util.List;
import java.util.Set;

import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.ChatRoom;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.ChatRoomUser;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.InstantMessage;

public interface PendingIMService {
	// return list of users who messages are pending for @user
	Set<String> find(String user);

	// add a sender @user whose messages are pending for @toUser
	void add(String user, String fromUser);

	// @user reads pending messages from @fromUser
	void remove(String user, String fromUser);

	// remove all pending messages of @user
	void removeAll(String user);
}
