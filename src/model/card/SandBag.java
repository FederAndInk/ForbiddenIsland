package model.card;

import model.game.Tile;
import model.game.TileState;



public class SandBag extends Card {
    
    /**
     * @author nihil
     *
     * @param type
     */
    protected SandBag() {
        super(CardType.SANDBAG_CARD);
    }
    
    
    /**
     * 
     * @param destTile
     * @param applied
     */
    @Override
    public void applyAction(Tile destTile, Object applied) {
        if (destTile.getState() == TileState.FLOODED) {
            destTile.setState(TileState.DRIED);
        } else {
            // TODO add exception
        }
    }
    
}