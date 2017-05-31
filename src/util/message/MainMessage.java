/**
 * 
 */
package util.message;

/**
 * @author nihil
 *
 */
public class MainMessage extends Message {
    private MainAction type;
    
    
    public MainMessage(MainAction type, Object content) {
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
