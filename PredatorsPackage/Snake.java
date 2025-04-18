package org.javarush.PredatorsPackage;

import org.javarush.Animals;
import org.javarush.IslandMap;
import org.javarush.IslandPackage.Cell;
import org.javarush.Predator;

import java.util.List;

public class Snake extends Predator {


    public Snake(boolean isMale) {
        super(15, "Snake", 30, isMale, 1, 3);
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
        return "\uD83D\uDC0D";
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
