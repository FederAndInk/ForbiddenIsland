package view.player;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Parameters;



public class playerInventory extends JPanel {
    private JPanel inventory;
    
    
    public playerInventory() {
        inventory = new JPanel();
        inventory.add(new JLabel("Inventory"));
        
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        Dimension dim = Parameters.appSize;
        return new Dimension((int) (dim.getWidth() * 0.2), (int) (dim.getHeight() * 0.1));
    }
    
}
