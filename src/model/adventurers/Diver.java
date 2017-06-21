package model.adventurers;

import java.util.ArrayList;

import model.game.Coords;
import model.game.Island;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;



/**
 * the Diver can move through the deep water when they went to move or when they have to swim
 * 
 * @author nihil
 */
public class Diver extends Adventurer {
    
    public Diver(Player player) {
        super(player, AdventurerType.DIVER);
    }
    
    
    private ArrayList<Tile> getReachableTiles(Tile tile, ArrayList<Tile> tilesAlreadyRead) {
        
        Island island = getPlayer().getCurrentGame().getIsland();
        Coords coords = tile.getCoords();
        
        int j = 2;
        int effI;
        int effJ;
        Tile tileTmp;
        for (int i = -1; i <= 2; i += 1) {
            effI = i % 2;
            effJ = j % 2;
            tileTmp = island.getTile(coords.getCol() + effI, coords.getRow() + effJ);
            if (!tilesAlreadyRead.contains(tileTmp)) { // if the tile is not already treated
                if (tileTmp != null && !tileTmp.getState().equals(TileState.SINKED)) {
                    tilesAlreadyRead.add(tileTmp);
                } else if (tileTmp != null && tileTmp.getState().equals(TileState.SINKED)) {
                    tilesAlreadyRead.add(tileTmp);
                    getReachableTiles(tileTmp, tilesAlreadyRead);
                }
            }
            j--;
        }
        return tilesAlreadyRead;
    }
    
    
    @Override
    public ArrayList<Tile> getReachableTiles() {
        ArrayList<Tile> reachableTiles = new ArrayList<>();
        Tile current = getCurrentTile();
        
        reachableTiles = getReachableTiles(current, reachableTiles);
        
        Tile tile;
        for (int i = 0; i < reachableTiles.size(); i++) {
            tile = reachableTiles.get(i);
            if (tile.getState().equals(TileState.SINKED) || getCurrentTile().equals(tile)) {
                reachableTiles.remove(i);
                i--; // when a tile have been removed i must be decrement to treat all the tiles because of the list offset
            }
        }
        
        return reachableTiles;
    }
    
    
    @Override
    public ArrayList<Tile> getReachableTiles(Tile tile) {
        ArrayList<Tile> reachable = new ArrayList<>();
        Coords coords = tile.getCoords();
        
        Island island = getPlayer().getCurrentGame().getIsland();
        Tile tileTmp;
        // we will apply a sweet function to get through -1,0,1,0 and meanwhile 0,1,0,-1 (uses of modulo is awesome)
        int j = 2;
        int effI;
        int effJ;
        for (int i = -1; i <= 2; i += 1) {
            effI = i % 2;
            effJ = j % 2;
            tileTmp = island.getTile(coords.getCol() + effI, coords.getRow() + effJ);
            if ((tileTmp != null)) {
                reachable.add(tileTmp);
            }
            j--;
        } // end for
        return reachable;
    }
    
    
    @Override
    public ArrayList<Tile> getReachableTiles(int nbHit) {
        int i = 1;
        ArrayList<Tile> reachable = new ArrayList<>();
        ArrayList<Tile> reachableTmp = new ArrayList<>();
        reachable = getReachableTiles(getCurrentTile());
        while (i <= nbHit) {
            for (int j = 0; j < reachable.size(); j++) {
                reachableTmp.addAll(getReachableTiles(reachable.get(j)));
                reachable.remove(j);
                j--; // when a tile have been removed i must be decrement to treat all the tiles because of the list offset
            }
        }
        i++;
        reachable.addAll(reachableTmp);
        Tile tile;
        for (int j = 0; j < reachable.size(); j++) {
            tile = reachable.get(j);
            if (tile.getState().equals(TileState.SINKED) || getCurrentTile().equals(tile)) {
                reachable.remove(j);
                j--; // when a tile have been removed i must be decrement to treat all the tiles because of the list offset
            }
        }
        return reachable;
    }
    
    
    @Override
    public ArrayList<Tile> getShoreUpTiles() {
        return getShoreUpTiles(super.getReachableTiles());
    }
}