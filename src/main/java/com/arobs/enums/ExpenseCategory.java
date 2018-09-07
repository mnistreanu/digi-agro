package com.arobs.enums;

public enum ExpenseCategory {
    HR  (1),
    MACHINERY_OWN(2),
    MACHINERY_RENT(3),
    FUEL (4),
    SEEDS  (5),
    PESTICIDES(6),
    FERTILIZERS (7),
    LAND (8),
    OTHERS (10);


    private final int category;

    private ExpenseCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return "" + category;
    }
}