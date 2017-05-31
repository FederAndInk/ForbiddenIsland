package model.game;

import util.BoardGeneration;
import util.BoardType;



public class Island {
    
    private Tile grid[][];
    
    
    public Island() {
        setGrid(BoardGeneration.generateBoard(BoardType.DEFAULT));
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