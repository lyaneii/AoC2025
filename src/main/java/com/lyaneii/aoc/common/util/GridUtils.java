package com.lyaneii.aoc.common.util;

public class GridUtils {
    public static char[] getColumn(char[][] grid, int index) {
        char[] column = new char[grid.length];

        for (int i = 0; i < grid.length; ++i) {
            column[i] = grid[i][index];
        }
        return column;
    }

    public static char[][] transposeGrid(char[][] grid) {
        char[][] transposedGrid = new char[grid[0].length][grid.length];
        for (int x = 0; x < grid[0].length; ++x) {
            transposedGrid[x] = getColumn(grid, x);
        }
        return transposedGrid;
    }
    
    public static String[] toStringArray(char[][] grid) {
        String[] stringArray = new String[grid.length];
        for (int i = 0; i < grid.length; ++i) {
            stringArray[i] = new String(grid[i]);
        }
        return stringArray;
    }
}
