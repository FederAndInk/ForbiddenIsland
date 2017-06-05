package view.board;

import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import model.game.Island;
import model.game.Site;



/**
 * the panel for the board gestion
 * you have to {@link #initGrid(ArrayList) before #setVisible(boolean)}
 * 
 * @author nihil
 *
 */
public class BoardPanel extends JPanel {
    private JFrame parentFrame;
    SpringLayout   layout;
    JPanel         gridPane;
    /**
     * the board size : a value between 0 to 1 this is a portion of the GameView
     */
    private double boardSize;
    String         htmlNewLine = "</p><p class=\"second\">";
    
    
    public BoardPanel(JFrame parentFrame) {
        layout = new SpringLayout();
        setParentFrame(parentFrame);
        gridPane = new JPanel(new GridLayout(6, 6));
        setLayout(layout);
        setBoardSize(0.9);
        
        centerGrid();
    }
    
    
    /**
     * @author nihil
     *
     * @param sites
     * the list of tile' site in order row per row
     * @see Island#getGrid()
     */
    public void initGrid(ArrayList<Site> sites) {
        add(gridPane);
        // loop for set the tiles
        for (Site f : sites) {
            gridPane.add(f == null ? new JPanel() : new TilePanel(f));
        } // end for
    }
    
    
    protected JPanel getThis() {
        return this;
    }
    
    
    private void centerGrid() {
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
                gridPane.doLayout();
                
            }
            
            
            @Override
            public void componentHidden(ComponentEvent arg0) {
                // TODO Auto-generated method stub
                
            }
            
            
            @Override
            public void componentMoved(ComponentEvent arg0) {
                // TODO Auto-generated method stub
                
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
     * @param boardSize
     * the boardSize to set
     */
    private void setBoardSize(double boardSize) {
        this.boardSize = boardSize;
    }
}
