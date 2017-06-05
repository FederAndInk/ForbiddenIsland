/**
 * 
 */
package view.board;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.game.TileState;
import util.Parameters;



/**
 * @author nihil
 *
 */
public class TextTile extends JLabel {
    
    private static final String LABEL_EXT     = "@2x.png";
    private static final String LABEL_ICON    = Parameters.TILES + "extra/tile_text_bg";
    private static final String LABEL_ICON_FL = Parameters.TILES + "extra/tile_text_bg_fools_landing";
    private static final float  TEXT_SIZE     = (float) 0.15;
    private TileState           state;
    
    
    /**
     * @author nihil
     *
     */
    public TextTile(String text) {
        super(text, SwingConstants.CENTER);
        setState(TileState.DRIED);
        initListeners();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initListeners() {
        addComponentListener(new ComponentListener() {
            
            @Override
            public void componentShown(ComponentEvent e) {
            }
            
            
            @Override
            public void componentResized(ComponentEvent e) {
                setFont(Parameters.DEFAULT_FONT.deriveFont((float) (getParent().getSize().getHeight() * TEXT_SIZE)));
            }
            
            
            @Override
            public void componentMoved(ComponentEvent e) {
            }
            
            
            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        paintLabelBg(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        super.paintComponent(g);
        
    }
    
    
    /**
     * @author nihil
     * @param g
     *
     */
    private void paintLabelBg(Graphics g) {
        try {
            BufferedImage bi;
            String path;
            if (getText().contains("Fools' Landing")) {
                path = LABEL_ICON_FL;
            } else {
                path = LABEL_ICON;
            } // end if
            if (state.equals(TileState.FLOODED)) {
                path += "_flood";
            } // end if
            path += LABEL_EXT;
            bi = ImageIO.read(new File(path));
            g.drawImage(bi, 0, 0, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    
    /**
     * @author nihil
     *
     * @param state
     */
    public void setState(TileState state) {
        this.state = state;
        repaint();
    }
}
