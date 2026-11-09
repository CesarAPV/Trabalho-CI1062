package utils;

import java.util.Random;

/* Classe para encapsular as ações de aleatoriedade */
public class Dado {
    private static final Random rand = new Random(42);

    private Dado() {};
    
    /* Devolve um número entre 1 à 6 */
    public static int rolarDado() {
        return rand.nextInt(6) + 1;
    }

    /* Devolve um número entre min à max */
    public static int rolarIntervalo(int min, int max) {
        return rand.nextInt((max-min) + 1) + min;
    }

    /* Devolve um valor booleano aleatório */
    public static boolean moeda() {
        return rand.nextBoolean();
    }
}
