package model.game;

import java.util.ArrayList;
import java.util.Arrays;



public enum TreasureType {
    EARTH_STONE("The Earth Stone"),
    STATUE_OF_THE_WIND("The Statue Of The Wind"),
    CRYSTAL_OF_FIRE("The Crystal Of Fire"),
    OCEANS_CHALICE("The Ocean's Chalice");
    
    private String label;
    
    
    private TreasureType(String label) {
        this.label = label;
    }
    
    
    @Override
    public String toString() {
        return label;
    }
    
    
    public static ArrayList<TreasureType> toList() {
        return (ArrayList<TreasureType>) Arrays.asList(values());
    }// end name
}
