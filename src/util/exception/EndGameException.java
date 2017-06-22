/**
 * 
 */
package util.exception;

/**
 * @author nihil
 *
 */
public class EndGameException extends ForbiddenIslandException {
    ExceptionType endType;
    
    
    /**
     * @author nihil
     *
     */
    public EndGameException(ExceptionType endGameException) {
        super("Something has caused the end of game", endGameException);
        endType = endGameException;
    }
    
    
    /**
     * @return the endType
     */
    public ExceptionType getEndType() {
        return endType;
    }
}
