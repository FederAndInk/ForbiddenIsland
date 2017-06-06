package model.adventurers;

import model.player.Player;



/**
 * the engineer can shore 2 tiles up for 1 action
 * 
 * @author nihil
 *
 */
public class Engineer extends Adventurer {
    
    public Engineer(Player player) {
        super(player, AdventurerType.ENGINEER);
    }
}