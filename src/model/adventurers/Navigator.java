package model.adventurers;

import java.util.ArrayList;

import model.game.Tile;
import model.player.Player;
import util.exception.InadequateUseOfCapacityException;
import util.exception.NavigatorCannotMoveHimselfException;
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
    public void useCapacity(Tile destTile, Object applied) throws NavigatorCannotMoveHimselfException {
        if (applied instanceof Player) {
            Player player = (Player) applied;
            ArrayList<Tile> reachable = new ArrayList<>();
            switch (player.getCurrentAdventurer().getADVENTURER_TYPE()) {
            case DIVER:
                reachable = getReachableTiles(2);
                break;
            case NAVIGATOR:
                throw new NavigatorCannotMoveHimselfException(player.getCurrentAdventurer().getADVENTURER_TYPE());
            default:
                reachable = getReachableTiles(2);
                break;
            }
        }
        
    }
    
    
    /**
     * 
     * @param applied
     * the player to move
     * @return
     * @throws InadequateUseOfCapacityException
     */
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