package view;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.adventurers.AdventurerType;
import util.LogType;
import util.Parameters;
import util.message.MainAction;
import util.message.MainMessage;



public class JPanelOptionJeu extends JPanel {
    
    private JPanel       nbjoueur;
    private JLabel       joueur;
    private ButtonGroup  grpjoueur;
    private JRadioButton deux;
    private JRadioButton trois;
    private JRadioButton quatre;
    private JPanel       nomJoueurPanel;
    private JLabel       nomJoueur1;
    private JTextField   joueur1;
    private JLabel       nomJoueur2;
    private JTextField   joueur2;
    private JLabel       nomJoueur3;
    private JTextField   joueur3;
    private JLabel       nomJoueur4;
    private JTextField   joueur4;
    private JButton      annuler;
    private JButton      selectionMap;
    private JPanel       selectionHero;
    private JPanel       vide;
    private JButton      hero1;
    private JButton      hero2;
    private JButton      hero3;
    private JButton      hero4;
    private JButton      random;
    private JPanelMenu   card;
    private JButton      jouer;
    
    private static final String AJ1  = "joueur1";
    private static final String AJ2  = "joueur2";
    private static final String AJ3  = "joueur3";
    private static final String AJ4  = "joueur4";
    private static final String PLAY = "jouer";
    
    
    /**
     * @param card
     */
    public JPanelOptionJeu(JPanelMenu card) {
        this.card = card;
        
        initComponent();
        dynamicPlayer();
        listener();
    }
    
    
    private void initComponent() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        nbjoueur = new JPanel();
        joueur = new JLabel("Nombre de joueur :");
        grpjoueur = new ButtonGroup();
        deux = new JRadioButton("2");
        trois = new JRadioButton("3");
        quatre = new JRadioButton("4");
        nomJoueurPanel = new JPanel(new GridLayout(4, 1));
        nomJoueur1 = new JLabel("nom du premier joueur :");
        joueur1 = new JTextField();
        nomJoueur2 = new JLabel("nom du deuxieme joueur :");
        joueur2 = new JTextField();
        nomJoueur3 = new JLabel("nom du troisieme joueur :");
        joueur3 = new JTextField();
        nomJoueur4 = new JLabel("nom du quatrieme joueur :");
        joueur4 = new JTextField();
        annuler = new JButton("annuler");
        selectionMap = new JButton("selection de la carte");
        selectionHero = new JPanel(new GridLayout(3, 3));
        vide = new JPanel();
        hero1 = new JButton("1er hero");
        hero2 = new JButton("2eme hero");
        hero3 = new JButton("3eme hero");
        hero4 = new JButton("4eme hero");
        random = new JButton("au hasard");
        jouer = new JButton("jouer");
        jouer.setName(PLAY);
        jouer.setActionCommand(getName());
        
        this.add(nbjoueur);
        this.add(nomJoueurPanel);
        this.add(Box.createVerticalGlue());
        this.add(selectionMap);
        this.add(Box.createVerticalGlue());
        this.add(selectionHero);
        this.add(Box.createVerticalGlue());
        this.add(jouer);
        this.add(Box.createVerticalGlue());
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
        
        nomJoueurPanel.add(nomJoueur1);
        nomJoueurPanel.add(joueur1);
        nomJoueurPanel.add(nomJoueur2);
        nomJoueurPanel.add(joueur2);
        nomJoueurPanel.add(nomJoueur3);
        nomJoueurPanel.add(joueur3);
        nomJoueurPanel.add(nomJoueur4);
        nomJoueurPanel.add(joueur4);
        
        selectionHero.add(hero1);
        selectionHero.add(new JPanel());
        hero1.setName(AJ1);
        hero1.setActionCommand(hero1.getName());
        selectionHero.add(hero2);
        selectionHero.add(new JPanel());
        hero2.setName(AJ2);
        hero2.setActionCommand(hero2.getName());
        selectionHero.add(random);
        selectionHero.add(new JPanel());
        hero3.setName(AJ3);
        hero3.setActionCommand(hero3.getName());
        selectionHero.add(hero3);
        selectionHero.add(new JPanel());
        hero4.setName(AJ4);
        hero4.setActionCommand(hero4.getName());
        selectionHero.add(hero4);
        
        annuler.setAlignmentX(CENTER_ALIGNMENT);
        selectionMap.setAlignmentX(CENTER_ALIGNMENT);
        jouer.setAlignmentX(CENTER_ALIGNMENT);
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
                    nomJoueur3.setVisible(true);
                    hero3.setVisible(true);
                    Parameters.printLog("3 players", LogType.INFO);
                    
                } else if (quatre.isSelected()) {
                    joueur3.setVisible(true);
                    nomJoueur3.setVisible(true);
                    joueur4.setVisible(true);
                    nomJoueur4.setVisible(true);
                    hero3.setVisible(true);
                    hero4.setVisible(true);
                    Parameters.printLog("4 players", LogType.INFO);
                    
                }
                repaint();
            }
        };
        
        deux.addActionListener(listener);
        trois.addActionListener(listener);
        quatre.addActionListener(listener);
    }
    
    
    public void modifyButton(String nomPlayer, AdventurerType adventurer) {
        if (nomPlayer == nomJoueur1.getText()) {
            hero1.setText(adventurer.toString());
        } else if (nomPlayer == nomJoueur2.getText()) {
            hero2.setText(adventurer.toString());
        } else if (nomPlayer == nomJoueur3.getText()) {
            hero3.setText(adventurer.toString());
        } else if (nomPlayer == nomJoueur4.getText()) {
            hero4.setText(adventurer.toString());
        }
    }
    
    
    /**
     * 
     */
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
                ((CardLayout) (card.getCard().getLayout())).show(card.getCard(), JPanelMenu.MAIN);
            }
        });
        
        ActionListener listenhero = new Listeners();
        
        hero1.addActionListener(listenhero);
        hero2.addActionListener(listenhero);
        hero3.addActionListener(listenhero);
        hero4.addActionListener(listenhero);
        jouer.addActionListener(listenhero);
    }
    
    
    /**
     * met les elements 3 et 4 des bouton de selection d'hero et de champ texte
     */
    public void initDefault() {
        joueur3.setVisible(false);
        nomJoueur3.setVisible(false);
        joueur4.setVisible(false);
        nomJoueur4.setVisible(false);
        hero3.setVisible(false);
        hero4.setVisible(false);
    }
    
    private class Listeners extends Observable implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            switch (e.getActionCommand()) {
            case AJ1:
                Parameters.printLog("JOUEUR 1", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_PLAYER, joueur1.getText()));
                break;
            case AJ2:
                Parameters.printLog("JOUEUR 2", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_PLAYER, joueur2.getText()));
                break;
            case AJ3:
                Parameters.printLog("JOUEUR 3", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_PLAYER, joueur3.getText()));
                break;
            case AJ4:
                Parameters.printLog("JOUEUR 4", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_PLAYER, joueur4.getText()));
                break;
            case PLAY:
                Parameters.printLog("Create Game", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.CREATE_GAME, null));
                break;
            default:
                throw new UnsupportedOperationException();
            }
            clearChanged();
        }
    }
}
