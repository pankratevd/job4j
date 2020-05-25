package ru.job4j.io.minicmd;

import java.util.Scanner;

public class Directory {
    public static void main(String[] args) {

        final Shell shell = new Shell();
        assert shell.path().equals("/");

        shell.cd("/");
        assert shell.path().equals("/");

        shell.cd("usr/..");
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");


        shell.cd("/");
        System.out.println("Type 'exit' for EXIT");
        Scanner sc = new Scanner(System.in);
        System.out.printf("[%s]: ", shell.path());
        String str = sc.nextLine();
        while (!"exit".equals(str)) {
            if (str.startsWith("cd")) {
                String[] arr = str.split(" ");
                if (arr.length > 1) {
                    shell.cd(arr[1]);
                }
            }
            System.out.printf("[%s]: ", shell.path());
            str = sc.nextLine();
        }
    }
}
