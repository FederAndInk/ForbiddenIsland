package model.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import util.BoardGeneration;
import util.BoardType;



public class Island {
    public static final Coords GRID_SIZE = new Coords(6, 6);
    private Tile               grid[][];
    
    
    public Island() {
        setGrid(BoardGeneration.generateBoard(BoardType.DEFAULT));
    }
    
    
    /**
     * 
     * @param pos
     */
    public Tile getTile(Coords pos) {
        return getTile(pos.getCol(), pos.getRow());
    }
    
    
    /**
     * 
     * @param pos
     */
    public Tile getTile(Site site) {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Tile[] tilesG : grid) {
            tiles.addAll(Arrays.asList(tilesG));
        } // end for
        
        tiles.removeAll(Collections.singleton(null));
        
        Iterator iterator = tiles.iterator();
        if (iterator.hasNext()) {
            Tile tile = (Tile) iterator.next();
            while (iterator.hasNext() && (tile == null || !tile.getSite().equals(site))) {
                tile = (Tile) iterator.next();
            } // end while
            return tile;
        } // end if
        return null;
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
                Tile tile = grid[j][i];
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
    
    
    /**
     * @author nihil
     *
     * @param col
     * @param row
     * @return
     */
    public Tile getTile(int col, int row) {
        try {
            return grid[col][row];
        } catch (Exception e) {
            return null;
        } // end try
    }
    
}