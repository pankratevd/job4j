package isp;

import java.util.Scanner;

public class ConsoleInput implements IMenuInput {
    @Override
    public String input() {
        System.out.print("Выберите пункт меню: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }
}
