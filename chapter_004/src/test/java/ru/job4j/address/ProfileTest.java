package ru.job4j.address;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProfileTest {
    @Test
    public void collect() {
        Address a1 = new Address("City2", "Street2", 2, 2);
        Address a2 = new Address("City2", "Street2", 2, 2);
        Address a3 = null;
        Address a4 = new Address("City1", "Street1", 1, 1);
        Address a5 = new Address("City4", "Street4", 4, 4);
        Address a6 = new Address("City5", "Street5", 5, 5);

        Profile p1 = new Profile(a1);
        Profile p2 = new Profile(a2);
        Profile p3 = new Profile(a3);
        Profile p4 = new Profile(a4);
        Profile p5 = new Profile(a5);
        Profile p6 = new Profile(a6);

        List<Profile> list = List.of(p1, p2, p3, p4, p5, p6);
        /*list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
*/
        List<Address> expected = List.of(a4, a1, a5, a6);
       /* expected.add(a4);
        expected.add(a1);
        expected.add(a5);
        expected.add(a6);*/

        List<Address> result = Profile.collect(list);

        assertThat(result, is(expected));

    }

}
