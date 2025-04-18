package org.javarush.IslandPackage;
import org.javarush.Animals;
import org.javarush.IslandMap;
import org.javarush.Plants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Cell {

    private final List<Animals> animals = new CopyOnWriteArrayList<>();
    private int plantCount = 0;
    private static final int MAX_PLANTS = 200;

    public List<Animals> getAnimals() {
        return animals;
    }

    public void addAnimal(Animals animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animals animal) {
        animals.remove(animal);
    }

    public void updateAnimalsInCell(int x, int y, IslandMap map) {
        List<Animals> newAnimals = new ArrayList<>();

        for (Animals animal : animals) {
            if (!animal.isAlive()) continue;

            animal.eat(this);
            animal.toReproduce(this, newAnimals);
            animal.movement(map, x, y);
        }

        animals.addAll(newAnimals);


        animals.removeIf(a -> !a.isAlive());
    }

    public void growPlants() {
        if (plantCount < MAX_PLANTS) {
            plantCount += 1;
        }
    }

    public int getPlantCount() {
        return plantCount;
    }

    public void setPlantCount(int plantCount) {
        this.plantCount = plantCount;
    }

    public void consumePlants(int amount) {
        plantCount = Math.max(plantCount - amount, 0);
    }

    public String getCellRepresentation() {
        if (animals.isEmpty()) {
            return plantCount > 0 ? "🌿" : " . ";
        }

        Animals example = animals.get(0);
        return example.getIcon(); // 🐺 🐃 🐐 тощо
    }

}
