package com.lyaneii.aoc.common.util;

import java.util.ArrayList;
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

    public static String[][] toStringGrid(String[] stringArray) {
        String[][] grid = new String[stringArray.length][];
        for (int i = 0; i < stringArray.length; ++i) {
            grid[i] = stringArray[i].trim().split(" +");
        }
        return grid;
    }

    public static int[] toIntegerArray(String[] stringArray) {
        int[] integerArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            integerArray[i] = Integer.parseInt(stringArray[i]);
        }
        return integerArray;
    }

    public static long[] toLongArray(String[] stringArray) {
        long[] longArray = new long[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            longArray[i] = Long.parseLong(stringArray[i]);
        }
        return longArray;
    }

    public static ArrayList<Range<Long>> toRangeArrayList(String[] stringArray, String rangeSeparator) {
        ArrayList<Range<Long>> rangeArrayList = new ArrayList<>(stringArray.length);

        for (String string : stringArray) {
            String[] range = string.split(rangeSeparator);
            rangeArrayList.add(new Range<>(Long.parseLong(range[0]), Long.parseLong(range[1])));
        }
        return rangeArrayList;
    }

    public static String stripTrailingLineBreaks(String string) {
        return string.replaceFirst("[\r\n]+$", "");
    }
    
    public static int countOccurrences(String[] stringArray, char ch) {
        int occurrences = 0;
        for (String string : stringArray) {
            occurrences += countOccurrences(string, ch);
        }
        return occurrences;
    }
    
    public static int countOccurrences(String string, char ch) {
        int occurrences = 0;
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == ch) {
                ++occurrences;
            }
        }
        return occurrences;
    }
}
