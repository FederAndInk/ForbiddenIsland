package model.game;

/**
 *
 * @author lordofkawaiii
 */
public enum Site {
    CAVE_OF_EMBERS,
    OBSERVATORY,
    SILVER_GATE,
    IRON_GATE,
    PHANTOM_ROCK,
    TEMPLE_OF_THE_SUN,
    WATCHTOWER,
    CORAL_PALACE,
    WHISPERING_GARDEN,
    CRIMSON_FOREST,
    FOOLS_LANDING,
    DUNES_OF_DECEPTION,
    GOLD_GATE,
    COPPER_GATE,
    LOST_LAGOON,
    CAVE_OF_SHADOWS,
    TIDAL_PALACE,
    MISTY_MARSH,
    TWILIGHT_HOLLOW,
    TEMPLE_OF_THE_MOON,
    BRONZE_GATE,
    BREAKERS_BRIDGE,
    CLIFES_OF_ABANDON,
    HOWLING_GARDEN;
    
    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
    
}