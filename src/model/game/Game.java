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
    
    
    public Game getcurrentGame() {
        // TODO - implement Game.getcurrentGame
        throw new UnsupportedOperationException();
    }
    
    
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
    
}