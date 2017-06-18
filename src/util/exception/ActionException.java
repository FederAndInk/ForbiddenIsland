/**
 * 
 */
package util.exception;

/**
 * exception for :<br>
 * actions, as an adventurer does not have enough actionPoints
 * 
 * @author nihil
 *
 */
public class ActionException extends ForbiddenIslandException {
    /**
     * 
     * @author nihil
     * actually this happen when there is no remaining actions
     *
     * @param nbAction
     * (0) the remaining action
     */
    public ActionException(int nbAction) {
        super("Action impossible, " + nbAction + " actions remaining", ExceptionType.ACTION);
    }// end name
}
