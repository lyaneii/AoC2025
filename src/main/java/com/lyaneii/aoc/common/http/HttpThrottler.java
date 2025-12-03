package com.lyaneii.aoc.common.http;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class HttpThrottler {
    private static final String DEFAULT_CACHE_LOCATION = "src/main/resources/lastRequest";
    private static final long DEFAULT_RATE_LIMIT_SECONDS = 60;
    private static final String ENTRY_SEPARATOR = "\\|";

    private Map<String, LocalDateTime> lastRequestMap;

    private File lastRequestCache;
    private Duration rateLimit;

    private HttpThrottler() {
    }

    public static HttpThrottler create() {
        try {
            return new HttpThrottler()
                    .setRateLimit(DEFAULT_RATE_LIMIT_SECONDS)
                    .setLastRequestCacheLocation(DEFAULT_CACHE_LOCATION);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void invalidateCache() {
        try {
            Files.deleteIfExists(lastRequestCache.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean rateLimitIsExceeded(Duration timeSinceLastRequest) {
        return timeSinceLastRequest.compareTo(rateLimit) < 0;
    }

    public HttpThrottler setLastRequestCacheLocation(String path) throws IOException {
        lastRequestCache = new File(path);
        lastRequestMap = new HashMap<>();
        readLastRequestCache();
        return this;
    }

    public HttpThrottler setRateLimit(long hours, long minutes, long seconds) {
        rateLimit = Duration.ofSeconds(hours * 3600 + minutes * 60 + seconds);
        return this;
    }

    public HttpThrottler setRateLimit(long minutes, long seconds) {
        rateLimit = Duration.ofSeconds(minutes * 60 + seconds);
        return this;
    }

    public HttpThrottler setRateLimit(long seconds) {
        rateLimit = Duration.ofSeconds(seconds);
        return this;
    }

    LocalDateTime getLastRequest(String uri) {
        return lastRequestMap.get(uri);
    }

    private void readLastRequestCache() throws IOException {
        if (!lastRequestCache.exists()) {
            return;
        }
        String cache = Files.readString(lastRequestCache.toPath());
        String[] entries = cache.split("\n");
        for (String entry : entries) {
            String[] keyValuePair = entry.trim().split(ENTRY_SEPARATOR);
            lastRequestMap.put(keyValuePair[0], LocalDateTime.parse(keyValuePair[1]));
        }
    }

    public void writeLastRequestCache(String uri, LocalDateTime lastRequestTime) throws IOException {
        lastRequestMap.put(uri, lastRequestTime);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry setEntry : lastRequestMap.entrySet()) {
            stringBuilder
                    .append(setEntry.getKey())
                    .append("|")
                    .append(setEntry.getValue())
                    .append("\n");
        }
        Files.write(lastRequestCache.toPath(), stringBuilder.toString().getBytes());
    }

    public boolean tryPermissionToSendRequest(String uri) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();

            if (!lastRequestCache.exists()) {
                writeLastRequestCache(uri, localDateTime);
                return true;
            }

            LocalDateTime lastRequestTime = getLastRequest(uri);
            if (lastRequestTime != null) {
                Duration timeSinceLastRequest = Duration.between(lastRequestTime, localDateTime);
                if (rateLimitIsExceeded(timeSinceLastRequest)) {
                    return false;
                }
            }

            writeLastRequestCache(uri, localDateTime);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
