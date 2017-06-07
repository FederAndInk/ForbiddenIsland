/**
 * 
 */
package model.card;

/**
 * @author nihil
 *
 */
public enum CardType {
    TREASURE_CARD(getSubTreasures(), false),
    FLOOD_CARD(getSubFlood(), false),
    HELICOPTER_CARD(getSubTreasures(), true),
    SANDBAG_CARD(getSubTreasures(), true),
    WATERSRISE_CARD(getSubFlood(), false);
    /**
     * @category Treasures
     */
    private static final String SUB_TREASURES = "Treasure_Deck";
    /**
     * @category Floods
     */
    private static final String SUB_FLOOD     = "Flood_Deck";
    private String              subType;
    private boolean             usable;
    
    
    private CardType(String subType, boolean usable) {
        this.subType = subType;
        this.usable = usable;
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
