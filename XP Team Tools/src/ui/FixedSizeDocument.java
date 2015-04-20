package ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * 
 * A class that imposes that the time setted by the user is in the following format:
 * mm:ss
 * 
 * @author Alberto
 *
 */
public class FixedSizeDocument extends PlainDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int LIMIT = 5;
	
	
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		
		if(getLength() + str.length() > LIMIT) {
			str = str.substring(0, LIMIT-getLength());
		}
		
		try {
			switch (getLength()) {
			case 1:
				Integer.parseInt(getText(0, 1));
				break;
			case 2:
				Integer.parseInt(getText(1, 1));
				break;
			case 3:
				boolean isDots = getText(2, 1).equals(":");
				if(!isDots) {
					throw new NumberFormatException();
				}
				break;
			case 4: 
				Integer.parseInt(getText(3, 1));
				break;
			case 5: 
				Integer.parseInt(getText(4, 1));
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			str = "";
		}
		super.insertString(offs, str, a);
	}
	
}
