package view.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.adventurers.AdventurerType;
import util.Parameters;



public class Icone extends JPanel {
    private JPanel         icone;
    private JPanel         descr;
    private Image          bImage;
    private AdventurerType pawn;
    
    
    public Icone() {
        setLayout(new GridLayout(1, 2));
        icone = new JPanel();
        descr = new JPanel();
        
        icone.setBackground(Color.RED);
        descr.setBackground(Color.BLUE);
        icone.add(new JLabel("   "));
        descr.add(new JLabel("   "));
        add(descr);
        try {
            bImage = ImageIO.read(new File(pawn.getPath()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        add(icone);
        setBorder(BorderFactory.createLineBorder(Color.red));
        
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        Dimension dim = Parameters.appSize;
        return new Dimension((int) (dim.getWidth() * 0.1), (int) (dim.getHeight() * 0.1));
    }
}
