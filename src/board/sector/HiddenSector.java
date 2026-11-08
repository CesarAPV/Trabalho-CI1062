package board.sector;

import character.Personagem;
import utils.Posicao;
import utils.Dado;

public class HiddenSector extends Sector { 
    public HiddenSector(Posicao pos, boolean n, boolean s, boolean l, boolean o){
        super(pos, n, s, l, o);
    }

    @Override 
    public void processarAtaque(Personagem atacante, Personagem alvo){
        boolean podeAtacar = Dado.moeda();
        if(podeAtacar) {
            super.processarAtaque(atacante, alvo);
        }
    }
}
