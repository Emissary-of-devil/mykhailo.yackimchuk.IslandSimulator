package org.javarush;

import org.javarush.HerbivoresPackage.*;
import org.javarush.PredatorsPackage.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ProbabilityMatrix {

    private static final Map<Class<? extends Animals>, Map<Class<? extends Animals>, Integer>> matrix = new HashMap<>();

    static {
        // Приклад для Вовка (Wolf)
        Map<Class<? extends Animals>, Integer> wolfDiet = new HashMap<>();
        wolfDiet.put(Horse.class, 10);
        wolfDiet.put(Deer.class, 15);
        wolfDiet.put(Rabbit.class, 60);
        wolfDiet.put(Mouse.class, 80);
        wolfDiet.put(Goat.class, 60);
        wolfDiet.put(Sheep.class, 70);
        wolfDiet.put(Boar.class, 15);
        wolfDiet.put(Buffalo.class, 10);
        wolfDiet.put(Duck.class, 40);
        matrix.put(Wolf.class, wolfDiet);

        // Удав
        Map<Class<? extends Animals>, Integer> snakeDiet = new HashMap<>();
        snakeDiet.put(Fox.class, 15);
        snakeDiet.put(Rabbit.class, 20);
        snakeDiet.put(Mouse.class, 40);
        snakeDiet.put(Duck.class, 10);
        matrix.put(Snake.class, snakeDiet);

        // Лисиця
        Map<Class<? extends Animals>, Integer> foxDiet = new HashMap<>();
        foxDiet.put(Rabbit.class, 70);
        foxDiet.put(Mouse.class, 90);
        foxDiet.put(Duck.class, 60);
        foxDiet.put(Caterpillar.class, 40);
        matrix.put(Fox.class, foxDiet);

        // Ведмідь
        Map<Class<? extends Animals>, Integer> bearDiet = new HashMap<>();
        bearDiet.put(Snake.class, 80);
        bearDiet.put(Horse.class, 40);
        bearDiet.put(Deer.class, 80);
        bearDiet.put(Rabbit.class, 80);
        bearDiet.put(Mouse.class, 90);
        bearDiet.put(Goat.class, 70);
        bearDiet.put(Sheep.class, 70);
        bearDiet.put(Boar.class, 50);
        bearDiet.put(Buffalo.class, 20);
        bearDiet.put(Duck.class, 10);
        matrix.put(Bear.class, bearDiet);

        // Орел
        Map<Class<? extends Animals>, Integer> eagleDiet = new HashMap<>();
        eagleDiet.put(Fox.class, 10);
        eagleDiet.put(Rabbit.class, 90);
        eagleDiet.put(Mouse.class, 90);
        eagleDiet.put(Duck.class, 80);
        matrix.put(Eagle.class, eagleDiet);

        // Качка (Duck) — їсть гусінь
        Map<Class<? extends Animals>, Integer> duckDiet = new HashMap<>();
        duckDiet.put(Caterpillar.class, 90);
        matrix.put(Duck.class, duckDiet);

        // Кабан
        Map<Class<? extends Animals>, Integer> boarDiet = new HashMap<>();
        boarDiet.put(Mouse.class, 50);
        boarDiet.put(Caterpillar.class, 90);
        matrix.put(Boar.class, boarDiet);

        // Миша
        Map<Class<? extends Animals>, Integer> mouseDiet = new HashMap<>();
        mouseDiet.put(Caterpillar.class, 90);
        matrix.put(Mouse.class, mouseDiet);
    }

    // Повертає шанс
    public static int getProbability(Class<? extends Animals> predator, Class<? extends Animals> prey) {
        return matrix.getOrDefault(predator, new HashMap<>()).getOrDefault(prey, 0);
    }

    public void addChance(Class<? extends Animals> predator, Class<? extends Animals> prey, int chance) {
        if (predator.equals(prey)) return; // тварина не їсть сама себе
        matrix.computeIfAbsent(predator, k -> new HashMap<>()).put(prey, chance);
    }

    // Випадково визначає, чи з’їсть хижак жертву
    public static boolean canEat(Animals predator, Animals prey) {
        int probability = getProbability(predator.getClass(), prey.getClass());
        int chance = ThreadLocalRandom.current().nextInt(100);
        return chance < probability;
    }
    public void printMatrix() {
        for (var predator : matrix.keySet()) {
            System.out.println(predator.getSimpleName() + " eats:");
            for (var prey : matrix.get(predator).keySet()) {
                int chance = matrix.get(predator).get(prey);
                System.out.println("  " + prey.getSimpleName() + " with chance " + chance + "%");
            }
        }
    }
}
