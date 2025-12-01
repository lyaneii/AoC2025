package com.lyaneii.aoc.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Input {
    private static final String DEFAULT_RESOURCE_PATH = "src/main/resources/";
    private static final String DEFAULT_DELIMITER = "\n";

    String path;
    String input;

    public Input(String path) {
        this.path = path;
        try {
            input = getInputAsString(path);
        } catch (IOException e) {
            input = "";
        }
    }

    private static File getInputFile(String path) {
        return new File(DEFAULT_RESOURCE_PATH + path);
    }

    private static String getInputAsString(String path) throws IOException {
        File file = getInputFile(path);
        return Files.readString(file.toPath());
    }

    public String asString() {
        return input;
    }

    public String[] asStringArray(String delimiter) {
        return input.split(delimiter);
    }

    public String[] asStringArray() {
        return input.split(DEFAULT_DELIMITER);
    }
}
