package model.card;

import model.game.Tile;
import model.game.TileState;
import util.exception.TileException;



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
    public void applyAction(Tile destTile, Object applied) throws TileException {
        if (destTile.getState() == TileState.FLOODED) {
            destTile.setState(TileState.DRIED);
        } else {
            throw new TileException(destTile, TileState.DRIED);
        }
    }
    
}