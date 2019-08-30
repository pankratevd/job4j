package ru.job4j.profession;

public class Task {
    String description;
    Program program;
    Programmer programmer;
    boolean isCompleted;

    public String getDescription() {
        return description;
    }

    public Program getProgram() {
        return program;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
