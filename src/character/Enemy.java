package character;

import utils.Die;
import utils.Position;

public class Enemy extends Character {
   /*************** Construtor *******************/
   private Enemy(int x, int y, int atributos) {
      super(atributos, atributos, x, y);
   }

   public Enemy(Position pos) {
      int atributos = Die.interval(1, 3);
      this(pos.getX(), pos.getY(), atributos);
   }

   @Override
   public void attack(Character alvo) {
      int rand = Die.roll();

      if (rand % 2 == 0) {
         super.attack(alvo);
      }
   }
}
