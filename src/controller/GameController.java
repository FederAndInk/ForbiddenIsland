/**
 * 
 */
package controller;

import java.util.ArrayList;
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
import util.exception.ActionException;
import util.exception.InadequateUseOfCapacity;
import util.exception.MoveException;
import util.exception.TileException;
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
        gameView.getListObs().addObserver(this);
        playersChain = new Stack<>();
    }
    
    
    /**
     * @author nihil
     *
     */
    public void StartGame() {
        getCurrentGame().initGame();
        playersChain.clear();
        playersChain.push(getCurrentGame().getCurrentPlayer());
        
        gameView.setBoard(getCurrentGame().getIsland().getSites(), this);
        
        setSpawns();
        
        refreshBoard();
        defaultAction();
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
    }// end setSpawns
    
    
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
        } catch (MoveException e) {
            e.printStackTrace();
        } catch (ActionException e) {
            e.printStackTrace();
        } finally {
            defaultAction();
        }
        
    }
    
    
    /**
     * @author nihil
     *
     * @param tile
     */
    private void shoreUp(Tile tile) {
        Game g = getCurrentGame();
        Adventurer adv = g.getCurrentPlayer().getCurrentAdventurer();
        
        try {
            adv.shoreUp(tile);
            Parameters.printLog("Shore Up " + tile, LogType.INFO);
            
            // to update the view
            gameView.shoreUp(tile.getCoords());
        } catch (TileException | ActionException e) {
            e.printStackTrace();
        } finally {
            defaultAction();
        }
    }
    
    
    /**
     * @author nihil
     *
     * @param tile
     */
    private void useCapacity(Tile tile) {
        Adventurer adv = getCurrentGame().getCurrentPlayer().getCurrentAdventurer();
        Tile cTile = adv.getCurrentTile();
        
        try {
            adv.useCapacity(tile);
            gameView.movePawn(adv.getADVENTURER_TYPE(), cTile.getCoords(), tile.getCoords());
        } catch (InadequateUseOfCapacity | MoveException | ActionException e) {
            e.printStackTrace();
        } finally {
            defaultAction();
        }
    }
    
    
    /**
     * @author nihil
     *
     */
    private void refreshBoard() {
        reInitBoard();
        Tile[][] grid = getCurrentGame().getIsland().getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (gameView.getTileG(new Coords(j, i)) instanceof TilePanel) {
                    TilePanel tileG = (TilePanel) gameView.getTileG(new Coords(j, i));
                    tileG.setState(grid[j][i].getState());
                } // end if
            } // end for
        } // end
    } // end for
    
    
    /**
     * @author nihil
     *
     */
    private void refreshBoard(ArrayList<Tile> selectedTiles, InGameAction action) {
        reInitBoard();
        for (Tile tile : selectedTiles) {
            gameView.setEnabled(true, tile.getCoords(), action);
        } // end for
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
        gameView.doLayout();
    }
    
    
    /**
     * to update the view for move action of the current player
     * 
     * @author nihil
     *
     */
    private void setMoveAction() {
        if (getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getActionPoints() > 0) {
            gameView.notifyPlayers("Cliquer sur une partie de l'ile en vert pour vous y rendre");
            getCurrentGame().setCurrentAction(InGameAction.MOVE);
            Parameters.printLog("get Move", LogType.INFO);
            refreshBoard(getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getReachableTiles(),
                    InGameAction.MOVE);
        } else {
            defaultAction();
        } // end if
    }
    
    
    /**
     * to update the view for using capacity action of the current player
     * 
     * @author nihil
     *
     */
    private void setCapacityActionT() {
        if (getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getActionPoints() > 0) {
            gameView.notifyPlayers("Cliquer sur une partie de l'ile en vert pour vous y rendre");
            getCurrentGame().setCurrentAction(InGameAction.USE_CAPACITY);
            Parameters.printLog("get Capacity", LogType.INFO);
            
            ArrayList<Object> objs;
            ArrayList<Tile> tiles = new ArrayList<>();
            try {
                objs = getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getPotentialUse();
                for (int i = 0; i < objs.size(); i++) {
                    tiles.add(i, (Tile) objs.get(i));
                } // end for
                refreshBoard(tiles, InGameAction.USE_CAPACITY);
            } catch (InadequateUseOfCapacity e) {
                e.printStackTrace();
            }
        } else {
            verifyEndTurn();
        } // end if
    }
    
    
    /**
     * call when the turn is up
     * 
     * @author nihil
     *
     */
    private void endTurn() {
        playersChain.clear();
        getCurrentGame().endTurn();
        playersChain.push(getCurrentGame().getCurrentPlayer());
        defaultAction();
    }// end endTurn
    
    
    /**
     * @author nihil
     *
     */
    private void setShoreUpAction() {
        ArrayList<Tile> tiles = getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getShoreUpTiles();
        if (tiles.isEmpty()) {
            gameView.notifyPlayers("Aucune partie de l'ile proche de vous à assécher");
            defaultAction();
        } else {
            getCurrentGame().setCurrentAction(InGameAction.SHORE_UP_TILE);
            gameView.notifyPlayers("Cliquer sur une partie de l'ile en beige pour l'assécher");
            Parameters.printLog("get Shore Up tiles", LogType.INFO);
            refreshBoard(tiles, InGameAction.SHORE_UP_TILE);
        } // end if
    }
    
    
    /**
     * set smart action (the most probable action)
     * 
     * @author nihil
     *
     */
    private void defaultAction() {
        ArrayList<InGameAction> acts = getCurrentGame().getPossibleActions();
        
        if (acts.contains(InGameAction.GET_TREASURE)) {
            // TODO : setGetTreasure
        } else if (acts.contains(InGameAction.MOVE)) {
            setMoveAction();
        } else if (acts.contains(InGameAction.SHORE_UP_TILE)) {
            setShoreUpAction();
        }
        turnGestion();
    }
    
    
    /**
     * to set the view correctly after an action
     * 
     * @author nihil
     *
     */
    private void turnGestion() {
        verifyEndTurn();
        gameView.setCPlayer(getCurrentGame().getCurrentPlayer().getName(),
                getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getActionPoints());
        gameView.setCurrentP(getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getADVENTURER_TYPE());
        ArrayList<InGameAction> acts = getCurrentGame().getPossibleActions();
        InGameAction act = getCurrentGame().getCurrentAction();
        gameView.setActions(acts);
    }// end turnGestion
    
    
    /**
     * @author nihil
     * @param object
     *
     */
    private void doAction(Object object) {
        Adventurer adv = getCurrentGame().getCurrentPlayer().getCurrentAdventurer();
        
        if (object instanceof Coords) {
            Coords coords = (Coords) object;
            
            switch (getCurrentGame().getCurrentAction()) {
            case MOVE:
                movePawn(coords);
                
                break;
            case SHORE_UP_TILE:
                shoreUp(getCurrentGame().getIsland().getTile(coords));
                break;
            case USE_CAPACITY:
                useCapacity(getCurrentGame().getIsland().getTile(coords));
                break;
            default:
                break;
            }// end switch
        } // end if
        
    }
    
    
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
                setShoreUpAction();
                break;
            case USE_CAPACITY:
                setCapacityActionT();
                break;
            case USE_CARD:
                
                break;
            case INTERRUPT:
                
                break;
            case SELECT_PAWN:
                
                break;
            case END_TURN:
                endTurn();
                break;
            
            default:
                break;
            }// end switch
            
        } else if (arg1 instanceof MainMessage) {
            Parameters.printLog("Main action Message", LogType.INFO);
        } else {
            throw new IllegalArgumentException("The class " + arg0.getClass().getName() + " was going to send "
                    + arg1.getClass() + " Object, but a " + InGameMessage.class.getName() + " is expected");
        } // end if
        turnGestion();
    }
    
    
    /**
     * verify if the turn can be end
     * 
     * @author nihil
     * @return true if this is the end of turn (no actions remaining)
     *
     */
    private boolean verifyEndTurn() {
        if (getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getPossibleActions().isEmpty()) {
            gameView.setEndTurn(true);
            gameView.notifyPlayers("C'est la fin du tour pour " + getCurrentGame().getCurrentPlayer().getName());
            reInitBoard();
            Parameters.printLog("SetEndTurn", LogType.INFO);
            return true;
        } else {
            gameView.setEndTurn(false);
            return false;
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
