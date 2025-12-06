package com.lyaneii.aoc.common.util;

public class Range<T extends Number & Comparable<T>> {
    public T low;
    public T high;

    public Range(T low, T high) {
        this.low = low;
        this.high = high;
    }
    
    private T max(T a, T b) {
        if (b.compareTo(a) > 0) {
            return b;
        }
        return a;
    }
    
    private T min(T a, T b) {
        if (a.compareTo(b) > 0) {
            return b;
        }
        return a;
    }

    public boolean overlaps(Range<T> other) {
        return max(low, other.low).compareTo(min(high, other.high)) <= 0;
    }

    public boolean isInRangeInclusive(T value) {
        return value.compareTo(low) >= 0 && value.compareTo(high) <= 0;
    }

    public boolean isInRangeExclusive(T value) {
        return value.compareTo(low) >= 0 && value.compareTo(high) < 0;
    }

    public boolean isBetweenRange(T value) {
        return value.compareTo(low) > 0 && value.compareTo(high) < 0;
    }

    public Range<T> merge(Range<T> other) {
        return new Range<>(min(low, other.low), max(high, other.high));
    }

}
