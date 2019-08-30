package ru.job4j.profession;

import java.util.Date;

public class Doctor extends Profession {
    String license;

    public Appointment appointment(Patient patient, Date date) {
        return new Appointment();
    }

    public Diagnosis diagnosis(Patient patient, String desc) {
        return new Diagnosis();
    }

    public String getLicense() {
        return license;
    }
}
