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
        return (grid[pos.getX()][pos.getY()]);
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
    
}