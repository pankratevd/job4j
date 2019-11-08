package ru.job4j.student;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StudentTest {

    @Test
    public void levelOfCheck() {
        Student s1 = new Student("Ivan", 81);
        Student s2 = new Student("Ivan", 60);
        Student s3 = new Student("Alex", 70);
        Student s4 = new Student("Tim", 90);
        List<Student> list = new ArrayList<>();

        list.add(null);
        list.add(null);
        list.add(s1);
        list.add(null);
        list.add(null);
        list.add(s2);
        list.add(s3);
        list.add(null);
        list.add(s4);
        list.add(null);
        list.add(null);

        List<Student> expected = List.of(s4, s1);

       /* expected.add(s4);
        expected.add(s1);*/

        List<Student> result = Student.levelOf(list, 80);
        assertThat(result, is(expected));
    }

}
