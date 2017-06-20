package model.game;

import java.util.*;

import model.adventurers.Adventurer;
import model.adventurers.AdventurerType;
import model.player.Player;
import util.BoardType;
import util.LogType;
import util.Parameters;
import util.exception.PlayerOutOfIslandException;
import util.message.InGameAction;



public class Game {
    private final static Integer MAX_PLAYER = 4;
    private final static Integer MIN_PLAYER = 2;
    private ArrayList<Treasure>  treasures;
    private SeaLevel             seaLevel;
    private Island               island;
    private LinkedList<Player>   players;
    private Deck                 treasureDeck;
    private Deck                 floodDeck;
    private Player               currentPlayer;
    private boolean              started;
    
    private InGameAction currentAction;
    
    
    /**
     * @author nihil
     *
     */
    public Game(BoardType bType) {
        started = false;
        island = new Island(bType);
        treasureDeck = new TreasureDeck();
        floodDeck = new FloodDeck();
        players = new LinkedList<>();
        treasures = new ArrayList<>();
    }
    
    
    public Game() {
        this(BoardType.DEFAULT);
    }
    
    
    /**
     * to call when you want to start the game
     * 
     * @author nihil
     */
    public void initGame(SeaLevel seaLevel) {
        if (players.size() < 2) {
            throw new IndexOutOfBoundsException("Too few players");
        } // end if
        
        this.seaLevel = seaLevel;
        randomAdventurer();
        initTreasure();
        for (Player player : players) {
            Site spawn = player.getCurrentAdventurer().getADVENTURER_TYPE().getSpawn();
            player.getCurrentAdventurer().setSpawn(getIsland().getTile(spawn));
        } // end for
        
        Collections.shuffle(players);
        setCurrentPlayer(players.get(0));
        setCurrentAction(InGameAction.MOVE);
        started = true;
    }// end name
    
    
    /**
     * 
     * @author nihil
     * add a player to the game (4 max)
     * @param p
     * the player to add to the party
     * @param adventurer
     * the adventurer to set to the player
     * @return the number of players after adding they
     * @throws IndexOutOfBoundsException
     */
    public Integer addPlayer(Adventurer adventurer) throws IndexOutOfBoundsException {
        if (players.size() < MAX_PLAYER) {
            adventurer.getPlayer().setCurrentGame(this);
            adventurer.getPlayer().setCurrentAdventurer(adventurer);
            players.add(adventurer.getPlayer());
        } else {
            throw new IndexOutOfBoundsException("Too many players");
        }
        return players.size();
    }// end
    
    
    /**
     * get a random adventurer if necessary for players
     * 
     * @author nihil
     */
    private void randomAdventurer() {
        ArrayList<AdventurerType> restAdv = new ArrayList<>(Arrays.asList(AdventurerType.values()));
        restAdv.remove(AdventurerType.RANDOM); // normally done just later :
        for (Player player : players) {
            restAdv.remove(player.getCurrentAdventurer().getADVENTURER_TYPE());
        } // end for
        Collections.shuffle(restAdv);
        int i = 0;
        for (Player player : players) {
            if (player.getCurrentAdventurer().getADVENTURER_TYPE().equals(AdventurerType.RANDOM)) {
                player.setCurrentAdventurer(restAdv.get(i).getClassFor(player));
                i++;
            } // end if
        } // end for
    }// end randomGame
    
    
    /**
     * Initialize the treasures
     * 
     * @author nihil
     *
     */
    private void initTreasure() {
        for (TreasureType treasureT : TreasureType.toList()) {
            treasures.add(new Treasure(treasureT));
        } // end for
    }// end initTreasure
    
    
    /**
     * @return the possibleActions
     */
    public ArrayList<InGameAction> getPossibleActions() {
        ArrayList<InGameAction> list = new ArrayList<>();
        list.addAll(currentPlayer.getCurrentAdventurer().getPossibleActions());
        
        return list;
    }
    
    
    /**
     * @author nihil
     *
     */
    public void endTurn() {
        getCurrentPlayer().getCurrentAdventurer().endTurn();
        
        int indLastP = getPlayers().indexOf(getCurrentPlayer());
        setCurrentPlayer(getPlayers().get((indLastP + 1) % 4));
        getCurrentPlayer().getCurrentAdventurer().beginTurn();
        setCurrentAction(InGameAction.MOVE);
    }
    
    
    public void setTileState(Tile tile, TileState state) throws PlayerOutOfIslandException {
        tile.setState(state);
        if (state.equals(TileState.SINKED) && !getPlayersOnTile(tile).isEmpty()) {
            throw new PlayerOutOfIslandException();
        } // end if
    }// end setTileState
    
    
    /**
     * get the players on tile
     * 
     * @author nihil
     */
    public ArrayList<Player> getPlayersOnTile(Tile tile) {
        ArrayList<Player> players = new ArrayList<>();
        for (Player player : getPlayers()) {
            if (player.getCurrentAdventurer().getCurrentTile().equals(tile)) {
                players.add(player);
            } // end if
        } // end for
        return players;
    }
    
    
    public boolean isStarted() {
        return started;
    }
    
    
    /**
     * @return the treasures
     */
    public Collection<Treasure> getTreasures() {
        return treasures;
    }
    
    
    /**
     * @return the island
     */
    public Island getIsland() {
        return island;
    }
    
    
    /**
     * @return the players
     */
    public LinkedList<Player> getPlayers() {
        return players;
    }
    
    
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    
    /**
     * @return the treasureDeck
     */
    public Deck getTreasureDeck() {
        return treasureDeck;
    }
    
    
    /**
     * @return the floodDeck
     */
    public Deck getFloodDeck() {
        return floodDeck;
    }
    
    
    /**
     * <p>
     * then, there is no more treasures in game
     * </p>
     * 
     * @author nihil
     *
     * @return true if the players get all the treasures
     */
    private boolean isTreasureAllInvoked() {
        return treasures.isEmpty();
    }
    
    
    /**
     * @param currentPlayer
     * the currentPlayer to set
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    
    /**
     * @return the currentAction
     */
    public InGameAction getCurrentAction() {
        return currentAction;
    }
    
    
    /**
     * @param currentAction
     * the currentAction to set
     */
    public void setCurrentAction(InGameAction currentAction) {
        this.currentAction = currentAction;
        Parameters.printLog("Set action to : " + currentAction, LogType.INFO);
    }
    
    
    /**
     * @author nihil
     *
     * @return
     */
    public ArrayList<AdventurerType> getPawns() {
        ArrayList<AdventurerType> advs = new ArrayList<>();
        for (Player player : players) {
            advs.add(player.getCurrentAdventurer().getADVENTURER_TYPE());
        } // end for
        return advs;
    }
}