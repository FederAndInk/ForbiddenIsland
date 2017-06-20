/**
 * 
 */
package util.exception;

/**
 * exception for :<br>
 * - An adventurer get drown
 * 
 * @author nihil
 *
 */
public class PlayerOutOfIslandException extends ForbiddenIslandException {
    /**
     * @author nihil
     *
     */
    public PlayerOutOfIslandException() {
        super("An Adventurer drowned", ExceptionType.PLAYER_OUT_OF_ISLAND);
    }
}
