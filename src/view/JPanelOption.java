package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.*;

import util.LogType;
import util.Parameters;



public class JPanelOption extends JPanel {
    private JPanel            screen;
    private JPanel            mainOptionPanel;
    private JRadioButton      fullscreen;
    private JRadioButton      windowed;
    private ButtonGroup       screenoption;
    private JButton           valider;
    private JButton           annuler;
    private JPanel            valan;
    private JPanel            langPanel;
    private String[]          itemLangue;
    private JComboBox<String> langueBox;
    private JPanel            debugPanel;
    private ButtonGroup       debugButtonGroup;
    private JRadioButton      debugOk;
    private JRadioButton      debugNo;
    
    
    public JPanelOption() {
        initComponent();
        listener();
    }
    
    
    public void initComponent() {
        setLayout(new BorderLayout());
        screen = new JPanel();
        valan = new JPanel(new BorderLayout());
        screenoption = new ButtonGroup();
        langPanel = new JPanel();
        mainOptionPanel = new JPanel(new GridLayout(3, 1));
        debugPanel = new JPanel();
        
        annuler = new JButton("annuler");
        valider = new JButton("valider");
        
        add(mainOptionPanel, BorderLayout.CENTER);
        mainOptionPanel.add(screen);
        mainOptionPanel.add(langPanel);
        mainOptionPanel.add(debugPanel);
        add(valan, BorderLayout.SOUTH);
        
        // le type de fenetre
        
        valan.add(annuler, BorderLayout.EAST);
        valan.add(valider, BorderLayout.WEST);
        
        screen.add(new JLabel("selectionner le style de fenetre : "));
        windowed = new JRadioButton("windowed");
        screen.add(windowed);
        
        fullscreen = new JRadioButton("plein ecran");
        screen.add(fullscreen);
        if (Parameters.fullscreen) {
            fullscreen.setSelected(true);
        } else {
            windowed.setSelected(true);
        } // end if
        
        screenoption.add(fullscreen);
        screenoption.add(windowed);
        
        // les langues
        itemLangue = new String[] { "français", "english", "español", "deutsch", "nihon" };
        langueBox = new JComboBox<>(itemLangue);
        langPanel.add(new JLabel("selectionnez une langue :"));
        langPanel.add(langueBox);
        
        // le debuger
        debugPanel.add(new JLabel("activer le debuger?"));
        debugButtonGroup = new ButtonGroup();
        debugNo = new JRadioButton("non");
        debugPanel.add(debugNo);
        
        debugOk = new JRadioButton("oui");
        debugPanel.add(debugOk);
        if (Parameters.debug) {
            debugOk.setSelected(true);
        } else {
            debugNo.setSelected(true);
        } // end if
        
        debugButtonGroup.add(debugNo);
        debugButtonGroup.add(debugOk);
    }
    
    
    public void listener() {
        annuler.addActionListener(e -> {
            Parameters.printLog("annuler", LogType.INFO);
            ((CardLayout) getParent().getLayout()).show(getParent(), "main");
        });
        valider.addActionListener(e -> {
            Parameters.printLog("valider", LogType.INFO);
            if (fullscreen.isSelected()) {
                Parameters.printLog("fullscreen", LogType.INFO);
                Parameters.fullscreen = true;
            } else {
                Parameters.printLog("windowed", LogType.INFO);
                Parameters.fullscreen = false;
            }
            ((MainView) getRootPane().getParent()).initSize();
            Parameters.debug = debugOk.isSelected();
            repaint();
            Parameters.printLog("valider", LogType.INFO);
            ((CardLayout) getParent().getLayout()).show(getParent(), "main");
        });
    }
}
