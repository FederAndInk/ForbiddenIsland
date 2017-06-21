/**
 * 
 */
package model.card;

/**
 * @author nihil
 *
 */
public enum CardType {
    TREASURE_CARD(getSubTreasures(), false, true),
    FLOOD_CARD(getSubFlood(), true, false),
    HELICOPTER_CARD(getSubTreasures(), true, true),
    SANDBAG_CARD(getSubTreasures(), true, true),
    WATERSRISE_CARD(getSubFlood(), false, false);
    /**
     * @category Treasures
     */
    private static final String SUB_TREASURES = "Treasure_Deck";
    /**
     * @category Floods
     */
    private static final String SUB_FLOOD     = "Flood_Deck";
    private String              subType;
    private boolean             activable;
    private boolean             canAddToInventory;
    
    
    private CardType(String subType, boolean usable, boolean canAdd) {
        this.subType = subType;
        this.activable = usable;
        this.canAddToInventory = canAdd;
    }
    
    
    /**
     * @return the activable
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
