package com.skrill.team_orange.factory;

public abstract class ProdFactory {

    public static FarmProductionInterface getProduction(String creatureType, String creatureName) {
        if ("animal".equals(creatureType)) {
            return new Animal(creatureName);
        } else if ("plant".equals(creatureType)) {
            return new Plant(creatureName);
        } else {
            return null;
        }
    }
}
