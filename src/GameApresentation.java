
import board.sector.Sector;
import board.Board;
import character.Enemy;
import character.Player;
import character.SimplePlayer;
import utils.*;

public class GameApresentation {

    private char[][] templateBoard;
    private char[][] templateSector;

    public GameApresentation(Position infecPosition) {

        this.templateBoard = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', '1', ' ', ' ', ' ', '2', ' ', ' ', ' ', '3', ' ', ' ', ' ', '4', ' ', ' ', ' ', '5', ' ', ' '},
            {' ', ' ', ' ', ' ', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|'},
            {'1', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|'},
            {'2', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '*', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|'},
            {'3', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '*', ' ', 'C', ' ', '*', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '*', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|'},
            {'4', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|'},
            {'5', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-', '|'}
        };

        this.templateSector = new char[][]{
            {'|', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '|'},
        };
        
        int i_TB = 2 * infecPosition.getX();
        int j_TB = 4 * infecPosition.getY() + 2;
        this.templateBoard[i_TB][j_TB] = 'X';

    }

    // Métodos de cabeçalho 
    public void printMenuMove(Player player) {
        System.out.print("----------------------------------\n");
        System.out.print("- Action - P");
        if (player instanceof SimplePlayer)
            System.out.print("1");
        else
            System.out.print("2");
        System.out.print(               "                  -\n");
        System.out.print("-    'w'    |        'up'        -\n");
        System.out.print("- 'a''s''d' | 'left''down''right'-\n");
        System.out.print("----------------------------------\n");
    }

    public void printMenuAction() {
        System.out.print("-----------------------------------\n");
        System.out.print("- atk  (1,2,3) | procurar (p)     -\n");
        System.out.print("- curar  (4,5) | passar   (0)     -\n");
        System.out.print("-----------------------------------\n");
    }

    public void printTitle(int round) {
        System.out.print("----------------------------\n"
                     + "|    Antivírus por um dia   |\n"
                     + "----------------------------\n");
        System.out.println("\n============= Ciclo " + (round + 1) + " / 25 =============\n");
    }

    public void printTitleSector(Board board, Player P1, Player P2){
        Position posP1 = P1.getPos();
        Position posP2 = P2.getPos();
  
        System.out.print("  Sector " + posP1.toString() + "  ");
        if(!posP1.equals(posP2)){
            System.out.print("     Sector " + posP2.toString() + "  ");
        }
        System.out.print("\n");
    }

    public void printGame(Board board, Player P1, Player P2, int round) {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        printTitle(round);
        this.updateTemplateBoard(board, P1, P2);
        char[][] sectors = updateTemplateSector(P1, P2, board);

        System.out.println(this.templateBoard[0]);
        System.out.println(this.templateBoard[1]);
        System.out.print(new String(this.templateBoard[2]) + "\t"); this.printTitleSector(board, P1, P2);
        System.out.println(this.templateBoard[3]);
        for(int i = 4; i < 11; i++)
            System.out.println(new String(this.templateBoard[i]) + "\t" + new String(sectors[i - 4]));
         System.out.println(this.templateBoard[11]);

        this.clearTemplateSector();
        this.clearTemplateBoard(P1, P2);
    }

    /**
     * Métodos Privados
     */
    private void updateTemplateBoard(Board board, Player P1, Player P2) {

        Position[] positions = {P1.getPos(), P2.getPos()};
        Position center = new Position(3,3);
        Sector[] sectors = {board.getSector(positions[0]), board.getSector(positions[1])};
        Boolean[] doors = new Boolean[4];
        int i_TB = 0, j_TB = 0;

        // Atualiza as portas que devem ser mostradas
        for (int i = 0; i < 2; i++) {
            doors = sectors[i].getDoors();
            i_TB = 2 * positions[i].getX();
            j_TB = 4 * positions[i].getY() + 2;

            if (doors[Direction.UP.ordinal()]) {
                this.templateBoard[i_TB - 1][j_TB] = '*';
            }
            if (doors[Direction.DOWN.ordinal()]) {
                this.templateBoard[i_TB + 1][j_TB] = '*';
            }
            if (doors[Direction.LEFT.ordinal()]) {
                this.templateBoard[i_TB][j_TB - 2] = '*';
            }
            if (doors[Direction.RIGHT.ordinal()]) {
                this.templateBoard[i_TB][j_TB + 2] = '*';
            }

            // Atualiza o campo do template que representa os jogadores;
            if(!positions[i].equals(center)){
                this.templateBoard[i_TB][j_TB] = 'P';
                this.templateBoard[i_TB][j_TB + 1] = (i == 0) ? '1' : '2';
            }
            else
                this.templateBoard[i_TB][j_TB] = 'C';

        }

        // Se estão no mesmo lugar corrige os números
        if (positions[0].equals(positions[1])) {
            this.templateBoard[i_TB][j_TB + 1] = ' ';
        }
    }

    private void clearTemplateBoard(Player P1, Player P2) {
        Position[] positions = {P1.getPos(), P2.getPos()};
        int i_TB = 2 * positions[0].getX();
        int j_TB = 4 * positions[0].getY() + 2;

        this.templateBoard[i_TB][j_TB] = ' ';
        this.templateBoard[i_TB][j_TB + 1] = ' ';
        if (!positions[0].equals(positions[1])) {
            /* Está correto essa algebra ? */
            i_TB = 2 * positions[1].getX() ;
            j_TB = 4 * positions[1].getY() + 2;
            this.templateBoard[i_TB][j_TB] = ' ';
            this.templateBoard[i_TB][j_TB + 1] = ' ';
        }
        this.templateBoard[6][14] = 'C';

    }

    private char[][] updateTemplateSector (Player P1, Player P2, Board board) {
        if (P1.getPos().equals(P2.getPos())) {
            sectorUpdate(P1, P2, board); 
            return this.templateSector;
        } else {
            sectorUpdate(P1, null, board);
        
            char[][] sectors = new char[7][33];
            
            for (int i = 0; i < 7; i++)
                for (int j = 0; j < 15; j++)
                    sectors[i][j] = this.templateSector[i][j];
                    //sectors[i][j + 18] = sector2[i][j];
            
            clearTemplateSector();
            sectorUpdate(null, P2, board);
            for (int i = 0; i < 7; i++)
                for (int j = 0; j < 15; j++)
                    sectors[i][j + 18] = this.templateSector[i][j];

            for (int i = 0; i < 7; i++) {
                sectors[i][15] = ' ';
                sectors[i][16] = ' ';
                sectors[i][17] = ' ';
            }
            return sectors;
        }

    }

    private void clearTemplateSector(){
        this.templateSector[0][7] = '-';
        this.templateSector[3][14] = '|';
        this.templateSector[3][0] = '|';
        this.templateSector[6][7] = '-';

        for(int j=2; j<14; j++){
            this.templateSector[1][j] = ' ';
            this.templateSector[4][j] = ' ';
            this.templateSector[5][j] = ' ';
        }
    }

    private void sectorUpdate(Player P1, Player P2, Board board) {
        Sector setor;
        if(P1 != null)
            setor = board.getSector(P1.getPos());
        else 
            setor = board.getSector(P2.getPos());

        Boolean[] doors = setor.getDoors();
        Enemy[] enemies = setor.getEnemies();
        int col = 2;
        for (Enemy e : enemies) {
            this.templateSector[1][col] = e.toString().charAt(0);
            this.templateSector[1][col + 1] = e.toString().charAt(1);
            this.templateSector[1][col + 2] = e.toString().charAt(2);
            col += 4;
        }

        if (doors[Direction.UP.ordinal()])
            this.templateSector[0][7] = '*';
        if (doors[Direction.RIGHT.ordinal()])
            this.templateSector[3][14] = '*';
        if (doors[Direction.LEFT.ordinal()]) 
            this.templateSector[3][0] = '*';
        if (doors[Direction.DOWN.ordinal()]) 
            this.templateSector[6][7] = '*';

        if(P1 != null){
            this.templateSector[4][3] = 'P';
            this.templateSector[4][4] = '1';
            for (int i = 0; i < 3; i++)
                this.templateSector[5][3 + i] = P1.toString().charAt(i);
        } else {
            this.templateSector[4][3] = 'P';
            this.templateSector[4][4] = '2';
            for (int i = 0; i < 3; i++)
                this.templateSector[5][3 + i] = P2.toString().charAt(i);
            return;
        }

        if(P2 != null ){
            this.templateSector[4][9] = 'P';
            this.templateSector[4][10] = '2';
            for (int i = 0; i < 3; i++) {
                this.templateSector[5][9 + i] = P2.toString().charAt(i);
            }
        }
    }
}
