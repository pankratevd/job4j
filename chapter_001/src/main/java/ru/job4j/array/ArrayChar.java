package ru.job4j.array;

public class ArrayChar {
    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startsWith(String word, String prefix) {
        boolean result = true;
        char[] pref = prefix.toCharArray();
        char[] wrd = word.toCharArray();
        for (int i = 0; i < pref.length; i++) {
            if (pref[i] != wrd[i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] i = new int[3][3];
        System.out.println(i.length);
    }
}
