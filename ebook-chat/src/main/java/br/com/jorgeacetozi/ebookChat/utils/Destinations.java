package br.com.jorgeacetozi.ebookChat.utils;

public class Destinations {

	public static class ChatRoom {
		
		public static String publicMessages(String chatRoomId) {
			return "/topic/" + chatRoomId + ".public.messages";
		}
		
		public static String privateMessages() {
			return "/queue/private.messages";
		}

		public static String pendingMessages() {
			return "/queue/pending.messages";
		}
		
		public static String connectedUsers(String chatRoomId) {
			return "/topic/" + chatRoomId + ".connected.users";
		}
	}
}
