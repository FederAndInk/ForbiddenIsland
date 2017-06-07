/**
 * 
 */
package util.exception;

import model.adventurers.Adventurer;



/**
 * exception for :<br>
 * - An adventurer get drown
 * 
 * @author nihil
 *
 */
public class AdventurerException extends Exception {
    ExceptionType type;
    
    
    /**
     * @author nihil
     *
     */
    public AdventurerException(Adventurer adv) {
        super("The Adventurer " + adv.getADVENTURER_TYPE() + " is on " + adv.getCurrentTile());
        type = ExceptionType.PLAYER_OUT_OF_ISLAND;
    }
    
    
    /**
     * @return the type
     */
    public ExceptionType getType() {
        return type;
    }
}
