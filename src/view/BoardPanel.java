package view;

import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import model.game.Island;
import model.game.Site;
import view.testComponantPerso.CompCard;



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
            try {
                gridPane.add(f == null ? new JPanel() : new CompCard(new File(f.getFile()), f.getName()));
            } catch (FontFormatException | IOException e1) {
                e1.printStackTrace();
            }
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
     * **––**</br>
     * *––––*</br>
     * ––––––</br>
     * *––––*</br>
     * **––**</br>
     * 
     * @author nihil
     * 
     * 
     * 
     * @param i
     * (0-5)
     * the row
     * @param j
     * (0-5)
     * the column
     * @return true if i,j is in the corner (where the star is)
     */
    public static boolean isBord(int i, int j) {
        // @formatter:off
        return (i == 0) && (j == 0 || j == 1) || (i == 1 && j == 0) || (i == 5) && (j == 0 || j == 1)
                || (i == 4 && j == 0) || (i == 0) && (j == 4 || j == 5) || (i == 1 && j == 5)
                || (i == 5) && (j == 4 || j == 5) || (i == 4 && j == 5);
        // @formatter:on
    }// end
     // isbord
    
    
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
