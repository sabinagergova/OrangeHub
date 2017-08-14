package com.skrill.team_orange.factory;

public interface FarmProductionInterface {

    public void populate();
    public boolean isAlive();
    public double getPrice(int avgPrice);
    public void feed();
    public void water();
    public void exhaust();


}
