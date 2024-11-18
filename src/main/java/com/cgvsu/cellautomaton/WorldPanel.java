package com.cgvsu.cellautomaton;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WorldPanel {

    World world;

    public WorldPanel(LifeWorld world) {
        this.world = world;
    }

    public WorldPanel(WolframWorld world) {
        this.world = world;
    }

    public int cellSize = 5;

    public void drawCelluralWorld(GraphicsContext gc, double canvasWidth, double canvasHeight) {
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                if(world.isAlive(x,y)){
                    gc.setFill(Color.BLACK);

                    double drawX = (canvasWidth / 2) + (x - world.getWidth() / 2) * cellSize;
                    double drawY = (canvasHeight / 2) + (y - world.getHeight() / 2) * cellSize;

                    gc.fillRect(drawX, drawY, cellSize, cellSize);
                }
            }
        }
    }
}
