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
}