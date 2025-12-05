package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.Input;
import com.lyaneii.aoc.common.util.LongRange;
import com.lyaneii.aoc.common.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;

public class Day5 extends Day {

    public Day5() {
        super(5, "Day 5: Cafeteria ");
    }

    private static class IngredientDatabase {
        public ArrayList<LongRange> freshRanges;
        public long[] ingredientIds;

        public boolean isInRange(long value) {
            for (LongRange freshRange : freshRanges) {
                if (freshRange.isInRangeInclusive(value)) {
                    return true;
                }
            }
            return false;
        }
    }

    private IngredientDatabase parseInput(Input input) {
        String[] splitRangeAndIds = input.asStringArray("\n\n");
        IngredientDatabase database = new IngredientDatabase();

        database.freshRanges = StringUtils.toLongRangeArrayList(splitRangeAndIds[0].split("\n"), "-");
        database.freshRanges = mergeRanges(database.freshRanges);
        database.ingredientIds = StringUtils.toLongArray(splitRangeAndIds[1].split("\n"));
        return database;
    }

    public ArrayList<LongRange> mergeRanges(ArrayList<LongRange> ranges) {
        ranges.sort(Comparator.comparingLong((LongRange range) -> range.low));
        ArrayList<LongRange> mergedRanges = new ArrayList<>();

        LongRange currentRange = ranges.getFirst();
        for (int i = 1; i < ranges.size(); ++i) {
            LongRange nextRange = ranges.get(i);
            if (currentRange.overlaps(nextRange)) {
                currentRange = currentRange.merge(nextRange);
            } else {
                mergedRanges.add(currentRange);
                currentRange = nextRange;
            }
        }
        mergedRanges.add(currentRange);
        return mergedRanges;
    }

    @Override
    public Object partOne() {
        IngredientDatabase database = parseInput(input);
        int result = 0;
        for (long ingredientId : database.ingredientIds) {
            if (database.isInRange(ingredientId)) {
                ++result;
            }
        }
        return result;
    }

    @Override
    public Object partTwo() {
        IngredientDatabase database = parseInput(input);
        long result = 0;
        for (LongRange freshRange : database.freshRanges) {
            result += freshRange.high - freshRange.low + 1;
        }
        return result;
    }
}
