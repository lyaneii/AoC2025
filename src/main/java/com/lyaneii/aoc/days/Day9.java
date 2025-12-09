package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.util.Vec2;

import java.util.ArrayList;

public class Day9 extends Day {

    public Day9() {
        super(9, "Day 9");
    }

    @Override
    public Object partOne() {
        String[] redTileCoordinates = input.asStringArray();
        ArrayList<Vec2> redTiles = Vec2.toArrayList(redTileCoordinates);
        
        long maxArea = 0;
        for (int i = 0; i < redTiles.size() - 1; ++i) {
            for (int j = i + 1; j < redTiles.size(); ++j) {
                long area = Vec2.area(redTiles.get(i), redTiles.get(j));
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    @Override
    public Object partTwo() {
        return 0;
    }
}
