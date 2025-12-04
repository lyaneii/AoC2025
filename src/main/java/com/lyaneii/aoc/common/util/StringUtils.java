package com.lyaneii.aoc.common.util;

import java.util.regex.*;

public class StringUtils {
    public static int findFirstInteger(String input) throws RuntimeException {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new RuntimeException();
        }

        return Integer.parseInt(matcher.group());
    }

    public static long findFirstLong(String input) throws RuntimeException {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new RuntimeException();
        }

        return Long.parseLong(matcher.group());
    }

    public static double findFirstDouble(String input) throws RuntimeException {
        Pattern pattern = Pattern.compile("-?\\d+(\\.?\\d+)?");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new RuntimeException();
        }

        return Double.parseDouble(matcher.group());
    }

    public static float findFirstFloat(String input) throws RuntimeException {
        Pattern pattern = Pattern.compile("-?\\d+(\\.?\\d+)?");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new RuntimeException();
        }

        return Float.parseFloat(matcher.group());
    }

    public static char[][] toCharacterGrid(String[] stringArray) {
        char[][] grid = new char[stringArray.length][stringArray[0].length()];
        for (int i = 0; i < stringArray.length; ++i) {
            grid[i] = stringArray[i].toCharArray();
        }
        return grid;
    }
}
