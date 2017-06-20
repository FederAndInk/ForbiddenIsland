package model.card;

import model.game.Game;
import model.game.Tile;
import model.game.TileState;
import util.exception.TileException;



public class FloodCard extends Card {
    
    /**
     * @author nihil
     *
     * @param type
     */
    protected FloodCard() {
        super(CardType.FLOOD_CARD);
    }
    
    
    /**
     * 
     * @param applied
     * = {@link Game}
     * @see model.card.Card#applyAction(model.game.Tile, java.lang.Object)
     */
    @Override
    public void applyAction(Tile destTile, Object applied) throws TileException {
        switch (destTile.getState()) {
        case DRIED:
            if (applied instanceof Game) {
                Game game = (Game) applied;
                game.getFloodDeck().discard(this);
            } else {
                throw new IllegalArgumentException("Not a game");
            } // end if
            destTile.setState(TileState.FLOODED);
            break;
        
        case FLOODED:
            destTile.setState(TileState.SINKED);
            break;
        
        case SINKED:
            throw new TileException(destTile, TileState.SINKED);
        }
    }
    
}