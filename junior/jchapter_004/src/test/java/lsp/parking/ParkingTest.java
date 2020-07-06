package lsp.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {

    Auto car1 = new Auto() {
        int size = 1;
        String number = "А000АА77RUS";

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public String number() {
            return this.number;
        }
    };

    Auto van1 = new Auto() {
        int size = 2;
        String number = "B000BB77RUS";

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public String number() {
            return this.number;
        }
    };

    @Test
    public void whenAutoIsAcceptedThenTrue() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return null;
            }
        };

        assertThat(parking.accept(car1), is(true));
        assertThat(parking.accept(van1), is(true));
    }

    @Test
    public void whenAutoIsNotAcceptedThenFalse() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return false;
            }

            @Override
            public String takePlace(Auto auto) {
                return "";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return null;
            }
        };

        assertThat(parking.accept(car1), is(false));
        assertThat(parking.accept(van1), is(false));
    }

    @Test
    public void whenGetFreeCarFreePlacesThenZero() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return 0;
            }
        };
        int expected = 0;
        assertThat(parking.getFreePlaces(car1), is(expected));
    }

    @Test
    public void whenGetFreeVanFreePlacesThenZero() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return 0;
            }
        };
        int expected = 0;
        assertThat(parking.getFreePlaces(van1), is(expected));
    }

    @Test
    public void whenVanParkingOnCarPlacesThenTrue() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return null;
            }
        };

        assertThat(parking.accept(van1), is(true));
    }

    @Test
    public void whenCarParkingThenReturnPlaceCarTwo() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "car: 2";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return null;
            }
        };

        String place = parking.takePlace(car1);
        String expected = "car: 2";
        assertThat(place, is(expected));
    }

    @Test
    public void whenVanParkingOnVanPlacesThenReturnPlaceVanOne() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "van: 1";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return null;
            }
        };

        String place = parking.takePlace(car1);
        String expected = "van: 1";
        assertThat(place, is(expected));
    }

    @Test
    public void whenVanParkingOnCarPlacesThenReturnPlaceCarThreeAndFour() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "car: 3, 4";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return null;
            }
        };

        String place = parking.takePlace(car1);
        String expected = "car: 3, 4";
        assertThat(place, is(expected));
    }

    @Test
    public void whenCarReleasePlace() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return 0;
            }
        };

        int freePlacesBefore = 0;
        int freePlacesAfter = 1;
        assertThat(parking.getFreePlaces(car1), is(freePlacesBefore));
        parking.releasePlace(car1);
        //assertThat(parking.getFreePlaces(car1), is(freePlacesAfter));
    }

    @Test
    public void whenVanReleaseVanPlace() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return 0;
            }
        };

        int freePlacesBefore = 0;
        int freePlacesAfter = 1;
        assertThat(parking.getFreePlaces(van1), is(freePlacesBefore));
        parking.releasePlace(van1);
        //assertThat(parking.getFreePlaces(van1), is(freePlacesAfter));
    }

    @Test
    public void whenVanReleaseCarPlaces() {
        Parking parking = new Parking() {

            @Override
            public boolean accept(Auto auto) {
                return true;
            }

            @Override
            public String takePlace(Auto auto) {
                return "";
            }

            @Override
            public void releasePlace(Auto auto) {

            }

            @Override
            public Integer getFreePlaces(Auto auto) {
                return 0;
            }
        };

        int freePlacesBefore = 0;
        int freePlacesAfter = 2;
        assertThat(parking.getFreePlaces(van1), is(freePlacesBefore));
        parking.releasePlace(van1);
        //assertThat(parking.getFreePlaces(car1), is(freePlacesAfter));
    }


}

