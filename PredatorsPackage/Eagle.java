package org.javarush.PredatorsPackage;

import org.javarush.Animals;
import org.javarush.IslandMap;
import org.javarush.IslandPackage.Cell;
import org.javarush.Predator;

import java.util.List;

public class Eagle extends Predator {

    public Eagle(boolean isMale) {
        super(6, "Eagle", 20, isMale, 3, 1);
    }

    @Override
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
        return "\uD83E\uDD85";
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
