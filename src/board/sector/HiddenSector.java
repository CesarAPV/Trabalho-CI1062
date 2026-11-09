package board.sector;


import utils.Dado;
import utils.Posicao;

public class HiddenSector extends Sector { 
    public HiddenSector(Posicao pos, boolean n, boolean s, boolean l, boolean o){
        super(pos, n, s, l, o);
    }

    @Override 
    public boolean podeAtacar(){
        return Dado.moeda();
    }
}
