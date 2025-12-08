package com.lyaneii.aoc;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.http.AocHttpClient;
import com.lyaneii.aoc.common.util.Bitmask;

import java.time.LocalDateTime;

public class AoCRunner {
    private final int currentDay;
    private static final int AOC_LOCAL_START_HOUR = 6;
    private static final String DAY_CLASS_PREFIX = "com.lyaneii.aoc.days.Day";
    private static final int AOC_MAX_DAYS = 12;
    private final AocHttpClient httpClient;

    public static final int EXAMPLE = 1;
    public static final int INPUT = 1 << 1;

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

    public void runCurrentDay(int flags) {
        runForSpecifiedDay(currentDay, flags);
    }

    public void runAllDays() {
        for (int dayNumber = 1; dayNumber <= currentDay; ++dayNumber) {
            runForSpecifiedDay(dayNumber, INPUT);
        }
    }

    public void runForSpecifiedDay(int dayNumber, int flags) {
        try {
            if (dayNumber > currentDay || dayNumber > AOC_MAX_DAYS) {
                return;
            }

            Day day = (Day) Class.forName(DAY_CLASS_PREFIX + dayNumber).getDeclaredConstructor().newInstance();

            httpClient.createExampleInput(day);

            day.printTitle();

            if (Bitmask.flagIsSet(flags, EXAMPLE)) {
                day.parseExampleInput();
                System.out.println("Example: ");
                System.out.println("Part One: " + day.partOne());
                System.out.println("Part Two: " + day.partTwo());
            }

            if (Bitmask.flagIsSet(flags, INPUT)) {
                httpClient.createInput(day);
                day.parseInput();
                System.out.println("To Submit: ");
                System.out.println("Part One: " + day.partOne());
                System.out.println("Part Two: " + day.partTwo());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSolutionBrowser(int day) {
        SolutionBrowserUpdater.update(day);
    }
}
