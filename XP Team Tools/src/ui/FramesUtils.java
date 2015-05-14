package ui;

import javax.swing.JFrame;

/**
 * Utility class that creates a JFrame
 * @author alberto
 *
 */
public class FramesUtils {

	/**
	 * creates a JFrame
	 * @param title
	 * @param dimX
	 * @param dimY
	 * @return JFrame
	 */
	public static JFrame createFrame(String title, int dimX, int dimY) {
		JFrame frame = new JFrame();
		frame.setSize(dimX, dimY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		return frame;
	}
	
	

}
