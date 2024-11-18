package com.cgvsu.cellautomaton;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MandelbrotAutomaton {
    private final int width;
    private final int height;
    private final int maxIterations;
    private final double xMin, xMax, yMin, yMax;
    private final int[][] iterations; // Массив для хранения числа итераций

    public MandelbrotAutomaton(int width, int height, int maxIterations, double xMin, double xMax, double yMin, double yMax) {
        this.width = width;
        this.height = height;
        this.maxIterations = maxIterations;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.iterations = new int[width][height];
        generateMandelbrotSet();
    }

    // Метод для генерации фрактала Мандельброта
    private void generateMandelbrotSet() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double a = xMin + x * (xMax - xMin) / width;
                double b = yMin + y * (yMax - yMin) / height;

                double real = a;
                double imag = b;
                int iteration = 0;

                while (real * real + imag * imag <= 4 && iteration < maxIterations) {
                    double tempReal = real * real - imag * imag + a;
                    imag = 2 * real * imag + b;
                    real = tempReal;
                    iteration++;
                }
                iterations[x][y] = iteration;
            }
        }
    }

    public void draw(GraphicsContext gc, double canvasWidth, double canvasHeight) {
        double cellWidth = canvasWidth / width;
        double cellHeight = canvasHeight / height;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int iter = iterations[x][y];
                if (iter == maxIterations) {
                    gc.setFill(Color.BLACK); // Точки множества Мандельброта
                } else {
                    // Градиент цвета для точек, не принадлежащих множеству
                    gc.setFill(Color.RED);
                }
                gc.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
            }
        }
    }
}
