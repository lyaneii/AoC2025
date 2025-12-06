package com.lyaneii.aoc.common;

import com.lyaneii.aoc.common.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Input {
    private static final String DEFAULT_DELIMITER = "\n";
    private String input;

    public Input(String path) {
        try {
            input = getInputAsString(path);
        } catch (IOException e) {
            input = "";
        }
    }

    private static String getInputAsString(String path) throws IOException {
        File file = new File(path);
        return Files.readString(file.toPath());
    }

    public String asString() {
        return input;
    }

    public String[] asStringArray(String regex) {
        return input.split(regex);
    }

    public String[] asStringArray() {
        return input.split(DEFAULT_DELIMITER);
    }

    public char[][] asCharacterGrid() {
        return StringUtils.toCharacterGrid(asStringArray());
    }

    public String[][] asStringGrid() {
        return StringUtils.toStringGrid(asStringArray());
    }
}
