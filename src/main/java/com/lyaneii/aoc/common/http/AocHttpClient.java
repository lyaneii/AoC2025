package com.lyaneii.aoc.common.http;

import com.lyaneii.aoc.common.Day;
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
import java.time.Duration;

public class AocHttpClient {
    private static final String EXAMPLE_INPUT_HTML_TAG = "pre";
    private static final String BASE_URL = "https://adventofcode.com/2025/day/";

    private static final String USER_AGENT_HEADER_KEY = "User-Agent";
    private static final String COOKIE_HEADER_KEY = "Cookie";

    private final HttpClient client;
    private final HttpRequest.Builder requestBuilder;

    private final HttpThrottler throttler;
    private static final int RATE_LIMIT = 900;

    public AocHttpClient() {
        Dotenv env = Dotenv.load();
        String sessionCookie = env.get("AOC_SESSION_COOKIE");
        if (sessionCookie == null) {
            throw new RuntimeException();
        }
        String userAgentHeaderValue = env.get("AOC_USER_AGENT_VALUE");

        client = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build();
        requestBuilder = HttpRequest.newBuilder()
                .header(COOKIE_HEADER_KEY, "session=" + sessionCookie)
                .header(USER_AGENT_HEADER_KEY, userAgentHeaderValue)
                .timeout(Duration.ofSeconds(10))
                .GET();
        throttler = HttpThrottler.create()
                .setRateLimit(RATE_LIMIT);
    }

    private String inputUriString(int day) {
        return exampleInputUriString(day) + "/input";
    }

    private String exampleInputUriString(int day) {
        return BASE_URL + day;
    }


    public void createInput(Day day) {
        File input = new File(day.getInputResourcePath());
        if (input.exists()) {
            return;
        }

        URI uri = URI.create(inputUriString(day.getDayNumber()));
        HttpRequest request = requestBuilder
                .uri(uri)
                .build();

        try {
            if (!throttler.tryPermissionToSendRequest(uri.toString())) {
                return;
            }
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Files.createDirectories(input.toPath().getParent());
                Files.write(input.toPath(), response.body().stripTrailing().getBytes());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createExampleInput(Day day) {
        File input = new File(day.getExampleResourcePath());
        if (input.exists()) {
            return;
        }

        URI uri = URI.create(exampleInputUriString(day.getDayNumber()));
        HttpRequest request = requestBuilder
                .uri(uri)
                .build();

        try {
            if (!throttler.tryPermissionToSendRequest(uri.toString())) {
                return;
            }
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Document document = Jsoup.parse(response.body());
                Element exampleInput = document.select(EXAMPLE_INPUT_HTML_TAG).first();
                if (exampleInput != null) {
                    Files.createDirectories(input.toPath().getParent());
                    Files.write(input.toPath(), exampleInput.text().getBytes());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
