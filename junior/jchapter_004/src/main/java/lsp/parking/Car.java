package lsp.parking;

import java.util.Objects;

public class Car implements Auto {
    private final int size;

    private final String number;

    public Car(String number, int size) {
        this.number = number;
        this.size = size;
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
        Car car = (Car) o;
        return Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
