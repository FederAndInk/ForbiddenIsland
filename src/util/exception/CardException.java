/**
 * 
 */
package util.exception;

import model.adventurers.AdventurerType;
import model.card.Card;


/**
 * exception for :<br>
 * Cards, as Exceeding card limit
 * 
 * @author nihil
 *
 */
public class CardException extends ForbiddenIslandException {
    /**
     * @author nihil
     *
     */
    public CardException(Card noCard, AdventurerType adv) {
        super("You are trying to add the " + noCard + " card to " + adv + " inventory", ExceptionType.TOO_MANY_CARD);
    }
}
