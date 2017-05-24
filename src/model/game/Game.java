package model.game;

import java.util.Collection;

import model.player.Player;



public class Game {

    /**
     * @return the treasures
     */
    public Collection<Treasure> getTreasures() {
	return treasures;
    }

    /**
     * @param treasures the treasures to set
     */
    public void setTreasures(Collection<Treasure> treasures) {
	this.treasures = treasures;
    }

    /**
     * @return the island
     */
    public Island getIsland() {
	return island;
    }

    /**
     * @param island the island to set
     */
    public void setIsland(Island island) {
	this.island = island;
    }

    /**
     * @return the players
     */
    public Collection<Player> getPlayers() {
	return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(Collection<Player> players) {
	this.players = players;
    }

    /**
     * @return the treasureDeck
     */
    public Deck getTreasureDeck() {
	return treasureDeck;
    }

    /**
     * @param treasureDeck the treasureDeck to set
     */
    public void setTreasureDeck(Deck treasureDeck) {
	this.treasureDeck = treasureDeck;
    }

    /**
     * @return the floodDeck
     */
    public Deck getFloodDeck() {
	return floodDeck;
    }

    /**
     * @param floodDeck the floodDeck to set
     */
    public void setFloodDeck(Deck floodDeck) {
	this.floodDeck = floodDeck;
    }

    /**
     * @param currentPlayer the currentPlayer to set
     */
    public void setCurrentPlayer(Player currentPlayer) {
	this.currentPlayer = currentPlayer;
    }

    /**
     * @return the gameID
     */
    public Integer getGameID() {
	return gameID;
    }

    /**
     * @param gameID the gameID to set
     */
    public void setGameID(Integer gameID) {
	this.gameID = gameID;
    }
    
    private Collection<Treasure> treasures;
    private Island               island;
    private Collection<Player>   players;
    private Deck                 treasureDeck;
    private Deck                 floodDeck;
    private Player               currentPlayer;
    private Integer              gameID;
    
    
    public Game getcurrentGame() {
        // TODO - implement Game.getcurrentGame
        throw new UnsupportedOperationException();
    }
    
    
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    
}