package view.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.adventurers.AdventurerType;
import util.Parameters;



public class PlayerInfo extends JPanel {
    private PlayerIcon     icone;
    private JLabel         descr;
    private AdventurerType pawn;
    
    
    public PlayerInfo(AdventurerType pawn, boolean left) {
        this.pawn = pawn;
        
        setLayout(new GridLayout(1, 2));
        icone = new PlayerIcon(pawn);
        descr = new JLabel(pawn.getDescription(), SwingConstants.CENTER);
        descr.setFont(new Font(getFont().getFontName(), getFont().getStyle(), (int) (getFont().getSize() * 0.9)));
        if (left) {
            add(icone);
            add(descr);
        } else {
            add(descr);
            add(icone);
        } // end if
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
    
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        icone.setEnabled(enabled);
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
