/**
 * 
 */
package view.Cards;

import java.awt.GridLayout;
import java.util.Observer;

import javax.swing.JPanel;

import model.card.CardType;



/**
 * @author nihil
 *
 */
public class DeckComponent extends JPanel {
    private CardType         type;
    private DiscardComponent discard;
    private CardsComponent   cards;
    
    
    /**
     * @author nihil
     *
     */
    public DeckComponent(CardType type) {
        this.type = type;
        setLayout(new GridLayout(2, 1));
        initComponents();
    }
    
    
    /**
     * @see javax.swing.JComponent#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        cards.setEnabled(enabled);
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initComponents() {
        discard = new DiscardComponent(type);
        cards = new CardsComponent(type);
        
        if (type == CardType.FLOOD_CARD) {
            add(cards);
            add(discard);
        } else {
            add(discard);
            add(cards);
        } // end if
    }
    
    
    /**
     * @author nihil
     *
     * @param observer
     */
    public void addObs(Observer observer) {
        cards.addObs(observer);
    }
    
}
