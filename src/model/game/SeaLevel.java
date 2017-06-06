package model.game;

public enum SeaLevel {
    LEVEL1("Level 1", 2, "Easy"),
    LEVEL2("Level 2", 2, "Medium"),
    LEVEL3("Level 3", 3, "Hard"),
    LEVEL4("Level 4", 3, "Expert"),
    LEVEL5("Level 5", 3, null),
    LEVEL6("Level 6", 4, null),
    LEVEL7("Level 7", 4, null),
    LEVEL8("Level 8", 5, null),
    LEVEL9("Level 9", 5, null),
    LEVEL10("Level 10", null, null);
    
    private String  levelName;
    private Integer level;
    private String  difficulty;
    
    
    private SeaLevel(String levelName, Integer level, String difficulty) {
        this.levelName = levelName;
        this.level = level;
        this.difficulty = difficulty;
    }
    
    
    public String getLevelName() {
        return levelName;
    }
    
    
    public Integer getLevel() {
        return level;
    }
    
    
    public String getDifficulty() {
        return difficulty;
    }
    
}