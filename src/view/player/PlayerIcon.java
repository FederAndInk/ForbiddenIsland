/**
 * 
 */
package view.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.adventurers.AdventurerType;



/**
 * @author nihil
 *
 */
public class PlayerIcon extends JPanel {
    private AdventurerType pawn;
    
    
    /**
     * @author nihil
     *
     * @param pawn
     */
    public PlayerIcon(AdventurerType pawn) {
        super();
        this.pawn = pawn;
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage bImage = ImageIO.read(new File(isEnabled() ? pawn.getIconSelect() : pawn.getIconEnable()));
            g.drawImage(bImage, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
