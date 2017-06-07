/**
 * 
 */
package util.exception;

import model.adventurers.AdventurerType;



/**
 * exception for :<br>
 * adventurer who cannot use Capacity
 * 
 * @author nihil
 *
 */
public class InadequateUseOfCapacity extends Exception {
    /**
     * @author nihil
     *
     */
    public InadequateUseOfCapacity() {
        super("You cannot perform a useCapacity, as there is no capacity to use");
    }
    
    
    /**
     * @author nihil
     *
     */
    public InadequateUseOfCapacity(AdventurerType adv) {
        super("You cannot perform a useCapacity, as there is no capacity remains for " + adv);
    }
}
