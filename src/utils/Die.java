package utils;

import java.util.Random;

/* Classe para encapsular as ações de aleatoriedade */
public class Die {
    private static final Random rand = new Random(42);

    private Die() {};
    

    /* Devolve um número entre min à max */
    public static int interval(int min, int max) {
        return rand.nextInt((max-min) + 1) + min;
    }

    /* Devolve um número entre 1 à 6 */
    public static int roll() {
        return interval(1, 6);
    }


    /* Devolve um valor booleano aleatório */
    public static boolean headsOrTails() {
        return rand.nextBoolean();
    }
}
