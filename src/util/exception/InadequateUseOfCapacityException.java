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
public class InadequateUseOfCapacityException extends ForbiddenIslandException {
    /**
     * @author nihil
     *
     */
    public InadequateUseOfCapacityException() {
        super("You cannot perform a useCapacity, as there is no capacity to use",
                ExceptionType.INADEQUATE_USE_OF_CAPACITY);
    }
    
    
    /**
     * @author nihil
     *
     */
    public InadequateUseOfCapacityException(AdventurerType adv) {
        super("You cannot perform a useCapacity, as there is no capacity remains for " + adv,
                ExceptionType.INADEQUATE_USE_OF_CAPACITY);
    }
}
