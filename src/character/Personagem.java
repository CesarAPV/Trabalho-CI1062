public abstract class Personagem {
   // Atributos
   protected int atk;
   protected int def;
   protected Coordenada pos;
   protected boolean vivo;

   // Construtor
   public Personagem(int atk, int def, int x, int y) {
       this.atk = atk;
       this.def = def;
       this.vivo = true;
       this.pos = new Coordenada(x, y);
   }

   // Getters
   public int getAtk() {
      return this.atk;
   }
   public int getDef() {
      return this.def;
   }
   public Coordenada getPos() {
      return this.pos;
   }
   public boolean isVivo() {
      return this.vivo;
   }	
   
   // Setters
   public void setAtk(int atk) {
      if (atk >= 1)
         this.atk = atk;
   }
   public void setDef(int def) {
      if (def >= 0)
         this.def = def;
   }
   public void setPos(int x, int y) {
      this.pos.setX(x);
      this.pos.setY(y);
   }
   
   public void morre() {
      this.vivo = false;
   }

   // Ataque
   public abstract void actionAtk(Personagem p);
}
