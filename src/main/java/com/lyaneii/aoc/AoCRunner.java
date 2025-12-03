package com.lyaneii.aoc;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.http.AocHttpClient;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

public class AoCRunner {
    private final int currentDay;
    private static final int AOC_LOCAL_START_HOUR = 6;
    private static final String DAY_CLASS_PREFIX = "com.lyaneii.aoc.days.Day";
    private final AocHttpClient httpClient;

    public AoCRunner() {
        currentDay = getCurrentAvailableDay();
        httpClient = new AocHttpClient();
    }

    public int getCurrentAvailableDay() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int day = localDateTime.getDayOfMonth();

        if (localDateTime.getHour() < AOC_LOCAL_START_HOUR) {
            return day - 1;
        }
        return day;
    }

    public void runCurrentDay(boolean exampleOnly) {
        try {
            runForSpecifiedDay(currentDay, exampleOnly);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void runAllDays() {
        for (int dayNumber = 1; dayNumber <= currentDay; ++dayNumber) {
            try {
                runForSpecifiedDay(dayNumber, false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void runForSpecifiedDay(int dayNumber, boolean exampleOnly)
            throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        if (dayNumber > currentDay) {
            return;
        }

        Day day = (Day) Class.forName(DAY_CLASS_PREFIX + dayNumber).getDeclaredConstructor().newInstance();

        httpClient.createExampleInput(day);

        day.printTitle();

        if (exampleOnly) {
            day.parseExampleInput();
            System.out.println("Example: ");
        } else {
            httpClient.createInput(day);
            day.parseInput();
            System.out.println("To Submit: ");
        }
        System.out.println("Part One: " + day.partOne());
        System.out.println("Part Two: " + day.partTwo());
    }
}
