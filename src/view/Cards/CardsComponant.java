/**
 * 
 */
package view.Cards;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.card.CardType;



/**
 * @author nihil
 *
 */
public class CardsComponant extends JPanel {
    private CardType type;
    private boolean  empty;
    
    
    /**
     * @author nihil
     *
     * @param type
     */
    public CardsComponant(CardType type) {
        super();
        this.type = type;
        empty = false;
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!empty) {
            try {
                BufferedImage bi = ImageIO.read(new File(type.getBack()));
                g.drawImage(bi, 0, 0, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end if
    }
    
}
