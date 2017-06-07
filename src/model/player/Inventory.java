package model.player;

import java.util.ArrayList;

import model.card.Card;
import model.game.Treasure;



public class Inventory {
    
    private ArrayList<Card>     cards;
    private ArrayList<Treasure> tresures;
    private static final int    MAX_CARD = 5;
    
    
    /**
     * @author nihil
     *
     */
    public Inventory() {
        cards = new ArrayList<>();
        tresures = new ArrayList<>();
    }
    
    
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