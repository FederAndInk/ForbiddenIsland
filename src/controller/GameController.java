/**
 * 
 */
package controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import javax.swing.JLayeredPane;

import model.adventurers.Adventurer;
import model.card.CardType;
import model.game.Coords;
import model.game.Game;
import model.game.Island;
import model.game.Tile;
import model.player.Player;
import util.LogType;
import util.Parameters;
import util.exception.MoveException;
import util.message.InGameAction;
import util.message.InGameMessage;
import util.message.MainMessage;
import view.board.GameView;
import view.board.TilePanel;



/**
 * @author nihil
 *
 */
public class GameController implements Observer {
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
        
        gameView.setBoard(getCurrentGame().getIsland().getSites(), this);
        
        setSpawns();
        
        setMoveAction();
        gameView.setVisible(true);
    }
    
    
    /**
     * @author nihil
     *
     */
    private void setSpawns() {
        for (Player player : getCurrentGame().getPlayers()) {
            JLayeredPane panel = gameView.getTileG(player.getCurrentAdventurer().getCurrentTile().getCoords());
            if (panel instanceof TilePanel) {
                ((TilePanel) panel).addPawn(player.getCurrentAdventurer().getADVENTURER_TYPE());
            } // end if
        } // end for
    }
    
    
    /**
     * @author nihil
     *
     * @param coords
     */
    private void movePawn(Coords coords) {
        
        // TODO : do something if the move can't be applied (exception)
        // TODO : get the tile from the UI
        try {
            Game g = getCurrentGame();
            Adventurer adv = g.getCurrentPlayer().getCurrentAdventurer();
            Tile cTile = adv.getCurrentTile();
            
            Tile t = g.getIsland().getTile(coords);
            adv.move(t);
            Parameters.printLog("Move to " + t, LogType.INFO);
            
            // to update the view
            gameView.movePawn(adv.getADVENTURER_TYPE(), cTile.getCoords(), coords);
            if (adv.getActionPoints() > 0) {
                setMoveAction();
            } else {
                reInitBoard();
            } // end if
        } catch (MoveException e) {
            e.printStackTrace();
        }
        
    }
    
    
    /**
     * @author nihil
     *
     */
    private void refreshBoard() {
    }
    
    
    /**
     * @author nihil
     *
     */
    private void reInitBoard() {
        for (int i = 0; i < Island.GRID_SIZE.getRow(); i++) {
            for (int j = 0; j < Island.GRID_SIZE.getCol(); j++) {
                JLayeredPane tile = gameView.getTileG(new Coords(j, i));
                if (tile instanceof TilePanel) {
                    ((TilePanel) tile).setEnabled(false);
                } // end if
            } // end for
        } // end for
    }
    
    
    /**
     * to update the view for move action of the current player
     * 
     * @author nihil
     *
     */
    private void setMoveAction() {
        getCurrentGame().setCurrentAction(InGameAction.MOVE);
        Parameters.printLog("get Move", LogType.INFO);
        reInitBoard();
        for (Tile tile : getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getReachableTiles()) {
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
    
    
    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        if (arg1 instanceof InGameMessage) {
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
                    + arg1.getClass() + " Object, but a " + InGameMessage.class.getName() + " is expected");
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
