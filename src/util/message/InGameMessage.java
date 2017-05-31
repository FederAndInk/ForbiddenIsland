/**
 * 
 */
package util.message;

/**
 * @author nihil
 *
 */
public class InGameMessage extends Message {
    private InGameAction type;
    
    
    /**
     * @author nihil
     *
     * @param content
     */
    public InGameMessage(InGameAction type, Object content) {
        super(content);
        this.type = type;
    }
    
    
    /**
     * @see util.message.Message#getType()
     */
    @Override
    public Object getType() {
        return type;
    }
    
}
