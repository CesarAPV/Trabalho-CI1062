package board;


import board.sector.*;
import utils.*;

public class Board {
    // Nosso tabuleiro 0 "porta fechada", 1 setor, 2 "porta aberta"
    final private int [][] map = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 2, 1, 2, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
/*
ind. col.0  1  2  3  4  5  6  7  8  9  10
ind.verificar porta -> {1,3,5,7,9} (Open ou close)
*/



    };
    private Sector[][] sectors;
    // Construtor
    public Board() {
        sectors = gerarSetores();
    }

    private Sector[][] gerarSetores() {
        Sector[][] sectors = new Sector[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Position pos = new Position(i+1, j+1);
                boolean n, s, l, o;
                n = hasDoor(pos, Direction.UP);
                s = hasDoor(pos, Direction.DOWN);
                l = hasDoor(pos, Direction.RIGHT);
                o = hasDoor(pos, Direction.LEFT);
 
                int num = Die.interval(0, 3);
                switch (num) {
                    case 0:
                        sectors[i][j] = new NormalSector(pos,n,s,l,o);
                        break;
                    case 1:
                        sectors[i][j] = new HiddenSector(pos,n,s,l,o);
                        break;
                    case 2:
                        sectors[i][j] = new PrivateSector(pos,n,s,l,o);
                        break;
                }
            }   
        }
        int infecRow, infecCol;
        do {
            infecRow = Die.interval(0, 4);
            infecCol = Die.interval(0, 4);
        } while(infecRow == 3 && infecCol == 3);
        sectors[infecRow][infecCol].infect();

        return sectors;
    }

    private boolean isOpen(int i, int j) {
        if(map[i][j] == 2)
            return true;
        return false;
    }

    // Metodos publicos
    public boolean hasDoor(Position pos, Direction dir) {
        int x = 2*pos.getX() - 1;
        int y = 2*pos.getY() - 1;

        switch (dir) {
            case UP:
                x--;
                break;
            case RIGHT:
                y++;
                break;
            case DOWN:
                x++;
                break;
            case LEFT:
                y--;
                break;
        }        

        return isOpen(x, y);
    }

    public Sector getSector(Position pos) {
        int row, col;
        row = pos.getX() - 1;
        col = pos.getY() - 1;
        return sectors[row][col];
    }
