package com.lyaneii.aoc.common.util;

public class Bitmask {
    public static boolean flagIsSet(int flags, int flag) {
        return (flags & flag) != 0;
    }
}
