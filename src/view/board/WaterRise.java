/**
 * 
 */
package view.board;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import util.Parameters;



/**
 * @author nihil
 *
 */
public class WaterRise extends JPanel {
    private int        level;
    private static int locationLevel = 0;
    private static int levelUp       = 0;
    
    
    /**
     * @author nihil
     *
     */
    public WaterRise(int level) {
        super();
        setLevel(level);
    }
    
    
    /**
     * @param level
     * the level to set
     */
    private void setLevel(int level) {
        this.level = level - 1;
    }
    
    
    /**
     * @author nihil
     *
     */
    public void moveCursor(int level) {
        setLevel(level);
        repaint();
    }
    
    
    /**
     * @author nihil
     *
     */
    private double getScale() {
        if (level < 2) {
            return 0.082;
        } else if (level < 5) {
            return 0.085;
        } else if (level < 7) {
            return 0.084589;
        } else if (level < 8) {
            return 0.083289;
        } else if (level < 9) {
            return 0.084;
        } else {
            return 0.084;
        } // end if
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        locationLevel = (int) (getSize().getHeight() * 0.809);
        levelUp = (int) (getSize().getHeight() * getScale());
        try {
            BufferedImage bImage = ImageIO.read(new File(Parameters.RESOURCES + "Niveau.png"));
            g.drawImage(bImage, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
            BufferedImage bImageS = ImageIO.read(new File(Parameters.RESOURCES + "stick.png"));
            g.drawImage(bImageS, 0, locationLevel - levelUp * level, (int) (getSize().getWidth() * 0.2),
                    (int) ((getSize().getWidth() * 0.2) * bImageS.getHeight() / bImageS.getWidth()), this);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
