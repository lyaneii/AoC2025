package com.lyaneii.aoc;

import java.io.File;
import java.nio.file.Files;

public class SolutionBrowserUpdater {
    private static final String URL_RECIPE =
            "https://github.com/lyaneii/AoC2025/blob/master/src/main/java/com/lyaneii/aoc/days/Day#.java";
    private static final String README_PATH = "README.md";

    public static void update(int day) {
        try {
            File readme = new File(README_PATH);
            String linkToSolution = "[#" + day + "](" + URL_RECIPE.replace("#", Integer.toString(day)) + ")";
            String content = Files.readString(readme.toPath());
            if (content.contains(linkToSolution)) {
                return;
            }

            String regex = "\\[?#" + day + "]?(\\(?.+?(?=\\))\\)|\\(\\))?";
            content = content.replaceFirst(regex, linkToSolution);
            Files.write(readme.toPath(), content.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}