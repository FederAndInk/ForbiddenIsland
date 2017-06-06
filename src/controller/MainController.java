package controller;

import java.util.*;

import model.game.Game;
import model.player.Player;
import util.message.InGameMessage;
import util.message.MainAction;
import util.message.MainMessage;



public class MainController implements Observer {
    
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
    
    
    public void createGame() {
        // TODO : complete with IHM
        if (gameController.getCurrentGame() != null) {
            // FIXME : do something to prevent erasement
        } // end if
        gameController.setCurrentGame(new Game());
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
        // FIXME : get data from the UI or the UI send the player directly ?
        if (!getPlayers().containsKey(pName)) {
            players.put(pName, new Player(pName));
        } // end if
          // gameController.getCurrentGame().addPlayer(players.get(pName), AdventurerType.PILOT.getClassFor(null));
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
                createGame();
                break;
            case BEGIN_GAME:
                gameController.StartGame();
                
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
                
                break;
            default:
                if (arg1 instanceof InGameMessage) {
                    System.out.println("InGame action Message");
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
    
}