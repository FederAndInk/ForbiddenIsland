package model.adventurers;

import java.util.ArrayList;

import model.game.Tile;
import model.game.TileState;
import model.player.Player;



public class Pilot extends Adventurer {
    
    private Boolean heliUsed;
    
    
    public Pilot(Player player) {
        super(player);
    }
    
    
    public void useCapacity(Tile tile) {
        if (helicopterLocations().contains(tile) && getActionPoints() > 0) {
            setCurrentTile(tile);
        } else {
            // TODO :throw exception
        } // end if
    }// end useCapacity
    
    
    public ArrayList<Tile> helicopterLocations() {
        
        ArrayList<Tile> reachable = new ArrayList<>();
        
        Tile[][] grid = getPlayer().getCurrentGame().getIsland().getGrid();
        
        if (getHeliUsed() == false) {
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    Tile tile = grid[x][y];
                    if (tile != null && !tile.getState().equals(TileState.SINKED) && !tile.equals(getCurrentTile())) {
                        reachable.add(grid[x][y]);
                    }
                }
                
            }
        }
        return reachable;
        // TODO :penser a changer l'etat de heliused
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