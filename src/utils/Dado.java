package utils;

import java.util.Random;

public class Dado {
    private static final Random rand = new Random(42);

    private Dado() {};

    public static int rolarDado() {
        return rand.nextInt(6) + 1;
    }

    public static int rolarIntervalo(int min, int max) {
        return rand.nextInt((max-min) + 1) + min;
    }

    public static boolean moeda() {
        return rand.nextBoolean();
    }
}
