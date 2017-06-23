/**
 * 
 */
package view.Cards;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.card.CardType;
import model.game.Site;



/**
 * @author nihil
 *
 */
public class DiscardComponent extends JPanel {
    private CardType type;
    private Site     lastCardSite;
    private boolean  empty;
    private JLabel   desc;
    
    
    /**
     * @author nihil
     * @param type
     */
    public DiscardComponent(CardType type) {
        super();
        desc = new JLabel();
        this.type = type;
        empty = true;
        init();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void init() {
        desc = new JLabel(type.getSubType() + " Discard", SwingConstants.CENTER);
        // desc.add(lab);
        add(desc);
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!empty) {
            try {
                BufferedImage bi = ImageIO
                        .read(new File(lastCardSite != null ? lastCardSite.getCard() : type.getPathCard()));
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
    public void addCard(CardType card, Site site) {
        lastCardSite = site;
        this.type = card;
        setEmpty(false);
        repaint();
    }
    
    
    /**
     * @author nihil
     *
     */
    public void addCard(CardType card) {
        this.type = card;
        lastCardSite = null;
        setEmpty(false);
        repaint();
    }
    
    
    /**
     * @author nihil
     *
     */
    public void empty() {
        setEmpty(true);
    }
    
    
    /**
     * @param empty
     * the empty to set
     */
    private void setEmpty(boolean empty) {
        this.empty = empty;
        if (empty && getComponent(0) == null) {
            add(desc);
        } else if (!empty) {
            remove(desc);
        } // end if
    }
}
