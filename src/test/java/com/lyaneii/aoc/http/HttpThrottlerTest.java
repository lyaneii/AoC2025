package com.lyaneii.aoc.http;

import com.lyaneii.aoc.common.http.HttpThrottler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpThrottlerTest {
    private static final String CACHE_LOCATION = "src/test/resources/lastRequest";

    @Test
    public void shouldThrottleWhenRequestExceedsRateLimit() throws InterruptedException {
        HttpThrottler throttler = HttpThrottler.create()
                .setLastRequestCacheLocation(CACHE_LOCATION)
                .setRateLimit(3);

        throttler.invalidateCache();
        throttler.tryPermissionToSendRequest();
        Thread.sleep(250);
        assertFalse(throttler.tryPermissionToSendRequest());
        Thread.sleep(250);
        assertFalse(throttler.tryPermissionToSendRequest());
        Thread.sleep(250);
        assertFalse(throttler.tryPermissionToSendRequest());
        throttler.invalidateCache();
    }

    @Test
    public void shouldSucceedWhenRequestRespectsRateLimit() throws InterruptedException {
        HttpThrottler throttler = HttpThrottler.create()
                .setLastRequestCacheLocation(CACHE_LOCATION)
                .setRateLimit(1);

        throttler.invalidateCache();
        throttler.tryPermissionToSendRequest();
        Thread.sleep(2000);
        assertTrue(throttler.tryPermissionToSendRequest());
        throttler.invalidateCache();
    }
}
