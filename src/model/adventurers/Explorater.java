package model.adventurers;

import java.util.ArrayList;
import model.game.Coords;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;



public class Explorater extends Adventurer {
    
    public Explorater(Player player) {
        super(player);
    }
    
    
    @Override
    public ArrayList<Tile> getReachableTiles() {
        ArrayList<Tile> reachable = new ArrayList<>();
        Coords coords = getCurrentTile().getCoords();
        
        Tile[][] grid = getPlayer().getCurrentGame().getIsland().getGrid();
        
        for (int i = -1; i <= 1; i += 2) {
            Tile tileTmp = (grid[super.getCurrentTile().getCoords().getX()][super.getCurrentTile().getCoords().getY()
                    + i]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                reachable.add(tileTmp);
            }
            tileTmp = (grid[super.getCurrentTile().getCoords().getX() + i][super.getCurrentTile().getCoords().getY()]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                reachable.add(tileTmp);
            }
            tileTmp = (grid[super.getCurrentTile().getCoords().getX() + i][super.getCurrentTile().getCoords().getY()
                    + i]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                reachable.add(tileTmp);
            }
        }
        
        return reachable;
    }
    
}