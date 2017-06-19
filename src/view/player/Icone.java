package view.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import util.Parameters;



public class Icone extends JPanel {
    private JPanel player3Spe;
    private JPanel icone;
    private JPanel descr;
    
    
    public Icone() {
        player3Spe = new JPanel(new GridLayout(1, 2));
        icone = new JPanel();
        descr = new JPanel();
        icone.setBackground(Color.RED);
        descr.setBackground(Color.BLUE);
        player3Spe.add(descr);
        player3Spe.add(icone);
        
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        Dimension dim = Parameters.appSize;
        return new Dimension((int) (dim.getWidth() * 0.2), (int) (dim.getHeight() * 0.05));
    }
}
