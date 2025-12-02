package com.lyaneii.aoc.common;

public abstract class Day {
    private static final String DAY_PREFIX = "day";
    private static final String RESOURCE_PATH = "src/main/resources/";

    private final String title;
    private final String inputResourcePath;
    private final String exampleResourcePath;
    private final int day;
    protected Input input;

    protected Day(int day, String title) {
        this.day = day;
        this.title = title;
        this.inputResourcePath = RESOURCE_PATH + DAY_PREFIX + day + "/input";
        this.exampleResourcePath = RESOURCE_PATH + DAY_PREFIX + day + "/example";
    }

    abstract public Object partOne();
    abstract public Object partTwo();

    public int getDayNumber() {
        return day;
    }

    public String getInputResourcePath() {
        return inputResourcePath;
    }

    public String getExampleResourcePath() {
        return exampleResourcePath;
    }

    public void printTitle() {
        System.out.println("\n" + title);
    }

    public void parseInput(String path) {
        input = new Input(path);
    }

    public void parseInput() {
        input = new Input(inputResourcePath);
    }

    public void parseExampleInput() {
        input = new Input(exampleResourcePath);
    }
}
