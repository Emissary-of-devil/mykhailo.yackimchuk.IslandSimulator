package org.javarush;

import org.javarush.HerbivoresPackage.Caterpillar;
import org.javarush.HerbivoresPackage.Duck;
import org.javarush.IslandPackage.Cell;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Herbivores extends Animals {

    public Herbivores(double weight, String name, int maxCapacity, boolean isMale, int speed, double foodRequired) {
        super(weight, name, maxCapacity, isMale, speed, foodRequired);
    }

    @Override
    public void eat(Cell cell) {
        if (this instanceof Duck) {
            // Качка їсть гусінь
            Optional<Animals> caterpillar = cell.getAnimals().stream()
                    .filter(a -> a instanceof Caterpillar)
                    .findAny();
            caterpillar.ifPresent(c -> {
                cell.getAnimals().remove(c);
                this.foodLevel = Math.min(this.foodRequired, this.foodRequired);
            });
        } else {
            // Інші травоїдні їдять рослини
            if (cell.getPlantCount() > 0) {
                int toEat = (int) Math.min(foodRequired, cell.getPlantCount());
                foodLevel = Math.min(foodRequired, foodLevel + toEat);
                cell.consumePlants(toEat);
            }
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

    @Override
    public String getIcon() {
        return "";
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    protected boolean isHerbivore() {
        return true ;
    }
}
