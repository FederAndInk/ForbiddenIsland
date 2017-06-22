package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.BoardType;
import util.LogType;
import util.Parameters;
import util.message.MainAction;
import util.message.MainMessage;



public class JPanelChoixMap extends JPanel {
    private JButton   annuler;
    private JPanel    main;
    private JButton   defaut;
    private JButton   test;
    private Listeners listener;
    
    
    public JPanelChoixMap() {
        initComponent();
        initListener();
    }
    
    
    public void initComponent() {
        this.setLayout(new BorderLayout());
        annuler = new JButton("annuler");
        main = new JPanel(new GridLayout(3, 3));
        defaut = new JButton("default");
        defaut.setActionCommand("defaut");
        test = new JButton("test");
        test.setActionCommand("test");
        
        add(main, BorderLayout.CENTER);
        add(annuler, BorderLayout.SOUTH);
        
        main.add(defaut);
        main.add(test);
    }
    
    
    public void initListener() {
        listener = new Listeners();
        
        annuler.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.printLog("annuler", LogType.INFO);
                ((CardLayout) getParent().getLayout()).show(getParent(), "picture");
            }
        });
        
        test.addActionListener(listener);
        defaut.addActionListener(listener);
    }
    
    private class Listeners extends Observable implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            setChanged();
            switch (e.getActionCommand()) {
            case "test":
                Parameters.printLog("test", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.MAP_SELECTED, BoardType.HARD_TEST));
                break;
            case "defaut":
                Parameters.printLog("defaut", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.MAP_SELECTED, BoardType.DEFAULT));
                break;
            default:
                notifyObservers(new MainMessage(MainAction.MAP_SELECTED, BoardType.DEFAULT));
                break;
            }
            clearChanged();
            ((CardLayout) getParent().getLayout()).show(getParent(), "picture");
        }
        
    }
    
    
    public void addObs(Observer observer) {
        listener.addObserver(observer);
    }
}
