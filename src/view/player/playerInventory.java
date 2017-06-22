package view.player;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import model.adventurers.AdventurerType;
import model.card.CardType;
import util.Parameters;
import view.Cards.PlayerCard;
import view.board.TreasureComponent;



public class playerInventory extends JPanel {
    private JPanel                inventory;
    private ArrayList<PlayerCard> cards;
    private boolean               left;
    private GridBagLayout         lT;
    private GridBagConstraints    gLT;
    private JPanel                treasure;
    
    
    public playerInventory(AdventurerType adv, boolean left) {
        cards = new ArrayList<>();
        
        setBorder(BorderFactory.createLineBorder(Color.red));
        
        initLayout();
        initTreasures();
        
        addCard(CardType.SANDBAG_CARD);
        addCard(CardType.SANDBAG_CARD);
        addCard(CardType.SANDBAG_CARD);
        addCard(CardType.SANDBAG_CARD);
        addCard(CardType.SANDBAG_CARD);
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initTreasures() {
        treasure = new JPanel(new GridLayout(2, 2));
        treasure.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.BLACK));
        gLT.gridx = 5;
        add(treasure, gLT);
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initLayout() {
        lT = new GridBagLayout();
        double[] nb = { 0.2, 0.2, 0.2, 0.2, 0.2, 0.4 };
        lT.columnWeights = nb;
        // to set the number of columns
        int[] nb2 = new int[6];
        lT.columnWidths = nb2;
        gLT = new GridBagConstraints();
        
        setLayout(lT);
        gLT.gridy = 0;
        gLT.gridheight = 1;
        gLT.gridwidth = 1;
        gLT.weighty = 1;
        
        gLT.fill = GridBagConstraints.BOTH;
    }
    
    
    public void addCard(CardType cardType) {
        cards.add(new PlayerCard(cardType, cards.size()));
        gLT.gridx = cards.size() - 1;
        add(cards.get(cards.size() - 1), gLT);
    }// end
     // addCard
    
    
    public void addTreasure() {
        treasure.add(new TreasureComponent());
    }// end name
    
    
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
