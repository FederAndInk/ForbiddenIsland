package model.card;

import model.game.Tile;
import model.game.TileState;



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
    public void applyAction(Tile destTile, Object applied) {
        switch (destTile.getState()) {
        case DRIED:
            destTile.setState(TileState.FLOODED);
            break;
        
        case FLOODED:
            destTile.setState(TileState.SINKED);
            break;
        
        case SINKED:
            // TODO add exception
            break;
        }
    }
    
}