package ru.job4j.compare;

public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(User user) {
        return Integer.compare(this.age, user.age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
