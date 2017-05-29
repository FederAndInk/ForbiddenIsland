package model.game;

import java.util.ArrayList;
import java.util.Collection;

import model.player.Player;



public class Game {
    private final static Integer MAX_PLAYER = 4;
    private ArrayList<Treasure>  treasures;
    private Island               island;
    private Collection<Player>   players;
    private Deck                 treasureDeck;
    private Deck                 floodDeck;
    private Player               currentPlayer;
    private boolean              started;
    
    
    /**
     * @author nihil
     * add a player to the game (4 max)
     * @param p
     * the player to add to the party
     * @return the number of players after adding they
     */
    public Integer addPlayer(Player p) {
        if (players.size() < MAX_PLAYER) {
            players.add(p);
        } // end if
        return players.size();
    }// end addPlayer
    
    
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
    
    
    private void setIsland(Island island) {
        this.island = island;
    }
    
    
    /**
     * @return the players
     */
    public Collection<Player> getPlayers() {
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
    
    
    public boolean isStarted() {
        return started;
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
    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
}