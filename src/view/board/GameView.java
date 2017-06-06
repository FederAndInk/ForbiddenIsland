package view.board;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.adventurers.AdventurerType;
import model.game.Coords;
import model.game.Island;
import model.game.Site;
import util.LogType;
import util.Parameters;



public class GameView extends JFrame {
    private JPanel     mainPane;
    private BoardPanel gamePane;
    
    
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
    
    
    public TilePanel getTileG(Coords c) {
        TilePanel tilePanel = (TilePanel) gamePane.getGridPane()
                .getComponent(c.getY() * Island.GRID_SIZE.getX() + c.getX());
        Parameters.printLog("get " + tilePanel.getSite().getName() + " at " + c, LogType.ACCESS);
        return tilePanel;
    }// end
     // getTileG
    
    
    public void setEnabled(boolean b, Coords c) {
        getTileG(c).setEnabled(b);
    }
    
    
    public void movePawn(AdventurerType p, Coords current, Coords next) {
        
    }// end movePawn
    
    
    /**
     * @author nihil
     *
     */
    public void setBoard(ArrayList<Site> board) {
        gamePane.initGrid(board);
    }
}
