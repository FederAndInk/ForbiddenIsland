package view.pause;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.LogType;
import util.Parameters;
import util.message.MainAction;
import util.message.MainMessage;



public class PausePanel extends JPanel {
    
    private JPanel  mainPanel;
    private JPanel  optionPanel;
    private JButton resume;
    private JButton save;
    private JButton load;
    private JButton score;
    private JButton abandon;
    private JButton quitter;
    private JButton recommencer;
    
    
    public PausePanel() {
        initComponent();
    }
    
    
    public void initComponent() {
        setLayout(new BorderLayout());
        setBackground(Color.pink);
        mainPanel = new JPanel(new GridLayout(6, 1));
        optionPanel = new JPanel(new BorderLayout());
        optionPanel.setBackground(Color.CYAN);
        resume = new JButton("Resume");
        save = new JButton("Save");
        load = new JButton("load");
        score = new JButton("Score");
        abandon = new JButton("abandonner");
        quitter = new JButton("Quitter");
        recommencer = new JButton("Recommencer");
        
        add(optionPanel);
        add(new JPanel(), BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.WEST);
        
        optionPanel.add(mainPanel, BorderLayout.CENTER);
        optionPanel.add(new JPanel(), BorderLayout.NORTH);
        optionPanel.add(new JPanel(), BorderLayout.EAST);
        optionPanel.add(new JPanel(), BorderLayout.WEST);
        optionPanel.add(new JPanel(), BorderLayout.SOUTH);
        add(quitter, BorderLayout.SOUTH);
        quitter.setActionCommand("quit");
        
        mainPanel.add(resume);
        resume.setActionCommand("resume");
        mainPanel.add(save);
        save.setActionCommand("save");
        mainPanel.add(load);
        load.setActionCommand("load");
        mainPanel.add(score);
        score.setActionCommand("score");
        mainPanel.add(abandon);
        abandon.setActionCommand("abandon");
        mainPanel.add(recommencer);
        recommencer.setActionCommand("recommencer");
        
        ActionListener actionListener = new Listeners();
        abandon.addActionListener(actionListener);
        quitter.addActionListener(actionListener);
        resume.addActionListener(actionListener);
        save.addActionListener(actionListener);
        load.addActionListener(actionListener);
        score.addActionListener(actionListener);
        recommencer.addActionListener(actionListener);
    }
    
    private class Listeners extends Observable implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            switch (e.getActionCommand()) {
            case "resume":
                Parameters.printLog("RESUME", LogType.INFO);
                ((CardLayout) getParent().getLayout()).show(getParent(), getName());
                break;
            case "save":
                Parameters.printLog("SAVE", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SAVE_GAME, null));
                break;
            case "load":
                Parameters.printLog("LOAD", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.LOAD_GAME, null));
                break;
            case "score":
                Parameters.printLog("SCORE", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SCORES, null));
                break;
            case "abandon":
                Parameters.printLog("ABANDON", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.ABANDON, null));
                break;
            case "recommencer":
                Parameters.printLog("RECOMMENCER", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.BEGIN_GAME, null));
                break;
            case "quit":
                Parameters.printLog("quitter", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.BEGIN_GAME, null));
                break;
            }
            clearChanged();
        }
    }
}
