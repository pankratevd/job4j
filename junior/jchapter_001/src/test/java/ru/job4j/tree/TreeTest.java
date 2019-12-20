package ru.job4j.tree;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(4, 5);
        tree.add(5, 5);
        tree.add(5, 6);
        tree.add(5, 6);
        tree.add(5, 8);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void iterateByTree() {
        Tree<Integer> tree = new Tree<>(1);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> result = new ArrayList<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(4, 5);
        tree.add(5, 5);
        tree.add(5, 6);
        tree.add(5, 6);
        tree.add(1, 7);
        tree.add(5, 8);
        tree.add(5, 8);
        Iterator<Integer> it = tree.iterator();
        while (it.hasNext()) {
         result.add(it.next());
        }
        Collections.sort(result);
        assertThat(result, is(expected));
    }
}