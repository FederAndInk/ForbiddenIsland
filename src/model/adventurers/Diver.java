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
            tileTmp = island.getTile(coords.getX() + effI, coords.getY() + effJ);
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
    
    
    public Diver(Player player) {
        super(player);
    }
    
}