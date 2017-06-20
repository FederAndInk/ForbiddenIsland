package view.board;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import model.game.Coords;
import model.game.Island;
import model.game.Site;
import util.LogType;
import util.Parameters;



/**
 * the panel for the board gestion
 * you have to {@link #initGrid(ArrayList) before #setVisible(boolean)}
 * 
 * @author nihil
 *
 */
public class BoardPanel extends JPanel {
    private JFrame       parentFrame;
    private SpringLayout layout;
    private JPanel       gridPane;
    /**
     * the board size : a value between 0 to 1 this is a portion of the GameView
     */
    private double       boardSize;
    
    
    public BoardPanel(JFrame parentFrame) {
        super();
        gridPane = new JPanel(new GridLayout(6, 6, 3, 3));
        layout = new SpringLayout();
        setParentFrame(parentFrame);
        setLayout(layout);
        setBoardSize(0.9);
        
        initListeners();
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        Parameters.printLog("paint Board--------------------------", LogType.GRAPHICS);
        super.paintComponent(g);
    }
    
    
    /**
     * @author nihil
     *
     * @param sites
     * the list of tile' site in order row per row
     * @param observer
     * @see Island#getGrid()
     */
    public void initGrid(ArrayList<Site> sites, Observer observer) {
        add(gridPane);
        
        // loop for set the tiles
        int i = 0;
        int j = 0;
        JLayeredPane panel;
        for (Site f : sites) {
            Parameters.printLog(
                    "add " + (f == null ? "empty tile" : f.getName()) + " to board at (" + i + "," + j + ")",
                    LogType.GRAPHICS);
            
            panel = f == null ? new JLayeredPane() : new TilePanel(f, new Coords(j, i));
            if (panel instanceof TilePanel) {
                ((TilePanel) panel).addObs(observer);
            } // end if
            gridPane.add(panel);
            j++;
            if (j >= Island.GRID_SIZE.getCol()) {
                j = 0;
                i++;
            } // end if
        } // end for
        repaint();
        doLayout();
    }
    
    
    protected JPanel getThis() {
        return this;
    }
    
    
    private void initListeners() {
        /**
         * in order to resize the grid when the frame is resized
         * 
         */
        addComponentListener(new ComponentListener() {
            
            @Override
            public void componentShown(ComponentEvent e) {
            }
            
            
            @Override
            public void componentResized(ComponentEvent e) {
                Parameters.printLog("Componant " + getThis().getClass().getName() + " is resizing", LogType.GRAPHICS);
                
                // for the length of grid side (with multiplier to not take the entire space)
                int gridBord = (int) (getBoardSize()
                        * Integer.min(getParentFrame().getHeight(), getParentFrame().getWidth()));
                // to center the grid
                int x = (int) ((getSize().getWidth() - gridBord) / 2);
                int y = (int) ((getSize().getHeight() - gridBord) / 2);
                
                // gridPane.setSize(gridBord, gridBord);
                layout.putConstraint(SpringLayout.NORTH, gridPane, y, SpringLayout.NORTH, getThis());
                layout.putConstraint(SpringLayout.WEST, gridPane, x, SpringLayout.WEST, getThis());
                layout.putConstraint(SpringLayout.SOUTH, gridPane, -y, SpringLayout.SOUTH, getThis());
                layout.putConstraint(SpringLayout.EAST, gridPane, -x, SpringLayout.EAST, getThis());
                e.getComponent().doLayout();
            }
            
            
            @Override
            public void componentHidden(ComponentEvent arg0) {
            }
            
            
            @Override
            public void componentMoved(ComponentEvent arg0) {
            }
        });
    }
    
    
    JFrame getParentFrame() {
        return parentFrame;
    }
    
    
    void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }
    
    
    /**
     * @return the boardSize
     */
    public double getBoardSize() {
        return boardSize;
    }
    
    
    /**
     * @return the gridPane
     */
    public JPanel getGridPane() {
        return gridPane;
    }
    
    
    /**
     * @param boardSize
     * the boardSize to set
     */
    private void setBoardSize(double boardSize) {
        this.boardSize = boardSize;
    }
}
