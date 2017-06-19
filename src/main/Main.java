package main;

import controller.MainController;
import model.game.SeaLevel;
import util.BoardType;



public class Main {
    
    public static void main(String[] args) {
        
        MainController mainController = new MainController();
        
        mainController.createGame(BoardType.HARD_TEST);
        
        mainController.getGameController().StartGame(SeaLevel.LEVEL2);
    }
    
}
