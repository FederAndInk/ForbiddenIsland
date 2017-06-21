/**
 * 
 */
package model.card;

import util.Parameters;



/**
 * @author nihil
 *
 */
public enum CardType {
    TREASURE_CARD(getSubTreasures(), false),
    FLOOD_CARD(getSubFlood(), false),
    HELICOPTER_CARD(getSubTreasures(), true),
    SANDBAG_CARD(getSubTreasures(), true),
    WATERSRISE_CARD(getSubTreasures(), false);
    /**
     * @category Treasures
     */
    private static final String SUB_TREASURES = "Treasure Deck";
    /**
     * @category Floods
     */
    private static final String SUB_FLOOD     = "Flood Deck";
    private String              subType;
    private boolean             usable;
    
    
    private CardType(String subType, boolean usable) {
        this.subType = subType;
        this.usable = usable;
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
     * @return
     */
    public String getBackHover() {
        return Parameters.CARDS + (subType.equals(SUB_FLOOD) ? "Flood_" : "") + "Card_BackSelectHover@2x.png";
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
