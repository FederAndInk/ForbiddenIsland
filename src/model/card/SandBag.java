package model.card;

import java.util.ArrayList;

import model.game.Island;
import model.game.Tile;
import model.game.TileState;
import util.exception.EndGameException;
import util.exception.TileException;



public class SandBag extends Card {
    
    /**
     * @author nihil
     *
     * @param type
     */
    public SandBag() {
        super(CardType.SANDBAG_CARD);
    }
    
    
    /**
     * @see model.card.Card#getTilesDest(model.game.Island)
     */
    @Override
    public ArrayList<Tile> getTilesDest(Island island) {
        return island.getTiles(TileState.FLOODED);
    }
    
    
    /**
     * 
     * @param destTile
     * @param applied
     */
    @Override
    public void applyAction(Tile destTile, Object applied) throws TileException {
        if (destTile.getState() == TileState.FLOODED) {
            try {
                destTile.setState(TileState.DRIED);
            } catch (EndGameException ex) {
            }
        } else {
            throw new TileException(destTile, TileState.DRIED);
        }
    }
    
}