package model.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import model.adventurers.Adventurer;
import model.player.Player;



public class Game {
    private final static Integer MAX_PLAYER = 4;
    private ArrayList<Treasure>  treasures;
    private Island               island;
    private ArrayList<Player>    players;
    private TreasureDeck         treasureDeck;
    private FloodDeck            floodDeck;
    private Player               currentPlayer;
    private boolean              started;
    
    
    public Game() {
        started = false;
        island = new Island();
        treasureDeck = new TreasureDeck();
        floodDeck = new FloodDeck();
        players = new ArrayList<>();
        treasures = new ArrayList<>();
    }
    
    
    public void initGame() {
        initTreasure();
        Collections.shuffle(players);
        currentPlayer = players.get(0);
        started = true;
    }// end name
    
    
    /**
     * @author nihil
     * add a player to the game (4 max)
     * @param p
     * the player to add to the party
     * @param adventurer
     * @return the number of players after adding they
     * @throws IndexOutOfBoundsException
     */
    public Integer addPlayer(Player p, Adventurer adventurer) throws IndexOutOfBoundsException {
        if (players.size() < MAX_PLAYER) {
            p.setCurrentAdventurer(adventurer);
            players.add(p);
        } else {
            throw new IndexOutOfBoundsException("Too many players");
        } // end if
        return players.size();
    }// end addPlayer
    
    
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
    
    
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    
    public boolean isStarted() {
        return started;
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
    private boolean isTreasureAllInveked() {
        return treasures.isEmpty();
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
    public Collection<Player> getPlayers() {
        return players;
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
     * @param currentPlayer
     * the currentPlayer to set
     */
    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}