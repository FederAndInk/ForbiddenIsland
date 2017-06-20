package model.player;

import java.util.ArrayList;

import model.card.Card;
import model.card.CardType;
import model.card.TreasureCard;
import model.game.Treasure;
import model.game.TreasureType;



public class Inventory {
    
    private ArrayList<Card>     cards;
    private ArrayList<Treasure> treasures;
    private static final int    MAX_CARD = 5;
    
    
    /**
     * @author nihil
     *
     */
    public Inventory() {
        cards = new ArrayList<>();
        treasures = new ArrayList<>();
    }
    
    
    /**
     * @author nihil
     *
     * @return true if the inventory has a card standalone usable
     */
    public boolean hasCardUsable() {
        for (Card card : cards) {
            if (card.getType().isUsable()) {
                return true;
            } // end if
        } // end for
        return false;
    }
    
    
    public boolean isFull() {
        return (cards.size() >= MAX_CARD);
    }
    
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    
    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }
    
    
    public boolean removeCard(Card card) {
        return cards.remove(card);
    }
    
    
    public Card removeTreasureCard(TreasureType type) {
        int i = 0;
        Card card = cards.get(i);
        while (!card.getType().equals(CardType.TREASURE_CARD) || ((TreasureCard) card).getTreasureType() != type) {
            i++;
            card = cards.get(i);
        }
        cards.remove(card);
        return card;
    }
    
    
    public int howManyCards(TreasureType type) {
        int nbCards = 0;
        for (Card card : cards) {
            if (card.getType().equals(CardType.TREASURE_CARD)) {
                TreasureCard tCard = (TreasureCard) card;
                if (tCard.getTreasureType() == type) {
                    nbCards = nbCards + 1;
                }
            }
        }
        return nbCards;
    }
}