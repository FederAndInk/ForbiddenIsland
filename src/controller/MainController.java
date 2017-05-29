package controller;

import java.util.*;

import model.game.Game;
import model.player.Player;
import util.message.InGameMessage;
import util.message.MainAction;
import util.message.MainMessage;
import util.message.Message;



public class MainController implements Observer {
    
    private HashMap<String, Player> players;
    private Game                    currentGame;
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
        setCurrentGame(new Game());
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
        // TODO : when the player will have a name
        // TODO : get data from the UI or the UI send the player directly ?
        // if (!getPlayers().containsKey(pName)) {
        // players.put(players.getName(), new Player(pName));
        // } // end if
        getCurrentGame().addPlayer(players.get(pName));
        return true; // FIXME
    }// end addPlayer
    
    
    @Override
    public void update(Observable arg0, Object arg1) {
        if (arg1 instanceof MainMessage) {
            MainMessage m = ((MainMessage) arg1);
            
            if (m.getType().equals(MainAction.CREATE_GAME)) {
                
                createGame();
                
            } else if (m.getType().equals(MainAction.ADD_PLAYER)) {
                
                // TODO : implement later if add player is necessary
                throw new UnsupportedOperationException();
                
            } else if (m.getType().equals(MainAction.ADD_PLAYER_TO_GAME)) {
                
                if (m.getContent() instanceof Player) {
                    addPlayer((String) m.getContent());
                } else {
                    throw new IllegalArgumentException("In order to create a player, a player name must be given");
                } // end if
                
            } else if (m.getType().equals(MainAction.BEGIN_GAME)) {
                
                // TODO : Begin the game
                System.out.println(m.getContent());
                
            }
        } else if (arg1 instanceof InGameMessage) {
            System.out.println("InGame action Message");
        } else {
            throw new IllegalArgumentException("The class " + arg0.getClass().getName() + " was going to send "
                    + arg1.getClass() + " Object, but a " + Message.class.getName() + " is expected");
        } // end if
        
    }
    
    
    public Game getCurrentGame() {
        return currentGame;
    }
    
    
    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
    
    
    public Map<String, Player> getPlayers() {
        return players;
    }
    
}