package util.exception;

import model.adventurers.Adventurer;
import model.game.TreasureType;;



public class MissingCard extends ForbiddenIslandException {
    
    public MissingCard(TreasureType card, Adventurer adv) {
        super("Card " + card + " were not found into " + adv + "'s inventory", ExceptionType.MISSING_CARD);
    }
}
