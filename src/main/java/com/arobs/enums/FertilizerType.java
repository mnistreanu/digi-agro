package com.arobs.enums;

public enum FertilizerType {
    MINERAL  (1),
    CHEMICAL(2),
    ORGANIC(3);


    private final int type;

    private FertilizerType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "" + type;
    }
}