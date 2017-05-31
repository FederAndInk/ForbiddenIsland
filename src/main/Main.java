package main;

import javax.swing.JFrame;

import view.GameView;



public class Main {
    
    public static void main(String[] args) {
        GameView gameView = new GameView();
        gameView.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameView.setVisible(true);
    }
    
}
