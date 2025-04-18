package org.javarush;

public class Island {

    private static Island instance;
    private Location[][] locations;
    private int width;
    private int height;

    private Island(int width, int height) {
        this.width = width;
        this.height = height;
        locations = new Location[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                locations[i][j] = new Location();
            }
        }
    }

    public static Island getInstance(int width, int height) {
        if (instance == null) {
            instance = new Island(width, height);
        }
        return instance;
    }

    public Location getLocation(int j, int i) {
        if (j >= 0 && j < width && i >= 0 && i < height) {
            return locations[i][j];
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

