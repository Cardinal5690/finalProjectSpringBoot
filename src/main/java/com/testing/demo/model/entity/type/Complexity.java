package com.testing.demo.model.entity.type;

public enum Complexity {
    EASY(0),
    MIDDLE(1),
    DIFFICULT(2);

    private final int LEVEL;

    Complexity(int LEVEL) {
        this.LEVEL = LEVEL;
    }

    public int getLEVEL() {
        return LEVEL;
    }
}
