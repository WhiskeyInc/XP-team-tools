package ui.tests;

import javax.swing.JFrame;

/**
 * Classe di utilità Frame
 * @author alberto
 *
 */
public class FramesUtils {

	/**
	 * Crea un JFrame
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
