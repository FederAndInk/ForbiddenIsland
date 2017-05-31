/**
 * 
 */
package view.testComponantPerso;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SpringLayout;



/**
 * @author nihil
 *
 */
public class TestComponent {
    private static JPanel panel;
    
    
    public static void main(String[] args) throws FontFormatException, IOException {
        // frame->mainPane -> panel_1 -> panel -> tiles
        JFrame frame = new JFrame("Test");
        JPanel superPane = new JPanel(new CardLayout());
        JPanel mainPane = new JPanel(new BorderLayout());
        JPanel menuPane = new JPanel();
        // frame.addWindowStateListener(e -> {
        // int paneSize = (int) Math.min(frame.getSize().getHeight(), frame.getSize().getWidth());
        // panel.setSize(paneSize, paneSize);
        // });
        // for the font
        Font font;
        String htmlNewLine = "</p><p class=\"second\">";
        // in order to add the font
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        font = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Treamd.ttf"));
        genv.registerFont(font);
        
        frame.getContentPane().add(superPane);
        JButton returnTo = new JButton("Retourner");
        returnTo.addActionListener(e -> {
            ((CardLayout) superPane.getLayout()).show(superPane, "game");
        });
        
        JMenu mainMenu = new JMenu("Menu");
        frame.setJMenuBar(new JMenuBar());
        frame.getJMenuBar().add(mainMenu);
        
        menuPane.add(returnTo);
        superPane.add(mainPane, "game");
        superPane.add(menuPane, "menu");
        
        mainMenu.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            
            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            
            @Override
            public void mouseExited(MouseEvent e) {
                mainMenu.setArmed(false);
            }
            
            
            @Override
            public void mouseEntered(MouseEvent e) {
                mainMenu.setArmed(true);
            }
            
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // ((JMenuItem) (e.getComponent())).
                ((CardLayout) superPane.getLayout()).show(superPane, "menu");
                mainMenu.setArmed(false);
                mainMenu.setSelected(false);
            }
        });
        
        JPanel panel_1 = new JPanel();
        mainPane.add(panel_1, BorderLayout.CENTER);
        SpringLayout sl_panel_1 = new SpringLayout();
        panel_1.setLayout(sl_panel_1);
        
        /**
         * in order to resize the grid when the frame is resized
         * 
         */
        panel_1.addComponentListener(new ComponentListener() {
            
            @Override
            public void componentShown(ComponentEvent e) {
            }
            
            
            @Override
            public void componentResized(ComponentEvent e) {
                // for the length of grid side (with multiplier to not take the entire space)
                int gridBord = (int) (0.90 * Integer.min(frame.getHeight(), frame.getWidth()));
                // to center the grid
                int x = (panel_1.getWidth() - gridBord) / 2;
                int y = (panel_1.getHeight() - gridBord) / 2;
                // panel.setSize(gridBord, gridBord);
                sl_panel_1.putConstraint(SpringLayout.NORTH, panel, y, SpringLayout.NORTH, panel_1);
                sl_panel_1.putConstraint(SpringLayout.WEST, panel, x, SpringLayout.WEST, panel_1);
                sl_panel_1.putConstraint(SpringLayout.SOUTH, panel, -y, SpringLayout.SOUTH, panel_1);
                sl_panel_1.putConstraint(SpringLayout.EAST, panel, -x, SpringLayout.EAST, panel_1);
                e.getComponent().doLayout();
                panel.doLayout();
            }
            
            
            @Override
            public void componentMoved(ComponentEvent e) {
            }
            
            
            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
        
        panel = new JPanel(new GridLayout(6, 6));
        panel_1.add(panel);
        
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
                    panel.add(new JPanel());
                } else {
                    try {
                        panel.add(new CompCard(files.get(imgNb), "Breakers" + htmlNewLine + "Bridge"));
                        // panel.add(new CompCard(files.get(imgNb), "Breakers"));
                    } catch (FontFormatException | IOException e1) {
                        e1.printStackTrace();
                    }
                    imgNb++;
                } // end if
            } // end for
        } // end for
          // --------------------------------
        
        panel.getComponent(2).setEnabled(true);
        int size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.5);
        frame.setSize(size, size);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        
    }// end
    
    
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
