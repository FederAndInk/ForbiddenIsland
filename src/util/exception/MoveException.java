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
    private ExceptionType type;
    
    
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
     * 
     * @author nihil
     * actually this happen when there is no remaining actions
     *
     * @param nbAction
     * (0) the remaining action
     */
    public MoveException(int nbAction) {
        super("Move impossible, " + nbAction + "remaining");
    }// end name
    
    
    /**
     * @author nihil
     *
     */
    public MoveException() {
        super("Move impossible");
    }
    
    
    /**
     * @return the type
     */
    public ExceptionType getType() {
        return type;
    }
}
