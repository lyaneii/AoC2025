package com.lyaneii.aoc.common.http;

import io.github.cdimascio.dotenv.Dotenv;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AocHttpClient {
    private static final String EXAMPLE_INPUT_HTML_TAG = "pre";
    private static final String DAY_PREFIX = "day";
    private static final String EXAMPLE_PREFIX = "example";
    private static final String RESOURCE_PATH = "src/main/resources/";
    private static final String BASE_URL = "https://adventofcode.com/2025/day/";

    private static final String USER_AGENT_HEADER_KEY = "User-Agent";
    private static final String COOKIE_HEADER_KEY = "Cookie";

    private final String sessionCookie;
    private final String userAgentHeaderValue;
    private final HttpClient client;
    private final HttpRequest.Builder requestBuilder;

    public AocHttpClient() {
        Dotenv env = Dotenv.load();
        sessionCookie = env.get("AOC_SESSION_COOKIE");
        userAgentHeaderValue = env.get("AOC_USER_AGENT_VALUE");

        client = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build();
        requestBuilder = HttpRequest.newBuilder()
                .header(COOKIE_HEADER_KEY, "session=" + sessionCookie)
                .header(USER_AGENT_HEADER_KEY, userAgentHeaderValue)
                .GET();
    }

    private String inputUriString(int day) {
        return exampleInputUriString(day) + "/input";
    }

    private String exampleInputUriString(int day) {
        return BASE_URL + day;
    }

    private File exampleFileName(int day) {
        return new File(RESOURCE_PATH + EXAMPLE_PREFIX + day);
    }

    private File inputFileName(int day) {
        return new File(RESOURCE_PATH + DAY_PREFIX + day);
    }

    public void createInput(int day) {
        File input = inputFileName(day);
        if (input.exists()) {
            return;
        }

        URI uri = URI.create(inputUriString(day));
        HttpRequest request = requestBuilder
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Files.write(Paths.get(input.toString()), response.body().getBytes());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createExampleInput(int day) {
        File input = exampleFileName(day);
        if (input.exists()) {
            return;
        }

        URI uri = URI.create(exampleInputUriString(day));
        HttpRequest request = requestBuilder
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Document document = Jsoup.parse(response.body());
                Element exampleInput = document.select(EXAMPLE_INPUT_HTML_TAG).first();
                if (exampleInput != null) {
                    Files.write(Paths.get(input.toString()), exampleInput.text().getBytes());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
