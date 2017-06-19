/**
 * 
 */
package util.exception;

/**
 * @author nihil
 *
 */
public class EndGameException extends ForbiddenIslandException {
    
    /**
     * @author nihil
     *
     */
    public EndGameException() {
        super("Something has caused the end of game", ExceptionType.END_GAME);
    }
    
}
