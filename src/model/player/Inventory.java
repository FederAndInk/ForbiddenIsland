package model.player;

import java.util.ArrayList;
import java.util.Iterator;

import model.adventurers.Adventurer;
import model.card.Card;
import model.card.CardType;
import model.card.TreasureCard;
import model.game.Treasure;
import model.game.TreasureType;
import util.LogType;
import util.Parameters;
import util.exception.CardException;
import util.exception.MissingCardException;



public class Inventory {
    
    private ArrayList<Card>     cards;
    private ArrayList<Treasure> treasures;
    private Adventurer          adventurer;
    private static final int    MAX_CARD = 5;
    
    
    /**
     * @author nihil
     *
     */
    public Inventory(Adventurer adventurer) {
        cards = new ArrayList<>();
        treasures = new ArrayList<>();
        this.adventurer = adventurer;
    }
    
    
    /**
     * @author nihil
     *
     * @return true if the inventory has a card standalone usable
     */
    public boolean hasCardUsable() {
        boolean b = false;
        Iterator<Card> it = cards.iterator();
        while (it.hasNext() && !b) {
            b = it.next().getType().isActivable();
        } // end while
        return b;
    }
    
    
    public boolean hasCard() {
        return !cards.isEmpty();
    }// end hasCard
    
    
    public boolean isFull() {
        return (cards.size() >= MAX_CARD);
    }
    
    
    public void addCard(Card card) throws CardException {
        if (!(getAdventurer().getPlayer().getCurrentAdventurer().getInventory().isFull())) {
            cards.add(card);
        } else {
            Parameters.printLog("l'inventaire est plein", LogType.INFO);
            throw new CardException(card, getAdventurer().getADVENTURER_TYPE());
        }
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
    
    
    public Adventurer getAdventurer() {
        return adventurer;
    }
    
    
    /**
     * @param treasureType
     * @return the specified card or exception
     * @throws MissingCardException
     */
    public Card getCard(TreasureType treasureType) throws MissingCardException {
        Parameters.printLog("Wants " + treasureType, LogType.INFO);
        for (Card i : cards) {
            if (i.getType().equals(CardType.TREASURE_CARD)
                    && ((TreasureCard) i).getTreasureType().equals(treasureType)) {
                return i;
            }
        }
        throw new MissingCardException(treasureType, getAdventurer());
    }
    
    
    /**
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
    
    
    public Card getCard(CardType cardType) {
        for (Card card : cards) {
            if (card.getType().equals(cardType)) {
                return card;
            }
        }
        return null;
    }
}