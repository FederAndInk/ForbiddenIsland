/**
 * 
 */
package util;

/**
 * @author nihil
 *
 */
public enum LogType {
    ACCESS("\u001B[34m"),
    GRAPHICS("\u001B[35m"),
    WARNING("\u001B[33m"),
    ERROR("\u001B[31m"),
    INFO("\u001B[36m");
    
    private String color;
    
    
    private LogType(String color) {
        this.color = color;
    }
    
    
    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }
    
    
    /**
     * @author nihil
     *
     */
    public static String getReset() {
        return "\u001B[0m";
    }
}
