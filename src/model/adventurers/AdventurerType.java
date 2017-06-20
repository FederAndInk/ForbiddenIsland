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
    DIVER("Diver", Site.IRON_GATE, "Peut se deplacer à travers une case coulée ou plus pour une action"),
    ENGINEER("Engineer", Site.BRONZE_GATE, "Peut assecher deux cases pour une action"),
    EXPLORER("Explorer", Site.COPPER_GATE, "Peut assecher et se deplacer en diagonal pour une action"),
    MESSENGER("Messenger", Site.SILVER_GATE, "Peut donner une carte à n'importe qui sur la carte pour une action"),
    NAVIGATOR(
            "Navigator",
            Site.GOLD_GATE,
            "Peut deplacer un autre joueur d'une ou deux case adjacente pour une action"),
    PILOT("Pilot", Site.FOOLS_LANDING, "Peut voler vers n'importe où une fois par tour pour une action"),
    RANDOM("Random", null, "Random");
    
    private String className;
    private Site   spawn;
    private String description;
    
    
    /**
     * @author nihil
     *
     */
    private AdventurerType(String className, Site spawn, String description) {
        this.className = className;
        this.spawn = spawn;
        this.description = description;
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
    
    
    // réccupérer le logo du joueur en train de jouer
    public String getPathSelect() {
        return Parameters.LOGO + getClassName() + "_Adventurer_IconSelect@2x.png";
    }
    
    
    /**
     * @author nihil
     *
     */
    public String getIcon() {
        return Parameters.LOGO + "RoleTable_Icon_" + getClassName() + "@2x.png";
    }
    
    
    public String getIconSelect() {
        return Parameters.ADVENTURER + "Player_Card_" + getClassName() + "_IconSelect@2x.png";
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
