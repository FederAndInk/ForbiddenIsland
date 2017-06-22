package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.adventurers.AdventurerType;
import model.game.SeaLevel;
import util.BoardType;
import util.LogType;
import util.Parameters;
import util.message.MainAction;
import util.message.MainMessage;



public class JPanelOptionJeu extends JPanel {
    
    private JPanel                          nbjoueur;
    private JLabel                          joueur;
    private ButtonGroup                     grpjoueur;
    private JRadioButton                    deux;
    private JRadioButton                    trois;
    private JRadioButton                    quatre;
    private JPanel                          nomJoueurPanel;
    private JLabel                          nomJoueur1;
    private JTextField                      joueur1;
    private JLabel                          nomJoueur2;
    private JTextField                      joueur2;
    private JLabel                          nomJoueur3;
    private JTextField                      joueur3;
    private JLabel                          nomJoueur4;
    private JTextField                      joueur4;
    private JButton                         annuler;
    private JButton                         selectionMap;
    private JPanel                          selectionHero;
    private JPanel                          vide;
    private JButton                         hero1;
    private JButton                         hero2;
    private JButton                         hero3;
    private JButton                         hero4;
    private JButton                         random;
    private JPanelMenu                      card;
    private JButton                         jouer;
    private HashMap<String, AdventurerType> playerMap;
    private BoardType                       boardType;
    private JPanel                          selectsealvlPanel;
    private JSlider                         sealvlbar;
    private SeaLevel                        seaLevel;
    
