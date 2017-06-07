/**
 * 
 */
package util.exception;

import model.adventurers.AdventurerType;



/**
 * exception for :<br>
 * Cards, as Exceeding card limit
 * 
 * @author nihil
 *
 */
public class CardException extends Exception {
    /**
     * @author nihil
     *
     */
    public CardException(int noCard, AdventurerType adv) {
        super("You are trying to add a " + noCard + "th card to " + adv + " inventory");
    }
}
