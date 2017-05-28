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
        return currentAdventurer;
    }
    
}