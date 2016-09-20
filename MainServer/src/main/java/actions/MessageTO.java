package actions;

import java.io.Serializable;
import java.util.List;

import database.Message;

public class MessageTO implements Serializable {

	private static final long serialVersionUID = 8985162540577327498L;
	
	private List<Message> messages;

	public MessageTO(List<Message> messages) {
		this.messages = messages;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
}
