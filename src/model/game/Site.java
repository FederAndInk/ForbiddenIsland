package model.game;

/**
 *
 * @author lordofkawaiii
 */
public enum Site {
    CAVE_OF_EMBERS("Cave of Embers"),
    OBSERVATORY("Observatory"),
    SILVER_GATE("Silver Gate"),
    IRON_GATE("Iron Gate"),
    PHANTOM_ROCK("Phantom Rock"),
    TEMPLE_OF_THE_SUN("Temple of the" + Site.getHtmlNewLine() + "Sun"),
    WATCHTOWER("Watchtower"),
    CORAL_PALACE("Coral Palace"),
    WHISPERING_GARDEN("Whispering" + Site.getHtmlNewLine() + "Garden"),
    CRIMSON_FOREST("Crimson Forest"),
    FOOLS_LANDING("Fools' Landing"),
    DUNES_OF_DECEPTION("Dunes of" + Site.getHtmlNewLine() + "Deception"),
    GOLD_GATE("Gold Gate"),
    COPPER_GATE("Copper Gate"),
    LOST_LAGOON("Lost Lagoon"),
    CAVE_OF_SHADOWS("Cave of Shadows"),
    TIDAL_PALACE("Tidal Palace"),
    MISTY_MARSH("Misty Marsh"),
    TWILIGHT_HOLLOW("Twilight Hollow"),
    TEMPLE_OF_THE_MOON("Temple of the" + Site.getHtmlNewLine() + "Moon"),
    BRONZE_GATE("Bronze Gate"),
    BREAKERS_BRIDGE("Breakers Bridge"),
    CLIFFS_OF_ABANDON("Cliffs of" + Site.getHtmlNewLine() + "Abandon"),
    HOWLING_GARDEN("Howling Garden");
    private String              name;
    private static final String HTML_NEW_LINE = "</p><p class=\"second\">";
    private static final String HTML_STYLE    = "<style>body{margin: auto;text-align: center;} p.second{margin-top:-6;margin-bottom: -2}</style>";
    private static final String HTML_BEG      = "<html><head>" + HTML_STYLE + "</head><body><p>";
    private static final String HTML_END      = "</p></body></html>";
    
    
    Site(String name) {
        this.name = name;
    }
    
    
    public String getName() {
        return name;
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
            return ("./resources/tiles/" + name.replace(HTML_NEW_LINE, " ") + "@2x.png");
        
        case FLOODED:
            return ("./resources/tiles/" + name.replace(HTML_NEW_LINE, " ") + "_flood@2x.png");
        
        case SINKED:
            return ("./resources/tiles/extra/Tile_Flood_Water@2x.png");
        
        default:
            return ("./resources/tiles/extra/Tile_Flood_Water@2x.png");
        
        }// end switch
    }
    
    
    /**
     * @return the htmlNewLine
     */
    public static String getHtmlNewLine() {
        return HTML_NEW_LINE;
    }
}