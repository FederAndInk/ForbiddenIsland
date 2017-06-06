/**
 * 
 */
package model.adventurers;

import java.lang.reflect.InvocationTargetException;

import model.player.Player;
import util.Parameters;




/**
 * @author nihil
 *
 */
public enum AdventurerType {
    DIVER("Diver"),
    ENGINEER("Engineer"),
    EXPLORER("Explorer"),
    MESSAGER("Messenger"),
    NAVIGATOR("Navigator"),
    PILOT("Pilot"),
    RANDOM(null);
    
    private String className;
    
    
    /**
     * @author nihil
     *
     */
    private AdventurerType(String className) {
        this.className = className;
    }
    
    
    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }
    
    
    /**
     * @author nihil
     *
     */
    public String getPath() {
        return Parameters.LOGO + getClassName() + "_Adventurer_Icon@2x.png";
    }
    
    
     *
     * @param p
     * @return an instance of a type adventurer class
     */
    public Adventurer getClassFor(Player p) {
        try {
            return (Adventurer) Class.forName("model.adventurers." + className).getConstructor(Player.class)
                    .newInstance(p);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }// end getClassFor
}
