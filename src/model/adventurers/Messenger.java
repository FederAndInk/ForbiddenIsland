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
        super(player, AdventurerType.MESSENGER);
    }
    
    
    /**
     * @see model.adventurers.Adventurer#getPossibleActions()
     */
    @Override
    public ArrayList<InGameAction> getPossibleActions() {
        ArrayList<InGameAction> actions = super.getPossibleActions();
        actions.add(InGameAction.GIVE_CARD);
        return actions;
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
    public boolean isExchangePossibleHere() {
        return true;
    }
}