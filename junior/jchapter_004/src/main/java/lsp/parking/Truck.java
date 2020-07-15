package lsp.parking;

import java.util.Objects;

public class Truck implements Auto {

    private final int size;
    private final String number;

    public Truck(String number, int size) {
        this.size = size;
        this.number = number;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String number() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return Objects.equals(number, truck.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
