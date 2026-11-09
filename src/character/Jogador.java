package character;

import board.sector.Sector;
import utils.Dado;

public abstract class Jogador extends Personagem implements Explorador {
   /***************Construtor*******************/
   public Jogador(int atk, int def) {
      super(atk, def, 3, 3);
   }
   
   /***************Metodos*******************/   
   public void movimentar(Sector destino) {
      setPos(destino.getPos());
   }

   public void procurar(Sector destino) {
      int dado = Dado.rolarDado();

      if(dado <= 3)
         return;
      else if(dado == 4)
         setDef(def + 1);
      else if(dado == 5)
         setDef(def + 2);
      else {
         for (Inimigo inimigo : destino.getInimigos()) {
            atacar(inimigo);
         }
      }
   }

}
