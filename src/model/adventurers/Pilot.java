package model.adventurers;

import java.util.ArrayList;
import model.game.Coords;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;



public class Pilot extends Adventurer {
    
    private Boolean heliUsed;
    
    
    public Pilot(Player player) {
        super(player);
    }
    
    
    public ArrayList<Tile> utilCap() {
        
        ArrayList<Tile> reachable = new ArrayList<>();
        Coords coords = getCurrentTile().getCoords();
        
        Tile[][] grid = getPlayer().getCurrentGame().getIsland().getGrid();
        
        if (getHeliUsed() == false) {
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if ((grid[x][y] != null) && (grid[x][y].getState() != TileState.SINKED)) {
                        reachable.add(grid[x][y]);
                    }
                }
                
            }
        }
        
        setHeliUsed(true);
        return reachable;
        // **penser a changer l'etat de heliused**//
    }
    
    
    /**
     * @return the heliUsed
     */
    public Boolean getHeliUsed() {
        return heliUsed;
    }
    
    
    /**
     * @param heliUsed
     * the heliUsed to set
     */
    public void setHeliUsed(Boolean heliUsed) {
        this.heliUsed = heliUsed;
    }
    
}