package com.cgvsu.cellautomaton;

import java.util.List;
import static com.cgvsu.cellautomaton.CellState.*;

public class LifeWorld extends World{

    public LifeWorld(int width, int height) {
        super(width, height);
    }

    public void updateWorld(List<Integer> B, List<Integer> S) {
        CellState[][] nextState = new CellState[width][height];

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {

                int neighboursAlive = 0;

                //считаем сколько соседей живы
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int xn = x + dx;
                        int yn = y + dy;
                        if (xn < 0 || yn < 0 || xn >= width || yn >= height){
                            break;
                        }
                        else if (cells[xn][yn] == ALIVE){
                            neighboursAlive++;
                        }
                    }
                }

                //Определяем состояние клетки,основываясь на правилах. S(Survival) B(Birth)
                if (cells[x][y] == ALIVE && S.contains(neighboursAlive)) {
                    nextState[x][y] = ALIVE;
                } else if (cells[x][y] == DEAD && B.contains(neighboursAlive)) {
                    nextState[x][y] = ALIVE;
                }
                else nextState[x][y] = DEAD;
            }
        }
        cells = nextState;
    }
}
