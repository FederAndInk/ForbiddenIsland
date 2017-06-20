/**
 * 
 */
package view.Cards;

import java.awt.GridLayout;

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
    
}
