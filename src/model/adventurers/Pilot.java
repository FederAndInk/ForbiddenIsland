package model.adventurers;

import java.util.ArrayList;

import model.game.Island;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;
import util.message.InGameAction;



/**
 * the pilot can use their helicopter one time per turn for one action
 * 
 * @author nihil
 *
 */
public class Pilot extends Adventurer {
    
    private Boolean heliUsed;
    
    
    public Pilot(Player player) {
        super(player);
        setHeliUsed(false);
    }
    
    
    @Override
    public void useCapacity(Object o) {
        if (o instanceof Tile) {
            Tile tile = (Tile) o;
            if (getPotentialUse().contains(tile) && getActionPoints() > 0) {
                setCurrentTile(tile);
                setActionPoints(getActionPoints() - 1);
            } else {
                // TODO :throw exception
            } // end if
        } else {
            throw new IllegalArgumentException("not a Tile !");
        } // end if
    }// end useCapacity
    
    
    /**
     * @author nihil
     *
     * @return the tiles where the pilot can go with their helicopter
     * @see {@link #getReachableTiles()} and use removeAll to get only the tile where the pilot can go exclusively with their helicopter
     */
    @Override
    public ArrayList<Object> getPotentialUse() {
        
        ArrayList<Object> reachable = new ArrayList<>();
        
        Island island = getPlayer().getCurrentGame().getIsland();
        
        if (isHeliUsed()) {
            Tile tile;
            for (int x = 0; x <= 5; x++) {
                for (int y = 0; y <= 5; y++) {
                    tile = island.getTile(x, y);
                    if (tile != null && !tile.getState().equals(TileState.SINKED) && !tile.equals(getCurrentTile())) {
                        reachable.add(island.getTile(x, y));
                    }
                }
                
            }
        }
        return reachable;
        // TODO :penser a changer l'etat de heliused when the turn end
    }
    
    
    /**
     * @see model.adventurers.Adventurer#getPossibleActions()
     */
    @Override
    public ArrayList<InGameAction> getPossibleActions() {
        ArrayList<InGameAction> list = super.getPossibleActions();
        list.add(InGameAction.USE_CAPACITY);
        return list;
    }
    
    
    /**
     * @return the heliUsed
     */
    public Boolean isHeliUsed() {
        return heliUsed;
    }
    
    
    /**
     * @param heliUsed
     * the heliUsed to set
     */
    private void setHeliUsed(Boolean heliUsed) {
        this.heliUsed = heliUsed;
    }
    
}