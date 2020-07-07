package lsp.parking;

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
}
