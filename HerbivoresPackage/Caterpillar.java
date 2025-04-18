package org.javarush.HerbivoresPackage;

import org.javarush.Animals;
import org.javarush.Herbivores;
import org.javarush.IslandMap;
import org.javarush.IslandPackage.Cell;

import java.util.List;

public class Caterpillar extends Herbivores {

    public Caterpillar(boolean isMale){
        super(0.01,"Caterpillar",1000, isMale, 0, 0);
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
        return "\uD83D\uDC1B";
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
