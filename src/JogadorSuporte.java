public class JogadorSuporte extends Jogador {
   // Construtor
   public JogadorSuporte() {
      super(1, 7);
   }
   
   // Habilidade Especial
   public void recuperaDef(Jogador j) {
      int defesaAtual = j.getDef();
      j.setDef(defesaAtual + 2);
   }
}
