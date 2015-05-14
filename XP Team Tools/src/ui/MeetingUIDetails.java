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
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.qt.datapicker.DatePicker;

/**
 * Class to ask details of a meeting to the user, made using DatePicker library
 * @author Nicola
 */
public class MeetingUIDetails extends JPanel {

	private static final long serialVersionUID = 1L;

	private ObservingTextField dateField = new ObservingTextField();
	private JTextField hourField = new JTextField();
	private JButton btn = new JButton("Pick Date");
	private JTextArea name = new JTextArea("");
	private JButton createButton = new JButton("Create Event");
	
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	
	public MeetingUIDetails() {
		super();
		setLayout(new GridLayout(4, 1));
		
		
		p1.add(new JLabel("Date:      "));
		
		dateField.setColumns(10);
		dateField.setText("");
		dateField.setToolTipText("Date format: dd/mm/yy");
		dateField.setEditable(false);
		dateField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		final Locale locale = getLocale(null);
		
		p1.add(dateField);
		p1.add(new JLabel("       "));
		p1.add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// instantiate the DatePicker
				DatePicker dp = new DatePicker(dateField, locale);
				Date selectedDate = dp.parseDate(dateField.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(dateField);
			};
		});
		
		p2.add(new JLabel("Hour:     "));
		p2.add(new JLabel(""));
		hourField.setColumns(10);
		hourField.setText("");
		hourField.setToolTipText("Hour format: hh:mm");
		hourField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p2.add(hourField);
		p2.add(new JLabel("                                  "));
		
		
		p3.add(new JLabel("Agenda:"));
		name.setLineWrap(true);
		name.requestFocus();
		name.setWrapStyleWord(true);
		name.setFont(new Font("TimesRoman", Font.ITALIC, 13));
		
		name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JScrollPane pane = new JScrollPane(name, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(300, 65));
		pane.setMinimumSize(new Dimension(300, 65));
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.getVerticalScrollBar().setUI(new MyScrollbarUI());
		p3.add(pane);
		p4.add(createButton);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
	
		
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

	public String getYear() throws NoSuchElementException{
		StringTokenizer tok = new StringTokenizer(dateField.getText(),"/");
		tok.nextToken();
		tok.nextToken();
		return tok.nextToken();
	}
	
	public String getMonth() throws NoSuchElementException{
		StringTokenizer tok = new StringTokenizer(dateField.getText(),"/");
		tok.nextToken();
		return tok.nextToken();
	}
	
	public String getDay() throws NoSuchElementException{
		StringTokenizer tok = new StringTokenizer(dateField.getText(),"/");
		return tok.nextToken();
	}
	
	public String getMinute() throws NoSuchElementException{
		StringTokenizer tok = new StringTokenizer(hourField.getText(),":");
		tok.nextToken();
		return tok.nextToken();
	}
	
	public String getHour() throws NoSuchElementException{
		StringTokenizer tok = new StringTokenizer(hourField.getText(),":");
		return tok.nextToken();
	}

	@Override
	public String getName() {
		return name.getText();
	}

}

class ObservingTextField extends JTextField implements Observer {

	private static final long serialVersionUID = 1L;

	@Override
	public void update(Observable o, Object arg) {
		Calendar calendar = (Calendar) arg;
		DatePicker dp = (DatePicker) o;
		setText(dp.formatDate(calendar));
	}
}
