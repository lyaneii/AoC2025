package com.lyaneii.aoc.common.util;

public class GridUtils {
    public static char[] getColumn(char[][] grid, int index) {
        char[] column = new char[grid.length];

        for (int i = 0; i < grid.length; ++i) {
            column[i] = grid[i][index];
        }
        return column;
    }
}
