/**
 * 
 */
package util;

/**
 * @author nihil
 *
 */
public enum Lang {
    EN("English"),
    FR("Français");
    
    private String name;
    
    
    private Lang(String name) {
        this.name = name;
    }
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
