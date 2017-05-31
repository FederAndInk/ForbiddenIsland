package tests;

import java.util.ArrayList;
import java.util.Observable;

import model.adventurers.Diver;
import model.game.Game;
import model.game.Island;
import model.game.Tile;
import model.player.Player;



public class Test extends Observable {
    
    public static void main(String[] args) throws InterruptedException {
        Island island = new Island();
        Tile grid[][] = island.getGrid();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(grid[i][j] == null ? " " : "*");
            } // end for
            System.out.println();
        } // end for
        Game game = new Game();
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        game.addPlayer(p1);
        game.addPlayer(p2);
        p1.setCurrentAdventurer(new Diver(p1));
        p2.setCurrentAdventurer(new Diver(p2));
        game.initGame();
        Tile t = game.getIsland().getGrid()[2][2];
        p1.getAdventurer().setCurrentTile(t);
        p2.getAdventurer().setCurrentTile(t);
        ArrayList<Tile> ts = p1.getAdventurer().getReachableTiles();
        for (Tile tile : ts) {
            System.out.println(tile.getSite());
        } // end for
    }
    
}
