package character;

import utils.Posicao;

public abstract class Personagem implements Atacante {
   /***************Atributos*******************/
   protected int atk, def;
   protected Posicao pos;

   /***************Construtor*******************/
   public Personagem(int atk, int def, int x, int y) {
      this.atk = atk;
      this.def = def;
      this.pos = new Posicao(x, y);
   }

   /***************Getters*******************/
   public int getAtk() { return this.atk; }
   public int getDef() { return this.def; }
   public Posicao getPos() { return this.pos; }

   /***************Setters*******************/
   public void setPos(Posicao pos) {
      this.pos = pos;
   }
   public void setDef(int def) {
      this.def = def;
   }

   /***************Metodos*******************/
   public boolean estaVivo() { 
      if(def <= 0)
         return false;
      return true;
   }

   public void receberDano(int dmg) {
      def -= dmg;
      if(def < 0)
         def = 0;
   }
   
   public abstract void atacar(Personagem alvo);
}
