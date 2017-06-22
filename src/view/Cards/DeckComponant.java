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
public class DeckComponant extends JPanel {
    private CardType         type;
    private DiscardComponant discard;
    private CardsComponant   cards;
    
    
    /**
     * @author nihil
     *
     */
    public DeckComponant(CardType type) {
        this.type = type;
        setLayout(new GridLayout(2, 1));
        initComponants();
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
    private void initComponants() {
        discard = new DiscardComponant(type);
        cards = new CardsComponant(type);
        
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
