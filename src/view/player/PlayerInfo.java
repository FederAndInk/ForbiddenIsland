package view.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.adventurers.AdventurerType;
import util.Parameters;



public class PlayerInfo extends JPanel {
    private PlayerIcon     icone;
    private JPanel         descr;
    private AdventurerType pawn;
    
    
    public PlayerInfo(AdventurerType pawn, boolean left) {
        this.pawn = pawn;
        
        setLayout(new GridLayout(1, 2));
        icone = new PlayerIcon(pawn);
        descr = new JPanel();
        if (left) {
            add(icone);
            add(descr);
        } else {
            add(descr);
            add(icone);
        } // end if
        setBorder(BorderFactory.createLineBorder(Color.red));
        
    }
    
    
    public void setCurrent(boolean current) {
        Graphics g;
        if (current = true) {
            
            try {
                BufferedImage bImage = ImageIO.read(new File(pawn.getIconSelect()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }// end name
    
    
    @Override
    public Dimension getPreferredSize() {
        Dimension dim = Parameters.appSize;
        return new Dimension((int) (dim.getWidth() * 0.1), (int) (dim.getHeight() * 0.1));
    }
    
    
    /**
     * @return the pawn
     */
    public AdventurerType getPawn() {
        return pawn;
    }
}
