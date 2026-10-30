package character;

import java.util.Random;

public class Inimigo extends Personagem {
   /*************** Construtor *******************/
   private Inimigo(int x, int y, int attr) {
      super(attr, attr, x, y);
   }

   public Inimigo(int x, int y) {
      this(x, y, gerarAtributo());
   }

   /***************Metodos*******************/
   private static int gerarAtributo() {
      Random random = new Random();
      return random.nextInt(3) + 1;
   }

   @Override
   public void attack(Personagem alvo) {
      Random random = new Random();
      int aleat = random.nextInt(6) + 1;

      if (aleat % 2 == 0) {
         super.attack(alvo);
      }
   }
}
