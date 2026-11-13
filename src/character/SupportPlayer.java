package character;

public class SupportPlayer extends Player implements Healer {
   /***************Construtor*******************/
   public SupportPlayer() {
      super(1, 7);
   }
   
   /***************Habilidade Especial*******************/
   public void heal(Player target) {
      int targetDef = target.getDef();
      target.setDef(targetDef + 2);
   }
}
