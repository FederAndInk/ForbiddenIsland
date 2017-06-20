package util.exception;

import model.adventurers.Adventurer;
import model.game.TreasureType;



public class CantGiveCard extends ForbiddenIslandException {
    
    public CantGiveCard(Adventurer adv1, Adventurer adv2) {
        super(adv1 + " can't give card to " + adv2 + " : not on the same Tile", ExceptionType.DIFFERENT_TILE);
    }
    
    
    public CantGiveCard(TreasureType site, Adventurer adv1) {
        super(adv1 + " isn't on a " + site + " Tile", ExceptionType.INCORRECT_TILE);
    }
}
