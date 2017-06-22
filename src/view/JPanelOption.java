package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
        mainOptionPanel = new JPanel(new GridLayout(2, 1));
        
        annuler = new JButton("annuler");
        valider = new JButton("valider");
        
        add(mainOptionPanel, BorderLayout.CENTER);
        mainOptionPanel.add(screen);
        mainOptionPanel.add(langPanel);
        add(valan, BorderLayout.SOUTH);
        
        // le type de fenetre
        
        valan.add(annuler, BorderLayout.EAST);
        valan.add(valider, BorderLayout.WEST);
        
        screen.add(new JLabel("selectionner le style de fenetre : "));
        windowed = new JRadioButton("windowed");
        screen.add(windowed);
        
        fullscreen = new JRadioButton("plein ecran");
        screen.add(fullscreen);
        fullscreen.setSelected(true);
        
        screenoption.add(fullscreen);
        screenoption.add(windowed);
        
        // les langues
        itemLangue = new String[] { "français", "english", "español", "deutsch", "nihon" };
        langueBox = new JComboBox<>(itemLangue);
        langPanel.add(new JLabel("selectionnez une langue :"));
        langPanel.add(langueBox);
    }
    
    
    public void listener() {
        annuler.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.printLog("annuler", LogType.INFO);
                ((CardLayout) getParent().getLayout()).show(getParent(), "main");
            }
        });
        valider.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.printLog("valider", LogType.INFO);
                if (fullscreen.isSelected()) {
                    Parameters.printLog("fullscreen", LogType.INFO);
                    // FIXME fullscreen
                } else {
                    Parameters.printLog("windowed", LogType.INFO);
                    // FIXME windowed
                }
                repaint();
            }
        });
    }
}
