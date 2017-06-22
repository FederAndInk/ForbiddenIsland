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

import model.game.TreasureType;



/**
 * @author nihil
 *
 */
public class TreasureComponent extends JPanel {
    TreasureType type;
    
    
    public TreasureComponent(TreasureType type) {
        this.type = type;
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage bi = ImageIO.read(new File(type.getPath()));
            g.drawImage(bi, 0, 0, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
