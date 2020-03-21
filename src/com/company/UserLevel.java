package com.company;

public enum UserLevel {
    BEGINNER(0),
    AMATEUR(1),
    PRO(2),
    LEGEND(3);

    public static UserLevel getLevel(int level){
        switch(level){
            case 0: return BEGINNER;
            case 1: return AMATEUR;
            case 2: return PRO;
            case 3: return LEGEND;
        }
        throw new RuntimeException("The level can't be recognized!");
    }

    private int levelRank;
    UserLevel(int rank){
        this.levelRank = rank;
    }

    public int getLevelRank() {
        return levelRank;
    }
}
