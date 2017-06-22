package model.game;

import java.util.*;

import model.adventurers.Adventurer;
import model.adventurers.AdventurerType;
import model.card.Card;
import model.card.CardType;
import model.player.Player;
import util.BoardType;
import util.LogType;
import util.Parameters;
import util.exception.*;
import util.message.InGameAction;



public class Game {
    private final static Integer MAX_PLAYER = 4;
    private final static Integer MIN_PLAYER = 2;
    private ArrayList<Treasure>  treasures;
    private SeaLevel             seaLevel;
    private Island               island;
    private LinkedList<Player>   players;
    private TreasureDeck         treasureDeck;
    private FloodDeck            floodDeck;
    private Player               currentPlayer;
    private int                  cardsDrawn;
    private boolean              started;
    
    private ArrayList<Player> SelectedPlayers;
    private InGameAction      currentAction;
    
    
    /**
     * @author nihil
     *
     */
    public Game(BoardType bType) {
        started = false;
        island = new Island(bType);
        treasureDeck = new TreasureDeck(island);
        floodDeck = new FloodDeck(island);
        players = new LinkedList<>();
        treasures = new ArrayList<>();
        SelectedPlayers = new ArrayList<>();
        cardsDrawn = 0;
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
        initCards();
        started = true;
    }// end name
    
    
    public void initCards() {
        for (Player player : players) {
            for (int i = 0; i < Parameters.NB_CARD_BEGIN; i++) {
                Card card = getTreasureDeck().draw();
                while (card.getType() == CardType.WATERSRISE_CARD) {
                    getTreasureDeck().discard(card);
                    getTreasureDeck().shuffleDeck();
                    card = getTreasureDeck().draw();
                }
                try {
                    player.getCurrentAdventurer().getInventory().addCard(card);
                } catch (CardException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    
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
     * Add treasure to the inventory of player
     * 
     * @param treasure
     * @throws util.exception.ActionException
     * @throws util.exception.NotEnoughCardsException
     * @throws util.exception.WrongTileTreasureException
     */
    public void addTreasureToPlayer(Treasure treasure)
            throws ActionException, NotEnoughCardsException, WrongTileTreasureException {
        Player player = getCurrentPlayer();
        if (treasures.contains(treasure)) {
            try {
                for (Card card : player.getCurrentAdventurer().createTreasure(treasure)) {
                    getTreasureDeck().discard(card);
                }
                treasures.remove(treasure);
            } catch (Exception e) {
                throw e;
            }
        }
    }
    
    
    /**
     * @return the possibleActions
     */
    public ArrayList<InGameAction> getPossibleActions() {
        ArrayList<InGameAction> list = new ArrayList<>();
        switch (getCurrentAction()) {
        case DRAW_TREASURE:
            list.add(InGameAction.DRAW_TREASURE);
            break;
        case DRAW_FLOOD:
            list.add(InGameAction.DRAW_FLOOD);
            break;
        case END_TURN:
            list.add(InGameAction.END_TURN);
            break;
        default:
            list.addAll(currentPlayer.getCurrentAdventurer().getPossibleActions());
            break;
        }// end switch
        return list;
    }
    
    
    /**
     * @author nihil
     *
     */
    public void endTurn() {
        if (currentAction == InGameAction.END_TURN) {
            cardsDrawn = 0;
            getCurrentPlayer().getCurrentAdventurer().endTurn();
            
            int indLastP = getPlayers().indexOf(getCurrentPlayer());
            setCurrentPlayer(getPlayers().get((indLastP + 1) % 4));
            getCurrentPlayer().getCurrentAdventurer().beginTurn();
            setCurrentAction(InGameAction.MOVE);
            deselectPlayers();
        } else {
            
            setCurrentAction(InGameAction.DRAW_TREASURE);
        } // end if
    }
    
    
    /**
     * 
     * @param tile
     * @param state
     * @throws PlayerOutOfIslandException
     * if a player get drown
     * @throws EndGameException
     */
    public void setTileState(Tile tile, TileState state) throws PlayerOutOfIslandException, EndGameException {
        tile.setState(state);
        if (state.equals(TileState.SINKED) && !getPlayersOnTile(tile).isEmpty()) {
            throw new PlayerOutOfIslandException();
        } // end if
        verifyTreasure();
    }// end setTileState
    
    
    public void verifyTreasure() throws EndGameException {
        if (!getTreasures().isEmpty()) {
            for (Treasure treasure : getTreasures()) {
                if (getIsland().isTreasureAllSinked((treasure.getName()))) {
                    throw new EndGameException(ExceptionType.END_GAME_TREASURE);
                }
            }
        }
    }
    
    
    /**
     *
     * @param type
     * = {@link CardType.TREASURE_CARD} for {@link TreasureDeck} <br>
     * or {@link CardType.FLOOD_CARD} for {@link FloodDeck}
     * @return
     * @throws CardException
     */
    public Card drawEndTurnCard(InGameAction type)
            throws EndGameException, IllegalAccessException, MoveException, TileException, CardException {
        Card card;
        // For actions
        if (cardsDrawn < getNbCards(InGameAction.DRAW_TREASURE)) {
            setCurrentAction(InGameAction.DRAW_TREASURE);
        } else if (cardsDrawn < getNbCards(InGameAction.DRAW_FLOOD)) {
            setCurrentAction(InGameAction.DRAW_FLOOD);
        } else {
            setCurrentAction(InGameAction.END_TURN);
        } // end if
        cardsDrawn++;
        
        // Draw
        if (type.equals(InGameAction.DRAW_TREASURE)) {
            card = getTreasureDeck().draw();
            if (card.getType().isCanAddToInventory()) {
                getCurrentPlayer().getCurrentAdventurer().getInventory().addCard(card);
            } else {
                card.applyAction(null, this);
            }
        } else if (type.equals(InGameAction.DRAW_FLOOD)) {
            card = getFloodDeck().draw();
            card.applyAction(null, this);
            verifyTreasure();
        } else {
            throw new IllegalArgumentException("not a valid deck");
        }
        Parameters.printLog("Draw card " + card, LogType.INFO);
        return card;
    }
    
    
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
    
    
    public void increaseSeaLevel() throws EndGameException {
        seaLevel = seaLevel.next();
        Parameters.printLog("The sealeavel has reach the level " + seaLevel, LogType.INFO);
        if (seaLevel.isLast()) {
            throw new EndGameException(ExceptionType.END_GAME_WATER);
        }
    }
    
    
    /**
     * @return the seaLevel
     */
    public SeaLevel getSeaLevel() {
        return seaLevel;
    }
    
    
    public boolean isStarted() {
        return started;
    }
    
    
    /**
     * @author nihil
     *
     */
    private int getNbCards(InGameAction type) {
        if (type == InGameAction.DRAW_TREASURE) {
            return Parameters.NB_CARDS;
        } // end if
        return Parameters.NB_CARDS + getSeaLevel().getNbCards();
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
     * @author nihil
     * @param player
     * @return true if this adding the player, return false this removing the player
     *
     */
    public boolean toggleSelectionPlayer(Player player) {
        if (!SelectedPlayers.remove(player)) {
            SelectedPlayers.add(player);
            Parameters.printLog("Add player " + player + " to selected", LogType.ACCESS);
            return true;
        } // end if
        Parameters.printLog("remove player " + player + " to selected", LogType.ACCESS);
        return false;
    }
    
    
    /**
     * @return the selectedPlayers
     */
    public ArrayList<Player> getSelectedPlayers() {
        return SelectedPlayers;
    }
    
    
    /**
     * @author nihil
     * @param player
     *
     */
    public void deselectPlayers() {
        SelectedPlayers.clear();
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
     * @param advT
     * @return the player specified by the {@link AdventurerType} or null if not in this {@link Game}
     */
    public Player getPlayer(AdventurerType advT) {
        Iterator<Player> it = getPlayers().iterator();
        Player p = null;
        while (it.hasNext() && !(p = it.next()).getCurrentAdventurer().getADVENTURER_TYPE().equals(advT)) {
        }
        return p;
    }
}