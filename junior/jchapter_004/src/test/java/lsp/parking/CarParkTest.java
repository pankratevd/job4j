package lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CarParkTest {

    Auto car1 = new Car("А000АА77RUS", 1);

    Auto van1 = new Truck("B000BB77RUS", 2);

    @Ignore
    @Test
    public void whenAutoIsAcceptedThenTrue() {
        Parking parking = new CarPark(4, 4);

        assertThat(parking.accept(car1), is(true));
        assertThat(parking.accept(van1), is(true));
    }

    @Ignore
    @Test
    public void whenAutoIsNotAcceptedThenFalse() {
        Parking parking = new CarPark(4, 4);

        for (int i = 0; i < 4; i++) {
            parking.takePlace(new Car(String.valueOf(i), 1));
            parking.takePlace(new Truck(String.valueOf(i), 2));
        }

        assertThat(parking.accept(car1), is(false));
        assertThat(parking.accept(van1), is(false));
    }

    @Ignore
    @Test
    public void whenGetFreeCarFreePlacesThenZero() {
        Parking parking = new CarPark(4, 4);

        for (int i = 0; i < 4; i++) {
            parking.takePlace(new Car(String.valueOf(i), 1));
        }

        int expected = 0;
        assertThat(parking.getFreePlaces(car1), is(expected));
    }

    @Ignore
    @Test
    public void whenGetFreeVanFreePlacesThenZero() {
        Parking parking = new CarPark(4, 4);

        for (int i = 0; i < 4; i++) {
            parking.takePlace(new Truck(String.valueOf(i), 2));
        }

        int expected = 0;
        assertThat(parking.getFreePlaces(van1), is(expected));
    }

    @Ignore
    @Test
    public void whenVanParkingOnCarPlacesThenTrue() {
        Parking parking = new CarPark(4, 4);

        for (int i = 0; i < 2; i++) {
            parking.takePlace(new Car(String.valueOf(i), 1));
        }

        for (int i = 0; i < 4; i++) {
            parking.takePlace(new Truck(String.valueOf(i), 2));
        }

        assertThat(parking.accept(van1), is(true));
    }

    @Ignore
    @Test
    public void whenCarParkingThenReturnPlaceCarTwo() {
        Parking parking = new CarPark(4, 4);

        parking.takePlace(car1);

        String place = parking.takePlace(car1);
        String expected = "car: 2";
        assertThat(place, is(expected));
    }

    @Ignore
    @Test
    public void whenVanParkingOnVanPlacesThenReturnPlaceVanOne() {
        Parking parking = new CarPark(4, 4);

        String place = parking.takePlace(van1);

        String expected = "van: 1";
        assertThat(place, is(expected));
    }

    @Ignore
    @Test
    public void whenVanParkingOnCarPlacesThenReturnPlaceCarThreeAndFour() {
        Parking parking = new CarPark(4, 4);

        for (int i = 0; i < 2; i++) {
            parking.takePlace(new Car(String.valueOf(i), 1));
        }

        for (int i = 0; i < 4; i++) {
            parking.takePlace(new Truck(String.valueOf(i), 2));
        }

        String place = parking.takePlace(van1);
        String expected = "car: 3, 4";
        assertThat(place, is(expected));
    }

    @Ignore
    @Test
    public void whenCarReleasePlace() {
        Parking parking = new CarPark(4, 4);

        parking.takePlace(car1);

        for (int i = 0; i < 3; i++) {
            parking.takePlace(new Car(String.valueOf(i), 1));
        }

        int freePlacesBefore = 0;
        int freePlacesAfter = 1;
        assertThat(parking.getFreePlaces(car1), is(freePlacesBefore));
        parking.releasePlace(car1);
        assertThat(parking.getFreePlaces(car1), is(freePlacesAfter));
    }

    @Ignore
    @Test
    public void whenVanReleaseVanPlace() {
        Parking parking = new CarPark(4, 4);

        for (int i = 0; i < 3; i++) {
            parking.takePlace(new Truck(String.valueOf(i), 2));
        }

        parking.takePlace(van1);

        int freePlacesBefore = 0;
        int freePlacesAfter = 1;
        assertThat(parking.getFreePlaces(van1), is(freePlacesBefore));
        parking.releasePlace(van1);
        assertThat(parking.getFreePlaces(van1), is(freePlacesAfter));
    }

    @Ignore
    @Test
    public void whenVanReleaseCarPlaces() {
        Parking parking = new CarPark(4, 4);

        for (int i = 0; i < 4; i++) {
            parking.takePlace(new Truck(String.valueOf(i), 2));
        }

        for (int i = 0; i < 2; i++) {
            parking.takePlace(new Car(String.valueOf(i), 1));
        }

        parking.takePlace(van1);

        int freePlacesBefore = 0;
        int freePlacesAfter = 2;
        assertThat(parking.getFreePlaces(van1), is(freePlacesBefore));
        parking.releasePlace(van1);
        assertThat(parking.getFreePlaces(car1), is(freePlacesAfter));
    }
}

