package ru.job4j.io.links;

import java.util.Objects;

public class Line {

    String field1;

    String field2;

    String field3;

    public Line(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
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
        return Objects.equals(field1, line1.field1)
                && Objects.equals(field2, line1.field2)
                && Objects.equals(field3, line1.field3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(field1);
        sb.append(";");
        sb.append(field2);
        sb.append(";");
        sb.append(field3);
        return sb.toString();
    }
}
