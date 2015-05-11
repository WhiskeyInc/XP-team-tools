package ui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JLabel;

public abstract class TeamLabelMouseAdapter extends MouseAdapter {
	
	private JLabel teamLabel;
	private Font original;

	public TeamLabelMouseAdapter(JLabel teamLabel, Font original) {
		super();
		this.teamLabel = teamLabel;
		this.original = original;
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		Cursor cursor = Cursor.getDefaultCursor();
//		cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		setCursorOut();
		teamLabel.setFont(original);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void mouseEntered(MouseEvent e) {

		setCursorIn();
		Font font = teamLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		teamLabel.setFont(font.deriveFont(attributes));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		launch();
	}
	public abstract void setCursorIn();
	public abstract void setCursorOut();
	public abstract void launch();
	
}
