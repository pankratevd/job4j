package ru.job4j.io.links;

import java.util.HashSet;
import java.util.Set;

public class Group {

    public Set<Line> list = new HashSet<>();

    @Override
    public String toString() {
        return "Group{" + "list=" + list + '}';
    }
}
