package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.game.Site;



public class GameView extends JFrame {
    JPanel     mainPane;
    BoardPanel gamePane;
    
    
    public GameView() {
        mainPane = new JPanel(new BorderLayout());
        gamePane = new BoardPanel(this);
        getContentPane().add(mainPane);
        mainPane.add(gamePane, BorderLayout.CENTER);
        
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setBoard(ArrayList<Site> board) {
        gamePane.initGrid(board);
    }
}
