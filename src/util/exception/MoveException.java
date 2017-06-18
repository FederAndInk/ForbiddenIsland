/**
 * 
 */
package util.exception;

import model.game.Tile;
import model.game.TileState;



/**
 * exception for :<br>
 * Move action
 * 
 * @author nihil
 *
 */
public class MoveException extends ForbiddenIslandException {
    
    /**
     * @author nihil
     * @param tile
     * where the player can't go
     *
     */
    public MoveException(Tile tile) {
        // TODO : complite msg
        super("Move impossible to " + tile,
                tile.getState().equals(TileState.SINKED) ? ExceptionType.TILE_STATE : ExceptionType.NOT_REACHABLE);
    }
    
    
    /**
     * @author nihil
     *
     */
    public MoveException() {
        super("Move impossible", ExceptionType.NOT_REACHABLE);
    }
}
