package server.model;

/**
 * Class representing a message
 * [Still needs a responsability]
 */
public class Message {

	private String author;
	private String message;

	public Message(String author, String message) {
		super();
		this.author = author;
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public String getAuthor() {
		return author;
	}
}
