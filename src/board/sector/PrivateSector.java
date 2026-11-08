package board.sector;


import utils.Posicao;

public class PrivateSector extends Sector { 
    public PrivateSector(Posicao pos, boolean n, boolean s, boolean l, boolean o){
        super(pos, n, s, l, o);
    }

    @Override 
    public boolean podeProcurar() {
        return false;
    }
}