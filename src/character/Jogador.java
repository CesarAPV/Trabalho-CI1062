package character;

import board.Direction;
import board.sector.Sector;
import utils.Dado;
import utils.Posicao;

public abstract class Jogador extends Personagem implements Explorador {
   /***************Construtor*******************/
   public Jogador(int atk, int def) {
      super(atk, def, 3, 3);
   }
   
   /***************Metodos*******************/   
   public void movimentar(Sector destino) {
      /* 
      Basta verificar duas coisas:
      - Se o setor destino está imediatamente ao redor 
      - Se tiver, tem uma porta nesse caminho para poder atravessar?
      Caso sim em ambos, apenas dar um setPos(new Position(destino.getPos()))
      */
      Boolean [] porta = destino.getPortas();
      Posicao posDestino = destino.getPos();
      int analiseX = this.pos.getX() - posDestino.getX();
      int analiseY = this.pos.getX() - posDestino.getY();
      
      if (analiseX == 0)
         if(analiseY > 0) {
            if(porta[Direction.LEFT.ordinal()])
               setPos(destino.getPos());
            if(porta[Direction.RIGHT.ordinal()])
               setPos(destino.getPos());
         }
      if (analiseY == 0)
         if(analiseX > 0) {
            if(porta[Direction.UP.ordinal()])
               setPos(destino.getPos());
            if(porta[Direction.DOWN.ordinal()])
               setPos(destino.getPos());
         }
   }

   public void procurar(Sector destino) {
      int dado = Dado.rolarDado();

      if(dado <= 3)
         return;
      else if(dado == 4)
         setDef(def + 1);
      else if(dado == 5)
         setDef(def + 2);
      else {
         for (Inimigo inimigo : destino.getInimigos()) {
            atacar(inimigo);
         }
      }
   }

}
