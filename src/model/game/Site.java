package model.game;

import model.adventurers.AdventurerType;



/**
 *
 * @author lordofkawaiii
 */
public enum Site {
    CAVE_OF_EMBERS("Cave of Embers", null),
    OBSERVATORY("Observatory", null),
    SILVER_GATE("Silver Gate", AdventurerType.MESSAGER),
    IRON_GATE("Iron Gate", AdventurerType.DIVER),
    PHANTOM_ROCK("Phantom Rock", null),
    TEMPLE_OF_THE_SUN("Temple of the" + Site.getHtmlNewLine() + "Sun", null),
    WATCHTOWER("Watchtower", null),
    CORAL_PALACE("Coral Palace", null),
    WHISPERING_GARDEN("Whispering" + Site.getHtmlNewLine() + "Garden", null),
    CRIMSON_FOREST("Crimson Forest", null),
    FOOLS_LANDING("Fools' Landing", AdventurerType.PILOT),
    DUNES_OF_DECEPTION("Dunes of" + Site.getHtmlNewLine() + "Deception", null),
    GOLD_GATE("Gold Gate", AdventurerType.NAVIGATOR),
    COPPER_GATE("Copper Gate", AdventurerType.EXPLORER),
    LOST_LAGOON("Lost Lagoon", null),
    CAVE_OF_SHADOWS("Cave of Shadows", null),
    TIDAL_PALACE("Tidal Palace", null),
    MISTY_MARSH("Misty Marsh", null),
    TWILIGHT_HOLLOW("Twilight Hollow", null),
    TEMPLE_OF_THE_MOON("Temple of the" + Site.getHtmlNewLine() + "Moon", null),
    BRONZE_GATE("Bronze Gate", AdventurerType.ENGINEER),
    BREAKERS_BRIDGE("Breakers Bridge", null),
    CLIFFS_OF_ABANDON("Cliffs of" + Site.getHtmlNewLine() + "Abandon", null),
    HOWLING_GARDEN("Howling Garden", null);
    
    private String         name;
    private AdventurerType pawn;
    
    private static final String HTML_NEW_LINE = "</p><p class=\"second\">";
    private static final String HTML_STYLE    = "<style>body{margin: auto;text-align: center;} p.second{margin-top:-5,margin-bottom:-2}</style>";
    private static final String HTML_BEG      = "<html><head>" + HTML_STYLE + "</head><body><p>";
    private static final String HTML_END      = "</p></body></html>";
    
    
    Site(String name, AdventurerType pawn) {
        this.name = name;
        this.pawn = pawn;
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
     * @return the pawn
     */
    public AdventurerType getPawn() {
        return pawn;
    }
    
    
    /**
     * @return the htmlNewLine
     */
    public static String getHtmlNewLine() {
        return HTML_NEW_LINE;
    }
}