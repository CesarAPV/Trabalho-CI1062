package character;

import utils.Dado;
import utils.Posicao;

public class Inimigo extends Personagem {
   /*************** Construtor *******************/
   private Inimigo(int x, int y, int atributos) {
      super(atributos, atributos, x, y);
   }

   public Inimigo(Posicao pos) {
      int atributos = Dado.rolarIntervalo(1, 3);
      this(pos.getX(), pos.getY(), atributos);
   }

   @Override
   /*
      Como atacar não estava implementado em personagem
      o super não funionava, logo atualizei a lógica;
   */
   public void atacar(Personagem alvo) {
      int aleat = Dado.rolarDado();

      if (aleat % 2 == 0) {
         super.atacar(alvo);
      }
   }
}
