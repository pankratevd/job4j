package ru.job4j.profession;

public class Programmer extends Engineer {
    String language;

    public Task task(Program program) {
        return new Task();
    }

    public void fulfil(Task task) {
    }

    public String getLanguage() {
        return language;
    }
}
