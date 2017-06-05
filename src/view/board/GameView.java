package view.board;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.game.Site;
import util.Parameters;



public class GameView extends JFrame {
    JPanel     mainPane;
    BoardPanel gamePane;
    
    
    public GameView() {
        mainPane = new JPanel(new BorderLayout());
        gamePane = new BoardPanel(this);
        getContentPane().add(mainPane);
        mainPane.add(gamePane, BorderLayout.CENTER);
        setScreen();
        
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setScreen() {
        if (Parameters.fullscreen) {
            setUndecorated(true);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        } else {
            setUndecorated(false);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
        } // end if
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setBoard(ArrayList<Site> board) {
        gamePane.initGrid(board);
    }
}
