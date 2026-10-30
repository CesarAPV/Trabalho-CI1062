public class Inimigo extends Personagem {
   // Construtor
   public Inimigo(int x, int y) {
      this(x, y, gerarAtributo());
   }

   private Inimigo(int x, int y, int atributo) {
      super(atributo, atributo, x, y);
   }

   private static int gerarAtributo() {
      Random random = new Random();
      return random.nextInt(3) + 1;
    }

   // Acao de atacar 
   @Override
   public abstract void actionAtk(Personagem p) {
      Random random = new Random();
      int aleat = random.nextInt(6) + 1;
      
      if (aleat % 2 == 0) {
         int defJog = p.getDef();
         int atkIni = this.getAtk();
         
         p.setDef(defJog - atkIni);

         if (p.getDef() <= 0)
            p.morre();

      }
   }
}
