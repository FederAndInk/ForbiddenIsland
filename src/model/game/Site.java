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
    private static final String htmlNewLine = "</p><p class=\"second\">";
    
    
    Site(String name) {
        this.name = name;
    }
    
    
    public String getName() {
        return name;
    }// end getName
    
    
    public String getFile() {
        return ("./resources/tiles/" + name.replace(htmlNewLine, " ") + "@2x.png");
    }
    
    
    /**
     * @return the htmlNewLine
     */
    public static String getHtmlNewLine() {
        return htmlNewLine;
    }
}