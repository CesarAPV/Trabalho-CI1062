package board.sector;


import character.Inimigo;
import character.Jogador;
import utils.Posicao;
import utils.Dado;

public class HiddenSector extends Sector { 
    public HiddenSector(Posicao pos, boolean n, boolean s, boolean l, boolean o){
        super(pos, n, s, l, o);
    }

    @Override 
    public void processarAtaque(Jogador atacante, Inimigo alvo){
        boolean podeAtacar = Dado.moeda();
        if(podeAtacar) {
            atacante.atacar(alvo);
        }
    }

    @Override 
    public boolean podeProcurar() {
        return true;
    }
}
