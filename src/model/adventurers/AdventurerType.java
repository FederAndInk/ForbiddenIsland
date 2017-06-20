/**
 * 
 */
package model.adventurers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import model.game.Site;
import model.player.Player;
import util.Parameters;



/**
 * @author nihil
 *
 */
public enum AdventurerType {
    DIVER("Diver", Site.IRON_GATE),
    ENGINEER("Engineer", Site.BRONZE_GATE),
    EXPLORER("Explorer", Site.COPPER_GATE),
    MESSENGER("Messenger", Site.SILVER_GATE),
    NAVIGATOR("Navigator", Site.GOLD_GATE),
    PILOT("Pilot", Site.FOOLS_LANDING),
    RANDOM("Random", null);
    
    public final String className;
    private Site        spawn;
    
    
    /**
     * @author nihil
     *
     */
    private AdventurerType(String className, Site spawn) {
        this.className = className;
        this.spawn = spawn;
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
    public static ArrayList<Site> getSpawns() {
        ArrayList<Site> spawns = new ArrayList<>();
        for (AdventurerType advT : values()) {
            spawns.add(advT.getSpawn());
        } // end for
        return spawns;
    }
    
    
    /**
     * @return the spawn
     */
    public Site getSpawn() {
        return spawn;
    }
    
    
    /**
     * @author nihil
     *
     */
    public String getPath() {
        return Parameters.LOGO + getClassName() + "_Adventurer_Icon@2x.png";
    }
    
    
    /**
     * @author nihil
     *
     */
    public String getIcon() {
        return Parameters.LOGO + "RoleTable_Icon_" + getClassName() + "@2x.png";
    }
    
    
    /**
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
    
    
    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return getClassName();
    }
}
