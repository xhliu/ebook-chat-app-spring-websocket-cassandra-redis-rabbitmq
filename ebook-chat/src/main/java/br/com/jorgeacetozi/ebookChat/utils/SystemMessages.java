package br.com.jorgeacetozi.ebookChat.utils;

import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.InstantMessage;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.InstantMessageBuilder;

public class SystemMessages {
	
	public static final InstantMessage welcome(String chatRoomId, String username) {
		return new InstantMessage();
	}

	public static final InstantMessage goodbye(String chatRoomId, String username) {
		return new InstantMessage();
	}
}
