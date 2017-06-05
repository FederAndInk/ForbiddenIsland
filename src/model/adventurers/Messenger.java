package model.adventurers;

import java.util.ArrayList;

import model.player.Player;
import util.message.InGameAction;



/**
 * the Messenger can give a card to anyone on the board (wherever they are)
 * 
 * @author nihil
 *
 */
public class Messenger extends Adventurer {
    
    public Messenger(Player player) {
        super(player);
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