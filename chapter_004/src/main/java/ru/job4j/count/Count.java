package ru.job4j.count;

import java.util.List;
import java.util.Objects;

public class Count {
    public static Integer count(List<Integer> data) {
        return data.stream()
                .filter(Objects::nonNull)
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .mapToInt(Integer::intValue).sum();
    }
}
