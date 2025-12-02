package com.lyaneii.aoc.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DayTemplateGenerator {
    private static final String TEMPLATE_PATH = "src/main/resources/DayTemplate";
    private static final String DESTINATION_PATH = "src/main/java/com/lyaneii/aoc/days/Day";
    private static final String EXTENSION = ".java";

    public static void generate(int day) {
        File destination = new File(DESTINATION_PATH + day + EXTENSION);

        if (destination.exists()) {
            return;
        }

        try {
            String template = Files.readString(new File(TEMPLATE_PATH).toPath());
            template = template.replace("#", Integer.toString(day));
            Files.write(destination.toPath(), template.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
