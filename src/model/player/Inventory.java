package model.player;

import java.util.Collection;

import model.card.Card;
import model.game.Treasure;



public class Inventory {
    
    private Collection<Card>     cards;
    private Collection<Treasure> tresures;
    private static final int     MAX_CARD = 5;
    
    
    /**
     * 
     * @param card
     */
    public void discard(Card card) {
        // TODO - implement Inventory.discard
        throw new UnsupportedOperationException();
    }
    
    
    /**
     * @author nihil
     *
     * @return true if the inventory has a card standalone usable
     */
    public boolean hasCardUsable() {
        for (Card card : cards) {
            if (card.getType().isUsable()) {
                return true;
            } // end if
        } // end for
        return false;
    }
    
}