package server.model;

public class Message {

	public String author;
	public String message;

	public Message(String author, String message) {// classe separata
		super();
		this.author = author;
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

}
