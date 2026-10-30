public abstract class Jogador extends Personagem {
   // Construtor
   public Jogador(int atk, int def) {
       super(atk, def, 3, 3);
   }
   
   // Acoes Personagens
   public void move(int x, int y) {
      this.pos.setPos(x, y);
   }
    
   public int procurar() {
      Random random = new Random();
      int aleat = random.nextInt(6) + 1;
      
      // Nada ocorre
      if (aleat >= 1 && aleat <= 3)
         return 0;
      // Jogador ganha +1 DEF   
      else if (aleat == 4)
         return 1;
      // Jogador ganha +2 DEF
      else if (aleat == 5)
         return 2;
      // Inimigos perdem 1 DEF 
      else
         return -1;
   }
  
   @Override
   public void actionAtk(Personagem p) {
      int defIni = p.getDef();
      int atkJog = this.getAtk();
      
      p.setDef(defIni - atkJog);
      
      if (p.getDef() <= 0)
         p.morre();
   }
}
