package br.com.jorgeacetozi.ebookChat.chatroom.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.google.common.annotations.VisibleForTesting;

@RedisHash("pendingim")
public class PendingIM {
	
	@Id
	private String toUser;
	// instant messages sent to @toUser from whom @toUser has not read yet
	private Set<String> pendingFromUsers = new HashSet<>();

	public PendingIM() {
	}

	public PendingIM(String toUser) {
		this.toUser = toUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public Set<String> getPendingFromUsers() {
		return pendingFromUsers;
	}

	public void setPendingFromUsers(Set<String> pendingFromUsers) {
		this.pendingFromUsers = pendingFromUsers;
	}

	public void addPendingUser(String fromUser) {
		this.pendingFromUsers.add(fromUser);
	}

	public void removePendingUser(String fromUser) {
		this.pendingFromUsers.remove(fromUser);
	}

	public void removeAllPendingUsers() {
		this.pendingFromUsers.clear();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PendingIM pendingIM = (PendingIM) o;

		if (!toUser.equals(pendingIM.toUser)) return false;
		return pendingFromUsers != null ? pendingFromUsers.equals(pendingIM.pendingFromUsers) : pendingIM.pendingFromUsers == null;
	}

	@Override
	public int hashCode() {
		int result = toUser.hashCode();
		result = 31 * result + (pendingFromUsers != null ? pendingFromUsers.hashCode() : 0);
		return result;
	}
}
