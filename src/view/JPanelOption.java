package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import util.LogType;
import util.Parameters;



public class JPanelOption extends JPanel {
    private JPanel       screen;
    private JRadioButton fullscreen;
    private JRadioButton windowed;
    private ButtonGroup  screenoption;
    private JButton      valider;
    private JButton      annuler;
    private JPanel       valan;
    
    
    public JPanelOption() {
        initComponent();
        listener();
    }
    
    
    public void initComponent() {
        setLayout(new BorderLayout());
        screen = new JPanel();
        valan = new JPanel(new BorderLayout());
        screenoption = new ButtonGroup();
        
        annuler = new JButton("annuler");
        valider = new JButton("valider");
        
        add(screen);
        add(valan, BorderLayout.SOUTH);
        
        valan.add(annuler, BorderLayout.EAST);
        valan.add(valider, BorderLayout.WEST);
        
        screen.add(new JLabel("selectionner le style de fenetre : "));
        windowed = new JRadioButton("windowed");
        screen.add(windowed);
        
        fullscreen = new JRadioButton("plein ecran");
        screen.add(fullscreen);
        
        screenoption.add(fullscreen);
        screenoption.add(windowed);
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
