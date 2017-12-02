package br.com.jorgeacetozi.ebookChat.chatroom.domain.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.InstantMessage;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstantMessageRepository extends CassandraRepository<InstantMessage> {

//	@Query("SELECT * FROM messages WHERE fromuser IN (?0)")
//	List<InstantMessage> findInstantMessagesByFromUserAndToUser(String[] userPair);
	//List<InstantMessage> findInstantMessagesByFromUserAndToUser(@Param("username") String username, @Param("toUser") String toUser);
//	@Query("SELECT * FROM messages WHERE fromuser=?0 AND touser=?1")
	List<InstantMessage> findInstantMessagesByFromUserAndToUser(String username, String toUser);
}
