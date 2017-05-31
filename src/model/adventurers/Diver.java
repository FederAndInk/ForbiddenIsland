package model.adventurers;

import java.util.ArrayList;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;



public class Diver extends Adventurer {
    
    private ArrayList<Tile> getReachableTiles(Tile tile, ArrayList<Tile> tilesAlreadyRead) {
        ArrayList<Tile> tilesAlreadyDone = new ArrayList<>();
        
        Tile grid[][] = getPlayer().getCurrentGame().getIsland().getGrid();
        
        for (int i = -1; i <= 1; i += 2) {
            Tile tileTmp = (grid[tile.getCoords().getX()][tile.getCoords().getY() + i]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                tilesAlreadyRead.add(tileTmp);
            } else if ((tileTmp != null) && (tileTmp.getState() == TileState.SINKED)) {
                if (!tilesAlreadyRead.contains(tileTmp)) {
                    tilesAlreadyRead.add(tileTmp);
                    getReachableTiles(tileTmp, tilesAlreadyRead);
                }
            }
            tileTmp = (grid[tile.getCoords().getX() + i][tile.getCoords().getY()]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                tilesAlreadyRead.add(tileTmp);
            } else if ((tileTmp != null) && (tileTmp.getState() == TileState.SINKED)) {
                if (!tilesAlreadyRead.contains(tileTmp)) {
                    tilesAlreadyRead.add(tileTmp);
                    getReachableTiles(tileTmp, tilesAlreadyRead);
                }
            }
        }
        tilesAlreadyDone.addAll(tilesAlreadyRead);
        return tilesAlreadyDone;
    }
    
    
    @Override
    public ArrayList<Tile> getReachableTiles() {
        ArrayList<Tile> reachableTiles = new ArrayList<>();
        ArrayList<Tile> tilesAlreadyDone = new ArrayList<>();
        Tile current = getCurrentTile();
        
        tilesAlreadyDone = getReachableTiles(current, tilesAlreadyDone);
        
        for (Tile tile : tilesAlreadyDone) {
            if ((tile.getState() != TileState.SINKED) && (getCurrentTile() != tile)) {
                reachableTiles.add(tile);
            }
        }
        
        return reachableTiles;
    }
    
    
    public Diver(Player player) {
        super(player);
    }
    
}