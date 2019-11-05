package ru.job4j.address;

import java.util.List;
import java.util.stream.Collectors;

public class Profile {

    private Address address;

    public Profile(Address address) {
        this.address = address;
    }

    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream().filter((p) -> p.address != null).map((p) -> p.address).collect(Collectors.toList());
    }


}
