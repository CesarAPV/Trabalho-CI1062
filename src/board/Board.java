package board;


import board.sector.*;
import utils.*;

public class Board {
    // Nosso tabuleiro 0 "porta fechada", 1 setor, 2 "porta aberta"
    private int [][] map = {
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
    };
    private Sector[][] setores;

    // construtor
    public Board() {
        setores = gerarSetores();
    }

    private Sector[][] gerarSetores() {
        Sector[][] setores = new Sector[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Posicao pos = new Posicao(i+1, j+1);
                boolean n, s, l, o;
                n = hasDoor(pos, Direction.UP);
                s = hasDoor(pos, Direction.DOWN);
                l = hasDoor(pos, Direction.RIGHT);
                o = hasDoor(pos, Direction.LEFT);

                int num = Dado.rolarIntervalo(0, 3);
                switch (num) {
                    case 0:
                        setores[i][j] = new NormalSector(pos,n,s,l,o);
                        break;
                    case 1:
                        setores[i][j] = new HiddenSector(pos,n,s,l,o);
                        break;
                    case 2:
                        setores[i][j] = new PrivateSector(pos,n,s,l,o);
                        break;
                }
            }   
        }
        int linInfec, colInfec;
        do {
            linInfec = Dado.rolarIntervalo(0, 4);
            colInfec = Dado.rolarIntervalo(0, 4);
        } while(linInfec == 3 && colInfec == 3);
        setores[linInfec][colInfec].Infectar();

        return setores;
    }

    private boolean isOpen(int i, int j) {
        if(map[i][j] == 2)
            return true;
        return false;
    }

    // Metodos publicos
    public boolean hasDoor(Posicao pos, Direction dir) {
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

    public Sector getSetor(int x, int y) {
        return setores[x-1][y-1];
    }

/*     @Override
    public String toString(){
        char[][] board = this.genBoard();
        System.out.println("");
        return "Mudar retorno";
    } */
}
