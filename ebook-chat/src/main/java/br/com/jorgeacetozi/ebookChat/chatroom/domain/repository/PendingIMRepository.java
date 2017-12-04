package br.com.jorgeacetozi.ebookChat.chatroom.domain.repository;

import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.PendingIM;
import org.springframework.data.repository.CrudRepository;

import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.ChatRoom;

public interface PendingIMRepository extends CrudRepository<PendingIM, String> {

}
