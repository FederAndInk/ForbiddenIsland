/**
 * 
 */
package util.exception;

import model.game.Tile;



/**
 * @author nihil
 *
 */
public class MoveException extends Exception {
    /**
     * @author nihil
     * @param tile
     * where the player can't go
     *
     */
    public MoveException(Tile tile) {
        // TODO : complite msg
        super("Move impossible to " + tile);
    }
    
    
    /**
     * @author nihil
     *
     */
    public MoveException() {
        super("Move impossible");
    }
}
