package org.javarush.HerbivoresPackage;

import org.javarush.Animals;
import org.javarush.Herbivores;
import org.javarush.IslandMap;
import org.javarush.IslandPackage.Cell;
import org.javarush.ProbabilityMatrix;

import java.util.ArrayList;
import java.util.List;

public class Duck extends Herbivores {
    public Duck(boolean isMale){
        super(1,"Duck",200, isMale, 4, 0.15);
    }

    @Override
    public void eat(Cell cell) {
        double neededFood = this.getMaxFood() - this.getCurrentFood();
        if (neededFood <= 0) return;

        // 1. Їмо гусінь
        List<Animals> animalsInCell = new ArrayList<>(cell.getAnimals());
        for (Animals other : animalsInCell) {
            if (this == other) continue;
            if (!(other instanceof Caterpillar)) continue;

            if (ProbabilityMatrix.canEat(this, other)) {
                double foodValue = other.getWeight();
                cell.removeAnimal(other);
                this.setCurrentFood(this.getCurrentFood() + foodValue);

                neededFood = this.getMaxFood() - this.getCurrentFood();
                if (neededFood <= 0) {
                    this.setCurrentFood(this.getMaxFood());
                    return;
                }
            }
        }

        // 2. Їмо рослини
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
        return "\uD83E\uDD86";
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
