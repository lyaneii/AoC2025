package com.lyaneii.aoc.common.util;

public class Grid {
    public char[][] grid;
    public int width;
    public int height;

    public Grid(char[][] grid) {
        this.grid = grid;
        width = grid[0].length;
        height = grid.length;

    }

    public char[][] cloneGrid() {
        char[][] clone = new char[height][width];
        for (int i = 0; i < width; ++i) {
            clone[i] = grid[i].clone();
        }
        return clone;
    }

    public char at(int x, int y) {
        return grid[y][x];
    }
}
