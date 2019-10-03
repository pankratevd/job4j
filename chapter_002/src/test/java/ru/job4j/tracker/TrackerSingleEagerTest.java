package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TrackerSingleEagerTest {

    @Test
    public void checkSingleton() {
        TrackerSingleEager tracker = TrackerSingleEager.getInstance();
        TrackerSingleEager tracker2 = TrackerSingleEager.getInstance();
        assertTrue(tracker == tracker2);
    }
}