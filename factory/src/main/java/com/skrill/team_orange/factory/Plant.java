package com.skrill.team_orange.factory;

public class Plant implements FarmProductionInterface {

    private static final int DEFAULT_LIFESTATUS_FOR_AVERAGE_PRICED_PRODUCT = 10;

    boolean alive;
    String name;
    double lifeStatus;

    public Plant(String name) {
        super();
        this.alive = true;
        this.name = name;
        this.lifeStatus = 6;
        System.out.println("Generating the plant: " + name);
    }

    public void populate() {
        System.out.println("Spreading seeds");

    }

    public boolean isAlive() {
        return alive;
    }

    public double getPrice(int avgPrice) {
        return (this.lifeStatus / DEFAULT_LIFESTATUS_FOR_AVERAGE_PRICED_PRODUCT) * avgPrice;
    }

    public void feed() {
        System.out.println("Fertilizing the " + this.name);
        this.lifeStatus++;

    }

    public void water() {
        System.out.println("Watering the " + this.name);
        this.lifeStatus++;
    }

    public void exhaust() {
        if (this.alive) {
            this.lifeStatus--;
            if (this.lifeStatus == 0) {
                this.alive = false;
            }
        } else {
            System.out.println("The plant is no more alive");
        }

    }

    @Override
    public String toString() {
        return this.name;
    }

}
