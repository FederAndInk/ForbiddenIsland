package model.game;

import util.exception.EndGameException;



public enum SeaLevel {
    /**
     * Easy
     */
    LEVEL1("Level 1", 2, "Easy"),
    /**
     * Medium
     */
    LEVEL2("Level 2", 2, "Medium"),
    /**
     * Hard
     */
    LEVEL3("Level 3", 3, "Hard"),
    /**
     * Expert
     */
    LEVEL4("Level 4", 3, "Expert"),
    LEVEL5("Level 5", 3, null),
    LEVEL6("Level 6", 4, null),
    LEVEL7("Level 7", 4, null),
    LEVEL8("Level 8", 5, null),
    LEVEL9("Level 9", 5, null),
    LEVEL10("Level 10", null, null);
    
    private String  levelName;
    private Integer nbCards;
    private String  difficulty;
    
    
    private SeaLevel(String levelName, Integer nbCards, String difficulty) {
        this.levelName = levelName;
        this.nbCards = nbCards;
        this.difficulty = difficulty;
    }
    
    
    public String getLevelName() {
        return levelName;
    }
    
    
    public Integer getNbCards() {
        return nbCards;
    }
    
    
    public String getDifficulty() {
        return difficulty;
    }
    
    
    /**
     * @author nihil
     * @return true if this is the last level
     *
     */
    public boolean isLast() {
        return equals(LEVEL10);
    }
    
    public SeaLevel next() {
        return values()[ordinal() + 1 % values().length];
    }
}