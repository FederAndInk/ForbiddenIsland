package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.adventurers.AdventurerType;
import util.LogType;
import util.Parameters;
import util.message.MainAction;
import util.message.MainMessage;



public class JPanelSelectHero extends JPanel {
    
    private JButton  diver;
    private JButton  explorer;
    private JButton  engineer;
    private JButton  messenger;
    private JButton  navigator;
    private JButton  pilot;
    private JButton  random;
    private MainView mainView;
    
    private static final String DIVER     = "DIVER";
    private static final String EXPLORER  = "EXPLORER";
    private static final String ENGINEER  = "ENGINEER";
    private static final String MESSENGER = "MESSENGER";
    private static final String PILOT     = "PILOT";
    private static final String RANDOM    = "RANDOM";
    private static final String NAVIGATOR = "NAVIGATOR";
    
    
    public JPanelSelectHero(MainView view) {
        mainView = view;
        initComponent();
    }
    
    
    private void initComponent() {
        
        setLayout(new GridLayout(3, 3));
        
        diver = new JButton("plongeur");
        diver.setName(DIVER);
        diver.setActionCommand(diver.getName());
        explorer = new JButton("exploreur");
        explorer.setName(EXPLORER);
        explorer.setActionCommand(explorer.getName());
        engineer = new JButton("ingenieur");
        engineer.setName(ENGINEER);
        engineer.setActionCommand(engineer.getName());
        messenger = new JButton("messager");
        messenger.setName(MESSENGER);
        messenger.setActionCommand(messenger.getName());
        navigator = new JButton("navigateur");
        navigator.setName(NAVIGATOR);
        navigator.setActionCommand(navigator.getName());
        pilot = new JButton("pilote");
        pilot.setName(PILOT);
        pilot.setActionCommand(pilot.getName());
        random = new JButton("au hasard");
        random.setName(RANDOM);
        random.setActionCommand(random.getName());
        
        add(diver);
        add(engineer);
        add(explorer);
        add(messenger);
        add(navigator);
        add(pilot);
        add(new JPanel());
        add(random);
        add(new JPanel());
    }
    
    
    private void listener() {
        Listeners heroListerners = new Listeners();
        
        diver.addActionListener(heroListerners);
        engineer.addActionListener(heroListerners);
        explorer.addActionListener(heroListerners);
        messenger.addActionListener(heroListerners);
        navigator.addActionListener(heroListerners);
        pilot.addActionListener(heroListerners);
        random.addActionListener(heroListerners);
        
    }
    
    
    // les fonctions
    
    public void addAdventurer(AdventurerType adventurer, Boolean bool) {
        if (bool) {
            switch (adventurer) {
            case DIVER:
                diver.setVisible(true);
                break;
            case ENGINEER:
                engineer.setVisible(true);
                break;
            case EXPLORER:
                explorer.setVisible(true);
                break;
            case NAVIGATOR:
                navigator.setVisible(true);
                break;
            case PILOT:
                pilot.setVisible(true);
                break;
            case MESSENGER:
                messenger.setVisible(true);
                break;
            }
        } else {
            switch (adventurer) {
            case DIVER:
                diver.setVisible(false);
                break;
            case ENGINEER:
                engineer.setVisible(false);
                break;
            case EXPLORER:
                explorer.setVisible(false);
                break;
            case NAVIGATOR:
                navigator.setVisible(false);
                break;
            case PILOT:
                pilot.setVisible(false);
                break;
            case MESSENGER:
                messenger.setVisible(false);
                break;
            }
        }
    }
    
    private class Listeners extends Observable implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            setChanged();
            
            switch (e.getActionCommand()) {
            case DIVER:
                Parameters.printLog(DIVER, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.DIVER));
                break;
            case EXPLORER:
                Parameters.printLog(EXPLORER, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.EXPLORER));
                break;
            case ENGINEER:
                Parameters.printLog(ENGINEER, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.ENGINEER));
                break;
            case MESSENGER:
                Parameters.printLog(MESSENGER, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.MESSENGER));
                break;
            case NAVIGATOR:
                Parameters.printLog(NAVIGATOR, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.NAVIGATOR));
                break;
            case PILOT:
                Parameters.printLog(PILOT, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.PILOT));
                break;
            case RANDOM:
                Parameters.printLog(RANDOM, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.RANDOM));
                break;
            default:
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.RANDOM));
                break;
            }
            clearChanged();
        }
        
    }
}
