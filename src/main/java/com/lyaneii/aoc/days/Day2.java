package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;

import java.util.ArrayList;

public class Day2 extends Day {

    public Day2() {
        super(2, "Day 2: Gift Shop");
    }

    public boolean hasRepeatingDigitSequence(String string, int amountOfRepeats) {
        if (string.length() % amountOfRepeats != 0) {
            return false;
        }

        int interval = string.length() / amountOfRepeats;
        int currentInterval = 0;

        ArrayList<Long> digitSequences = new ArrayList<>();
        long digitSequenceToScan = Long.parseLong(string.substring(currentInterval, currentInterval + interval));
        digitSequences.add(digitSequenceToScan);
        currentInterval += interval;

        for (int times = 1; times < amountOfRepeats; ++times) {
            long currentDigitSequence = Long.parseLong(string.substring(currentInterval, currentInterval + interval));
            if (!digitSequences.contains(currentDigitSequence)) {
                return false;
            }
            digitSequences.add(currentDigitSequence);
            currentInterval += interval;
        }
        return true;
    }

    @Override
    public Object partOne() {
        long result = 0;
        String[] productIdRanges = input.asStringArray(",\n?");

        for (String productIdRange : productIdRanges) {
            String[] separatedIds = productIdRange.split("-");
            long currentId = Long.parseLong(separatedIds[0]);
            long upperId = Long.parseLong(separatedIds[1]);

            for (; currentId <= upperId; ++currentId) {
                String id = Long.toString(currentId);
                if (id.length() % 2 != 0) {
                    continue;
                }
                if (hasRepeatingDigitSequence(id, 2)) {
                    result += currentId;
                }
            }
        }
        return result;
    }

    @Override
    public Object partTwo() {
        long result = 0;
        String[] productIdRanges = input.asStringArray(",\n?");

        for (String productIdRange : productIdRanges) {
            String[] separatedIds = productIdRange.split("-");
            long currentId = Long.parseLong(separatedIds[0]);
            long upperId = Long.parseLong(separatedIds[1]);

            for (; currentId <= upperId; ++currentId) {
                String id = Long.toString(currentId);
                for (int repeats = 2; repeats <= id.length(); ++repeats) {
                    if (hasRepeatingDigitSequence(id, repeats)) {
                        result += currentId;
                        break;
                    }
                }
            }
        }

        return result;
    }
}
