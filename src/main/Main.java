package main;

import controller.GameController;
import model.adventurers.Diver;
import model.adventurers.Engineer;
import model.adventurers.Explorer;
import model.adventurers.Pilot;
import model.game.Game;
import model.player.Player;
import util.BoardType;



public class Main {
    
    public static void main(String[] args) {
        
        GameController gameController = new GameController(null);
        
        gameController.setCurrentGame(new Game(BoardType.HARD_TEST));
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Player p3 = new Player("p3");
        Player p4 = new Player("p4");
        gameController.getCurrentGame().addPlayer(new Diver(p1));
        gameController.getCurrentGame().addPlayer(new Explorer(p2));
        gameController.getCurrentGame().addPlayer(new Pilot(p3));
        gameController.getCurrentGame().addPlayer(new Engineer(p4));
        
        gameController.StartGame();
    }
    
}
