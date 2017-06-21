package view.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.adventurers.AdventurerType;
import model.card.CardType;
import util.Parameters;
import view.Cards.PlayerCard;



public class playerInventory extends JPanel {
    private JPanel                inventory;
    private ArrayList<PlayerCard> cards;
    private boolean               left;
    private GridBagLayout         lT;
    private GridBagConstraints    gLT;
    
    
    public playerInventory(AdventurerType adv, boolean left) {
        cards = new ArrayList<>();
        setBorder(BorderFactory.createLineBorder(Color.red));
        lT = new GridBagLayout();
        double[] nb = { 0.2, 0.2, 0.2, 0.2, 0.2 };
        lT.columnWeights = nb;
        // to set the number of columns
        int[] nb2 = new int[5];
        lT.columnWidths = nb2;
        gLT = new GridBagConstraints();
        setLayout(lT);
        gLT.gridy = 0;
        gLT.gridheight = 1;
        gLT.gridwidth = 1;
        gLT.weighty = 1;
        
        gLT.fill = GridBagConstraints.BOTH;
        
        addCard(CardType.SANDBAG_CARD);
        addCard(CardType.SANDBAG_CARD);
        addCard(CardType.SANDBAG_CARD);
    }
    
    
    public void addCard(CardType cardType) {
        cards.add(new PlayerCard(cardType, cards.size()));
        gLT.gridx = cards.size() - 1;
        add(cards.get(cards.size() - 1), gLT);
    }// end
     // addCard
    
    
    /**
     * @author nihil
     *
     * @param cardPlace
     * place of the card in the player inventory to remove
     */
    public void removeCard(int cardPlace) {
        remove(cards.remove(cardPlace));
    }// end removeCard
    
    
    @Override
    public Dimension getPreferredSize() {
        Dimension dim = Parameters.appSize;
        return new Dimension((int) (dim.getWidth() * 0.3), (int) (dim.getHeight() * 0.1));
    }
    
}
