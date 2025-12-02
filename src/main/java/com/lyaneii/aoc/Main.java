package com.lyaneii.aoc;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.http.AocHttpClient;

import java.lang.reflect.InvocationTargetException;

public class Main {
    private static final int CURRENT_DAY = 2;

    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        AocHttpClient client = new AocHttpClient();

        for (int dayNumber = 1; dayNumber <= CURRENT_DAY; ++dayNumber) {
            Day day = (Day) Class.forName("com.lyaneii.aoc.days.Day" + dayNumber).getDeclaredConstructor().newInstance();

            client.createExampleInput(dayNumber);
            client.createInput(dayNumber);

            day.printTitle();

            day.parseExampleInput();
            System.out.println("Example: ");
            System.out.println("Part One: " + day.partOne());
            System.out.println("Part Two: " + day.partTwo());

            day.parseInput();
            System.out.println("To Submit: ");
            System.out.println("Part One: " + day.partOne());
            System.out.println("Part Two: " + day.partTwo());
        }
    }
}
