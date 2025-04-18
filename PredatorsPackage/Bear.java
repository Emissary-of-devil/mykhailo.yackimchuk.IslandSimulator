package org.javarush.PredatorsPackage;

import org.javarush.Animals;
import org.javarush.IslandMap;
import org.javarush.IslandPackage.Cell;
import org.javarush.Predator;

import java.util.List;

public class Bear extends Predator {
    public Bear(boolean isMale) {
        super(500,"Bear", 2, isMale, 5 , 80);
    }

    public void eat(Cell cell) {
        super.eat(cell);
    }


    @Override
    public void toReproduce(Cell cell, List<Animals> newAnimals) {
        super.toReproduce(cell, newAnimals);
    }

    @Override
    public void movement(IslandMap map, int x, int y) {
        super.movement(map, x, y);
    }

    @Override
    public String getIcon() {
        return "\uD83D\uDC3B";
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
