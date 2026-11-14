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

      System.out.println("Procura Sorteou " + dado + ":");
      if(dado <= 3) {
         System.out.println("Nada foi encontrado no setor.");
      } else if(dado == 4) {
         setDef(def + 1);
         System.out.println("Ganhou +1 DEF!");
      } else if(dado == 5) {
         setDef(def + 2);
         System.out.println("Ganhou +2 DEF!");
      } else {
         for (Enemy enemy : dest.getEnemies()) {
            enemy.takeDmg(1);
         }
         System.out.println("Todos os inimigos no setor perderam 1 de DEF!");
      }
   }

}
