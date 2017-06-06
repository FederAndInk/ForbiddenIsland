/**
 * 
 */
package controller;

import java.util.Observable;
import java.util.Stack;

import javax.swing.JPanel;

import model.card.CardType;
import model.game.Coords;
import model.game.Game;
import model.game.Island;
import model.game.Tile;
import model.player.Player;
import util.exception.MoveException;
import util.message.InGameAction;
import util.message.InGameMessage;
import util.message.MainMessage;
import util.message.Message;
import view.board.GameView;
import view.board.TilePanel;



/**
 * @author nihil
 *
 */
public class GameController {
    private MainController mainController;
    private CardType       cardPlayed;
    private Stack<Player>  playersChain;
    private Game           currentGame;
    private GameView       gameView;
    
    
    /**
     * @author nihil
     *
     * @param mainController
     */
    public GameController(MainController mainController) {
        this.mainController = mainController;
        gameView = new GameView();
    }
    
    
    /**
     * @author nihil
     *
     */
    public void StartGame() {
        getCurrentGame().initGame();
        
        gameView.setBoard(getCurrentGame().getIsland().getSites());
        
        setMoveAction();
        gameView.setVisible(true);
    }
    
    
    private void movePawn(Coords coords) {
        
        // TODO : do something if the move can't be applied (exception)
        // TODO : get the tile from the UI
        try {
            Game g = getCurrentGame();
            Tile t = g.getIsland().getTile(coords);
            g.getCurrentPlayer().getCurrentAdventurer().move(t);
        } catch (MoveException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @author nihil
     *
     */
    private void reInitBoard() {
        for (int i = 0; i < Island.GRID_SIZE.getRow(); i++) {
            for (int j = 0; j < Island.GRID_SIZE.getCol(); j++) {
                JPanel tile = gameView.getTileG(new Coords(j, i));
                if (tile instanceof TilePanel) {
                    ((TilePanel) tile).setEnabled(false);
                } // end if
            } // end for
        } // end for
    }
    
    
    /**
     * @author nihil
     *
     */
    private void setMoveAction() {
        reInitBoard();
        for (Tile tile : getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getReachableTiles()) {
            System.out.println(tile);
            gameView.setEnabled(true, tile.getCoords());
        } // end forsetEnabled
    }
    
    
    /**
     * @author nihil
     * @param object
     *
     */
    private void doAction(Object object) {
        if (object instanceof Coords) {
            Coords coords = (Coords) object;
            
            switch (getCurrentGame().getCurrentAction()) {
            case MOVE:
                movePawn(coords);
                break;
            
            default:
                break;
            }// end switch
        } // end if
        
    }
    
    
    private void endTurn() {
        playersChain.clear();
        playersChain.push(getCurrentGame().getCurrentPlayer());
        getCurrentGame().endTurn();
    }// end endTurn
    
    
    public void update(Observable arg0, Object arg1) {
        if (arg1 instanceof InGameAction) {
            InGameMessage m = (InGameMessage) arg1;
            
            switch ((InGameAction) m.getType()) {
            case SELECT_TILE:
                doAction(m.getContent());
                break;
            case MOVE:
                setMoveAction();
                
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
            case INTERRUPT:
                
                break;
            case SELECT_PAWN:
                
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
