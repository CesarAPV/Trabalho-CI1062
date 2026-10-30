package character;

public class JogadorSuporte extends Jogador {
   /***************Construtor*******************/
   public JogadorSuporte() {
      super(1, 7);
   }
   
   /***************Habilidade Especial*******************/
   public void recuperaDef(Jogador j) {
      j.setDef(j.getDef() + 2);
   }
}
