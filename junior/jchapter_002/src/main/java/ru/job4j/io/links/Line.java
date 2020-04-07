package ru.job4j.io.links;


import java.util.Objects;

public class Line {

    private String line;

    private static final String DELIMITER = Link.DELIMITER;

    private final String[] arr;

    public Line(String line) {

        this.line = line;

        this.arr = line.split(DELIMITER);

    }

    public String[] getArr() {
        return arr;
    }

    @Override
    public String toString() {
        return line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line1 = (Line) o;
        return Objects.equals(line, line1.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line);
    }
}
