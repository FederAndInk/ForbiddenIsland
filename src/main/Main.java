package main;

import model.game.Coords;
import model.game.Island;
import model.game.TileState;
import view.board.GameView;



public class Main {
    
    public static void main(String[] args) {
        
        GameView gameView = new GameView();
        
        Island island = new Island();
        
        gameView.setBoard(island.getSites());
        
        gameView.setVisible(true);
        gameView.setEnabled(true, new Coords(1, 1));
        gameView.getTileG(new Coords(5, 2)).setState(TileState.SINKED);
        gameView.getTileG(new Coords(4, 2)).setState(TileState.FLOODED);
    }
    
}
