/**
 * 
 */
package view.Cards;

import java.awt.Cursor;
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
import util.message.InGameAction;
import util.message.InGameMessage;



/**
 * @author nihil
 *
 */
public class CardsComponant extends JPanel {
    private CardType type;
    private boolean  empty;
    private MlTile   mlTile;
    private File     img;
    
    
    /**
     * @author nihil
     *
     * @param type
     */
    public CardsComponant(CardType type) {
        super();
        this.type = type;
        empty = false;
        setEnabled(false);
        
        initListener();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initListener() {
        mlTile = new MlTile();
        addMouseListener(mlTile);
    }
    
    
    /**
     * @see javax.swing.JComponent#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        img = new File(isEnabled() ? type.getBackSelect() : type.getBack());
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!empty) {
            try {
                BufferedImage bi = ImageIO.read(img);
                g.drawImage(bi, 0, 0, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end if
    }
    
    
    /**
     * @author nihil
     *
     */
    public void addObs(Observer obs) {
        mlTile.addObserver(obs);
    }
    
    // the inner class for an event listener
    protected class MlTile extends Observable implements MouseListener {
        
        /**
         * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            setChanged();
            if (type.equals(CardType.TREASURE_CARD)) {
                notifyObservers(new InGameMessage(InGameAction.DRAW_TREASURE));
            } else {
                notifyObservers(new InGameMessage(InGameAction.DRAW_FLOOD));
            } // end if
            clearChanged();
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
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                img = new File(type.getBackHover());
                repaint();
            } // end if
        }
        
        
        /**
         * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            img = new File(type.getBackSelect());
            repaint();
        }
        
    }
}
