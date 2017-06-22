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
    public EndGameException(ExceptionType endGameException) {
        super("Something has caused the end of game", endGameException);
    }
    
}
