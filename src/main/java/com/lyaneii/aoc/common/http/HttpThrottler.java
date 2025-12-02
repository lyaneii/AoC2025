package com.lyaneii.aoc.common.http;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;

public class HttpThrottler {
    private static final String DEFAULT_CACHE_LOCATION = "src/main/resources/lastRequest";
    private static final long DEFAULT_RATE_LIMIT_SECONDS = 60;

    private File lastRequestCache;
    private Duration rateLimit;

    private HttpThrottler() {
    }

    public static HttpThrottler create() {
        return new HttpThrottler()
                .setRateLimit(DEFAULT_RATE_LIMIT_SECONDS)
                .setLastRequestCacheLocation(DEFAULT_CACHE_LOCATION);
    }

    public void invalidateCache() {
        try {
            Files.deleteIfExists(lastRequestCache.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpThrottler setLastRequestCacheLocation(String path) {
        lastRequestCache = new File(path);
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

    private boolean rateLimitIsExceeded(Duration timeSinceLastRequest) {
        return timeSinceLastRequest.compareTo(rateLimit) < 0;
    }

    public boolean tryPermissionToSendRequest() {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();

            if (!lastRequestCache.exists()) {
                Files.write(lastRequestCache.toPath(), localDateTime.toString().getBytes());
                return true;
            }

            String time = Files.readString(lastRequestCache.toPath());
            LocalDateTime lastRequestTime = LocalDateTime.parse(time);

            Duration timeSinceLastRequest = Duration.between(lastRequestTime, localDateTime);
            if (rateLimitIsExceeded(timeSinceLastRequest)) {
                return false;
            }

            Files.write(lastRequestCache.toPath(), localDateTime.toString().getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
