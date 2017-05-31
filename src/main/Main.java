package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import view.GameView;



public class Main {
    
    public static void main(String[] args) {
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Treamd.ttf"));
            genv.registerFont(font);
        } catch (FontFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        GameView gameView = new GameView();
        int size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.5);
        gameView.setSize(size, size);
        gameView.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameView.setVisible(true);
        
    }
    
}
