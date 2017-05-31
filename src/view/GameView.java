package view;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GameView extends JFrame {
    JPanel mainPane;
    JPanel gamePane;
    
    
    public GameView() {
        mainPane = new JPanel();
        gamePane = new BoardPanel(this);
        
        mainPane.add(gamePane);
        
    }
    
    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        gamePane.setVisible(true);
    }
}
