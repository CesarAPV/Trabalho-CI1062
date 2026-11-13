package character;

import board.sector.Sector;

public interface Explorer {
    public void move(Sector destino);
    public void search(Sector destino);
}