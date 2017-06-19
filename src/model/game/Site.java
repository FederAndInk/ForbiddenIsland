package model.game;

import util.LogType;
import util.Parameters;



/**
 *
 * @author lordofkawaiii
 */
public enum Site {
    CAVE_OF_EMBERS("Cave of Embers", TreasureType.CRYSTAL_OF_FIRE),
    OBSERVATORY("Observatory", null),
    SILVER_GATE("Silver Gate", null),
    IRON_GATE("Iron Gate", null),
    PHANTOM_ROCK("Phantom Rock", null),
    TEMPLE_OF_THE_SUN("Temple of the" + Site.getHtmlNewLine() + "Sun", TreasureType.EARTH_STONE),
    WATCHTOWER("Watchtower", null),
    CORAL_PALACE("Coral Palace", TreasureType.OCEANS_CHALICE),
    WHISPERING_GARDEN("Whispering" + Site.getHtmlNewLine() + "Garden", TreasureType.STATUE_OF_THE_WIND),
    CRIMSON_FOREST("Crimson Forest", null),
    FOOLS_LANDING("Fools' Landing", null),
    DUNES_OF_DECEPTION("Dunes of" + Site.getHtmlNewLine() + "Deception", null),
    GOLD_GATE("Gold Gate", null),
    COPPER_GATE("Copper Gate", null),
    LOST_LAGOON("Lost Lagoon", null),
    CAVE_OF_SHADOWS("Cave of Shadows", TreasureType.CRYSTAL_OF_FIRE),
    TIDAL_PALACE("Tidal Palace", TreasureType.OCEANS_CHALICE),
    MISTY_MARSH("Misty Marsh", null),
    TWILIGHT_HOLLOW("Twilight Hollow", null),
    TEMPLE_OF_THE_MOON("Temple of the" + Site.getHtmlNewLine() + "Moon", TreasureType.EARTH_STONE),
    BRONZE_GATE("Bronze Gate", null),
    BREAKERS_BRIDGE("Breakers Bridge", null),
    CLIFFS_OF_ABANDON("Cliffs of" + Site.getHtmlNewLine() + "Abandon", null),
    HOWLING_GARDEN("Howling Garden", TreasureType.STATUE_OF_THE_WIND);
    
    private String       name;
    private TreasureType treasureType;
    
    private static final String HTML_NEW_LINE = "</p><p class=\"second\">";
    private static final String HTML_STYLE    = "<style>body{margin: auto;text-align: center;} p.second{margin-top:-5,margin-bottom:-2}</style>";
    private static final String HTML_BEG      = "<html><head>" + HTML_STYLE + "</head><body><p>";
    private static final String HTML_END      = "</p></body></html>";
    
    
    Site(String name, TreasureType treasureType) {
        this.name = name;
        Parameters.printLog("instancate Site type", LogType.INFO);
        this.treasureType = treasureType;
    }
    
    
    public String getName() {
        return name.replace(HTML_NEW_LINE, " ");
    }// end getName
    
    
    /**
     * @author nihil
     *
     */
    public String getNameStyle() {
        return HTML_BEG + name + HTML_END;
    }
    
    
    public TreasureType geTreasureType() {
        return treasureType;
    }
    
    
    public String getFile(TileState state) {
        switch (state) {
        case DRIED:
            return ("./resources/tiles/" + getName() + "@2x.png");
        
        case FLOODED:
            return ("./resources/tiles/" + getName() + "_flood@2x.png");
        
        case SINKED:
            return ("./resources/tiles/extra/Tile_Flood_Water@2x.png");
        
        default:
            return ("./resources/tiles/extra/Tile_Flood_Water@2x.png");
        
        }// end switch
    }
    
    
    /**
     * @author nihil
     *
     */
    public boolean isDoubleLigned() {
        return name.contains(getHtmlNewLine());
    }
    
    
    /**
     * @return the htmlNewLine
     */
    public static String getHtmlNewLine() {
        return HTML_NEW_LINE;
    }
    
    
    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}