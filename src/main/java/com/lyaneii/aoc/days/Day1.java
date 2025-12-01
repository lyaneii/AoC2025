package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.util.StringUtils;
import java.lang.Math;

public class Day1 extends Day {
    private static final int DIAL_LIMIT = 100;
    private static final int DIAL_START = 50;
    private int dialState;

    public Day1() {
        super(1, "Day 1: Secret Entrance");
    }

    private int turnDialLeft(int amountToRotate) {
        int newDialState = dialState - amountToRotate;
        int timesPointedAtZero = Math.floorDiv(dialState - 1, DIAL_LIMIT) - Math.floorDiv(newDialState - 1, DIAL_LIMIT);

        dialState = Math.floorMod(newDialState, DIAL_LIMIT);
        return timesPointedAtZero;
    }

    private int turnDialRight(int amountToRotate) {
        int newDialState = dialState + amountToRotate;
        int timesPointedAtZero = newDialState / DIAL_LIMIT;

        dialState = newDialState % DIAL_LIMIT;
        return timesPointedAtZero;
    }

    @Override
    public Object partOne() {
        int result = 0;
        String[] dialInstructions = input.asStringArray();
        dialState = DIAL_START;

        for (String dialInstruction : dialInstructions) {
            int amountToRotate = StringUtils.findFirstInteger(dialInstruction);

            if (dialInstruction.charAt(0) == 'L') {
                turnDialLeft(amountToRotate);
            } else {
                turnDialRight(amountToRotate);
            }

            if (dialState == 0) {
                ++result;
            }
        }
        return result;
    }

    @Override
    public Object partTwo() {
        int result = 0;
        String[] dialInstructions = input.asStringArray();
        dialState = DIAL_START;

        for (String dialInstruction : dialInstructions) {
            int amountToRotate = StringUtils.findFirstInteger(dialInstruction);

            if (dialInstruction.charAt(0) == 'L') {
                result += turnDialLeft(amountToRotate);
            } else {
                result += turnDialRight(amountToRotate);
            }
        }
        return result;
    }
}
