package model.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import model.adventurers.AdventurerType;
import model.player.Player;
import util.message.InGameAction;



public class Game {
    private final static Integer MAX_PLAYER = 4;
    private final static Integer MIN_PLAYER = 2;
    private ArrayList<Treasure>  treasures;
    private Island               island;
    private Collection<Player>   players;
    private Deck                 treasureDeck;
    private Deck                 floodDeck;
    private Player               currentPlayer;
    private boolean              started;
    
    private InGameAction currentAction;
    
    
    /**
     * get a random adventurer if necessary for players
     * 
     * @author nihil
     */
    private void randomAdventurer() {
        ArrayList<AdventurerType> restAdv = new ArrayList<>(Arrays.asList(AdventurerType.values()));
        restAdv.remove(AdventurerType.RANDOM);
        for (Player player : players) {
            restAdv.remove(player.getAdventurer().getADVENTURER_TYPE());
        } // end for
        Collections.shuffle(restAdv);
        int i = 0;
        for (Player player : players) {
            if (player.getAdventurer().getADVENTURER_TYPE().equals(AdventurerType.RANDOM)) {
                player.setCurrentAdventurer(restAdv.get(i).getClassFor(player));
                i++;
            } // end if
        } // end for
    }// end randomGame
    
    
    /**
     * @return the possibleActions
     */
    public ArrayList<InGameAction> getPossibleActions() {
        
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
     // FIXME : add min check
    
    
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