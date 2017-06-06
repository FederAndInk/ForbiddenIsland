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
    
    
    public JPanel getTileG(Coords c) {
        JPanel tilePanel = (JPanel) gamePane.getGridPane().getComponent(c.getRow() * Island.GRID_SIZE.getCol() + c.getCol());
        if (!(tilePanel instanceof TilePanel)) {
            Parameters.printLog("get a JPanel", LogType.ACCESS);
        } else {
            Parameters.printLog("get " + ((TilePanel) tilePanel).getSite().getName() + " at " + c, LogType.ACCESS);
        } // end if
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
