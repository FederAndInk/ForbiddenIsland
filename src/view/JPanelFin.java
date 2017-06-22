package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import util.LogType;
import util.Parameters;



public class JPanelFin extends JPanel {
    private JButton menuPrincipale;
    private JLabel  why;
    private JButton quitter;
    // private JButton recommencer;
    
    private JPanelPictureFin             panelFin;
    private util.exception.ExceptionType exceptionType;
    
    private Boolean win;
    
    
    public JPanelFin(util.exception.ExceptionType exceptionType) {
        initComponent();
        setExceptionType(exceptionType);
        initListener();
    }
    
    
    private void initComponent() {
        menuPrincipale = new JButton("retour au menu principale");
        quitter = new JButton("quitter");
        // recommencer = new JButton("recommencer la partie");
        panelFin = new JPanelPictureFin();
        panelFin.setWin(true);
        why = new JLabel("meh", SwingConstants.CENTER);
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(why);
        why.setFont(new Font("meh", 25, 25));
        why.setAlignmentX(CENTER_ALIGNMENT);
        add(panelFin);
        add(quitter);
        quitter.setAlignmentX(CENTER_ALIGNMENT);
        add(menuPrincipale);
        menuPrincipale.setAlignmentX(CENTER_ALIGNMENT);
    }
    
    
    public void initListener() {
        menuPrincipale.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.printLog("annuler", LogType.INFO);
                ((CardLayout) getParent().getLayout()).show(getParent(), "picture");
            }
        });
        
        quitter.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    
    public JPanelPictureFin getPanelFin() {
        return panelFin;
    }
    
    
    public void setExceptionType(util.exception.ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        switch (exceptionType) {
        case END_GAME_DEATH:
            why.setText("<html><div align=\"center\">un aventurier est mort noyé</div></html>");
            panelFin.setWin(false);
            
            break;
        case END_GAME_WATER:
            why.setText(
                    "<html><div align=\"center\">le niveau d'eau est trop haut \n(eau lol), vous etes morts</div></html>");
            panelFin.setWin(false);
            
            break;
        case END_GAME_HELI:
            why.setText("<html><div align=\"center\">l'heliport a sombré</div></html>");
            panelFin.setWin(false);
            
            break;
        case END_GAME_TREASURE:
            why.setText(
                    "<html><div align=\"center\">il n'est plus possible de recuperer l'entiereté des tresors</div></html>");
            panelFin.setWin(false);
            break;
        case END_GAME_VICTORY:
            why.setText("");
            panelFin.setWin(true);
            break;
        default:
            break;
        }
    }
}
