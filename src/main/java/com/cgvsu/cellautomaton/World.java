package com.cgvsu.cellautomaton;

import java.util.List;

import static com.cgvsu.cellautomaton.CellState.ALIVE;
import static com.cgvsu.cellautomaton.CellState.DEAD;

public abstract class World{
    protected CellState[][] cells;
    protected int width;
    protected int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new CellState[width][height];

        // Изначально этот мир мертв внутри на этапе создания
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                this.cells[x][y] = CellState.DEAD;
            }
        }
    }

    public void updateWorld(List<Integer> B, List<Integer> S) {
    }

    public void updateWorld(List<Integer> rules) {
    }

    public static boolean getRandomBoolean(double rand) {
        return Math.random() < rand;
    }

    public void randomizeFilling(int x, int y, int width, int height, double rand) {
        for (int xn = x; xn < width + x; xn++) {
            for (int yn = y; yn < height + y; yn++) {
                if (getRandomBoolean(rand)){
                    cells[xn][yn] = ALIVE;
                }
            }
        }
    }

    public boolean isAlive(int x, int y) {
        return cells[x][y] == CellState.ALIVE;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
