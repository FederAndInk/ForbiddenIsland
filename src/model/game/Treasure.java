package model.game;

public class Treasure {
    
    private TreasureType name;
    
    
    public Treasure(TreasureType name) {
        this.name = name;
    }
    
    
    public TreasureType getName() {
        return name;
    }
    
}