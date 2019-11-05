package ru.job4j.filter;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {

    List<Student> collect(List<Student> students, Predicate<Student> predict) {

        return students.stream().filter(predict).collect(Collectors.toList());
    }

    static Map<String, String> convertToMap(List<Student> students) {
        return students.stream()
                .distinct()
                .collect(Collectors.toMap(Student::getSurname, Student::getName, (s, a) -> s));

    }

}
