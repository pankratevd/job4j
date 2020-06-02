package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MemTrackerSingleEnumTest {

    @Test
    public void checkSingleton() {
       TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
       TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
       assertTrue(tracker == tracker2);
    }
}


