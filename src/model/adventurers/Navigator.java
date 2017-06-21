package model.adventurers;

import java.util.ArrayList;
import model.game.Tile;

import model.player.Player;
import util.exception.InadequateUseOfCapacityException;
import util.message.InGameAction;



/**
 * the Navigator can move an {@link Adventurer} on the board for one action to one or two tiles
 * 
 * @author nihil
 *
 */
public class Navigator extends Adventurer {
    
    public Navigator(Player player) {
        super(player, AdventurerType.NAVIGATOR);
    }
    
    
    @Override
    public void useCapacity(Tile destTile, Object applied) {
        if (applied instanceof Player) {
            Player player = (Player) applied;
            if (player.getCurrentAdventurer().getCurrentTile() == this.getCurrentTile()) {
                ArrayList<Tile> reachable = new ArrayList<>();
                switch (player.getCurrentAdventurer().getADVENTURER_TYPE()) {
                case DIVER:
                    reachable = getReachableTiles(2);
                    break;
                case NAVIGATOR:
                    // TODO add new exception navigator cannot move himself
                default:
                    reachable = getReachableTiles(2);
                    break;
                }
            } else {
                // TODO add new exception for player not in the same tile
            }
        }
        
    }
    
    
    @Override
    public ArrayList<Object> getPotentialUse(Object applied) throws InadequateUseOfCapacityException {
        if (applied instanceof Player) {
            Player player = (Player) applied;
            return new ArrayList<Object>(player.getCurrentAdventurer().getReachableTiles(2));
        }
        throw new IllegalArgumentException("Not a player");
    }
    
    
    /**
     * @see model.adventurers.Adventurer#getPossibleActions()
     */
    @Override
    public ArrayList<InGameAction> getPossibleActions() {
        ArrayList<InGameAction> list = super.getPossibleActions();
        if (getActionPoints() > 0) {
            list.add(InGameAction.USE_CAPACITY);
        } // end if
        return list;
    }
}