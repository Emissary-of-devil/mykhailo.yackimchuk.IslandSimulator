package org.javarush;

import org.javarush.IslandPackage.Cell;

import java.util.List;

public abstract class Animals {

    protected double weight;
    protected String name;
    protected int maxCapacity;
    protected boolean isMale;
    protected int speed;
    protected double foodRequired;
    protected double foodLevel;
    protected boolean isAlive = true;

    public Animals(double weight, String name, int maxCapacity, boolean isMale, int speed, double foodRequired) {
        this.weight = weight;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.isMale = isMale;
        this.speed = speed;
        this.foodRequired = foodRequired;
    }

    public abstract void eat(Cell cell);
    public abstract void toReproduce(Cell cell, List<Animals> newAnimals);
    public abstract void movement(IslandMap map, int currentX, int currentY);
    public abstract String getIcon();
    public boolean isAlive(){
        return isAlive;
    }

    public boolean isMale(){
        return isMale;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getMaxFood() {
        return foodRequired;
    }

    public double getCurrentFood() {
        return foodLevel;
    }


    public void setCurrentFood(double currentFood) {
        this.foodLevel = currentFood;
    }
}
