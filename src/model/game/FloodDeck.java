package model.game;

import model.card.FloodCard;



public class FloodDeck extends Deck {
    
    public FloodDeck() {
        super();
    }
    
    
    @Override
    public void initDeck() {
        for (Site i : Site.values()) {
            addCardToDeck(new FloodCard(i));
        }
    }
    
}
