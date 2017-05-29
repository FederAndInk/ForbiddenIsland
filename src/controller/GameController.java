/**
 * 
 */
package controller;

import java.util.Observable;

import model.game.Coords;
import util.exception.MoveException;
import util.message.InGameAction;
import util.message.InGameMessage;
import util.message.MainMessage;
import util.message.Message;



/**
 * @author nihil
 *
 */
public class GameController {
    MainController mainController;
    
    
    /**
     * @author nihil
     *
     * @param mainController
     */
    public GameController(MainController mainController) {
        this.mainController = mainController;
    }
    
    
    public void movePawn(Coords coords) {
        
        // TODO : do something if the move can't be applied (exception)
        // TODO : get the tile from the UI
        try {
            getMainController().getCurrentGame().getCurrentPlayer().getAdventurer().move(coords);
        } catch (MoveException e) {
            e.printStackTrace();
        }
    }
    
    
    public void update(Observable arg0, Object arg1) {
        if (arg1 instanceof InGameAction) {
            InGameMessage m = (InGameMessage) arg1;
            if (m.getType().equals(InGameAction.MOVE)) {
                // TODO : is the UI have to send the coord, the tile, the int of coord ?
                // movePawn();
            } // end if
        } else if (arg1 instanceof MainMessage) {
            System.out.println("Main action Message");
        } else {
            throw new IllegalArgumentException("The class " + arg0.getClass().getName() + " was going to send "
                    + arg1.getClass() + " Object, but a " + Message.class.getName() + " is expected");
        } // end if
    }
    
    
    /**
     * @return the mainController
     */
    public MainController getMainController() {
        return mainController;
    }
}
