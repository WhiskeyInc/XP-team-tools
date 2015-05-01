package ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * New kind of button having a double color that changes on click  
 * @author Nicola
 */
public class JGradientButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	private static final Color defaultColor = Color.WHITE;
	private Color color1 = defaultColor;
	private Color color2 = defaultColor;


	JGradientButton(String str) {
		super(str);
		setContentAreaFilled(false);
		addMouseListener(this);
	}

	/**
	 * Sets the second color of the button, first is white
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color2 = color;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(new GradientPaint(new Point(0, 0), color1, new Point(
				0, getHeight()), color2));
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.dispose();

		super.paintComponent(g);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		color1 = color2;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		color1 = defaultColor;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

}