package character;

public class JogadorSuporte extends Jogador implements Curador {
   /***************Construtor*******************/
   public JogadorSuporte() {
      super(1, 7);
   }
   
   /***************Habilidade Especial*******************/
   public void recuperarDefesa(Jogador alvo) {
      int alvoDef = alvo.getDef();
      alvo.setDef(alvoDef + 2);
   }
}
