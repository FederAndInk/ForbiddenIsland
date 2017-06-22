package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.adventurers.AdventurerType;
import util.LogType;
import util.Parameters;
import util.message.MainAction;
import util.message.MainMessage;



public class JPanelSelectHero extends JPanel {
    
    private HashMap<AdventurerType, JButtonAdventurer> adventurers;
    // private JButtonAdventurer diver;
    // private JButtonAdventurer explorer;
    // private JButtonAdventurer engineer;
    // private JButtonAdventurer messenger;
    // private JButtonAdventurer navigator;
    // private JButtonAdventurer pilot;
    // private JButtonAdventurer random;
    private JButton         annuler;
    private JPanel          main;
    private Listeners       heroListeners;
    private String          hero;
    private JPanelOptionJeu optionJeu;
    
    private static final String DIVER     = "Diver";
    private static final String EXPLORER  = "Explorer";
    private static final String ENGINEER  = "Engineer";
    private static final String MESSENGER = "Messenger";
    private static final String PILOT     = "Pilot";
    private static final String RANDOM    = "Random";
    private static final String NAVIGATOR = "Navigator";
    
    
    public JPanelSelectHero(JPanelOptionJeu opt) {
        this.optionJeu = opt;
        initComponent();
        listener();
    }
    
    
    private void initComponent() {
        setLayout(new BorderLayout());
        main = new JPanel();
        main.setLayout(new GridLayout(3, 3));
        adventurers = new HashMap<>();
        heroListeners = new Listeners();
        
        for (AdventurerType adv : AdventurerType.values()) {
            adventurers.put(adv, new JButtonAdventurer(adv));
            adventurers.get(adv).setActionCommand(adv.getClassName());
            if (adv == AdventurerType.RANDOM) {
                main.add(new JPanel());
                main.add(adventurers.get(adv));
                main.add(new JPanel());
            } else {
                main.add(adventurers.get(adv));
            }
        }
        
        // diver = new JButtonAdventurer(AdventurerType.DIVER);
        // diver.setName(DIVER);
        // explorer = new JButtonAdventurer(AdventurerType.EXPLORER);
        // explorer.setName(EXPLORER);
        // explorer.setActionCommand(explorer.getName());
        // engineer = new JButtonAdventurer(AdventurerType.ENGINEER);
        // engineer.setName(ENGINEER);
        // engineer.setActionCommand(engineer.getName());
        // messenger = new JButtonAdventurer(AdventurerType.MESSENGER);
        // messenger.setName(MESSENGER);
        // messenger.setActionCommand(messenger.getName());
        // navigator = new JButtonAdventurer(AdventurerType.NAVIGATOR);
        // navigator.setName(NAVIGATOR);
        // navigator.setActionCommand(navigator.getName());
        // pilot = new JButtonAdventurer(AdventurerType.PILOT);
        // pilot.setName(PILOT);
        // pilot.setActionCommand(pilot.getName());
        // random = new JButtonAdventurer(AdventurerType.RANDOM);
        // random.setName(RANDOM);
        // random.setActionCommand(random.getName());
        
        annuler = new JButton("annuler");
        
        add(main, BorderLayout.CENTER);
        add(annuler, BorderLayout.SOUTH);
        
        // main.add(diver);
        // main.add(engineer);
        // main.add(explorer);
        // main.add(messenger);
        // main.add(navigator);
        // main.add(pilot);
        // main.add(new JPanel());
        // main.add(random);
        // main.add(new JPanel());
    }
    
    
    private void listener() {
        heroListeners = new Listeners();
        
        for (JButtonAdventurer btn : adventurers.values()) {
            btn.addActionListener(heroListeners);
        }
        
        // diver.addActionListener(heroListerners);
        // engineer.addActionListener(heroListerners);
        // explorer.addActionListener(heroListerners);
        // messenger.addActionListener(heroListerners);
        // navigator.addActionListener(heroListerners);
        // pilot.addActionListener(heroListerners);
        // random.addActionListener(heroListerners);
        
        annuler.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.printLog("annuler", LogType.INFO);
                ((CardLayout) getParent().getLayout()).show(getParent(), "picture");
            }
        });
        
    }
    
    
    public void setEnabled(boolean enabled, AdventurerType type) {
        adventurers.get(type).setEnabled(enabled);
    }
    
    private class Listeners extends Observable implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            setChanged();
            switch (e.getActionCommand()) {
            case DIVER:
                Parameters.printLog(DIVER, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.DIVER));
                setEnabled(false, AdventurerType.DIVER);
                break;
            case EXPLORER:
                Parameters.printLog(EXPLORER, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.EXPLORER));
                setEnabled(false, AdventurerType.EXPLORER);
                break;
            case ENGINEER:
                Parameters.printLog(ENGINEER, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.ENGINEER));
                // setEnabled(false, AdventurerType.ENGINEER);
                break;
            case MESSENGER:
                Parameters.printLog(MESSENGER, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.MESSENGER));
                // setEnabled(false, AdventurerType.MESSENGER);
                break;
            case NAVIGATOR:
                Parameters.printLog(NAVIGATOR, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.NAVIGATOR));
                // setEnabled(false, AdventurerType.NAVIGATOR);
                break;
            case PILOT:
                Parameters.printLog(PILOT, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.PILOT));
                // setEnabled(false, AdventurerType.PILOT);
                break;
            case RANDOM:
                
                Parameters.printLog(RANDOM, LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.RANDOM));
                break;
            default:
                notifyObservers(new MainMessage(MainAction.SELECT_ADVENTURER, AdventurerType.RANDOM));
                setEnabled(true, AdventurerType.RANDOM);
                break;
            }
            clearChanged();
            ((CardLayout) getParent().getLayout()).show(getParent(), "picture");
        }
        
    }
    
    
    public void addObs(Observer observer) {
        heroListeners.addObserver(observer);
    }
}
