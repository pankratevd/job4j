package ru.job4j.io.pack;

public class Pack {

    public static void main(String[] args) {
       Args a = new Args(args);
        System.out.println(a.directory());
        System.out.println(a.exclude());
        System.out.println(a.output());
    }
}
