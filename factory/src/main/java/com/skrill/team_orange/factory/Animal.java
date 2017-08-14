package com.skrill.team_orange.factory;

public class Animal implements FarmProductionInterface {

    boolean alive;
    double animalPrice;
    String species;
    double lifeStatus;
    private static final int coef = 10;

    public Animal() {
        alive = true;
        species = null;
        lifeStatus = coef;
    }

    public Animal(String species) {
        this.alive = true;
        this.species = species;
        this.lifeStatus = 12;
        System.out.println("Generating the animal " + species);
    }

    public void populate() {
        System.out.println(this.species + "Female get conceived");
    }

    public boolean isAlive() {
        return alive;
    }

    public double getPrice(int avgPrice) {
        animalPrice = (this.lifeStatus / coef) * avgPrice;
        long result = Math.round(animalPrice);
        return result;
    }

    public void feed() {
        System.out.println("Animal is fed" + this.species);
        this.lifeStatus++;
    }

    public void water() {
        System.out.println("Animal is water" + this.species);
        this.lifeStatus++;
    }

    public void exhaust() {
        if (this.alive) {
            this.lifeStatus--;
            if (this.lifeStatus == 0) {
                this.alive = false;
            }
        } else {
            System.out.println("Animal is dead ;(");
        }
    }

    @Override
    public String toString() {
        return this.species;
    }
}
