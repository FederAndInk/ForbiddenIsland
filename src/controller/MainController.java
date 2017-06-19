package controller;

import java.io.Serializable;
import java.util.*;

import model.game.Game;
import model.game.SeaLevel;
import model.player.Player;
import util.BoardType;
import util.LogType;
import util.Parameters;
import util.message.InGameMessage;
import util.message.MainAction;
import util.message.MainMessage;



/**
 * @author nihil
 *
 */
public class MainController implements Observer, Serializable {
    
    private HashMap<String, Player> players;
    private ArrayList<Game>         savedGames;
    private GameController          gameController;
    
    
    /**
     * @author nihil
     *
     */
    public MainController() {
        players = new HashMap<>();
        savedGames = new ArrayList<>();
        gameController = new GameController(this);
    }
    
    
    public void createGame(BoardType bType) {
        // TODO : complete with IHM
        if (gameController.getCurrentGame() != null) {
            // FIXME : do something to prevent erasement
        } // end if
        gameController.setCurrentGame(new Game(bType));
    }
    
    
    public void saveGame() {
        // TODO - implement MainController.saveGame
        throw new UnsupportedOperationException();
    }
    
    
    public void loadGame() {
        // TODO - implement MainController.loadGame
        throw new UnsupportedOperationException();
    }
    
    
    public void removeAllGames() {
        // TODO - implement MainController.removeAllGames
        throw new UnsupportedOperationException();
    }
    
    
    public boolean addPlayer(String pName) {
        if (!getPlayers().containsKey(pName)) {
            players.put(pName, new Player(pName));
        } // end if
          // TODO : something to return
        return true;
    }// end addPlayer
    
    
    @Override
    public void update(Observable arg0, Object arg1) {
        if (arg1 instanceof MainMessage) {
            MainMessage m = ((MainMessage) arg1);
            
            switch ((MainAction) m.getType()) {
            case ADD_PLAYER:
                if (m.getContent() instanceof Player) {
                    addPlayer((String) m.getContent());
                } else {
                    throw new IllegalArgumentException("In order to create a player, a player name must be given");
                } // end if
                break;
            case ADD_PLAYER_TO_GAME:
                
                break;
            case CREATE_GAME:
                if (m.getContent() instanceof BoardType) {
                    BoardType bType = (BoardType) m.getContent();
                    createGame(bType);
                } else {
                    throw new IllegalArgumentException("no board type given");
                } // end if
                break;
            case BEGIN_GAME:
                // FIXME : add view and give the sealevel
                gameController.StartGame(SeaLevel.LEVEL2);
                
                break;
            case LOAD_GAME:
                
                break;
            case SAVE_GAME:
                
                break;
            case REMOVE_GAME:
                
                break;
            case GET_HELP:
                
                break;
            case PARAMETERS:
                
                break;
            case SCORES:
                
                break;
            case QUIT:
                // FIXME do something on close (save)
                System.exit(0);
                break;
            default:
                if (arg1 instanceof InGameMessage) {
                    Parameters.printLog("InGame action Message", LogType.INFO);
                } else {
                    throw new IllegalArgumentException("The class " + arg0.getClass().getName() + " was going to send "
                            + arg1.getClass() + " Object, but a " + MainMessage.class.getName() + " is expected");
                } // end if
                break;
            }// end switch
        }
    }
    
    
    public Player getPlayer(String name) {
        return players.get(name);
    }// end name
    
    
    public Map<String, Player> getPlayers() {
        return players;
    }
    
    
    /**
     * @return the gameController
     */
    public GameController getGameController() {
        return gameController;
    }
}