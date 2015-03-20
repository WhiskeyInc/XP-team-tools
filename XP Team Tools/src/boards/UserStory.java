package boards;

public class UserStory {

	private String title;
	private String description;
	private String state;
	
	public UserStory(String title) {
		this.state = "TODO";
		this.description = "";
		this.title = title;
	}
	
	public UserStory(String title, String description) {
		this.state = "TODO";
		this.description = description;
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public String getState() {
		return this.state;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return this.title;
	}
}
