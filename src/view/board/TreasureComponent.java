/**
 * 
 */
package view.board;

import java.awt.Dimension;
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
    boolean      logo;
    
    
    /**
     * @author nihil
     *
     * @param type
     * of the treasure
     * @param logo
     * if true take the logo instead of the treasure img
     */
    public TreasureComponent(TreasureType type, boolean logo) {
        this.type = type;
        this.logo = logo;
        setOpaque(false);
    }
    
    
    /**
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        int dim = (int) (getParent().getParent().getSize().getHeight() * 0.4);
        return new Dimension(dim, dim);
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage bi = ImageIO.read(new File(logo ? type.getLogo() : type.getPath()));
            g.drawImage(bi, 0, 0, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
