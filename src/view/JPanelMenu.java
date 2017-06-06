package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class JPanelMenu extends JPanel {
    
    private JPanel          titre;
    private JButton         quitter;
    private JPanel          card;
    private JPanel          grid;
    private JButton         jouer;
    private JButton         score;
    private JButton         tuto;
    private JButton         option;
    private JPanel          optionPanel;
    private JPanel          scorePanel;
    private JPanelOptionJeu jeu;
    private JLabel          titreLabel;
    
    
    public JPanelMenu() {
        setLayout(new BorderLayout());
        initComponent();
        initLayout();
        
    }
    
    
    public void initComponent() {
        
        titre = new JPanel();
        add(titre, BorderLayout.NORTH);
        quitter = new JButton("quitter");
        add(quitter, BorderLayout.SOUTH);
        card = new JPanel(new CardLayout());
        add(card, BorderLayout.CENTER);
        
        // ** les cardlayout**//
        
        // le main
        grid = new JPanel(new GridLayout(6, 1));
        card.add(grid, "main");
        jouer = new JButton("jouer");
        grid.add(jouer);
        option = new JButton("option");
        grid.add(option);
        score = new JButton("scores");
        grid.add(score);
        tuto = new JButton("tutoriel");
        grid.add(tuto);
        
        optionPanel = new JPanel();
        card.add(optionPanel, "option");
        
        scorePanel = new JPanel();
        card.add(scorePanel, "scores");
        
        jeu = new JPanelOptionJeu();
        card.add(jeu, "jeu");
        
        // le tuto
        // toutdoux tuto a faire
        
        // **fin**//
        
        titreLabel = new JLabel("L'Ile Interdite");
        titre.add(titreLabel);
    }
    
    
    /**
     * 
     */
    public void initLayout() {
        ActionListener listener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(jouer)) {
                    
                    ((CardLayout) (card.getLayout())).show(card, "jeu");
                    
                } else if (e.getSource().equals(option)) {
                    
                    ((CardLayout) (card.getLayout())).show(card, "options");
                    
                } else if (e.getSource().equals(score)) {
                    
                    ((CardLayout) (card.getLayout())).show(card, "scores");
                    
                } else if (e.getSource().equals(tuto)) {
                    
                    // toutdoux tuto
                    
                } else if (e.getSource().equals(quitter)) {
                    
                    quitter();
                    
                }
            }
        };
        
        jouer.addActionListener(listener);
        option.addActionListener(listener);
        score.addActionListener(listener);
        tuto.addActionListener(listener);
        quitter.addActionListener(listener);
    }
    
    
    public void quitter() {
        System.exit(0);
    }
    
}
