/**
 * 
 */
package view.testComponantPerso;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;



/**
 * @author nihil
 * a componant test for tiles
 */
public class CompCard extends JButton {
    private File               tilePath;
    private JLabel             nameLabel;
    private static final float TEXT_SIZE         = (float) 0.16;
    String                     htmlStyle;
    String                     htmlB;
    String                     htmlE             = "</p></body></html>";
    /**
     * color of the background
     */
    private Color              color;
    private Color              colorBord         = new Color(24, 214, 28);
    private Border             activeBorderHover = BorderFactory.createLineBorder(Color.GREEN, 4, false);
    private Border             activeBorderExit  = BorderFactory.createLineBorder(colorBord, 3, false);
    private Border             inactiveBorder    = BorderFactory.createLineBorder(Color.DARK_GRAY, 2, false);
    private Font               font;
    
    
    /**
     * @author nihil
     * @param f
     * is the file to the tile image
     * but later, this will be an enum with all infos (name, path)
     * @throws IOException
     * @throws FontFormatException
     *
     */
    public CompCard(File f, String name) throws FontFormatException, IOException {
        setName(name);
        font = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Treamd.ttf"));
        // The HolyGrale !! SEE that is awesome !!!vvvvvvv
        nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setForeground(Color.BLACK);
        setTilePath(f);
        setEnabled(false);
        /**
         * for changing the border when a tile is active and hover or not
         */
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
                    setBorder(activeBorderExit);
                } // end if
            }
            
            
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled()) {
                    setBorder(activeBorderHover);
                } // end if
            }
            
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        initBackgroundImage(g2);
        initName();
        // super.paintComponent(g);
    }
    
    
    private void initName() {
        // name2Label.setFont(font.deriveFont((float) (getSize().getHeight() * TEXT_SIZE)));
        add(nameLabel);
        htmlStyle = "<style>body{margin: auto;text-align: center;} p.second{margin-top:-8;margin-bottom: -2}</style>";
        htmlB = "<html><head>" + htmlStyle + "</head><body><p>";
        // nameLabel.setMaximumSize(
        // new Dimension((int) (getSize().getWidth() * 0.8), (int) (getSize().getHeight() * 0.25)));
        nameLabel.setFont(font.deriveFont((float) (getSize().getHeight() * TEXT_SIZE)));
        nameLabel.setText(htmlB + getName() + htmlE);
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameLabel.setAlignmentY(BOTTOM_ALIGNMENT);
        System.out.println(getSize().getWidth() + " : " + nameLabel.getSize().getWidth());
        // FIXME:to remove !!
        nameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // nameLabel.setVerticalAlignment(ABORT);
        // namePane.setAlignmentX(CENTER_ALIGNMENT);
        // namePane.setAlignmentY(BOTTOM_ALIGNMENT);
        // if (name2 == null) {
        // nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        // nameLabel.setAlignmentY(BOTTOM_ALIGNMENT);
        //
        // } else {
        //
        // nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        // float alignY = (float) ((getSize().getHeight() - name2Label.getSize().getHeight()) / getSize().getHeight());
        // // nameLabel.setAlignmentY(alignY);
        // nameLabel.setAlignmentY((float) (BOTTOM_ALIGNMENT - 0.15));
        // System.out.println(alignY);
        //
        // name2Label.setAlignmentX(CENTER_ALIGNMENT);
        // name2Label.setAlignmentY(BOTTOM_ALIGNMENT);
        // name2Label.setFont(font.deriveFont((float) (getSize().getHeight() * TEXT_SIZE)));
        //
        // } // end if
    }// end initName
    
    
    private void initBackgroundImage(Graphics2D g2) {
        BufferedImage bi;
        try {
            if (tilePath.exists()) {
                bi = ImageIO.read(tilePath);
                g2.drawImage(bi, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), color, this);
                
            } // end if
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }// end initBackgroundImage
    
    
    /**
     * @see javax.swing.JComponent#setBackground(java.awt.Color)
     */
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        color = bg;
        repaint();
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
            setBorder(activeBorderExit);
        } else {
            setBorder(inactiveBorder);
            setBackground(Color.GRAY);
        } // end if
    }
    
    
    /**
     * @param tilePath
     * the tilePath to set
     */
    private void setTilePath(File tilePath) {
        this.tilePath = tilePath;
    }
}
