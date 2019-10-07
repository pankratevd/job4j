package ru.job4j.comparator;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result;
        int leftLength = left.length();
        int rightLength = right.length();
        int min = Integer.min(leftLength, rightLength);
        result = Integer.compare(leftLength, rightLength);
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                result = Character.compare(left.charAt(i), right.charAt(i));
                break;
            }
        }
        return result;
    }
}
