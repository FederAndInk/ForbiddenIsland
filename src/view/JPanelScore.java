package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.LogType;
import util.Parameters;



public class JPanelScore extends JPanel {
    private JButton annuler;
    private JPanel  annulerPanel;
    
    
    public JPanelScore() {
        initComponent();
        initListener();
    }
    
    
    public void initComponent() {
        annuler = new JButton("retour");
        annulerPanel = new JPanel();
        
        setLayout(new BorderLayout());
        
        this.add(annulerPanel, BorderLayout.SOUTH);
        annulerPanel.add(annuler);
    }
    
    
    public void initListener() {
        annuler.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.printLog("annuler", LogType.INFO);
                ((CardLayout) getParent().getLayout()).show(getParent(), "main");
            }
        });
    }
}
