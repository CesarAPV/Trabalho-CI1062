package board.sector;

import utils.Position;

public class PrivateSector extends Sector { 
    public PrivateSector(Position pos, boolean n, boolean s, boolean l, boolean o){
        super(pos, n, s, l, o);
    }

    @Override 
    public boolean canSearch() {
        return false;
    }
}