package model.adventurers;

import java.util.ArrayList;

import model.game.TreasureType;
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
        super(player, AdventurerType.MESSENGER);
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
    
    
    /**
     * @return true
     */
    @Override
    public boolean reachableExchangePlayer(Player player) {
        return true;
    }
    
    
    /**
     * @see model.adventurers.Adventurer#isExchangePossibleHere(model.game.TreasureType)
     */
    @Override
    protected boolean isExchangePossibleHere(TreasureType type) {
        return true;
    }
}