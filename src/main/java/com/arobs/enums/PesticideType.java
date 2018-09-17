package com.arobs.enums;

public enum PesticideType {
    ACARICIDE (1) ,
    HERBICIDE (2),
    FUNGICIDE (3),
    INSECTOFUNGICIDE (4),
    GROWTH_REGULATION (5),
    DEFOLIANT_DESICCANT (6),
    PHEROMONE (7),
    INSECTICIDE (8),
    NEMATOCIDE_SOIL_DESINFECTANT (9),
    MISCELLANEOUS_AUXILIARY_PRODUCT (10),
    RODENTICIDE_MOLLUSCICIDE_REPELLANT (11);

    private final int type;

    private PesticideType(int type) {
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