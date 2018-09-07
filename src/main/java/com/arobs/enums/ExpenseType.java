package com.arobs.enums;

public enum ExpenseType {
    HR  (1),
    MACHINERY_OWN(2),
    MACHINERY_RENT(3),
    FUEL (4),
    SEEDS  (5),
    PESTICIDES(6),
    FERTILIZERS (7),
    LAND (8),
    OTHERS (10);


    private final int type;

    private ExpenseType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}