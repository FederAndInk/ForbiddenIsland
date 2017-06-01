package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.game.Island;
import model.game.Site;
import model.game.Tile;
import view.GameView;



public class Main {
    
    public static void main(String[] args) {
        // -------------------------Font
        {
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font;
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Treamd.ttf"));
                genv.registerFont(font);
            } catch (FontFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // -------------------------End Font
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