    private static final String AJ1        = "joueur1";
    private static final String AJ2        = "joueur2";
    private static final String AJ3        = "joueur3";
    private static final String AJ4        = "joueur4";
    private static final String HASARD     = "random";
    private static final String PLAY       = "jouer";
    private static final String SELECT_MAP = "select_map";
    private Listeners           listenhero;
    
    
    /**
     * @param card
     */
    public JPanelOptionJeu(JPanelMenu card) {
        this.card = card;
        
        initComponant();
        dynamicPlayer();
        listener();
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
        selectionMap.setActionCommand(SELECT_MAP);
        selectionHero = new JPanel(new GridLayout(3, 3));
        vide = new JPanel();
        hero1 = new JButton("1er hero");
        hero2 = new JButton("2eme hero");
        hero3 = new JButton("3eme hero");
        hero4 = new JButton("4eme hero");
        random = new JButton("au hasard");
        jouer = new JButton("jouer");
        playerMap = new HashMap<>();
        playerMap.put("J1", AdventurerType.RANDOM);
        playerMap.put("J2", AdventurerType.RANDOM);
        jouer.setName(PLAY);
        jouer.setActionCommand(getName());
        selectsealvlPanel = new JPanel();
        selectsealvlPanel.setLayout(new BoxLayout(selectsealvlPanel, BoxLayout.PAGE_AXIS));
        
        this.add(nbjoueur);
        this.add(nomJoueurPanel);
        this.add(Box.createVerticalGlue());
        this.add(selectionMap);
        this.add(Box.createVerticalGlue());
        this.add(selectionHero);
        this.add(Box.createVerticalGlue());
        this.add(selectsealvlPanel);
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
        joueur1.setText("joueur 1");
        nomJoueurPanel.add(joueur1);
        nomJoueurPanel.add(nomJoueur2);
        joueur2.setText("joueur 2");
        nomJoueurPanel.add(joueur2);
        nomJoueurPanel.add(nomJoueur3);
        joueur3.setText("joueur 3");
        nomJoueurPanel.add(joueur3);
        nomJoueurPanel.add(nomJoueur4);
        joueur4.setText("joueur 4");
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
        random.setName(HASARD);
        random.setActionCommand(random.getName());
        selectionHero.add(hero3);
        selectionHero.add(new JPanel());
        hero3.setName(AJ3);
        hero3.setActionCommand(hero3.getName());
        selectionHero.add(hero4);
        hero4.setName(AJ4);
        hero4.setActionCommand(hero4.getName());
        
        annuler.setAlignmentX(CENTER_ALIGNMENT);
        selectionMap.setAlignmentX(CENTER_ALIGNMENT);
        jouer.setAlignmentX(CENTER_ALIGNMENT);
        
        boardType = BoardType.DEFAULT;
        
        sealvlbar = new JSlider(JSlider.HORIZONTAL, 1, 4, 1);
        sealvlbar.setPaintTicks(true);
        
        Hashtable sealvlTable = new Hashtable();
        sealvlTable.put(new Integer(1), new JLabel("novice"));
        sealvlTable.put(new Integer(2), new JLabel("normal"));
        sealvlTable.put(new Integer(3), new JLabel("élite"));
        sealvlTable.put(new Integer(4), new JLabel("legendaire"));
        Parameters.printLog(sealvlTable.size(), LogType.INFO);
        sealvlbar.setMajorTickSpacing(1);
        sealvlbar.setForeground(Color.black);
        sealvlbar.setLabelTable(sealvlTable);
        
        sealvlbar.setPaintLabels(true);
        
        selectsealvlPanel.add(new JLabel("selectionnez la difficultée"));
        selectsealvlPanel.add(Box.createGlue());
        selectsealvlPanel.add(sealvlbar);
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
                    if (!playerMap.containsKey("J4")) {
                        playerMap.put("J3", AdventurerType.RANDOM);
                    }
                    playerMap.remove("J4");
                    Parameters.printLog("3 players", LogType.INFO);
                    
                } else if (quatre.isSelected()) {
                    joueur3.setVisible(true);
                    nomJoueur3.setVisible(true);
                    joueur4.setVisible(true);
                    nomJoueur4.setVisible(true);
                    hero3.setVisible(true);
                    hero4.setVisible(true);
                    if (!getPlayerMap().containsKey("J3")) {
                        playerMap.put("J3", AdventurerType.RANDOM);
                    }
                    playerMap.put("J4", AdventurerType.RANDOM);
                    Parameters.printLog("4 players", LogType.INFO);
                    
                } else if (deux.isSelected()) {
                    playerMap.remove("J3");
                    playerMap.remove("J4");
                }
                repaint();
            }
        };
        
        deux.addActionListener(listener);
        trois.addActionListener(listener);
        quatre.addActionListener(listener);
        
        sealvlbar.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent arg0) {
                switch (sealvlbar.getValue()) {
                case 1:
                    setSeaLevel(SeaLevel.LEVEL1);
                    break;
                case 2:
                    setSeaLevel(SeaLevel.LEVEL2);
                    break;
                case 3:
                    setSeaLevel(SeaLevel.LEVEL3);
                    break;
                case 4:
                    setSeaLevel(SeaLevel.LEVEL4);
                    break;
                default:
                    setSeaLevel(SeaLevel.LEVEL1);
                    break;
                }
                Parameters.printLog(getSeaLevel(), LogType.INFO);
            }
        });
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
        
        CaretListener champtextevide = new CaretListener() {
            
            @Override
            public void caretUpdate(CaretEvent e) {
                if (((JTextField) e.getSource()).getText().isEmpty()) {
                    Runnable run = new Runnable() {
                        public void run() {
                            jouer.setEnabled(false);
                        }
                    };
                    SwingUtilities.invokeLater(run);
                } else {
                    Runnable run = new Runnable() {
                        public void run() {
                            jouer.setEnabled(true);
                        }
                    };
                    SwingUtilities.invokeLater(run);
                }
            }
        };
        joueur1.addCaretListener(champtextevide);
        joueur2.addCaretListener(champtextevide);
        joueur3.addCaretListener(champtextevide);
        joueur4.addCaretListener(champtextevide);
        
        listenhero = new Listeners();
        
        hero1.addActionListener(listenhero);
        hero2.addActionListener(listenhero);
        hero3.addActionListener(listenhero);
        hero4.addActionListener(listenhero);
        random.addActionListener(listenhero);
        jouer.addActionListener(listenhero);
        selectionMap.addActionListener(listenhero);
    }
    
    
    public void changeText(JTextField textField) {
        textField.setText("jean François");
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
                playerMap.put("J1", null);
                Parameters.printLog(playerMap.size(), LogType.INFO);
                
                notifyObservers(new MainMessage(MainAction.SELECT_PLAYER, "J1"));
                break;
            case AJ2:
                Parameters.printLog("JOUEUR 2", LogType.INFO);
                playerMap.put("J2", null);
                Parameters.printLog(playerMap.size(), LogType.INFO);
                
                notifyObservers(new MainMessage(MainAction.SELECT_PLAYER, "J2"));
                break;
            case AJ3:
                Parameters.printLog("JOUEUR 3", LogType.INFO);
                playerMap.put("J3", null);
                Parameters.printLog(playerMap.size(), LogType.INFO);
                
                notifyObservers(new MainMessage(MainAction.SELECT_PLAYER, "J3"));
                break;
            case AJ4:
                Parameters.printLog("JOUEUR 4", LogType.INFO);
                playerMap.put("J4", null);
                Parameters.printLog(playerMap.size(), LogType.INFO);
                
                notifyObservers(new MainMessage(MainAction.SELECT_PLAYER, "J4"));
                break;
            case HASARD:
                Parameters.printLog("random", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_PLAYER, "random"));
                break;
            case SELECT_MAP:
                Parameters.printLog("selection de la map", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.SELECT_MAP));
                break;
            case PLAY:
                Parameters.printLog("Create Game with " + getPlayerMap().size() + " joueurs", LogType.INFO);
                Parameters.printLog("Create Game with " + getBoardType() + " map", LogType.INFO);
                notifyObservers(new MainMessage(MainAction.CREATE_GAME, getBoardType()));
                break;
            default:
                throw new UnsupportedOperationException();
            }
            clearChanged();
        }
    }
    
    
    public HashMap<String, AdventurerType> getPlayerMap() {
        return playerMap;
    }
    
    
    public AdventurerType getPlayer(String string) {
        return playerMap.get(string);
    }
    
    
    public void setAdventurer(String string, AdventurerType adv) {
        playerMap.put(string, adv);
    }
    
    
    public void addObs(Observer observer) {
        listenhero.addObserver(observer);
    }
    
    
    public BoardType getBoardType() {
        return boardType;
    }
    
    
    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }
    
    
    public void setSeaLevel(SeaLevel seaLevel) {
        this.seaLevel = seaLevel;
    }
    
    
    public SeaLevel getSeaLevel() {
        return seaLevel;
    }
    
    
    public void changeButtonColor(String j) {
        switch (j) {
        case "J1":
            changeButtonColor(getPlayer(j), hero1);
            break;
        case "J2":
            changeButtonColor(getPlayer(j), hero2);
            break;
        case "J3":
            changeButtonColor(getPlayer(j), hero3);
            break;
        case "J4":
            changeButtonColor(getPlayer(j), hero4);
            break;
        default:
            break;
        }
    }
    
    
    private void changeButtonColor(AdventurerType adv, JButton button) {
        switch (adv) {
        case DIVER:
            button.setBackground(Color.black);
            button.setForeground(Color.WHITE);
            break;
        case ENGINEER:
            button.setBackground(Color.red);
            button.setForeground(Color.BLACK);
            break;
        case EXPLORER:
            button.setBackground(Color.green);
            button.setForeground(Color.BLACK);
            
            break;
        case MESSENGER:
            button.setBackground(Color.white);
            button.setForeground(Color.BLACK);
            
            break;
        case NAVIGATOR:
            button.setBackground(Color.yellow);
            button.setForeground(Color.BLACK);
            
            break;
        case PILOT:
            button.setBackground(Color.blue);
            button.setForeground(Color.BLACK);
            
            break;
        case RANDOM:
            button.setBackground(Color.gray);
            button.setForeground(Color.BLACK);
            
            break;
        default:
            break;
        }
        
    }
    
    
    public void setBaseColor() {
        hero1.setBackground(null);
        hero1.setForeground(Color.BLACK);
        hero2.setBackground(null);
        hero2.setForeground(Color.BLACK);
        hero3.setBackground(null);
        hero3.setForeground(Color.BLACK);
        hero4.setBackground(null);
        hero4.setForeground(Color.BLACK);
    }
}
