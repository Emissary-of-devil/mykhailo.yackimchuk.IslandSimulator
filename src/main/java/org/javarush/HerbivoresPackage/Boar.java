package org.javarush.HerbivoresPackage;

import org.javarush.Animals;
import org.javarush.Herbivores;
import org.javarush.IslandMap;
import org.javarush.IslandPackage.Cell;
import org.javarush.ProbabilityMatrix;

import java.util.ArrayList;
import java.util.List;

public class Boar extends Herbivores {

    public Boar(boolean isMale){
        super(400,"Boar",50, isMale, 2, 50);
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
        return "\uD83D\uDC17"; // Іконка для ведмедя
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
