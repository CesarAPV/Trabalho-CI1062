
import board.sector.Sector;
import board.Board;
import character.Enemy;
import character.Player;
import utils.*;

public class GameApresentation {

    private char[][] templateBoard;
    private char[][] templateSector;

    public GameApresentation() {
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
    }

    // Métodos de cabeçalho 
    public void printMenu() {
        System.out.print("----------------------------\n"
                + "| 1 - Iniciar o jogo       |\n"
                + "| 2 - Sair                 |\n"
                + "----------------------------\n");
    }

    public void printTitle() {
        System.out.print("----------------------------\n"
                + "|    Antivírus por um dia    |\n"
                + "----------------------------\n");
    }

    public void printBoard(Board board, Player P1, Player P2) {

        this.updateTemplateBoard(board, P1, P2);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 25; j++) {
                System.out.print(templateBoard[i][j]);
            }
            System.out.println();
        }
        this.undoUpdateTemplateBoard(board, P1, P2);
    }

    /**
     * Métodos Privados
     */
    private void updateTemplateBoard(Board board, Player P1, Player P2) {

        Position[] positions = {P1.getPos(), P2.getPos()};
        Sector[] sectors = {board.getSector(positions[0]), board.getSector(positions[1])};
        Boolean[] doors = new Boolean[4];
        int i_TB, j_TB;

        // Atualiza as portas que devem ser mostradas
        for (int i = 0; i < 2; i++) {
            doors = sectors[i].getDoors();
            i_TB = 5 * (positions[i].getX() - 1) + 7;
            j_TB = 2 * (positions[i].getY() - 1) + 3;

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
            this.templateBoard[i_TB][j_TB] = 'P';
            this.templateBoard[i_TB][j_TB + 1] = (i == 0) ? '1' : '2';
        }

        // Se estão no mesmo lugar corrige os números
        if (positions[1].equals(positions[2])) {
            this.templateBoard[i_TB][j_TB + 1] = ' ';
        }
    }

    private void undoUpdateTemplateBoard(Board board, Player P1, Player P2) {
        Position[] positions = {P1.getPos(), P2.getPos()};
        int i_TB = 5 * (positions[1].getX() - 1) + 7;
        int j_TB = 2 * (positions[1].getY() - 1) + 3;

        this.templateBoard[i_TB][j_TB] = ' ';
        this.templateBoard[i_TB][j_TB + 1] = ' ';

        if (!positions[1].equals(positions[2])) {
            i_TB = 2 * positions[2].getX() - 1;
            j_TB = 4 * positions[2].getY() - 2;
            this.templateBoard[i_TB][j_TB] = ' ';
            this.templateBoard[i_TB][j_TB + 1] = ' ';
        }
    }

    private char[][] updateSector(Player P1, Player P2, Board board) {
        if (P1.getPos().equals(P2.getPos())) {
            return SectorOutput(P1, P2, board, templateSector); 
        }else {
            char[][] sector1 = SectorOutput(P1, null, board, templateSector);
            char[][] sector2 = SectorOutput(P2, null, board, templateSector);

            char[][] sectors = new char[7][33];
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 15; j++) {
                    sectors[i][j] = sector1[i][j];
                    sectors[i][j + 18] = sector2[i][j];
                }
            }
            for (int i = 0; i < 7; i++) {
                sectors[i][15] = ' ';
                sectors[i][16] = ' ';
                sectors[i][17] = ' ';
            }

            return sectors;
        }
    }

    private char[][] SectorOutput(Player P1, Player P2, Board board, char[][] template) {
        Sector setor = board.getSector(P1.getPos());
        Boolean[] doors = setor.getDoors();
        Enemy[] enemies = setor.getEnemies();
        int col = 2;
        for (Enemy e : enemies) {
            template[1][col] = e.toString().charAt(0);
            template[1][col + 1] = e.toString().charAt(1);
            template[1][col + 2] = e.toString().charAt(2);
            col += 4;
        }

        if (doors[Direction.UP.ordinal()])
            template[0][7] = '*';
        if (doors[Direction.RIGHT.ordinal()])
            template[3][14] = '*';
        if (doors[Direction.LEFT.ordinal()]) 
            template[3][0] = '*';
        if (doors[Direction.DOWN.ordinal()]) 
            template[6][7] = '*';

        if(P1 != null){
            template[4][3] = 'P';
            template[4][4] = '1';
            for (int i = 0; i < 3; i++)
                template[5][3 + i] = P1.toString().charAt(i);
        } else {
            template[4][3] = 'P';
            template[4][4] = '2';
            for (int i = 0; i < 3; i++)
                template[5][3 + i] = P2.toString().charAt(i);
            return template;
        }

        if(P2 != null ){
            template[4][9] = 'P';
            template[4][10] = '2';
            for (int i = 0; i < 3; i++) {
                template[5][9 + i] = P2.toString().charAt(i);
            }
        }

        return template;
    }
}
