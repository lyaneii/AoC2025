package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;

public class Day4 extends Day {
    private static final char PAPER_ROLL = '@';
    private static final char COLLECTED = 'x';
    private static final int CARRY_LIMIT = 4;

    public Day4() {
        super(4, "Day 4: Printing Department");
    }

    private static int collectPaperRolls(char[][] paperRollGrid) {
        int paperRollsCollected = 0;

        for (int y = 0; y < paperRollGrid.length; ++y) {
            for (int x = 0; x < paperRollGrid[0].length; ++x) {
                if (paperRollGrid[y][x] != PAPER_ROLL) {
                    continue;
                }

                int paperRolls = countPaperRollsInAdjacentPositions(paperRollGrid, x, y);
                if (paperRolls < CARRY_LIMIT) {
                    paperRollGrid[y][x] = COLLECTED;
                    ++paperRollsCollected;
                }
            }
        }
        return paperRollsCollected;
    }

    private static int countPaperRollsInAdjacentPositions(char[][] paperRollGrid, int startX, int startY) {
        int rowStart = Math.max(0, startX - 1);
        int rowEnd = Math.min(paperRollGrid[0].length - 1, startX + 1);
        int currentColumn = Math.max(0, startY - 1);
        int columnEnd = Math.min(paperRollGrid.length - 1, startY + 1);
        int paperRolls = 0;

        for (;currentColumn <= columnEnd; ++currentColumn) {
            for (int currentRow = rowStart; currentRow <= rowEnd; ++currentRow) {
                if (paperRollGrid[currentColumn][currentRow] == PAPER_ROLL) {
                    ++paperRolls;
                }
            }
        }
        return paperRolls - 1;
    }

    @Override
    public Object partOne() {
        return collectPaperRolls(input.asCharacterGrid());
    }

    @Override
    public Object partTwo() {
        char[][] paperRollGrid = input.asCharacterGrid();
        int result = 0;
        int paperRollsCollected = 1;
        while (paperRollsCollected > 0) {
            paperRollsCollected = collectPaperRolls(paperRollGrid);
            result += paperRollsCollected;
        }
        return result;
    }
}
