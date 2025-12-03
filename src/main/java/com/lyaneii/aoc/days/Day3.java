package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;

public class Day3 extends Day {
    private static final int RADIX = 10;

    public Day3() {
        super(3, "Day 3: Lobby");
    }

    private static long findLargestJoltagePossible(String batteryBank, int joltageSize) {
        char digitToScan;
        long joltage = 0;
        int currentJoltageSize = 0;
        int largestBatteryIndex = -1;
        int searchOffset;

        while (currentJoltageSize < joltageSize) {
            digitToScan = '9';
            searchOffset = largestBatteryIndex + 1;
            while (digitToScan > '0') {
                largestBatteryIndex = batteryBank.indexOf(digitToScan, searchOffset);
                if (largestBatteryIndex >= 0 && largestBatteryIndex < batteryBank.length() - (joltageSize - currentJoltageSize) + 1) {
                    ++currentJoltageSize;
                    break;
                }
                --digitToScan;
            }
            joltage *= RADIX;
            joltage += Character.getNumericValue(batteryBank.charAt(largestBatteryIndex));
        }
        return joltage;
    }

    @Override
    public Object partOne() {
        long result = 0;
        String[] batteryBanks = input.asStringArray();
        for (String batteryBank : batteryBanks) {
            long largestJoltage = findLargestJoltagePossible(batteryBank, 2);
            result += largestJoltage;
        }
        return result;
    }

    @Override
    public Object partTwo() {
        long result = 0;
        String[] batteryBanks = input.asStringArray();
        for (String batteryBank : batteryBanks) {
            long largestJoltage = findLargestJoltagePossible(batteryBank, 12);
            result += largestJoltage;
        }
        return result;
    }
}
