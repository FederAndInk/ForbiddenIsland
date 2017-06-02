package model.adventurers;

import java.util.ArrayList;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;



public class Diver extends Adventurer {
    
    private ArrayList<Tile> getReachableTiles(Tile tile, ArrayList<Tile> tilesAlreadyRead) {
        
        Tile grid[][] = getPlayer().getCurrentGame().getIsland().getGrid();
        
        for (int i = -1; i <= 1; i += 2) {
            Tile tileTmp = (grid[tile.getCoords().getX()][tile.getCoords().getY() + i]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                if (!tilesAlreadyRead.contains(tileTmp)) {
                    tilesAlreadyRead.add(tileTmp);
                }
            } else if ((tileTmp != null) && (tileTmp.getState() == TileState.SINKED)) {
                if (!tilesAlreadyRead.contains(tileTmp)) {
                    tilesAlreadyRead.add(tileTmp);
                    getReachableTiles(tileTmp, tilesAlreadyRead);
                }
            }
            tileTmp = (grid[tile.getCoords().getX() + i][tile.getCoords().getY()]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                if (!tilesAlreadyRead.contains(tileTmp)) {
                    tilesAlreadyRead.add(tileTmp);
                }
            } else if ((tileTmp != null) && (tileTmp.getState() == TileState.SINKED)) {
                if (!tilesAlreadyRead.contains(tileTmp)) {
                    tilesAlreadyRead.add(tileTmp);
                    getReachableTiles(tileTmp, tilesAlreadyRead);
                }
            }
        }
        return tilesAlreadyRead;
    }
    
    
    @Override
    public ArrayList<Tile> getReachableTiles() {
        ArrayList<Tile> reachableTiles = new ArrayList<>();
        Tile current = getCurrentTile();
        
        reachableTiles = getReachableTiles(current, reachableTiles);
        
        for (Tile tile : reachableTiles) {
            if ((tile.getState() == TileState.SINKED) && (getCurrentTile() == tile)) {
                reachableTiles.remove(tile);
            }
        }
        
        return reachableTiles;
    }
    
    
    public Diver(Player player) {
        super(player);
    }
    
}