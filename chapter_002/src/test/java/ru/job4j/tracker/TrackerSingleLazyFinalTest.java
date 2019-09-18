package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TrackerSingleLazyFinalTest {

    @Test
    public void checkSingleton() {
        TrackerSingleLazyFinal tracker = TrackerSingleLazyFinal.getInstance();
        TrackerSingleLazyFinal tracker2 = TrackerSingleLazyFinal.getInstance();
        assertTrue(tracker == tracker2);
    }
}
