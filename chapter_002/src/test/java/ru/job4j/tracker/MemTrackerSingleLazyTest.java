package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MemTrackerSingleLazyTest {
    @Test
    public void checkSingleton() {
        TrackerSingleLazy tracker = TrackerSingleLazy.getInstance();
        TrackerSingleLazy tracker2 = TrackerSingleLazy.getInstance();
        assertTrue(tracker == tracker2);
    }

}
