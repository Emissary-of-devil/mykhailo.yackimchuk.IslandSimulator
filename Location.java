package org.javarush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    private List<Animals> animals = new ArrayList<>();
    private List<Plants> plants = new ArrayList<>();

    public List<Animals> getAnimals() {
        return animals;
    }

    public List<Plants> getPlants() {
        return plants;
    }

    public void addAnimal(Animals animal){
        animals.add(animal);
    }

    public void removeAnimal(Animals animal){
        animals.remove(animal);
    }

    public void addPlant(Plants plant){
        plants.add(plant);
    }

    public void removePlant(Plants plant){
        plants.remove(plant);
    }

    public void growPlants(){
        while (plants.size()<200){
            plants.add(new Plants());
        }
    }
    public Map<String, Long> countAnimalsByType() {
        Map<String, Long> countMap = new HashMap<>();
        for (Animals animal : animals) {
            countMap.put(animal.getName(), countMap.getOrDefault(animal.getName(), 0L) + 1);
        }
        return countMap;
    }
}
