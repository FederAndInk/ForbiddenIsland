/**
 * 
 */
package model.card;

import model.game.TreasureType;
import util.Parameters;



/**
 * @author nihil
 *
 */
public enum CardType {
    TREASURE_CARD("treasure", getSubTreasures(), false, true, null),
    EARTH_STONE_CARD("Earth_Stone", getSubTreasures(), false, true, TreasureType.EARTH_STONE),
    STATUE_OF_THE_WIND_CARD("Statue_of_the_Wind", getSubTreasures(), false, true, TreasureType.STATUE_OF_THE_WIND),
    OCEANS_CHALICE_CARD("Ocean's_Chalice", getSubTreasures(), false, true, TreasureType.OCEANS_CHALICE),
    CRYSTAL_OF_FIRE_CARD("Crystal_of_Fire", getSubTreasures(), false, true, TreasureType.CRYSTAL_OF_FIRE),
    FLOOD_CARD("flood", getSubFlood(), true, false, null),
    HELICOPTER_CARD("Helicopter", getSubTreasures(), true, true, null),
    SANDBAG_CARD("Sand_Bag", getSubTreasures(), true, true, null),
    WATERSRISE_CARD("Waters_Rise", getSubTreasures(), false, false, null);
    /**
     * @category Treasures
     */
    private static final String SUB_TREASURES = "Treasure Deck";
    /**
     * @category Floods
     */
    private static final String SUB_FLOOD     = "Flood Deck";
    private String              name;
    private String              subType;
    private boolean             activable;
    private boolean             canAddToInventory;
    private TreasureType        treasureType;
    
    
    private CardType(String name, String subType, boolean usable, boolean canAdd, TreasureType treasureType) {
        this.name = name;
        this.subType = subType;
        this.activable = usable;
        this.treasureType = treasureType;
        this.canAddToInventory = canAdd;
    }
    
    
    /**
     * @author nihil
     *
     */
    public String getBack() {
        return Parameters.CARDS + (subType.equals(SUB_FLOOD) ? "Flood_" : "") + "Card_Back@2x.png";
    }
    
    
    /**
     * @author nihil
     *
     * @return
     */
    
    public String getBackSelect() {
        return Parameters.CARDS + (subType.equals(SUB_FLOOD) ? "Flood_" : "") + "Card_BackSelect@2x.png";
    }
    
    
    /**
     * @author nihil
     *
     * @return name of the card's file
     */
    public String getPathCard() {
        return Parameters.CARDS + "Card_" + name + "@2x.png";
    }
    
    
    public String getBackHover() {
        return Parameters.CARDS + (subType.equals(SUB_FLOOD) ? "Flood_" : "") + "Card_BackSelectHover@2x.png";
    }
    
    
    /**
     * @return true if the card is activable
     */
    public boolean isActivable() {
        return activable;
    }
    
    
    /**
     * @return the subType
     */
    public String getSubType() {
        return subType;
    }
    
    
    /**
     * @return the subFlood
     */
    public static String getSubFlood() {
        return SUB_FLOOD;
    }
    
    
    /**
     * @return the subTreasures
     */
    public static String getSubTreasures() {
        return SUB_TREASURES;
    }
    
    
    /**
     * @return the canAddToInventory
     */
    public boolean isCanAddToInventory() {
        return canAddToInventory;
    }
}
