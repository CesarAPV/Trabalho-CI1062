package character;

import board.sector.Sector;
import utils.Die;

public abstract class Player extends Character implements Explorer {
   /***************Construtor*******************/
   public Player(int atk, int def) {
      super(atk, def, 3, 3);
   }
   
   /***************Metodos*******************/   
   public void move(Sector dest) {
      setPos(dest.getPos());
   }

   public void search(Sector dest) {
      int dado = Die.roll();

      if(dado <= 3)
         return;
      else if(dado == 4)
         setDef(def + 1);
      else if(dado == 5)
         setDef(def + 2);
      else {
         for (Enemy enemy : dest.getEnemies()) {
            attack(enemy);
         }
      }
   }

}
