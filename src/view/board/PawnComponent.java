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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import model.adventurers.AdventurerType;
import util.FIGraphics;
import util.LogType;
import util.Parameters;
import util.message.InGameAction;
import util.message.InGameMessage;



/**
 * @author nihil
 *
 */
public class PawnComponent extends JComponent {
    private float          pawnSize = (float) 0.4;
    private AdventurerType pawn;
    private boolean        selected;
    
    
    /**
     * @author nihil
     * @param pawn
     * give the {@link AdventurerType} and the maagic trick will do effect
     * 
     */
    public PawnComponent(AdventurerType pawn) {
        this.pawn = pawn;
        setSelected(false);
        initListeners();
    }
    
    
    /**
     * @see java.awt.Container#doLayout()
     */
    @Override
    public void doLayout() {
        super.doLayout();
    }
    
    
    /**
     * @author nihil
     * @param pawn
     * give the {@link AdventurerType} and the maagic trick will do effect
     * 
     */
    public PawnComponent(AdventurerType pawn, Observer obs) {
        this.pawn = pawn;
        setSelected(false);
        initListeners(obs);
    }
    
    
    protected void initSize() {
        Parameters.printLog("Resizing players : " + getClass().getSimpleName(), LogType.GRAPHICS);
        BufferedImage bImage;
        try {
            bImage = ImageIO.read(new File(pawn.getPath()));
            int dimBase = (int) (pawnSize * getParent().getSize().getHeight());
            int dimAjust = dimBase * bImage.getWidth() / bImage.getHeight();
            setPreferredSize(new Dimension(dimAjust, dimBase));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// end
    
    
    /**
     * @author nihil
     * @param obs
     *
     */
    private void initListeners(Observer obs) {
        initListeners();
        MlTile mlTile = new MlTile();
        addMouseListener(mlTile);
        mlTile.addObserver(obs);
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initListeners() {
        
        addComponentListener(new ComponentListener() {
            
            @Override
            public void componentShown(ComponentEvent e) {
                initSize();
                e.getComponent().doLayout();
                e.getComponent().repaint();
            }
            
            
            @Override
            public void componentResized(ComponentEvent e) {
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
     * @see javax.swing.JComponent#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled && !isSelected()) {
            setBorder(FIGraphics.ACTIVE_BORDER_EXIT);
        } else if (!enabled) {
            setBorder(null);
            setSelected(false);
        } // end if
    }
    
    
    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            setBorder(FIGraphics.ACTIVE_BORDER_SELECTED);
        } else {
            setBorder(null);
        } // end if
    }
    
    
    public AdventurerType getPawn() {
        return pawn;
    }
    
    
    /**
     * @author nihil
     *
     * @param size
     */
    protected void setPawnSize(float size) {
        this.pawnSize = size;
    }
    
    // the inner class for an event listener
    protected class MlTile extends Observable implements MouseListener {
        
        /**
         * @author nihil
         *
         */
        public MlTile() {
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
            if (isEnabled() && !isSelected()) {
                setBorder(FIGraphics.ACTIVE_BORDER_EXIT);
            } else if (isEnabled()) {
                setBorder(FIGraphics.ACTIVE_BORDER_SELECTED);
            } // end if
        }
        
        
        @Override
        public void mouseEntered(MouseEvent e) {
            if (isEnabled()) {
                setBorder(FIGraphics.ACTIVE_BORDER_HOVER);
            } // end if
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (isEnabled()) {
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.SELECT_PAWN, getPawn()));
                clearChanged();
            } // end if
        }
    }
}
