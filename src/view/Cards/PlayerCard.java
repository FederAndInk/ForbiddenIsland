/**
 * 
 */
package view.Cards;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.card.CardType;
import util.FIGraphics;
import util.LogType;
import util.Parameters;
import util.message.InGameAction;
import util.message.InGameMessage;



/**
 * @author nihil
 *
 */
public class PlayerCard extends JPanel {
    private CardType card;
    private Integer  cardPlace;
    private MLCard   mlCard;
    
    
    public PlayerCard(CardType cardType, int cardPlace, Observer obs) {
        this.card = cardType;
        this.cardPlace = cardPlace;
        
        initListener();
        addObs(obs);
        setEnabled(false);
    }// end addCard
    
    
    /**
     * @author nihil
     *
     */
    private void initListener() {
        mlCard = new MLCard();
        addMouseListener(mlCard);
    }
    
    
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
            Parameters.printLog("load file : " + card.getPathCard(), LogType.ACCESS);
            BufferedImage bi = ImageIO.read(new File((card.getPathCard())));
            g.drawImage(bi, 0, 0, (int) (getSize().getWidth()), (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @see javax.swing.JComponent#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setBorder(enabled ? FIGraphics.ACTIVE_BORDER_EXIT : FIGraphics.INACTIVE_BORDER);
    }
    
    
    /**
     * @author nihil
     *
     */
    public void addObs(Observer obs) {
        mlCard.addObserver(obs);
    }
    
    
    /**
     * @param cardPlace
     * the cardPlace to set
     */
    public PlayerCard setCardPlace(int cardPlace) {
        this.cardPlace = cardPlace;
        return this;
    }
    
    
    /**
     * @return the cardPlace
     */
    public Integer getCardPlace() {
        return cardPlace;
    }
    
    
    /**
     * @author nihil
     * @return
     *
     */
    private PlayerCard getThis() {
        return this;
    }
    
    
    /**
     * @return the card
     */
    public CardType getCard() {
        return card;
    }
    
    private class MLCard extends Observable implements MouseListener {
        
        /**
         * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if (isEnabled()) {
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.SELECT_CARD, getThis()));
                clearChanged();
            } // end if
        }
        
        
        /**
         * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
         */
        @Override
        public void mousePressed(MouseEvent e) {
        }
        
        
        /**
         * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        
        /**
         * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            if (isEnabled()) {
                setBorder(FIGraphics.ACTIVE_BORDER_HOVER);
            }
        }
        
        
        /**
         * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseExited(MouseEvent e) {
            if (isEnabled()) {
                setBorder(FIGraphics.ACTIVE_BORDER_EXIT);
            }
        }
        
    }
}
