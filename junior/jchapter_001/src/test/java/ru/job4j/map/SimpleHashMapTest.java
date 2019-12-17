package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class SimpleHashMapTest {
    SimpleHashMap<Integer, Character> map = new SimpleHashMap<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 12; i++) {
            map.insert(i, (char) (97 + i));
        }
        for (int i = 12; i < 26; i++) {
            map.insert(i, (char) (97 + i));
        }
    }

    @Test
    public void whenGetNotExistKeyThenNull() {
        Assert.assertNull(map.get(27));
    }

    @Test
    public void whenGetArraySizeThen64() {
        assertThat(map.getArraySize(), is(64));
    }

    @Test
    public void whenAddWithExistKeyThenFalse() {
        Assert.assertFalse(map.insert(1, 'А'));
    }

    @Test
    public void consistentlyDeleteAllElements() {
        for (int i = 0; i < 26; i++) {
            assertThat(map.get(i), is((char) (97 + i)));
            Assert.assertTrue(map.delete(i));
            Assert.assertNull(map.get(i));
        }
    }

    @Test
    public void consistentlyTestWithStringKey() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        Assert.assertTrue(map.insert("1", 1));
        Assert.assertTrue(map.insert("2", 2));
        Assert.assertTrue(map.insert("5", 5));
        Assert.assertFalse(map.delete("6"));
        Assert.assertFalse(map.insert("2", 10));
        Assert.assertTrue(map.delete("2"));
        Assert.assertNull(map.get("2"));
        assertThat(map.get("1"), is(1));
        assertThat(map.get("5"), is(5));
        Assert.assertTrue(map.insert("2", 2));
        assertThat(map.get("2"), is(2));
    }

    @Test
    public void iterateThroughKeySet() {
        int i = 0;
        for (Integer integer : map.keySet()) {
            assertThat(integer, is(i++));
        }
        assertThat(i, is(26));
    }

    @Test
    public void iterateThroughEntrySet() {
        ArrayList<Character> expected = new ArrayList<>();
        ArrayList<Character> result = new ArrayList<>();
        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            SimpleHashMap.Entry<Integer, Character> h = (SimpleHashMap.Entry) it.next();
            result.add(h.getValue());
        }

        for (int i = 97; i < 123; i++) {
            expected.add((char) i);
        }
        Collections.sort(expected);
        Collections.sort(result);
        assertEquals(expected, result);
    }
}
