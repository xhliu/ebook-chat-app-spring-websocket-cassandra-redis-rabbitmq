package br.com.jorgeacetozi.ebookChat.chatroom.domain.model;

import java.util.Date;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;
import br.com.jorgeacetozi.ebookChat.utils.SystemUsers;

@Table("messages")
public class InstantMessage {
	
	@PrimaryKeyColumn(name = "fromUser", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String fromUser;
	
	@PrimaryKeyColumn(name = "toUser", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String toUser;
	
	@PrimaryKeyColumn(name = "date", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
	private Date date;
	
	private String text;
	
	public InstantMessage() { 
		this.date = new Date();
	}
	
	public String getFromUser() {
        return fromUser;
    }
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
    public String getToUser() {
        return toUser;
    }
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }
    public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromUser == null) ? 0 : fromUser.hashCode());
        result = prime * result + ((toUser == null) ? 0 : toUser.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstantMessage other = (InstantMessage) obj;
		if (fromUser == null) {
			if (other.fromUser != null)
				return false;
		} else if (!fromUser.equals(other.fromUser))
			return false;
        if (toUser == null) {
            if (other.toUser != null)
                return false;
        } else if (!toUser.equals(other.toUser))
            return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}


}
