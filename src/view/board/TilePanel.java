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
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.adventurers.AdventurerType;
import model.game.Coords;
import model.game.Site;
import model.game.TileState;
import util.LogType;
import util.Parameters;



/**
 * @author nihil
 *
 */
public class TilePanel extends JPanel {
    private TileState    state;
    private final Site   site;
    private final Coords pos;
    
    private PlayerPanel playerPanel;
    private TextTile    text;
    
    private static final Border ACTIVE_BORDER_HOVER = BorderFactory.createLineBorder(Color.GREEN, 5, true);
    private static final Border ACTIVE_BORDER_EXIT  = BorderFactory.createLineBorder(new Color(24, 184, 28), 4, true);
    private static final Border INACTIVE_BORDER     = BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true);
    
    
    /**
     * @author nihil
     *
     */
    public TilePanel(Site site, Coords c) {
        super(new BorderLayout());
        this.site = site;
        this.pos = c;
        
        init();
        initListeners();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void init() {
        text = new TextTile(site.getNameStyle());
        playerPanel = new PlayerPanel();
        
        setEnabled(false);
        setState(TileState.DRIED);
        add(text, BorderLayout.SOUTH);
        add(playerPanel, BorderLayout.CENTER);
    }
    
    
    /**
     * @author nihil
     *
     */
    public void addPawn(AdventurerType pawn) {
        playerPanel.addPawn(pawn);
    }
    
    
    /**
     * @author nihil
     *
     */
    public void removePawn(AdventurerType pawn) {
        playerPanel.removePawn(pawn);
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Parameters.printLog("paint Tile " + site, LogType.GRAPHICS);
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
                Parameters.printLog("Draw double ligned image", LogType.GRAPHICS);
                g.drawImage(bi, 2, 2, (int) getSize().getWidth(), (int) (getSize().getHeight() * 0.85), this);
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
        addMouseListener(new mlTile());
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
     * @return the site
     */
    public Site getSite() {
        return site;
    }
    
    
    /**
     * @param state
     * the state to set
     */
    public void setState(TileState state) {
        this.state = state;
        text.setState(state);
        if (state.equals(TileState.SINKED)) {
            remove(text);
        }
        repaint();
    }
    
    // the inner class for an event listener
    private class mlTile extends Observable implements MouseListener {
        
        /**
         * @author nihil
         *
         */
        public mlTile() {
            super();
        }
        
        
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
    }
}
