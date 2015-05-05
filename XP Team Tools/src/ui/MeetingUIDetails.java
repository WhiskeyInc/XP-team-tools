package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.ScrollBarUI;

import com.qt.datapicker.DatePicker;

/**
 * Class to ask details of a meeting to the user, made using the DatePicker library
 * @author Nicola
 */
public class MeetingUIDetails extends JPanel {

	private static final long serialVersionUID = 1L;

	private ObservingTextField dateField = new ObservingTextField();
	private JButton btn = new JButton("Pick Date");
	private JTextArea description = new JTextArea("");
	private JButton createButton = new JButton("Create Event");
	
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	
	public MeetingUIDetails() {
		super();
		setLayout(new GridLayout(3, 1));
		
		
		p1.add(new JLabel("Select a date:"));
		p1.add(new JLabel(""));

		dateField.setColumns(10);
		dateField.setText("");
		dateField.setToolTipText("Date format: dd/mm/yy");
		dateField.setEditable(false);
		dateField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p1.add(dateField);
		final Locale locale = getLocale(null);
		p1.add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// instantiate the DatePicker
				DatePicker dp = new DatePicker(dateField, locale);
				// previously selected date
				Date selectedDate = dp.parseDate(dateField.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(dateField);
			};
		});
		
		p2.add(new JLabel("Description:"));
		description.setLineWrap(true);
		description.requestFocus();
		description.setWrapStyleWord(true);
		description.setFont(new Font("TimesRoman", Font.ITALIC, 13));
		
		description.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JScrollPane pane = new JScrollPane(description, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(300, 65));
		pane.setMinimumSize(new Dimension(300, 65));
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.getVerticalScrollBar().setUI(new MyScrollbarUI());
		p2.add(pane);
		p3.add(createButton);
		
		add(p1);
		add(p2);
		add(p3);
	
		
	}
	
	public void setCreateButtonListener(ActionListener actionListener){
		this.createButton.addActionListener(actionListener);
	}

	private static Locale getLocale(String loc) {
		if (loc != null && loc.length() > 0)
			return new Locale(loc);
		else
			return Locale.ITALY;
	}

	public String getDate() {
		return dateField.getText();
	}

	public String getDescrtiption() {
		return description.getText();
	}

}

class ObservingTextField extends JTextField implements Observer {

	private static final long serialVersionUID = 1L;

	public void update(Observable o, Object arg) {
		Calendar calendar = (Calendar) arg;
		DatePicker dp = (DatePicker) o;
		//System.out.println("picked=" + dp.formatDate(calendar));
		setText(dp.formatDate(calendar));
	}
}
