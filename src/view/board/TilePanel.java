/**
 * 
 */
package view.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.game.Site;
import model.game.TileState;



/**
 * @author nihil
 *
 */
public class TilePanel extends JPanel {
    private TileState state;
    private Site      site;
    
    private static final float  TEXT_SIZE           = (float) 0.16;
    private static final Color  COLOR_BORD          = new Color(24, 214, 28);
    private static final Border ACTIVE_BORDER_HOVER = BorderFactory.createLineBorder(Color.GREEN, 4, false);
    private static final Border ACTIVE_BORDER_EXIT  = BorderFactory.createLineBorder(COLOR_BORD, 3, false);
    private static final Border INACTIVE_BORDER     = BorderFactory.createLineBorder(Color.DARK_GRAY, 2, false);
    private Font                font;
    
    
    /**
     * @author nihil
     *
     */
    public TilePanel(Site site) {
        this.site = site;
        init();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void init() {
        setState(TileState.DRIED);
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Treamd.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        initBackground(g);
    }
    
    
    /**
     * @author nihil
     * @param g
     *
     */
    private void initBackground(Graphics g) {
        try {
            BufferedImage bi = ImageIO.read(new File(site.getFile(state)));
            g.drawImage(bi, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
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
