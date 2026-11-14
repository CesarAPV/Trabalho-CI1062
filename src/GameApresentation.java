import board.sector.Sector;
import board.Board;
import character.Player;
import utils.*;

public class GameApresentation {

    private char[][] templateBoard;

    public GameApresentation(){
        this.templateBoard = new char[][] {
            {' ',' ',' ',' ',' ',' ','1',' ',' ',' ','2',' ',' ',' ','3',' ',' ',' ','4',' ',' ',' ','5',' ',' '},
            {' ',' ',' ',' ','|','-','-','-','|','-','-','-','|','-','-','-','|','-','-','-','|','-','-','-','|'},
            {'1',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
            {' ',' ',' ',' ','|','-','-','-','|','-','-','-','|','-','-','-','|','-','-','-','|','-','-','-','|'},
            {'2',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
            {' ',' ',' ',' ','|','-','-','-','|','-','-','-','|','-','*','-','|','-','-','-','|','-','-','-','|'},
            {'3',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','*',' ','C',' ','*',' ',' ',' ','|',' ',' ',' ','|'},
            {' ',' ',' ',' ','|','-','-','-','|','-','-','-','|','-','*','-','|','-','-','-','|','-','-','-','|'},
            {'4',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
            {' ',' ',' ',' ','|','-','-','-','|','-','-','-','|','-','-','-','|','-','-','-','|','-','-','-','|'},
            {'5',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
            {' ',' ',' ',' ','|','-','-','-','|','-','-','-','|','-','-','-','|','-','-','-','|','-','-','-','|'}
        };
    }
    
    // Métodos de cabeçalho 
    public void printMenu(){
        String linhaOut = "----------------------------\n";
        linhaOut +=       "| 1 - Iniciar o jogo       |\n";
        linhaOut +=       "| 2 - Sair                 |\n";
        linhaOut +=       "----------------------------\n";
        System.out.print(linhaOut);
    }

    public void printTitle(){
        String linhaOut = "----------------------------\n";
        linhaOut +=       "|    Antivírus por um dia    |\n";
        linhaOut +=       "----------------------------\n";
        System.out.print(linhaOut);
    }
    
    public void printBoard(Board board, Player P1, Player P2){

        this.updateTemplateBoard(board,P1,P2);
        for(int i = 0; i < 11; i++) {
            for (int j = 0; j < 25; j++) {
                System.out.print(templateBoard[i][j]);
            }
            System.out.println();
        }
        this.undoUpdateTemplateBoard(board,P1,P2);
    }

    /**
     * Métodos Privados
     */

    private void updateTemplateBoard(Board board, Player P1, Player P2){

        Position [] positions = {P1.getPos(), P2.getPos()};
        Sector [] sectors = {board.getSector(positions[0]), board.getSector(positions[1])};
        Boolean[] doors = new Boolean[4];
        int i_TB, j_TB;
        
    
        // Atualiza as portas que devem ser mostradas
        for (int i = 0; i < 2; i++){
            doors = sectors[i].getDoors();
            i_TB = 5*(positions[i].getX()-1) + 7;
            j_TB = 2*(positions[i].getY()-1) + 3;
            
            if (doors[Direction.UP.ordinal()])
                this.templateBoard[i_TB-1][j_TB] = '*';
            if (doors[Direction.DOWN.ordinal()])
                this.templateBoard[i_TB+1][j_TB] = '*';
            if (doors[Direction.LEFT.ordinal()])
                this.templateBoard[i_TB][j_TB-2] = '*';
            if (doors[Direction.RIGHT.ordinal()])
                this.templateBoard[i_TB][j_TB+2] = '*';

            // Atualiza o campo do template que representa os jogadores;
            this.templateBoard[i_TB][j_TB] = 'P';
            this.templateBoard[i_TB][j_TB+1] = (i == 0) ? '1' : '2';
        }

        // Se estão no mesmo lugar corrige os números
        if(positions[1].equals(positions[2]))
            this.templateBoard[i_TB][j_TB+1] = ' ';
    }

    private void undoUpdateTemplateBoard(Board board, Player P1, Player P2){
        Position [] positions = {P1.getPos(), P2.getPos()};
        int i_TB = 5*(positions[1].getX()-1) + 7;
        int j_TB = 2*(positions[1].getY()-1) + 3;

        this.templateBoard[i_TB][j_TB] = ' ';
        this.templateBoard[i_TB][j_TB + 1] = ' ';

        if(!positions[1].equals(positions[2])){
            i_TB = 2 * positions[2].getX() - 1;
            j_TB = 4 * positions[2].getY() - 2;
            this.templateBoard[i_TB][j_TB] = ' ';
            this.templateBoard[i_TB][j_TB + 1] = ' ';
        }
    }

    private char[][] SectorOutput (Player P1, Player P2, Board board) {
        if(P1.getPos().equals(P2.getPos())){
            
            char[][] sector = board.getSector(P1.getPos()).scheme();
            
            sector[4][3] = 'P'; sector[4][4] = '1';
            sector[4][9] = 'P'; sector[4][10] = '2';
            for(int i = 0; i < 3; i++){
                sector[5][3+i] = P1.toString().charAt(i); 
                sector[5][9+i] = P2.toString().charAt(i);
            }

            return sector;
        } else {
            char[][] sector1 = board.getSector(P1.getPos()).scheme();
            sector1[4][3] = 'P'; sector1[4][4] = '1';
            for(int i = 0; i < 4; i++)
                sector1[5][3+i] = P1.toString().charAt(i); 
            
            char[][] sector2 = board.getSector(P2.getPos()).scheme();
            sector2[4][3] = 'P'; sector2[4][4] = '2';
            for(int i = 0; i < 4; i++)
                sector2[5][3+i] = P2.toString().charAt(i); 
            
            char[][] sectors = new char[7][33];
            for(int i = 0; i < 7; i++)
                for(int j = 0; j < 15; j++){
                    sectors[i][j] = sector1[i][j];
                    sectors[i][j+18] = sector2[i][j];
                }
            for(int i = 0; i < 7; i++){
                sectors[i][15] = ' ';
                sectors[i][16] = ' ';
                sectors[i][17] = ' ';
            }

            return sectors;
        }
    }         
}