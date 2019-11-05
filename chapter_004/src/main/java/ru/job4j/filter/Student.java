package ru.job4j.filter;

public class Student {

    private int score;

    private String name;

    private String surname;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
