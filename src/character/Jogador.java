package character;

import java.util.Random;

public abstract class Jogador extends Personagem {
   /***************Construtor*******************/
   public Jogador(int atk, int def) {
       super(atk, def, 3, 3);
   }
   
   /***************Metodos*******************/   
   public void move(int x, int y) {
      this.setPos(x, y);
   }
    
   public void procurar() {
      Random random = new Random();
      int aleat = random.nextInt(6) + 1;
      
      if(aleat <= 3) 
         return;
      else if(aleat == 4) 
         setDef(def + 1);
      else if(aleat == 5)
         setDef(def + 2);
      // else causa dano em todos os inimigos
   }
}
