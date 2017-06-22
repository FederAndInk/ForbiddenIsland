/**
 * 
 */
package view.Cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.card.CardType;



/**
 * @author nihil
 *
 */
public class PlayerCard extends JPanel {
    private CardType card;
    private int      cardPlace;
    
    
    public PlayerCard(CardType cardType, int cardPlace) {
        this.card = cardType;
        this.cardPlace = cardPlace;
        setBorder(BorderFactory.createLineBorder(Color.GREEN));
    }// end addCard
    
    
    /**
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getSize() {
        try {
            BufferedImage bi = ImageIO.read(new File(card.getPathCard()));
            return new Dimension((int) (getParent().getSize().getHeight() / bi.getHeight() * bi.getWidth()),
                    (int) getParent().getSize().getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        try {
            BufferedImage bi = ImageIO.read(new File(card.getPathCard()));
            g.drawImage(bi, 0, 0, (int) (getSize().getWidth()), (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @param cardPlace
     * the cardPlace to set
     */
    public PlayerCard setCardPlace(int cardPlace) {
        this.cardPlace = cardPlace;
        return this;
    }
}
