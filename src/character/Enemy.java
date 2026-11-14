package character;

import utils.Die;
import utils.Position;

public class Enemy extends Character {
   /*************** Construtor *******************/
   private Enemy(int x, int y, int atributos) {
      super(atributos, atributos, x, y);
   }

   public Enemy(Position pos) {
      this(pos.getX(), pos.getY(), Die.interval(1, 3));
   }

   @Override
   public void attack(Character alvo) {
      int rand = Die.roll();

      if (rand % 2 == 0) {
         super.attack(alvo);
         System.out.println("Inimigo " + this + " acertou o ataque!");
      } else {
         System.out.println("Inimigo " + this + " errou o ataque!");
      }
   }
}
