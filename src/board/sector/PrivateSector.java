package board.sector;

import character.Inimigo;
import character.Jogador;
import utils.Posicao;

public class PrivateSector extends Sector { 
    public PrivateSector(Posicao pos, boolean n, boolean s, boolean l, boolean o){
        super(pos, n, s, l, o);
    }

    @Override 
    public void processarAtaque(Jogador atacante, Inimigo alvo){
        atacante.atacar(alvo);
    }

    @Override 
    public boolean podeProcurar() {
        return false;
    }
}