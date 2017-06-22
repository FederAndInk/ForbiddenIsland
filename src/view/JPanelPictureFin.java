package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import util.LogType;
import util.Parameters;



public class JPanelPictureFin extends JPanel {
    private Boolean win;
    
    
    public JPanelPictureFin() {
        super();
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        if (win) {
            super.paintComponent(g);
            Parameters.printLog("Paint Main picture", LogType.GRAPHICS);
            try {
                BufferedImage bImage = ImageIO.read(new File(Parameters.RESOURCES + "win.png"));
                g.drawImage(bImage, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            super.paintComponent(g);
            Parameters.printLog("Paint Main picture", LogType.GRAPHICS);
            try {
                BufferedImage bImage = ImageIO.read(new File(Parameters.RESOURCES + "lose.jpg"));
                g.drawImage(bImage, 2, 2, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public void setWin(Boolean win) {
        this.win = win;
    }
}
