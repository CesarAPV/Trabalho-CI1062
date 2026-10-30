package character;

import utils.Coordenada;

public abstract class Personagem {
   /***************Atributos*******************/
   protected int atk, def;
   protected Coordenada pos;

   /***************Construtor*******************/
   public Personagem(int atk, int def, int x, int y) {
      this.atk = atk;
      this.def = def;
      this.pos = new Coordenada(x, y);
   }

   /***************Getters*******************/
   public int getAtk() { return this.atk; }
   public int getDef() { return this.def; }
   public Coordenada getPos() { return this.pos; }

   /***************Setters*******************/
   public void setAtk(int atk) {
      if (atk >= 1)
         this.atk = atk;
   }

   public void setDef(int def) {
      if (def >= 0)
         this.def = def;
   }

   public void setPos(int x, int y) {
      pos.atualizaCordenada(x,y);
   }

   /***************Metodos*******************/
   public boolean isAlive() { 
      if(def <= 0)
         return false;
      return true;
   }

   public void takeDmg(int dmg) {
      def -= dmg;
      if(def < 0)
         def = 0;
   }

   public void attack(Personagem alvo) {
      alvo.takeDmg(atk);
   }
}
