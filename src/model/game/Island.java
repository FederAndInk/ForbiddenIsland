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
        this(BoardType.DEFAULT);
    }
    
    
    public Island(BoardType bType) {
        setGrid(BoardGeneration.generateBoard(bType));
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
    
    
    public Tile[] tileTreasure(TreasureType treasure) {
        Tile[] tiles = new Tile[2];
        int i = 0;
        for (Tile[] tiles1 : getGrid()) {
            for (Tile tile : tiles1) {
                if (tile.getSite().getTreasureType() == treasure) {
                    tiles[i] = tile;
                    i++;
                }
            }
        }
        return tiles;
    }
    
    
    public boolean isTreasureAllSinked(TreasureType treasure) {
        Tile[] tiles = tileTreasure(treasure);
        int nbSinked = 0;
        for (Tile tile : tiles) {
            if (!tile.notSinked()) {
                nbSinked = nbSinked + 1;
            }
        }
        if (nbSinked == 2) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean isHeliportSinked() {
        Tile tile = getTile(Site.FOOLS_LANDING);
        return tile.getState() == TileState.SINKED;
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
    
    
    /**
     * @author nihil
     *
     * @return
     */
    public ArrayList<Tile> getTileNot(TileState state) {
        ArrayList<Tile> tiles = new ArrayList<>();
        
        for (Tile[] ts : grid) {
            for (Tile tile : ts) {
                if (tile != null && !tile.getState().equals(state)) {
                    tiles.add(tile);
                } // end if
            } // end for
        } // end for
        return tiles;
    }
    
    
    public ArrayList<Tile> getTiles(TileState state) {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Tile[] ts : grid) {
            for (Tile tile : ts) {
                if (tile != null && tile.getState().equals(state)) {
                    tiles.add(tile);
                }
            }
        }
        return tiles;
    }
    
}