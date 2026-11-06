package character;

import board.sector.Sector;;
public interface Explorador {
    public void movimentar(Sector destino);
    public void procurar(Sector destino);
}