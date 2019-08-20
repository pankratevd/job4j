package ru.job4j.loop;

public class PrimeNumber {
    public int calc(int finish) {
        int count = 0;
        boolean isPrime = true;
        for (int i = 1; i < finish; i++) {
            isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i%j==0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
            }
        }
        return count;
    }
}
