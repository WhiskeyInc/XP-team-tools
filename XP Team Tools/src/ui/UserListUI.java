package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class UserListUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private String[] nicknames;
	private JPanel nicksPanel = new JPanel();
	private JButton button = new JButton("Start new chat");
	private ArrayList<String> selectedNicknames = new ArrayList<String>();

	public UserListUI() {
		super.setBackground(new Color(244, 249, 228));
		super.setBackground(new Color(248, 244, 255));
		super.setLayout(new GridBagLayout());
		super.setPreferredSize(new Dimension(250, 400));
		super.setMinimumSize(new Dimension(250, 400));
		super.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
		         null, "Team Members panel",
		         TitledBorder.DEFAULT_JUSTIFICATION,
		         TitledBorder.DEFAULT_POSITION,
		         new java.awt.Font("Verdana", 1, 8)
		      ),
		      BorderFactory.createEmptyBorder(1, 1, 1, 1)
		   ));
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		JLabel label = new JLabel("Team members");
		label.setFont(new Font("TimesRoman", Font.BOLD, 16));
		lim.insets = new Insets(10, 5, 5, 5);
		super.add(label, lim);

	}
	
	

	public UserListUI(String[] nicknames) {
		super();
		this.nicknames = nicknames;
	}



	public void setButtonAction(ActionListener listener){
		button.addActionListener(listener);
	}
	

	public String[] getNicknames() {
		return nicknames;
	}

	public ArrayList<String> getListOfSelectedNicknames() {
		return selectedNicknames;
	}
	
	public String[] getSelectedNicknames() {
		int size = selectedNicknames.size();
		String[] sel = new String[size];
		for (int i = 0; i < size; i++) {
			sel[i] = selectedNicknames.get(i);
		}
		return sel;
	}
	//TODO javadoc
	public void setNicknames(String[] nicknames) {
		this.nicknames = nicknames;
		int size = nicknames.length;
		nicksPanel.removeAll();
		nicksPanel.setBackground(new Color(244, 249, 228));
		nicksPanel.setLayout(new GridLayout(size*2 + 1,1));
		//Spostare su un altro pannello...
//		super.setLayout(new GridBagLayout());
//		GridBagConstraints lim = new GridBagConstraints();
//		lim.gridx = 0;
//		lim.gridy = 0;
//		JLabel label = new JLabel("Team members");
//		label.setFont(new Font("TimesRoman", Font.BOLD, 16));
//		lim.insets = new Insets(5, 5, 5, 5);
//		super.add(label, lim);
//		// fino a qua
		nicksPanel.add(new JLabel());
		for(int index = 0; index< size;index++){
			final int i = index;
			labels.add(new JLabel(nicknames[i]));
			labels.get(i).addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					Cursor cursor = Cursor.getDefaultCursor();
				    cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR); 
				    setCursor(cursor);
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					Cursor cursor = Cursor.getDefaultCursor();
				    cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
				    setCursor(cursor);
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(labels.get(i).getForeground().equals(Color.BLUE)){
						labels.get(i).setForeground(Color.BLACK);
					}else{
					labels.get(i).setForeground(Color.BLUE);
					}
				}
			});
			nicksPanel.add(labels.get(index));
			nicksPanel.add(new JLabel());
		}
		
		JScrollPane pane = new JScrollPane(nicksPanel);
//		pane.setPreferredSize(new Dimension(170, 300));
//		pane.setMinimumSize(new Dimension(170, 300));
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		lim.insets = new Insets(5, 5, 5, 2);
		lim.weighty = 1f;
		super.add(pane, lim);
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 2;
		lim.insets = new Insets(5, 2, 5, 5);
		lim.weighty = 1f;
		button.setFont(new Font("TimesRoman", Font.BOLD, 10));
		button.setPreferredSize(new Dimension(170, 40));
		button.setMinimumSize(new Dimension(170, 40));
		
		super.add(button, lim);
	}
	
	public JButton getButton() {
		return button;
	}
	
	public ArrayList<JLabel> getLabels() {
		return labels;
	}
	
	public void deselectAll(){
		for (int i = 0; i < labels.size(); i++) {
			labels.get(i).setForeground(Color.BLACK);
		}
	}

}