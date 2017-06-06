package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;



public class JPanelOptionJeu extends JPanel {
    
    private JPanel       nbjoueur;
    private JLabel       joueur;
    private ButtonGroup  grpjoueur;
    private JRadioButton deux;
    private JRadioButton trois;
    private JRadioButton quatre;
    private JPanel       nomJoueurPanel;
    private JTextArea    joueur1;
    private JTextArea    joueur2;
    private JTextArea    joueur3;
    private JTextArea    joueur4;
    private JButton      annuler;
    private JButton      selectionMap;
    private JPanel       selectionHero;
    private JPanel       vide;
    private JButton      hero1;
    private JButton      hero2;
    private JButton      hero3;
    private JButton      hero4;
    private JButton      random;
    
    
    public JPanelOptionJeu() {
        initComponant();
        dynamicPlayer();
    }
    
    
    private void initComponant() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        nbjoueur = new JPanel();
        joueur = new JLabel("Nombre de joueur :");
        grpjoueur = new ButtonGroup();
        deux = new JRadioButton("2");
        trois = new JRadioButton("3");
        quatre = new JRadioButton("4");
        nomJoueurPanel = new JPanel(new GridLayout(4, 1));
        joueur1 = new JTextArea("nom du premier joueur");
        joueur2 = new JTextArea("nom du deuxieme joueur");
        joueur3 = new JTextArea("nom du troisieme joueur");
        joueur4 = new JTextArea("nom dud quatrieme joueur");
        annuler = new JButton("annuler");
        selectionMap = new JButton("selection de la carte");
        selectionHero = new JPanel(new GridLayout(3, 3));
        vide = new JPanel();
        hero1 = new JButton("1er hero");
        hero2 = new JButton("2eme hero");
        hero3 = new JButton("3eme hero");
        hero4 = new JButton("4eme hero");
        random = new JButton("tous le monde au hasard");
        
        this.add(nbjoueur);
        this.add(nomJoueurPanel);
        this.add(selectionMap);
        this.add(selectionHero);
        this.add(annuler);
        
        grpjoueur.add((AbstractButton) add(deux));
        grpjoueur.add((AbstractButton) add(trois));
        grpjoueur.add((AbstractButton) add(quatre));
        deux.setSelected(true);
        initDefault();
        
        nbjoueur.add(joueur);
        nbjoueur.add(deux);
        nbjoueur.add(trois);
        nbjoueur.add(quatre);
        
        nomJoueurPanel.add(joueur1);
        nomJoueurPanel.add(joueur2);
        nomJoueurPanel.add(joueur3);
        nomJoueurPanel.add(joueur4);
        
        selectionHero.add(hero1);
        selectionHero.add(new JPanel());
        selectionHero.add(hero2);
        selectionHero.add(new JPanel());
        selectionHero.add(random);
        selectionHero.add(new JPanel());
        selectionHero.add(hero3);
        selectionHero.add(new JPanel());
        selectionHero.add(hero4);
    }
    
    
    /**
     * 
     */
    private void dynamicPlayer() {
        ActionListener listener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                initDefault();
                if (trois.isSelected()) {
                    joueur3.setVisible(true);
                    hero3.setVisible(true);
                    System.out.println("nya~");
                    
                } else if (quatre.isSelected()) {
                    joueur3.setVisible(true);
                    joueur4.setVisible(true);
                    hero3.setVisible(true);
                    hero4.setVisible(true);
                    System.out.println("nya :3");
                    
                }
                repaint();
            }
        };
        
        deux.addActionListener(listener);
        trois.addActionListener(listener);
        quatre.addActionListener(listener);
    }
    
    
    public void listener() {
        selectionMap.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // toutdoux frame selection map a faire
            }
        });
        
        annuler.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // toutdoux
            }
        });
    }
    
    
    /**
     * met les elements 3 et 4 des bouton de selection d'hero et de champ texte
     */
    public void initDefault() {
        joueur3.setVisible(false);
        joueur4.setVisible(false);
        hero3.setVisible(false);
        hero4.setVisible(false);
    }
}
