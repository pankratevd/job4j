package ru.job4j.profession;

public class Patient {
    String name;
    String surname;
    Diagnosis diagnosis;
    Appointment appointment;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
