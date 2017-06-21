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
public class PlayerCard extends JPanel {
    private CardType card;
    private int      cardPlace;
    
    
    public PlayerCard(CardType cardType, int cardPlace) {
        this.card = cardType;
        this.cardPlace = cardPlace;
    }// end addCard
    
    
    @Override
    protected void paintComponent(Graphics g) {
        try {
            BufferedImage bi = ImageIO.read(new File(card.getPathCard()));
            g.drawImage(bi, 0, 0, (int) (getSize().getHeight() / bi.getHeight() * bi.getWidth()),
                    (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
