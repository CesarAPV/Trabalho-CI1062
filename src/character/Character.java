package character;

import utils.Position;

public abstract class Character implements Attacker {
   /***************Atributos*******************/
   protected int atk, def;
   protected Position pos;

   /***************Construtor*******************/
   public Character(int atk, int def, int x, int y) {
      this.atk = atk;
      this.def = def;
      this.pos = new Position(x, y);
   }

   /***************Getters*******************/
   public int getAtk() { return atk; }
   public int getDef() { return def; }
   public Position getPos() { return pos; }

   /***************Setters*******************/
   public void setPos(Position pos) {
      this.pos = pos;
   }

   public void setDef(int def) {
      this.def = def;
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
   
   public void attack(Character target){
      target.takeDmg(atk);
   }

   @Override
   public String toString() {return this.getAtk() + "/" + this.getDef();}
}
