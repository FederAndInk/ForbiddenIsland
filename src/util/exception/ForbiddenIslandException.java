/**
 * 
 */
package util.exception;

/**
 * @author nihil
 *
 */
public abstract class ForbiddenIslandException extends Exception {
    private ExceptionType type;
    
    
    public ForbiddenIslandException(String text, ExceptionType type) {
        super(text);
        this.type = type;
    }
    
    
    public ForbiddenIslandException(ExceptionType type) {
        super();
        this.type = type;
    }
    
    
    /**
     * @return the type
     */
    public ExceptionType getType() {
        return type;
    }
    
}
