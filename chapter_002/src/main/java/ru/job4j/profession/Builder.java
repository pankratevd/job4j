package ru.job4j.profession;

public class Builder extends Engineer {
    public String specialization;
    public Construction construction;

    public Work work(Construction construction) {
        return new Work();
    }

    public void work(Task task) {
    }

    public String getSpecialization() {
        return specialization;
    }

    public Construction getConstruction() {
        return construction;
    }
}
