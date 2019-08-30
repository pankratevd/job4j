package ru.job4j.profession;

import java.util.Date;

public class Diagnosis {
    public String decription;
    public Date date;
    public Patient patient;
    public Doctor doctor;

    public String getDecription() {
        return decription;
    }

    public Date getDate() {
        return date;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
