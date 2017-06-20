package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.LogType;
import util.Parameters;



public class JPanelTuto extends JPanel {
    private JPanel  retourPanel;
    private JButton retour;
    
    
    public JPanelTuto() {
        initComponnent();
        initListener();
    }
    
    
    public void initComponnent() {
        retourPanel = new JPanel();
        retour = new JButton("retour");
        
        setLayout(new BorderLayout());
        
        add(retourPanel, BorderLayout.SOUTH);
        
        retourPanel.add(retour);
    }
    
    
    public void initListener() {
        retour.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.printLog("annuler", LogType.INFO);
                ((CardLayout) getParent().getLayout()).show(getParent(), "lesBoutons");
            }
        });
    }
}
