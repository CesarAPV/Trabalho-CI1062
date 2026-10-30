import java.util.Random;

public class Board {
    private Sector[][] sectors;
    // Nosso tabuleiro 0 "porta fechada", 1 setor, 2 "porta aberta"
    private int [][] board = {
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

    public Board() {
        Random random = new Random();
        this.sectors = new Sector[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int num = random.nextInt(3);
                switch (num) {
                    case 0:
                        this.sectors[i][j] = new NormalSector();
                        break;
                    case 1:
                        this.sectors[i][j] = new NormalSector();
                        break;
                    case 2:
                        this.sectors[i][j] = new NormalSector();
                        break;
                }

                // Gera Inimigos 

                int ii = (2*i) + 1;
                int jj = (2*j) + 1;

                boolean has_door_right = this.board[ii][jj+1] != 0;
                Door door_right = new Door(has_door_right);
                this.sectors[i][j].doors[Direction.RIGHT.ordinal()] = door_right;
                if(j < 4)
                    this.sectors[i][j+1].doors[Direction.LEFT.ordinal()] = door_right;
                
                boolean has_door_down = this.board[ii][jj+1] != 0;
                Door door_down = new Door(has_door_down);
                this.sectors[i][j].doors[Direction.DOWN.ordinal()] = door_down;
                if(i < 4)
                    this.sectors[i][j+1].doors[Direction.UP.ordinal()] = door_down;
                   
            }   
        }
        for (int i = 0; i < 5; i++) {
            Door door = new Door(false);
            this.sectors[i][0].doors[Direction.LEFT.ordinal()] = door;
        }     
        for (int j = 0; j < 5; j++){
            Door door = new Door(false);
            this.sectors[0][j].doors[Direction.LEFT.ordinal()] = door;
        }
    }

    

    private char[][] genBoard() {
        char[][] board = new char[11][21];

        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                for(int j = 0; j < 21; j++)
                    board[i][j] = '-';
            }
        }
        for (int j = 0; j < 21; j++) {
            if (j % 4 == 0) {
                for(int i = 0; i < 11; i++)
                    board[i][j] = '|';
            }
        }

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if(sectors[i][j].isPassed()){
                    if(sectors[i][j].isOpen(Direction.DOWN)) 
                        board[(i*2)+2][(j*4)+2] = '*';
                    if(sectors[i][j].isOpen(Direction.LEFT))
                        board[(i*2)+1][(j*4)+4] = '*';
                }
            }
        }


        board[6][11] = 'C';

        return board;
    }

    @Override
    public String toString(){
        char[][] board = this.genBoard();
        System.out.println("");
        return "Mudar retorno";
    }
}
