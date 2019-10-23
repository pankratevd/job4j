package ru.job4j.bank;

import java.util.Objects;

public class Account {

    private String requisites;

    private double value;

    public Account(String requisites) {
        this.requisites = requisites;
    }

    public Account(String requisites, double value) {
        this.value = value;
        this.requisites = requisites;
    }

    double getValue() {
        return value;
    }

    void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }

    @Override
    public String toString() {
        return "Account{" + "requisites='" + requisites + '\'' + ", value=" + value + '}';
    }
}
