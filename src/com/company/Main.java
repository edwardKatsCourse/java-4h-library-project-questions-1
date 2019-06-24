package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 10_000_000; i++) {
            String s1 = String.valueOf(getRandomISBN());
            if (s1.length() != 13) {
                throw new RuntimeException("Hard mathematics solution fails with result: " + s1);
            }
            String s2 = String.valueOf(getRandomISBN2());
            if (s2.length() != 13) {
                throw new RuntimeException("Integer concatenation failed with result: " + s2);
            }

            String s3 = String.valueOf(getRandomISBN3());
            if (s3.length() != 13) {
                throw new RuntimeException("ThreadLocalRandom.long failed with result: " + s3);
            }


        }


    }

    private static long getRandomISBN() {
        long isbn = new Random().nextLong();
        //-----------------------------
        if (isbn < 0) {
            isbn = -isbn;
        }
        //-----------------------------

//        System.out.println("before isbn = " + isbn);
        isbn = isbn % 1_000_000_000_000L;
//        System.out.println("after isbn  = " + isbn);

        //-----------------------------
        while (isbn < 1000_000_000_000L) { //isbn.length < 13
            isbn = isbn * 10;
        }
        //-----------------------------
        return isbn;
    }

    private static long getRandomISBN2() {
        int firstPart = ThreadLocalRandom.current().nextInt(1_000_000, 9_000_000); //7 digits
        int secondPart = ThreadLocalRandom.current().nextInt(100_000, 1_000_000); //6 digits
        String total = String.valueOf(firstPart) + String.valueOf(secondPart); //1000000100000

        return Long.valueOf(total);
    }

    private static long getRandomISBN3() {
        return ThreadLocalRandom.current().nextLong(1_000_000_000_000L, 10_000_000_000_000L);
    }
}
