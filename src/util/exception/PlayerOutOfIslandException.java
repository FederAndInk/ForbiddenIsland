/**
 * 
 */
package util.exception;

import model.game.Tile;



/**
 * exception for :<br>
 * - An adventurer get drown
 * 
 * @author nihil
 *
 */
public class PlayerOutOfIslandException extends ForbiddenIslandException {
    Tile tile;
    
    
    /**
     * @author nihil
     *
     */
    public PlayerOutOfIslandException(Tile tile) {
        super("An Adventurer drowned", ExceptionType.PLAYER_OUT_OF_ISLAND);
        this.tile = tile;
    }
    
    
    /**
     * @return the tile
     */
    public Tile getTile() {
        return tile;
    }
}
