package view.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.GameController;
import model.adventurers.AdventurerType;
import model.card.CardType;
import model.game.TreasureType;
import util.LogType;
import util.Parameters;
import view.Cards.PlayerCard;
import view.board.TreasureComponent;



public class PlayerInventory extends JPanel {
    private JPanel                inventory;
    private ArrayList<PlayerCard> cards;
    private boolean               left;
    private boolean               top;
    private GridBagConstraints    gLCC;
    private GridBagConstraints    gLCT;
    private JPanel                treasure;
    private Observer              obs;
    
    
    public PlayerInventory(AdventurerType adv, boolean left, boolean top) {
        cards = new ArrayList<>();
        this.left = left;
        this.top = top;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        initLayout();
        initTreasures();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initTreasures() {
        treasure = new JPanel();
        GridBagLayout lT = new GridBagLayout();
        gLCT = new GridBagConstraints();
        lT.columnWidths = new int[2];
        lT.rowHeights = new int[2];
        double[] nb = { 0.5, 0.5 };
        lT.columnWeights = nb;
        lT.rowWeights = nb;
        
        treasure.setLayout(lT);
        if (left) {
            treasure.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
        } else {
            treasure.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
        } // end if
        gLCT.gridheight = 1;
        gLCT.gridwidth = 1;
        gLCT.weighty = 1;
        gLCT.fill = GridBagConstraints.BOTH;
        
        gLCC.gridx = left ? 0 : 6; // 0 for left *...... inventories and 6 for right ......*
        add(treasure, gLCC);
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initLayout() {
        GridBagLayout lT = new GridBagLayout();
        if (left) {
            double[] nb = { 0.25, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2 };
            lT.columnWeights = nb;
        } else {
            double[] nb = { 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.25 };
            lT.columnWeights = nb;
        } // end if
          // to set the number of columns
        int[] nb2 = new int[7];
        lT.columnWidths = nb2;
        gLCC = new GridBagConstraints();
        
        setLayout(lT);
        gLCC.gridy = 0;
        gLCC.gridheight = 1;
        gLCC.gridwidth = 1;
        gLCC.ipadx = 10;
        gLCC.weighty = 1;
        gLCC.fill = GridBagConstraints.BOTH;
    }
    
    
    public void addCard(CardType cardType) {
        Parameters.printLog(
                "Add card " + cardType.getName() + " to " + (top ? "top" : "bottom") + "-" + (left ? "left" : "right"),
                LogType.INFO);
        if (left) {
            cards.add(new PlayerCard(cardType, cards.size() + 1, obs));
            gLCC.gridx = cards.size();
            add(cards.get(cards.size() - 1), gLCC);
        } else {
            cards.add(new PlayerCard(cardType, 5 - cards.size(), obs));
            gLCC.gridx = 6 - cards.size();
            add(cards.get(cards.size() - 1), gLCC);
        } // end if
        Parameters.printLog("Adding card to " + gLCC.gridx, LogType.INFO);
        doLayout();
    }// end
     // addCard
    
    
    public void addTreasure(TreasureType type) {
        Parameters.printLog("Add treasure " + type + " to inventory", LogType.GRAPHICS);
        int nb = (treasure.getComponentCount()) % 2;
        if (left && top) {
            gLCT.gridx = nb;
            gLCT.gridy = ((((treasure.getComponentCount() - nb) + 1) % 3) + 1) % 2;
        } else if (!left && top) {
            gLCT.gridx = (nb + 1) % 2;
            gLCT.gridy = ((((treasure.getComponentCount() - nb) + 1) % 3) + 1) % 2;
        } else if (left && !top) {
            gLCT.gridx = nb;
            gLCT.gridy = (((treasure.getComponentCount() - nb) + 1) % 3) % 2;
        } else {
            gLCT.gridx = (nb + 1) % 2;
            gLCT.gridy = (((treasure.getComponentCount() - nb) + 1) % 3) % 2;
        } // end if
        treasure.add(new TreasureComponent(type, false), gLCT);
    }// end
     // name
    
    
    /**
     * @author nihil
     *
     * @param cardPlace
     * place of the card in the player inventory to remove
     */
    public void removeCard(int cardPlace) {
        if (left) {
            remove(cards.remove(cardPlace - 1));
            for (int i = cardPlace; i < cards.size(); i++) {
                remove(cards.get(i));
                gLCC.gridx = i + 1;
                add(cards.get(i).setCardPlace(i + 1), gLCC);
            } // end for
        } else {
            remove(cards.remove(5 - cardPlace));
            for (int i = cardPlace; i > 5 - cards.size(); i--) {
                remove(cards.get(5 - i));
                gLCC.gridx = i;
                add(cards.get(5 - i).setCardPlace(cardPlace - cards.size() + 1), gLCC);
            } // end for
        } // end if
        doLayout();
    }// end removeCard
    
    
    /**
     * @author nihil
     *
     */
    public PlayerCard getCard(int i) {
        return cards.get(left ? i - 1 : 5 - i);
    }
    
    
    /**
     * @return the cards
     */
    public ArrayList<PlayerCard> getCards() {
        return cards;
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        Dimension dim = Parameters.appSize;
        return new Dimension((int) (dim.getWidth() * 0.3), (int) (dim.getHeight() * 0.1));
    }
    
    
    /**
     * @author nihil
     *
     */
    public void addObs(Observer obs) {
        if (obs instanceof GameController) {
            this.obs = obs;
        } // end if
    }
    
}
