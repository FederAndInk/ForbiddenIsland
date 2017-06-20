package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.LogType;
import util.Parameters;



public class JPanelPicture extends JPanel {
    private JLabel background;
    
    
    public JPanelPicture() {
        super();
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Parameters.printLog("Paint Main picture", LogType.INFO);
        try {
            BufferedImage bImage = ImageIO.read(new File(Parameters.RESOURCES + "background.jpg"));
            g.drawImage(bImage, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
