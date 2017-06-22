package util.exception;

import model.adventurers.Adventurer;
import model.game.TreasureType;;



public class MissingCardException extends ForbiddenIslandException {
    
    public MissingCardException(TreasureType card, Adventurer adv) {
        super("Card " + card + " were not found into " + adv + "'s inventory", ExceptionType.MISSING_CARD);
    }
}
