/**
 * 
 */
package controller;

import java.util.Observable;
import java.util.Stack;

import model.card.CardType;
import model.game.Coords;
import model.game.Game;
import model.game.Tile;
import model.player.Player;
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
    private MainController mainController;
    private CardType       cardPlayed;
    private Stack<Player>  playersChain;
    private Game           currentGame;
    
    
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
            Game g = getCurrentGame();
            Tile t = g.getIsland().getTile(coords);
            g.getCurrentPlayer().getAdventurer().move(t);
        } catch (MoveException e) {
            e.printStackTrace();
        }
    }
    
    
    public void endTurn() {
        playersChain.clear();
        playersChain.push(getCurrentGame().getCurrentPlayer());
        getCurrentGame().endTurn();
    }// end endTurn
    
    
    public void update(Observable arg0, Object arg1) {
        if (arg1 instanceof InGameAction) {
            InGameMessage m = (InGameMessage) arg1;
            switch ((InGameAction) m.getType()) {
            case MOVE:
                // TODO : is the UI have to send the coord, the tile, the int of coord ?
                // movePawn();
                
                break;
            case GET_TREASURE:
                
                break;
            case GIVE_CARD:
                
                break;
            case SHORE_UP_TILE:
                
                break;
            case USE_CAPACITY:
                
                break;
            case USE_CARD:
                
                break;
            case SWIM:
                
                break;
            case INTERRUPT:
                
                break;
            case END_TURN:
                
                break;
            
            default:
                break;
            }// end switch
            
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
    
    
    public Game getCurrentGame() {
        return currentGame;
    }
    
    
    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
