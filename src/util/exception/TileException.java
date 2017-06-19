/**
 * 
 */
package util.exception;

import model.game.Tile;
import model.game.TileState;



/**
 * exception for :<br>
 * related to tiles
 * 
 * @author nihil
 *
 */
public class TileException extends ForbiddenIslandException {
    /**
     * @author nihil
     * give the state you want to set
     */
    public TileException(Tile tile, TileState tileState) {
        super("The tile " + tile.getSite() + " cannot become " + tileState + " it is in " + tile.getState() + " State",
                ExceptionType.TILE_STATE);
    }
}
