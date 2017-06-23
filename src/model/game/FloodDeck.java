package model.game;

import model.card.FloodCard;



public class FloodDeck extends Deck {
    
    public FloodDeck(Island island) {
        super(island);
    }
    
    
    @Override
    public void initDeck(Island island) {
        for (Tile[] i : island.getGrid()) {
            for (Tile tile : i) {
                if (tile != null) {
                    addCardToDeck(new FloodCard(tile));
                } // end if
            }
        }
    }
    
}
