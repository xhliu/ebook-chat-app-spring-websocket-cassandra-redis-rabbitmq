package br.com.jorgeacetozi.ebookChat.chatroom.domain.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.ChatRoom;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.model.InstantMessage;
import br.com.jorgeacetozi.ebookChat.chatroom.domain.repository.InstantMessageRepository;

import static java.util.Collections.*;

@Service
public class CassandraInstantMessageService implements InstantMessageService {

	@Autowired
	private InstantMessageRepository instantMessageRepository;

	@Override
	public void appendInstantMessageToConversations(InstantMessage instantMessage) {
		instantMessageRepository.save(instantMessage);
	}

	// TODO: put inside class InstantMessage
	class SortByDate implements Comparator<InstantMessage>
	{
		// Used for sorting in ascending order of date
		public int compare(InstantMessage a, InstantMessage b)
		{
			return a.getDate().compareTo(b.getDate());
		}
	}

	@Override
	public List<InstantMessage> findAllInstantMessagesFor(String username, String toUser) {
		// TODO: optimize w/ @Query
		List<InstantMessage> allIMs = new ArrayList<>();
		// sent from me to the other
		allIMs.addAll(instantMessageRepository.findInstantMessagesByFromUserAndToUser(username, toUser));
		// sent from the other to me
		allIMs.addAll(instantMessageRepository.findInstantMessagesByFromUserAndToUser(toUser, username));
		// order them chronically
		sort(allIMs, new SortByDate());
		return allIMs;
	}
}
