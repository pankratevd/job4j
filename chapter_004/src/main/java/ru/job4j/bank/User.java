package ru.job4j.bank;

public class User {

    private String name;

    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    String getPassport() {
        return passport;
    }

    String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", passport='" + passport + '\'' + '}';
    }

}
