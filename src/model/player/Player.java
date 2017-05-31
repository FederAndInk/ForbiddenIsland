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
    
    
    public Player(String name) {
        setName(name);
    }
    
    
    /**
     * @return the currentGame
     */
    public Game getCurrentGame() {
        return currentGame;
    }
    
    
    /**
     * @param currentGame
     * the currentGame to set
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
    
    
    public void addGameStat(GameInfo gameStat) {
        gamesStat.add(gameStat);
    }// end addGameStat
    
    
    /**
     * @return the currentAdventurer
     */
    public Adventurer getCurrentAdventurer() {
        return currentAdventurer;
    }
    
    
    /**
     * @param currentAdventurer
     * the currentAdventurer to set
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
     * @param name
     * the name to set
     */
    private void setName(String name) {
        this.name = name;
    }
    
}
