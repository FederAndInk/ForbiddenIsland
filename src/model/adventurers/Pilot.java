package model.adventurers;

import java.util.ArrayList;

import model.game.Island;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;
import util.exception.ActionException;
import util.exception.InadequateUseOfCapacityException;
import util.exception.MoveException;
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
        super(player, AdventurerType.PILOT);
        setHeliUsed(false);
    }
    
    
    @Override
    public void useCapacity(Object o) throws MoveException, ActionException, InadequateUseOfCapacityException {
        if (o instanceof Tile) {
            Tile tile = (Tile) o;
            if (getPotentialUse().contains(tile) && getActionPoints() > 0) {
                setCurrentTile(tile);
                setHeliUsed(true);
                setActionPoints(getActionPoints() - 1);
            } else {
                if (getActionPoints() < 1) {
                    throw new ActionException(getActionPoints());
                } else {
                    throw new MoveException(tile);
                } // end if
            } // end if
        } else {
            throw new IllegalArgumentException("not a Tile !");
        } // end if
    }// end useCapacity
    
    
    /**
     * @author nihil
     *
     * @return the tiles where the pilot can go with their helicopter
     * @throws InadequateUseOfCapacityException
     * @see {@link #getReachableTiles()} and use removeAll to get only the tile where the pilot can go exclusively with their helicopter
     */
    @Override
    public ArrayList<Object> getPotentialUse() throws InadequateUseOfCapacityException {
        
        ArrayList<Object> reachable = new ArrayList<>();
        
        Island island = getPlayer().getCurrentGame().getIsland();
        
        if (!isHeliUsed()) {
            Tile tile;
            for (int x = 0; x <= 5; x++) {
                for (int y = 0; y <= 5; y++) {
                    tile = island.getTile(x, y);
                    if (tile != null && !tile.getState().equals(TileState.SINKED) && !tile.equals(getCurrentTile())) {
                        reachable.add(island.getTile(x, y));
                    }
                }
                
            }
        } else {
            throw new InadequateUseOfCapacityException();
        } // end if
        return reachable;
    }
    
    
    /**
     * @author nihil
     *
     */
    @Override
    public void endTurn() {
        super.endTurn();
        setHeliUsed(false);
    }
    
    
    /**
     * @see model.adventurers.Adventurer#getPossibleActions()
     */
    @Override
    public ArrayList<InGameAction> getPossibleActions() {
        ArrayList<InGameAction> list = super.getPossibleActions();
        if (getActionPoints() > 0 && !isHeliUsed()) {
            list.add(InGameAction.USE_CAPACITY);
        } // end if
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