package model.player;

import java.util.Collection;

import model.adventurers.Adventurer;
import model.game.Game;
import util.GameInfo;



public class Player {
    
    private Game                 currentGame;
    private Collection<GameInfo> gamesStat;
    private Adventurer           currentAdventurer;
    private String               name;
    
    
    public Adventurer getAdventurer() {
        // TODO - implement Player.getAdventurer
        throw new UnsupportedOperationException();
    }

    /**
     * @return the currentGame
     */
    public Game getCurrentGame() {
	return currentGame;
    }

    /**
     * @param currentGame the currentGame to set
     */
    public void setCurrentGame(Game currentGame) {
	this.currentGame = currentGame;
    }

    /**
     * @return the gamesStat
     */
    public Collection<GameInfo> getGamesStat() {
	return gamesStat;
    }

    /**
     * @param gamesStat the gamesStat to set
     */
    public void setGamesStat(Collection<GameInfo> gamesStat) {
	this.gamesStat = gamesStat;
    }

    /**
     * @return the currentAdventurer
     */
    public Adventurer getCurrentAdventurer() {
	return currentAdventurer;
    }

    /**
     * @param currentAdventurer the currentAdventurer to set
     */
    public void setCurrentAdventurer(Adventurer currentAdventurer) {
	this.currentAdventurer = currentAdventurer;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
	this.name = name;
    }
    
}