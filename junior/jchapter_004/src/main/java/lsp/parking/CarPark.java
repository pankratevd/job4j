package lsp.parking;

public class CarPark implements Parking {

    private final Auto[] carPlaces;
    private final Auto[] truckPlaces;
    private int freeCar;
    private int freeTruck;

    public CarPark(final int carPlaces, final int truckPlaces) {
        this.carPlaces = new Auto[carPlaces];
        this.truckPlaces = new Auto[truckPlaces];
        freeCar = carPlaces;
        freeTruck = truckPlaces;
    }

    @Override
    public boolean accept(Auto auto) {
        boolean result = false;

        if (auto.size() == 1 && freeCar != 0) {
            result = true;
        } else if (freeTruck != 0) {
            result = true;
        }

        return result;
    }

    @Override
    public String takePlace(Auto auto) {
        String possiblePlace = "";
        if (auto.size() == 1) {
            for (int i = 0; i < carPlaces.length; i++) {
                if (carPlaces[i] == null) {
                    carPlaces[i] = auto;
                    freeCar--;
                    possiblePlace = "car: " + (i + 1);
                    break;
                }
            }
        } else if (freeTruck != 0) {
            for (int i = 0; i < truckPlaces.length; i++) {
                if (truckPlaces[i] == null) {
                    truckPlaces[i] = auto;
                    freeTruck--;
                    possiblePlace = "truck: " + (i + 1);
                    break;
                }
            }
        }
        return possiblePlace;
    }

    @Override
    public void releasePlace(Auto auto) {
        if (auto.size() == 1) {
            for (int i = 0; i < carPlaces.length; i++) {
                if (carPlaces[i].equals(auto)) {
                    carPlaces[i] = null;
                    freeCar++;
                    break;
                }
            }
        } else {
            for (int i = 0; i < truckPlaces.length; i++) {
                if (truckPlaces[i].equals(auto)) {
                    truckPlaces[i] = null;
                    freeTruck++;
                    break;
                }
            }
        }

    }

    @Override
    public Integer getFreePlaces(Auto auto) {
        return auto.size() == 1 ? freeCar : freeTruck;
    }

    private boolean checkDoublePlaces() {
        boolean result = false;

        for (int i = 0; i < carPlaces.length - 1; i++) {
            if (carPlaces[i] == null) {
                if (carPlaces[i++] == null) {
                    result = true;
                }
            }
        }

        return result;
    }

}
