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
    private double maxFood;
    private double currentFood;

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
    public abstract boolean isAlive();

    public boolean isMale(){
        return isMale;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public Animals(double maxFood) {
        this.maxFood = maxFood;
        this.currentFood = 0; // початкова кількість їжі
    }

    public double getMaxFood() {
        return maxFood;
    }

    public double getCurrentFood() {
        return currentFood;
    }


    public void setCurrentFood(double currentFood) {
        this.currentFood = currentFood;
    }
}
