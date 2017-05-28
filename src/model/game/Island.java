package model.game;

public class Island {
    
    private Tile grid[][];
    
    
    public Island() {
        generateIsland();
    }
    
    
    /**
     * 
     * @param pos
     */
    public Tile getTile(Coords pos) {
        // TODO - implement Island.getTile
        throw new UnsupportedOperationException();
    }
    
    
    public void getTiles() {
        // TODO - implement Island.getTiles
        throw new UnsupportedOperationException();
    }
    
    
    /**
     * @return the grid
     */
    public Tile[][] getGrid() {
        return grid;
    }
    
    
    /**
     * @param grid
     * the grid to set
     */
    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }
    
    
    private void generateIsland() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                
            }
            
        }
    }
    
}