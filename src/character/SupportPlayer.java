package character;

public class SupportPlayer extends Player implements Healer {
   private SimplePlayer simplePlayer;
   /***************Construtor*******************/
   public SupportPlayer(SimplePlayer simplePlayer) {
      super(1, 7);

      if(simplePlayer == null) {
         throw new IllegalArgumentException("O jogador de suporte nao pode existir sem um jogador simples!");
      }

      if (this.atk >= simplePlayer.getAtk()) {
         this.atk = simplePlayer.getAtk()-1;
      }
      this.simplePlayer = simplePlayer;
   }
   
   @Override
   public boolean isAlive() {
      boolean p2IsAlive = super.isAlive();
      boolean p1IsAlive = simplePlayer.isAlive();
      return p1IsAlive && p2IsAlive;   
   }

   /***************Habilidade Especial*******************/
   public void heal(Player target) {
      int targetDef = target.getDef();
      target.setDef(targetDef + 2);
   }


}
