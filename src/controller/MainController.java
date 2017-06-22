package controller;

import java.awt.CardLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import model.adventurers.AdventurerType;
import model.game.Game;
import model.game.SeaLevel;
import model.player.Player;
import util.BoardType;
import util.LogType;
import util.Parameters;
import util.message.InGameMessage;
import util.message.MainAction;
import util.message.MainMessage;
import view.MainView;



/**
 * @author nihil
 *
 */
public class MainController implements Observer, Serializable {
    
    private HashMap<String, Player> players;
    private ArrayList<Game>         savedGames;
    private GameController          gameController;
    private String                  currentPlayer;
    private MainView                view;
    
    
    /**
     * @author nihil
     *
     */
    public MainController() {
        view = new MainView();
        players = new HashMap<>();
        savedGames = new ArrayList<>();
        gameController = new GameController(this);
        view.AddObs(this);
        view.setVisible(true);
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
            case ADD_PLAYER_TO_GAME:
                
                break;
            case CREATE_GAME:
                if (m.getContent() instanceof BoardType) {
                    BoardType bType = (BoardType) m.getContent();
                    createGame(bType);
                    for (String p : view.getMainMenu().getjeu().getPlayerMap().keySet()) {
                        if (getPlayer(p) == null) {
                            addPlayer(p);
                        }
                        AdventurerType adv = view.getMainMenu().getjeu().getPlayerMap().get(p);
                        gameController.getCurrentGame().addPlayer(adv.getClassFor(getPlayer(p)));
                    }
                    
                    gameController.StartGame(view.getMainMenu().getjeu().getSeaLevel());
                    // FIXME :to remove
                    view.setVisible(false);
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
            case SELECT_PLAYER:
                Parameters.printLog(m.getContent(), LogType.INFO);
                if (m.getContent() != "random") {
                    Parameters.printLog("message select Player reçu", LogType.INFO);
                    setCurrentPlayer(((String) m.getContent()));
                    // view.getSelectHero().setEnabled(false, null);
                    Parameters.printLog(getCurrentPlayer(), LogType.INFO);
                    choixchamp();
                } else {
                    Parameters.printLog("les roles sont au hasard", LogType.INFO);
                    view.getMainMenu().getjeu().setBaseColor();
                    for (int i = 1; i <= 4; i++) {
                        view.getMainMenu().getjeu().setAdventurer("J" + i, AdventurerType.RANDOM);
                    }
                    for (AdventurerType adv : AdventurerType.values()) {
                        if (adv != AdventurerType.RANDOM) {
                            view.getSelectHero()
                                    .setEnabled(!view.getMainMenu().getjeu().getPlayerMap().containsValue(adv), adv);
                        }
                    }
                }
                break;
            case SELECT_ADVENTURER:
                Parameters.printLog("YOLOOOOOOOOOOO", LogType.INFO);
                if (m.getContent() == null) {
                    Parameters.printLog("YOLOOOOOOOOOOO", LogType.INFO);
                    view.getMainMenu().getjeu().setBaseColor();
                    for (AdventurerType adv : AdventurerType.values()) {
                        if (adv != AdventurerType.RANDOM) {
                            view.getSelectHero()
                                    .setEnabled(!view.getMainMenu().getjeu().getPlayerMap().containsValue(adv), adv);
                        }
                    }
                } else {
                    view.getMainMenu().getjeu().setAdventurer(currentPlayer, (AdventurerType) m.getContent());
                    // Parameters.printLog(view.getMainMenu().getjeu().getPlayer(currentPlayer), LogType.INFO);
                    for (AdventurerType adv : AdventurerType.values()) {
                        if (adv != AdventurerType.RANDOM) {
                            view.getSelectHero()
                                    .setEnabled(!view.getMainMenu().getjeu().getPlayerMap().containsValue(adv), adv);
                        }
                    }
                }
                for (String player : view.getMainMenu().getjeu().getPlayerMap().keySet()) {
                    view.getMainMenu().getjeu().changeButtonColor(player);
                }
                break;
            case SELECT_MAP:
                choixmap();
                break;
            case MAP_SELECTED:
                Parameters.printLog("MAP CHANGÉ DE " + view.getMainMenu().getjeu().getBoardType() + " à "
                        + (BoardType) m.getContent(), LogType.INFO);
                view.getMainMenu().getjeu().setBoardType((BoardType) m.getContent());
                break;
            case REMOVE_GAME:
                
                break;
            case ABANDON:
            
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
    
    
    public MainView getView() {
        return view;
    }
    
    
    /**
     * @return the gameController
     */
    public GameController getGameController() {
        return gameController;
    }
    
    
    public void choixchamp() {
        ((CardLayout) view.getCard().getLayout()).show(view.getCard(), "heroSelection");
    }
    
    
    public void choixmap() {
        ((CardLayout) view.getCard().getLayout()).show(view.getCard(), "choixMap");
    }
    
    
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    
    public void finjeu() {
        ((CardLayout) view.getCard().getLayout()).show(view.getCard(), "fin");
    }
}