package com.lyaneii.aoc.common;

public abstract class Day {
    protected static final String DAY_PREFIX = "day";
    protected static final String EXAMPLE_PREFIX = "example";

    private final String title;
    private final int day;
    protected Input input;

    protected Day(int day, String title) {
        this.day = day;
        this.title = title;
    }

    abstract public Object partOne();
    abstract public Object partTwo();

    public void printTitle() {
        System.out.println(title);
    }

    public void parseInput(String path) {
        input = new Input(path);
    }

    public void parseInput() {
        input = new Input(DAY_PREFIX + day);
    }

    public void parseExampleInput() {
        input = new Input(EXAMPLE_PREFIX + day);
    }
}
