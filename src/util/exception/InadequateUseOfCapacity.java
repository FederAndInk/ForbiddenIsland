/**
 * 
 */
package util.exception;

/**
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
