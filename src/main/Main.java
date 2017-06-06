package main;

import controller.GameController;
import model.adventurers.Diver;
import model.adventurers.Explorer;
import model.game.Game;
import model.player.Player;



public class Main {
    
    public static void main(String[] args) {
        
        GameController gameController = new GameController(null);
        
        gameController.setCurrentGame(new Game());
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        gameController.getCurrentGame().addPlayer(p1, new Diver(p1));
        gameController.getCurrentGame().addPlayer(p2, new Explorer(p2));
        gameController.StartGame();
    }
    
}
