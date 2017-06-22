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
    TREASURE_CARD("treasure", getSubTreasures(), false, null),
    EARTH_STONE_CARD("Earth_Stone", getSubTreasures(), false, TreasureType.EARTH_STONE),
    STATUE_OF_THE_WIND_CARD("Statue_of_the_Wind", getSubTreasures(), false, TreasureType.STATUE_OF_THE_WIND),
    OCEANS_CHALICE_CARD("Ocean's_Chalice", getSubTreasures(), false, TreasureType.OCEANS_CHALICE),
    CRYSTAL_OF_FIRE_CARD("Crystal_of_Fire", getSubTreasures(), false, TreasureType.CRYSTAL_OF_FIRE),
    FLOOD_CARD("flood", getSubFlood(), false, null),
    HELICOPTER_CARD("Helicopter", getSubTreasures(), true, null),
    SANDBAG_CARD("Sand_Bag", getSubTreasures(), true, null),
    WATERSRISE_CARD("Waters_Rise", getSubTreasures(), false, null);
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
    private boolean             usable;
    private TreasureType        treasureType;
    
    
    private CardType(String name, String subType, boolean usable, TreasureType treasureType) {
        this.name = name;
        this.subType = subType;
        this.usable = usable;
        this.treasureType = treasureType;
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
    
    
    /**
     * @return the usable
     */
    public boolean isUsable() {
        return usable;
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
}
