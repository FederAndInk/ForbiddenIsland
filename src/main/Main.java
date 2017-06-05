package main;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.game.Island;
import model.game.Site;
import model.game.Tile;
import util.Parameters;
import view.board.GameView;



public class Main {
    
    public static void main(String[] args) {
        Parameters.initialParameters();
        
        GameView gameView = new GameView();
        int size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.5);
        gameView.setSize(size, size);
        gameView.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        Island island = new Island();
        ArrayList<Site> board = new ArrayList<>();
        Tile[][] grid = island.getGrid();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Tile tile = grid[i][j];
                board.add(tile == null ? null : tile.getSite());
            } // end for
        } // end for
        
        gameView.setBoard(board);
        
        gameView.setVisible(true);
        
    }
    
}
