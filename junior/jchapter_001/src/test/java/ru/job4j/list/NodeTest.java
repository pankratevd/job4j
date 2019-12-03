package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

    @Test
    public void whenHasCycleAtEndThenTrue() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        Assert.assertTrue(first.hasCycle(first));
    }

    @Test
    public void whenHasCycleThenTrue() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = third;

        Assert.assertTrue(first.hasCycle(first));
    }

    @Test
    public void whenDoesntHaveCycleThenFalse() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;

        Assert.assertFalse(first.hasCycle(first));
    }

    @Test
    public void whenOneNodeThenFalse() {
        Node first = new Node(1);
        Assert.assertFalse(first.hasCycle(first));
    }

    @Test
    public void whenTwoNodeCycleThenFTrue() {
        Node first = new Node(1);
        Node two = new Node(2);

        first.next = two;
        two.next = first;

        Assert.assertTrue(first.hasCycle(first));
    }
}
