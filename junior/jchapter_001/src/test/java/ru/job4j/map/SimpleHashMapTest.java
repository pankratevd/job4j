package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class SimpleHashMapTest {
    SimpleHashMap<Integer, Character> map = new SimpleHashMap<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 26; i++) {
            Assert.assertTrue(map.insert(i, (char) (96 + i)));
        }
    }

    @Test
    public void whenGetByExistKeyThenReturnValue() {
        for (int i = 0; i < 26; i++) {
            assertThat(map.get(i), is((char) (96 + i)));
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
        Assert.assertFalse(map.insert(1, 'A'));
    }

    @Test
    public void consistentlyDeleteAllElements() {
        for (int i = 0; i < 26; i++) {
            assertThat(map.get(i), is((char) (96 + i)));
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
    public void whenAddNullAndZeroBucketExistThenFalse() {
        Assert.assertFalse(map.insert(null, null));
    }


}
