package main;

import model.game.Island;
import util.Parameters;
import view.board.GameView;



public class Main {
    
    public static void main(String[] args) {
        Parameters.initialParameters();
        
        GameView gameView = new GameView();
        
        Island island = new Island();
        
        gameView.setBoard(island.getSites());

        
        gameView.setVisible(true);
        
    }
    
}
