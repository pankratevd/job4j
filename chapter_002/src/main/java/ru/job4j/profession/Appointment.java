package ru.job4j.profession;

import java.util.Date;

public class Appointment {
    Date date;
    Doctor doctor;
    Patient patient;

    public Date getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }
}
