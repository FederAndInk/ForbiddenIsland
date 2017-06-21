package view.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
    
    
    public playerInventory(AdventurerType adv, boolean top) {
        setLayout(new GridLayout(1, 5));
        setBorder(BorderFactory.createLineBorder(Color.red));
        addCard(CardType.SANDBAG_CARD);
    }
    
    
    public void addCard(CardType cardType) {
        ArrayList<PlayerCard> cards = new ArrayList<>();
        cards.add(new PlayerCard(cardType, cards.size()));
        add(cards.get(cards.size() - 1));
    }// end addCard
    
    
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
