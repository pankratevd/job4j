package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleStackTest {

    @Test
    public void whenPollTheLastThenNull() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertThat(stack.poll(), is("3"));
        assertThat(stack.poll(), is("2"));
        assertThat(stack.poll(), is("1"));
        Assert.assertNull(stack.poll());
    }

    @Test
    public void consistentlyPollFirst() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertThat(stack.pollFirst(), is("1"));
        assertThat(stack.pollFirst(), is("2"));
        assertThat(stack.pollFirst(), is("3"));
        Assert.assertNull(stack.poll());
    }
}
