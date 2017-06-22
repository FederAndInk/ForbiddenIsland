package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import util.LogType;
import util.Parameters;



public class JPanelWorkInProgress extends JPanel {
    public JPanelWorkInProgress() {
        super();
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Parameters.printLog("Paint Main picture", LogType.GRAPHICS);
        try {
            BufferedImage bImage = ImageIO.read(new File(Parameters.RESOURCES + "Work_In_Progress.png"));
            g.drawImage(bImage, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
