package model.card;

public abstract class Card {
    private CardType type;
    
    
    /**
     * @author nihil
     *
     */
    protected Card(CardType type) {
        this.setType(type);
    }


    /**
     * @return the type
     */
    public CardType getType() {
        return type;
    }


    /**
     * @param type the type to set
     */
    public void setType(CardType type) {
        this.type = type;
    }
}