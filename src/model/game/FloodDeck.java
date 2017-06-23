package model.game;

import model.card.Card;
import model.card.FloodCard;



public class FloodDeck extends Deck {
    
    public FloodDeck(Island island) {
        super(island);
    }
    
    
    /**
     * @see model.game.Deck#discard(model.card.Card)
     */
    @Override
    public void discard(Card card) {
        if (!(((FloodCard) card).getAppliedTile().getState() == TileState.SINKED)) {
            super.discard(card);
        } // end if
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
