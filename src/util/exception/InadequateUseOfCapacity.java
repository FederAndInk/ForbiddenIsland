/**
 * 
 */
package util.exception;

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
}
