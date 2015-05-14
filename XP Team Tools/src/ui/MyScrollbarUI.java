package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * A class for the customization of scrollbars, it takes arrow icons from Internet and then 
 * replace the old arrows of the scrollbar by overriding methods of @BasicScrollBarUI
 * @author alessandro
 *
 */
public class MyScrollbarUI extends BasicScrollBarUI {

        private ImageIcon downArrow, upArrow, leftArrow, rightArrow;

        public MyScrollbarUI(){
            try {
                upArrow = new ImageIcon(new java.net.URL("http://icons.iconarchive.com/icons/fatcow/farm-fresh/16/bullet-arrow-up-icon.png"));
                downArrow = new ImageIcon(new java.net.URL("http://icons.iconarchive.com/icons/fatcow/farm-fresh/16/bullet-arrow-down-icon.png"));
                rightArrow = new ImageIcon(new java.net.URL("http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/16/Actions-arrow-right-icon.png"));
                leftArrow = new ImageIcon(new java.net.URL("http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/16/Actions-arrow-left-icon.png"));
            } catch (java.net.MalformedURLException ex) {
                ex.printStackTrace();
            }        
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // TODO Auto-generated method stub
        super.paintTrack(g, c, trackBounds);
        }
        
        @Override
        protected JButton createDecreaseButton(int orientation) {
            JButton decreaseButton = new JButton(getAppropriateIcon(orientation)){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(22, 22);
                }
            };
            return decreaseButton;
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            JButton increaseButton = new JButton(getAppropriateIcon(orientation)){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(22, 22);
                }
            };
            return increaseButton;
        }

        private ImageIcon getAppropriateIcon(int orientation){
            switch(orientation){
                case SwingConstants.SOUTH: return downArrow;
                case SwingConstants.NORTH: return upArrow;
                case SwingConstants.EAST: return rightArrow;
                    default: return leftArrow;
            }
        }
    }    
