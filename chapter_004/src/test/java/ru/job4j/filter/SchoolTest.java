package ru.job4j.filter;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    @Test
    public void collectWhenScoreLessThan50() {
        Student student1 = new Student(45);
        Student student2 = new Student(75);
        Student student3 = new Student(50);
        Student student4 = new Student(76);
        Student student5 = new Student(35);
        Student student6 = new Student(60);
        List<Student> list = new ArrayList<>(Arrays.asList(student1, student2, student3, student4, student5, student6));
        List<Student> result = new School().collect(list, (s) -> (s.getScore() < 50));
        List<Student> expected = new ArrayList<>(Arrays.asList(student1, student5));
        assertThat(result, is(expected));
    }

    @Test
    public void collectWhenScoreBetween50and70() {
        Student student1 = new Student(45);
        Student student2 = new Student(75);
        Student student3 = new Student(50);
        Student student4 = new Student(76);
        Student student5 = new Student(35);
        Student student6 = new Student(60);
        List<Student> list = new ArrayList<>(Arrays.asList(student1, student2, student3, student4, student5, student6));
        List<Student> result = new School().collect(list, (s) -> (s.getScore() >= 50 && s.getScore() < 70));
        List<Student> expected = new ArrayList<>(Arrays.asList(student3, student6));
        assertThat(result, is(expected));
    }

    @Test
    public void collectWhenScoreMoreThan70() {
        Student student1 = new Student(45);
        Student student2 = new Student(75);
        Student student3 = new Student(50);
        Student student4 = new Student(76);
        Student student5 = new Student(35);
        Student student6 = new Student(60);
        List<Student> list = new ArrayList<>(Arrays.asList(student1, student2, student3, student4, student5, student6));
        List<Student> result = new School().collect(list, (s) -> (s.getScore() >= 70));
        List<Student> expected = new ArrayList<>(Arrays.asList(student2, student4));
        assertThat(result, is(expected));
    }

    @Test
    public void convertToMap() {
        Student s1 = new Student("Ivan", "Ivanov");
        Student s2 = new Student("Petr", "Petrov");
        Student s3 = new Student("Petr", "Ivanov");
        Student s4 = new Student("Ivan", "Petrov");
        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        Map<String, String> result = School.convertToMap(list);

        Map<String, String> expected = new HashMap<>();
        expected.put(s1.getSurname(), s1.getName());
        expected.put(s2.getSurname(), s2.getName());

        assertThat(result, is(expected));


    }
}
