package com.lyaneii.aoc.http;

import com.lyaneii.aoc.common.http.HttpThrottler;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpThrottlerTest {
    private static final String CACHE_LOCATION = "src/test/resources/lastRequest";
    private static final String DUMMY_URI = "dummy";

    @Test
    public void shouldThrottleWhenRequestExceedsRateLimit() throws InterruptedException, IOException {
        HttpThrottler throttler = HttpThrottler.create()
                .setLastRequestCacheLocation(CACHE_LOCATION)
                .setRateLimit(3);

        throttler.invalidateCache();
        throttler.tryPermissionToSendRequest(DUMMY_URI);
        Thread.sleep(250);
        assertFalse(throttler.tryPermissionToSendRequest(DUMMY_URI));
        Thread.sleep(250);
        assertFalse(throttler.tryPermissionToSendRequest(DUMMY_URI));
        Thread.sleep(250);
        assertFalse(throttler.tryPermissionToSendRequest(DUMMY_URI));
        throttler.invalidateCache();
    }

    @Test
    public void shouldSucceedWhenRequestRespectsRateLimit() throws InterruptedException, IOException {
        HttpThrottler throttler = HttpThrottler.create()
                .setLastRequestCacheLocation(CACHE_LOCATION)
                .setRateLimit(1);

        throttler.invalidateCache();
        throttler.tryPermissionToSendRequest(DUMMY_URI);
        Thread.sleep(2000);
        assertTrue(throttler.tryPermissionToSendRequest(DUMMY_URI));
        throttler.invalidateCache();
    }
}
