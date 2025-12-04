package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.util.Grid;

public class Day4 extends Day {
    private static final char PAPER_ROLL = '@';
    private static final char COLLECTED = 'x';
    private static final int CARRY_LIMIT = 4;

    public Day4() {
        super(4, "Day 4: Printing Department");
    }

    private static int collectPaperRolls(Grid paperRollGrid) {
        char[][] gridAfter = paperRollGrid.cloneGrid();

        int paperRollsCollected = 0;
        for (int y = 0; y < paperRollGrid.height; ++y) {
            for (int x = 0; x < paperRollGrid.width; ++x) {
                if (paperRollGrid.at(x,y) != PAPER_ROLL) {
                    continue;
                }

                int paperRolls = countPaperRollsInAdjacentPositions(paperRollGrid, x, y);
                if (paperRolls < CARRY_LIMIT) {
                    gridAfter[y][x] = COLLECTED;
                    ++paperRollsCollected;
                }
            }
        }
        paperRollGrid.grid = gridAfter;
        return paperRollsCollected;
    }

    private static int countPaperRollsInAdjacentPositions(Grid paperRollGrid, int startX, int startY) {
        int endX = Math.min(paperRollGrid.width - 1, startX + 1);
        int endY = Math.min(paperRollGrid.height - 1, startY + 1);
        startX = Math.max(0, startX - 1);
        startY = Math.max(0, startY - 1);
        int paperRolls = 0;

        for (int y = startY; y <= endY; ++y) {
            for (int x = startX; x <= endX; ++x) {
                if (paperRollGrid.at(x, y) == PAPER_ROLL) {
                    ++paperRolls;
                }
            }
        }
        return paperRolls - 1;
    }

    @Override
    public Object partOne() {
        Grid paperRollGrid = new Grid(input.asCharacterGrid());
        return collectPaperRolls(paperRollGrid);
    }

    @Override
    public Object partTwo() {
        Grid paperRollGrid = new Grid(input.asCharacterGrid());
        int result = 0;
        int paperRollsCollected = 1;
        while (paperRollsCollected > 0) {
            paperRollsCollected = collectPaperRolls(paperRollGrid);
            result += paperRollsCollected;
        }
        return result;
    }
}
