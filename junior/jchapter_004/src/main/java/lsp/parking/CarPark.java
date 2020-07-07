package lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CarPark implements Parking {

    private final List<Integer> carPlaces;
    private final List<Integer> truckPlaces;

    public CarPark(final int carPlaces, final int truckPlaces) {
        this.carPlaces = new ArrayList<>(carPlaces);
        this.truckPlaces = new ArrayList<>(truckPlaces);
    }

    @Override
    public boolean accept(Auto auto) {
        return false;
    }

    @Override
    public String takePlace(Auto auto) {
        return null;
    }

    @Override
    public void releasePlace(Auto auto) {

    }

    @Override
    public Integer getFreePlaces(Auto auto) {
        return null;
    }
}
