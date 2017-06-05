package model.game;

import java.util.ArrayList;

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
    
    
    public ArrayList<Site> getSites() {
        ArrayList<Site> board = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Tile tile = grid[i][j];
                board.add(tile == null ? null : tile.getSite());
            } // end for
        } // end for
        return board;
    }// end getSites
    
    
    /**
     * @param grid
     * the grid to set
     */
    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }
    
}