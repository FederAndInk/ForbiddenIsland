package model.game;

public enum TileState {
    DRIED,
    FLOODED,
    SINKED;
    
    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
    
    
    /**
     * @author nihil
     *
     */
    public TileState next() {
        return values()[(ordinal() + 1) % values().length];
    }
}