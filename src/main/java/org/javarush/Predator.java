package org.javarush;

import org.javarush.IslandPackage.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animals{

    public Predator(double weight, String name, int maxCapacity, boolean isMale, int speed, double foodRequired) {
        super(weight, name, maxCapacity, isMale, speed, foodRequired);
    }

    @Override
    public void eat(Cell cell) {
        for (Animals prey : new ArrayList<>(cell.getAnimals())) {
            if (prey instanceof Herbivores && prey.isAlive() && prey != this) {
                int chance = ProbabilityMatrix.getProbability(this.getClass(), prey.getClass());
                if (ThreadLocalRandom.current().nextInt(100) < chance) {
                    cell.getAnimals().remove(prey);
                    foodLevel = Math.min(foodRequired, foodLevel + prey.getWeight());
                    return;
                }
            }
        }
        // Не з'їв — зменшується рівень ситості
        foodLevel -= 30;
        if (foodLevel <= 0) {
            this.isAlive = false;
        }
    }


    @Override
    public void toReproduce(Cell cell, List<Animals> newAnimals) {
        long count = cell.getAnimals().stream()
                .filter(a -> a.getClass() == this.getClass() && a.isMale() != this.isMale())
                .count();
        if (count > 0) {
            try {
                Animals baby = this.getClass()
                        .getDeclaredConstructor(boolean.class)
                        .newInstance(Math.random() < 0.5);
                newAnimals.add(baby);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void movement(IslandMap map, int x, int y) {
        if (!isAlive()) return;

        int dx = ThreadLocalRandom.current().nextInt(-speed, speed + 1);
        int dy = ThreadLocalRandom.current().nextInt(-speed, speed + 1);

        int newX = Math.max(0, Math.min(IslandMap.WIDTH - 1, x + dx));
        int newY = Math.max(0, Math.min(IslandMap.HEIGHT - 1, y + dy));

        if (newX != x || newY != y) {
            Cell currentCell = map.getCell(x, y);
            Cell targetCell = map.getCell(newX, newY);

            long countSameSpecies = targetCell.getAnimals().stream()
                    .filter(a -> a.getClass().equals(this.getClass()))
                    .count();

            if (countSameSpecies < maxCapacity) {
                currentCell.getAnimals().remove(this);
                targetCell.getAnimals().add(this);
            }
        }
    }
}
