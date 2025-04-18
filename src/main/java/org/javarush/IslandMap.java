package org.javarush;

import org.javarush.IslandPackage.Cell;

public class IslandMap {
    static final int WIDTH = Config.ISLAND_WIDTH;
    static final int HEIGHT = Config.ISLAND_HEIGHT;

    private final Cell[][] grid;

    public IslandMap() {
        grid = new Cell[HEIGHT][WIDTH];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                grid[y][x] = new Cell(); // створюємо порожні клітинки
            }
        }

    }

    public void updateAnimals() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                grid[y][x].updateAnimalsInCell(x, y, this);
            }
        }
    }

    public void growPlants() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                grid[y][x].growPlants();
            }
        }
    }

    public void printIsland() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                System.out.print(grid[y][x].getCellRepresentation() + " ");
            }
            System.out.println();
        }
    }

    public boolean areAllAnimalsDead() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (!grid[y][x].getAnimals().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            return grid[y][x];
        }
        return null;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
