package com.arobs.enums;

public enum UnitOfMeasure {
    Tone("t"),
    Quintal("q"),
    Kilogram("kg"),
    Litre("l"),
    SowingUnit("su");

    private String unitOfMeasure;

    public String getUnitOfMeasure() {
        return this.unitOfMeasure;
    }
    UnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
