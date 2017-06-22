package model.game;

import java.util.ArrayList;
import java.util.Arrays;

import util.LogType;
import util.Parameters;



public enum TreasureType {
    EARTH_STONE("Earth Stone"),
    STATUE_OF_THE_WIND("Statue of the Wind"),
    CRYSTAL_OF_FIRE("Crystal of Fire"),
    OCEANS_CHALICE("Ocean's Chalice");
    
    private String label;
    
    
    private TreasureType(String label) {
        this.label = label;
    }
    
    
    /**
     * @author nihil
     *
     */
    public String getPath() {
        Parameters.printLog("get " + Parameters.TREASURES + "Treasure_" + label.replace(" ", "_") + "@2x.png",
                LogType.ACCESS);
        return Parameters.TREASURES + "Treasure_" + label.replace(" ", "_") + "@2x.png";
    }
    
    
    public String getLogo() {
        return Parameters.LOGO + "Treasure_Icon_" + label.replace(" ", "_") + "@2x.png";
    }// end getLogo
    
    
    @Override
    public String toString() {
        return label;
    }
    
    
    public static ArrayList<TreasureType> toList() {
        return new ArrayList<>(Arrays.asList(values()));
    }// end name
}
