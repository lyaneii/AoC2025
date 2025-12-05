package com.lyaneii.aoc.common.util;

public class LongRange {
    public long low;
    public long high;

    public LongRange(long low, long high) {
        this.low = low;
        this.high = high;
    }

    public boolean overlaps(LongRange other) {
        return Math.max(low, other.low) <= Math.min(high, other.high);
    }

    public boolean isInRangeInclusive(long value) {
        return value >= low && value <= high;
    }

    public boolean isInRangeExclusive(long value) {
        return value >= low && value < high;
    }

    public boolean isBetweenRange(long value) {
        return value > low && value < high;
    }

    public LongRange merge(LongRange other) {
        return new LongRange(Math.min(low, other.low), Math.max(high, other.high));
    }

}
