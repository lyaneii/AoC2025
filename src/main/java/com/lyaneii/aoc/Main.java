package com.lyaneii.aoc;

import com.lyaneii.aoc.common.DayTemplateGenerator;

public class Main {
    public static void main(String[] args) {
        AoCRunner runner = new AoCRunner();

        DayTemplateGenerator.generate(runner.getCurrentAvailableDay());
        runner.runCurrentDay();
    }
}
