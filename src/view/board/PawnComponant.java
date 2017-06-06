/**
 * 
 */
package view.board;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import model.adventurers.AdventurerType;
import util.LogType;
import util.Parameters;



/**
 * @author nihil
 *
 */
public class PawnComponant extends JComponent {
    private float          pawnSize = (float) 0.25;
    private AdventurerType pawn;
    
    
    /**
     * @author nihil
     *
     */
    public PawnComponant(AdventurerType pawn) {
        this.pawn = pawn;
        initListeners();
    }
    
    
    protected void initSize() {
        BufferedImage bImage;
        try {
            bImage = ImageIO.read(new File(pawn.getPath()));
            int dimBase = (int) (pawnSize * getParent().getSize().getWidth());
            int dimAjust = dimBase * bImage.getHeight() / bImage.getWidth();
            setPreferredSize(new Dimension(dimBase, dimAjust));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// end
    
    
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
                Parameters.printLog("Resizing players : " + e.getComponent().getClass().getSimpleName(),
                        LogType.GRAPHICS);
                initSize();
                e.getComponent().doLayout();
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
        super.paintComponent(g);
        Parameters.printLog("Paint Player", LogType.GRAPHICS);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        try {
            BufferedImage bImage = ImageIO.read(new File(pawn.getPath()));
            g2.drawImage(bImage, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @author nihil
     *
     * @param size
     */
    protected void setPawnSize(float size) {
        this.pawnSize = size;
    }
}
