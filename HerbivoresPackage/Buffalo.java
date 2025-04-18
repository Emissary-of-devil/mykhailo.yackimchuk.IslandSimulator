package org.javarush.HerbivoresPackage;

import org.javarush.Animals;
import org.javarush.Herbivores;
import org.javarush.IslandMap;
import org.javarush.IslandPackage.Cell;

import java.util.List;

public class Buffalo extends Herbivores {

    public Buffalo(boolean isMale){
        super(700,"Buffalo",10, isMale, 3, 100);
    }

    @Override
    public void eat(Cell cell) {
        double neededFood = this.getMaxFood() - this.getCurrentFood();
        if (neededFood <= 0) return;

        int availablePlants = cell.getPlantCount();
        if (availablePlants > 0) {
            double plantEaten = Math.min(availablePlants, neededFood);
            cell.consumePlants((int) plantEaten);
            this.setCurrentFood(this.getCurrentFood() + plantEaten);

            if (this.getCurrentFood() > this.getMaxFood()) {
                this.setCurrentFood(this.getMaxFood());
            }
        }
    }
    public String getIcon() {
        return "\uD83D\uDC03"; // Іконка для ведмедя
    }


    @Override
    public void toReproduce(Cell cell, List<Animals> newAnimals) {
        super.toReproduce(cell, newAnimals);
    }

    @Override
    public void movement(IslandMap map, int x, int y) {
        super.movement(map, x, y);
    }
}
