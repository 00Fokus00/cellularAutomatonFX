package com.cgvsu.cellautomaton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cgvsu.cellautomaton.CellState.*;

public class WolframWorld extends World{

    public WolframWorld(int width, int height) {
        super(width, height);
        cells[width / 2][0] = CellState.ALIVE;
    }

    public void updateWorld(List<Integer> W) {
        CellState[][] nextState = new CellState[width][height];
        List<CellState> rules = new ArrayList<>();
        for (Integer i : W){
            if (i == 1){
                rules.add(ALIVE);
            }
            else {
                rules.add(DEAD);
            }
        }

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height - 1; ++y) {

                // Определение состояния соседей в виде трехбитного числа
                int left = (x > 0 && cells[x - 1][y] == ALIVE) ? 1 : 0;
                int center = (cells[x][y] == ALIVE) ? 1 : 0;
                int right = (x < width - 1 && cells[x + 1][y] == ALIVE) ? 1 : 0;

                int ruleIndex = left * 4 + center * 2 + right;

                cells[x][y + 1] = rules.get(ruleIndex);
            }
        }
    }
}
