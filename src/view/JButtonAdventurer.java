package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import model.adventurers.AdventurerType;
import util.LogType;
import util.Parameters;



public class JButtonAdventurer extends JButton {
    private AdventurerType type;
    
    
    public JButtonAdventurer(AdventurerType type) {
        super();
        this.type = type;
    }
    
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Parameters.printLog("Paint Main picture", LogType.INFO);
        try {
            Parameters.printLog(type.getIconEnable(), LogType.INFO);
            BufferedImage bImage = ImageIO.read(new File(isEnabled() ? type.getIconEnable() : type.getIconDisable()));
            g.drawImage(bImage, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public AdventurerType getType() {
        return type;
    }
}
