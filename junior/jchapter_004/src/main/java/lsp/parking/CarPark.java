package lsp.parking;

import java.util.ArrayList;
import java.util.List;

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
        } else {
            for (int i = 0; i < carPlaces.length - 1; i++) {
                int size = auto.size();
                if (carPlaces[i] == null) {
                    int start = i;
                    boolean isExists = true;
                    for (int j = ++start; j < size; j++) {
                        if (i == carPlaces.length || carPlaces[i++] != null) {
                            isExists = false;
                            break;
                        }
                    }
                    if (isExists) {
                        result = true;
                        break;
                    }
                }
            }
        }

        return result;
    }

    @Override
    public String takePlace(Auto auto) {
        String result = "";
        if (auto instanceof Car) {
            int index = findOnePlace(carPlaces);
            if (index != -1) {
                carPlaces[index] = auto;
                freeCar--;
                result = "car: " + (index + 1);
            }
        } else if (freeTruck != 0) {
            int index = findOnePlace(truckPlaces);
            if (index != -1) {
                truckPlaces[index] = auto;
                freeTruck--;
                result = "truck: " + (index + 1);
            }
        } else {
            List<Integer> list = findPlaces(carPlaces, auto.size());
            StringBuilder sb = new StringBuilder();
            sb.append("car:");
            for (Integer i : list) {
                carPlaces[i] = auto;
                freeCar--;
                sb.append(" ")
                        .append(i + 1)
                        .append(",");
            }
            result = sb.toString().substring(0, sb.length() - 1);
        }
        return result;
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
            boolean isExists = false;
            for (int i = 0; i < truckPlaces.length; i++) {
                if (truckPlaces[i].equals(auto)) {
                    truckPlaces[i] = null;
                    freeTruck++;
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                int count = 0;
                for (int i = 0; i < carPlaces.length; i++) {
                    if (carPlaces[i].equals(auto)) {
                        carPlaces[i] = null;
                        freeCar++;
                        if (++count == auto.size()) {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Integer getFreePlaces(Auto auto) {
        return auto.size() == 1 ? freeCar : freeTruck;
    }

    private int findOnePlace(Auto[] arr) {
        int result = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                result = i;
                break;
            }
        }
        return result;
    }

    private List<Integer> findPlaces(Auto[] arr, int number) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                int start = i;
                int end = i + number;
                boolean isExists = true;
                for (int j = i++; j < end; j++) {
                    if (arr[j] != null) {
                        isExists = false;
                        break;
                    }
                }
                if (isExists) {
                    for (int j = start; j < end; j++) {
                        result.add(j);
                    }
                    break;
                }
            }
        }
        return result;
    }
}
