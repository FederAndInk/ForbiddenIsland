package model.adventurers;

import java.util.ArrayList;

import model.player.Player;
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
    
    
    public void movePlayer() {
        // TODO - implement Navigater.movePlayer
        throw new UnsupportedOperationException();
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
}