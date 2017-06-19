package model.card;

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
    
    
    @Override
    public void applyAction(Tile destTile, Object applied) throws TileException {
        switch (destTile.getState()) {
        case DRIED:
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