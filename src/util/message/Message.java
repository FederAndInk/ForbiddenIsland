package util.message;

public abstract class Message {
    private Object content;
    
    
    protected Message(Object content) {
        this.content = content;
    }
    
    
    public Object getContent() {
        return content;
    }
    
    
    /**
     * @author nihil
     *
     * @return
     */
    public abstract Object getType();
    
}
