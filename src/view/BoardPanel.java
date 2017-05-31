package view;

import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import view.testComponantPerso.CompCard;



public class BoardPanel extends JPanel {
    private JFrame parentFrame;
    SpringLayout   layout;
    JPanel         gridPane;
    String         htmlNewLine = "</p><p class=\"second\">";
    
    
    public BoardPanel(JFrame parentFrame) {
        layout = new SpringLayout();
        setParentFrame(parentFrame);
        
        setLayout(layout);
        initGrid();
        centerGrid();
    }
    
    
    private void initGrid() {
        gridPane = new JPanel(new GridLayout(6, 6));
        add(gridPane);
        
        // -----------------------to add the tiles
        File file = new File("./resources/tiles");
        ArrayList<File> files = new ArrayList<>();
        for (String f : file.list()) {
            if (f.contains("png") && !f.contains("flood") && !f.contains("extra")) {
                files.add(new File("./resources/tiles/" + f));
                System.out.println(files.get(files.size() - 1));
            } // end if
        } // end for
          // alea
        Collections.shuffle(files);
        int imgNb = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (isBord(i, j)) {
                    gridPane.add(new JPanel());
                } else {
                    try {
                        gridPane.add(new CompCard(files.get(imgNb), "Breakers" + htmlNewLine + "Bridge"));
                    } catch (FontFormatException | IOException e1) {
                        e1.printStackTrace();
                    }
                    imgNb++;
                } // end if
            } // end for
        } // end for
          // --------------------------------
        
    }// end
    
    
    private JPanel getThis() {
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
                int gridBord = (int) (0.90 * Integer.min(getParentFrame().getHeight(), getParentFrame().getWidth()));
                // to center the grid
                int x = (getWidth() - gridBord) / 2;
                int y = (getHeight() - gridBord) / 2;
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
}
