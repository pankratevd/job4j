package lsp.parking;

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
}
