package board.sector;

import utils.Die;
import utils.Position;

public class HiddenSector extends Sector { 
    public HiddenSector(Position pos, boolean n, boolean s, boolean l, boolean o){
        super(pos, n, s, l, o);
    }

    @Override 
    public boolean canAttack(){
        return Die.headsOrTails();
    }
}
