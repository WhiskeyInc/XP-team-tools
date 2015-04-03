package string.formatter;

public class Formatter implements IStringFormatter{

	@Override
	public String formatMessage(String nickname) {
		return "[" + nickname + "]:";
	}
	
	@Override
	public String formatMessage(String sender, String conversation,
			String message) {
		// TODO Auto-generated method stub
		return "TODO formatting";
	}
}
