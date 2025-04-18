package org.javarush;

import org.javarush.HerbivoresPackage.*;
import org.javarush.IslandPackage.Cell;
import org.javarush.PredatorsPackage.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class IslandSimulationRunner {

    private static final int TICK_DURATION = Config.SIMULATION_TICK_DURATION_MS;

    private final IslandMap islandMap;
    private final ScheduledExecutorService scheduler;

    public IslandSimulationRunner() {
        this.islandMap = new IslandMap();
        this.scheduler = Executors.newScheduledThreadPool(3);
        initializeIsland(); // <== додаємо ініціалізацію
    }

    public void startSimulation() {
        scheduler.scheduleAtFixedRate(this::animalLifecycleTask, 0, TICK_DURATION, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(this::plantGrowthTask, 0, TICK_DURATION * 5, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(this::statisticsTask, 0, TICK_DURATION, TimeUnit.MILLISECONDS);
    }

    private void initializeIsland() {
        int width = islandMap.getWidth();
        int height = islandMap.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = islandMap.getCell(x, y);

                int animalsPerCell = ThreadLocalRandom.current().nextInt(1, 5);
                for (int i = 0; i < animalsPerCell; i++) {
                    Animals animal = createRandomAnimal();
                    if (animal != null) {
                        cell.addAnimal(animal);
                    }
                }

                // Додаємо рослини
                int plantCount = ThreadLocalRandom.current().nextInt(10, 30);
                cell.setPlantCount(plantCount);

            }
        }
    }

    private void animalLifecycleTask() {
        islandMap.updateAnimals();
    }

    private void plantGrowthTask() {
        islandMap.growPlants();
    }

    private void statisticsTask() {
        islandMap.printIsland();
        if (islandMap.areAllAnimalsDead()) {
            System.out.println("Усі тварини загинули. Симуляцію завершено.");
            scheduler.shutdown();
        }
    }
    private Animals createRandomAnimal() {
        int animalType = ThreadLocalRandom.current().nextInt(0, 15);
        return createAnimal(animalType);
    }


    public Animals createAnimal(int animalType) {
        boolean isMale = randomGender(); // Генеруємо стать

        return switch (animalType) {
            case 0 -> new Boar(isMale);
            case 1 -> new Buffalo(isMale);
            case 2 -> new Caterpillar(isMale);
            case 3 -> new Deer(isMale);
            case 4 -> new Duck(isMale);
            case 5 -> new Goat(isMale);
            case 6 -> new Horse(isMale);
            case 7 -> new Mouse(isMale);
            case 8 -> new Rabbit(isMale);
            case 9 -> new Sheep(isMale);
            case 10 -> new Bear(isMale);
            case 11 -> new Eagle(isMale);
            case 12 -> new Fox(isMale);
            case 13 -> new Snake(isMale);
            case 14 -> new Wolf(isMale);
            default -> null;
        };
    }

    private static boolean randomGender() {
        return ThreadLocalRandom.current().nextBoolean();
    }


    public static void main(String[] args) {
        IslandSimulationRunner runner = new IslandSimulationRunner();
        runner.startSimulation();
    }
}
