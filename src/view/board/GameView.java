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
        super();
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
        setSize(Parameters.appSize);
        if (Parameters.fullscreen) {
            setUndecorated(true);
            setExtendedState(MAXIMIZED_BOTH);
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
