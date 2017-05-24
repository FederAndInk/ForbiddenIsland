package model.game;

import java.util.Collection;

import model.player.Player;



public class Game {
    
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