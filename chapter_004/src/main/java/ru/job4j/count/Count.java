package ru.job4j.count;

import java.util.List;

public class Count {
    public static Integer count(List<Integer> data) {
        return data.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .mapToInt(Integer::intValue).sum();
    }
}
