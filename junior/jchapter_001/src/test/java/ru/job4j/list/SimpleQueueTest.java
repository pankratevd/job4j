package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleQueueTest {

    @Test
    public void consistentlyPushAndPoll() {
        SimpleQueue<String> queue = new SimpleQueue();
        queue.push("1");
        queue.push("2");
        queue.push("3");

        assertThat(queue.poll(), is("1"));
        assertThat(queue.poll(), is("2"));
        assertThat(queue.poll(), is("3"));
        Assert.assertNull(queue.poll());
    }
}
