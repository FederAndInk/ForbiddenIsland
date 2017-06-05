/**
 * 
 */
package view.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.game.Site;
import model.game.TileState;
import util.Parameters;



/**
 * @author nihil
 *
 */
public class TilePanel extends JPanel {
    private TileState state;
    private Site      site;
    
    private TextTile text;
    
    private static final Border ACTIVE_BORDER_HOVER = BorderFactory.createLineBorder(Color.GREEN, 4, true);
    private static final Border ACTIVE_BORDER_EXIT  = BorderFactory.createLineBorder(new Color(24, 154, 28), 3, true);
    private static final Border INACTIVE_BORDER     = BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true);
    
    
    /**
     * @author nihil
     *
     */
    public TilePanel(Site site) {
        super(new BorderLayout());
        this.site = site;
        
        init();
        initListeners();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void init() {
        text = new TextTile(site.getNameStyle());
        
        setEnabled(false);
        setState(TileState.DRIED);
        add(text, BorderLayout.SOUTH);
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Parameters.printLog("paint Tile " + site);
        paintBackground(g);
    }
    
    
    /**
     * @author nihil
     * @param g
     *
     */
    private void paintBackground(Graphics g) {
        try {
            BufferedImage bi = ImageIO.read(new File(site.getFile(state)));
            if (site.isDoubleLigned()) {
                Parameters.printLog("Draw double ligned image");
                g.drawImage(bi, 2, 2, (int) getSize().getWidth(), (int) (getSize().getHeight() * 0.9), this);
            } else {
                g.drawImage(bi, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
            } // end if
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initListeners() {
        addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            
            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (isEnabled()) {
                    setBorder(ACTIVE_BORDER_EXIT);
                } // end if
            }
            
            
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled()) {
                    setBorder(ACTIVE_BORDER_HOVER);
                } // end if
            }
            
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }
    
    
    /**
     * enable or not the tile (if the player can go there) </br>
     * and set the correct border color (gray or green)
     * 
     * @see javax.swing.AbstractButton#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        if (b) {
            setBorder(ACTIVE_BORDER_EXIT);
            setBackground(null);
        } else {
            setBorder(INACTIVE_BORDER);
            setBackground(Color.GRAY);
        } // end if
    }
    
    
    /**
     * @param state
     * the state to set
     */
    public void setState(TileState state) {
        this.state = state;
        repaint();
    }
    
}
