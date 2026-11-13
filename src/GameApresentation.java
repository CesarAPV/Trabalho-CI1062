import board.*;
import characters.*;
import utils.*;

public class GameApresentation {

    private char [11][25] templateBoard;
    /*Ver como Adicionar a representação da fonte de Infecção "X"*/
    public GameApresentation(){
        this.templateBoard = new char[11][25];
        this.templateBoard = {
            {" "," "," "," ","|","-","-","-","|","-","-","-","|","-","-","-","|","-","-","-","|","-","-","-","|"},
            {"1"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"},
            {" "," "," "," ","|","-","-","-","|","-","-","-","|","-","-","-","|","-","-","-","|","-","-","-","|"},
            {"2"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"},
            {" "," "," "," ","|","-","-","-","|","-","-","-","|","-","*","-","|","-","-","-","|","-","-","-","|"},
            {"3"," "," "," ","|"," "," "," ","|"," "," "," ","*"," ","C"," ","*"," "," "," ","|"," "," "," ","|"},
            {" "," "," "," ","|","-","-","-","|","-","-","-","|","-","*","-","|","-","-","-","|","-","-","-","|"},
            {"4"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"},
            {" "," "," "," ","|","-","-","-","|","-","-","-","|","-","-","-","|","-","-","-","|","-","-","-","|"},
            {"5"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"," "," "," ","|"},
            {" "," "," "," ","|","-","-","-","|","-","-","-","|","-","-","-","|","-","-","-","|","-","-","-","|"},
            }
    /*
        Os Indices dos setores serão:
        Linhas: 1,3,5,7,9
        Colunas: 6,10,14,18,22 

        Cordenadas Setores
        (1,1) -> (1,6)
        (1,2) -> (1,10)
        (1,3) -> (1,14)
        (1,4) -> (1,18)
        (1,5) -> (1,22)

        (2,1) -> (3,6)
        (2,2) -> (3,10)
        (2,3) -> (3,14)
        (2,4) -> (3,18)
        (2,5) -> (3,22)

        (3,1) -> (5,6)
        (3,2) -> (5,10)
        (3,3) -> (5,14)
        (3,4) -> (5,18)
        (3,5) -> (5,22)

        (4,1) -> (7,6)
        (4,2) -> (7,10)
        (4,3) -> (7,14)
        (4,4) -> (7,18)
        (4,5) -> (7,22)

        (5,1) -> (9,6)
        (5,2) -> (9,10)
        (5,3) -> (9,14)
        (5,4) -> (9,18)
        (5,5) -> (9,22)
    */
   
    }
    /* Manter ? Foi somente uma ideia */
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

    private void updateTemplateBoard(Board board, Player P1, Player P2){

        Position [2] positions = {P1.getPos(), P2.getPos()};
        Sector [2] sectors = {board.getSector(positions[0]), board.getSector(positions[1])};
        boolean [4] doors;
        int i_TB, j_TB;
        
        /*
            - Atualiza as portas que devem ser mostradas
            para cada um dos setores que os jogadores estão, para então printar o board;
            - Se são o mesmo setor, a dupla atualização não irá causar problemas, pois a 
            mesma porta será atualizada duas vezes;
        */
        for (int i = 0; i < 2; i++){
            doors = sectors[i].getDoors();
            i_TB = 2*positions[i].getX() - 1;
            j_TB = 4*positions[i].getY() - 2;
            
            if (doors[Direction.UP.ordinal()])
                this.templateBoard[i_TB-1][j_TB] = "*";
            if (doors[Direction.DOWN.ordinal()])
                this.templateBoard[i_TB+1][j_TB] = "*";
            if (doors[Direction.LEFT.ordinal()])
                this.templateBoard[i_TB][j_TB-2] = "*";
            if (doors[Direction.RIGHT.ordinal()])
                this.templateBoard[i_TB][j_TB+2] = "*";
        }

        /*
            - Atualiza o campo do template que representa os jogadores;
        */
        if(positions[0].getX() == positions[1].getX() && positions[0].getY() == positions[1].getY())
            this.templateBoard[i_TB][j_TB] = 'P';
        else{
            for (int i = 0; i < 2; i++){
                i_TB = 2*positions[i].getX() - 1;
                j_TB = 4*positions[i].getY() - 2;
                this.templateBoard[i_TB][j_TB-1] = 'P';
                this.templateBoard[i_TB][j_TB] = (i == 0) ? '1' : '2';
            }
        }
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

    private void undoUpdateTemplateBoard(Board board, Player P1, Player P2){

        Position [2] positions = {P1.getPos(), P2.getPos()};
        Sector [2] sectors = {board.getSector(positions[0]), board.getSector(positions[1])};
        boolean [4] doors;
        int i_TB = 2*positions[0].getX() - 1;
        int j_TB = 4*positions[0].getY() - 2;

        /* Vai "limpar" os player do templateBoard, 
            para ficar limpo o uso no próximo print
        */
        if(positions[0].getX() == positions[1].getX() && positions[0].getY() == positions[1].getY())
            this.templateBoard[i_TB][j_TB] = ' ';
        else{
            for (int i = 0; i < 2; i++){
                i_TB = 2*positions[i].getX() - 1;
                j_TB = 4*positions[i].getY() - 2;
                this.templateBoard[i_TB][j_TB-1] = ' ';
                this.templateBoard[i_TB][j_TB] = ' ';
            }
        }
    }

/*      Board boardAux = board;
        Sector spara então printar o board na próxima vez que for chamado o método printBoard ectorAux;
        Sector sectorP1 = boardAux.getSector(P1.getPos());
        Sector sectorP2 = boardAux.getSector(P2.getPos());
        Position posP1 = sectorP1.getPos();
        Position posP2 = sectorP2.getPos();
        boolean  flagLine;

        /* Atualiza as portas que devem ser mostradas
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                Position posAux = new Position(i,j);
                int i_board = 2*i - 1;
                int j_board = 4*j - 2;
                sectorAux = board.getSector(posAux);
                
                /* Atualiza no board as informações as portas para print;
                /* Verificando se o setor foi visitado e não está infectado, para então printar as portas 
                if (sectorAux.isVisited() && !sectorAux.isInfected()) {
                    if (sectorAux.getDoor(UP))
                        boardAux[i_board-1][j_board] = 2;
                    if (sectorAux.getDoor(DOWN))
                        boardAux[i_board+1][j_board] = 2;
                    if (sectorAux.getDoor(LEFT))
                        boardAux[i_board][j_board-2] = 2;
                    if (sectorAux.getDoor(RIGHT))
                        boardAux[i_board][j_board+2] = 2;
                }   
            }
        }

        for (int i = 0; i < 11; i++) {
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9){
                flagLine = true;
                System.out.print((int)(Math.ceil(i/2.0)) + "    |");
            }
            else{
                flagLine = false;
                System.out.print("     |");
            }

            for (int j = 0; j < 11; j++) {
                if (flagLine){
                    if(board[i][j] == 0)
                        if (j == 2 | j == 4 | j == 6 | j == 8 | j == 10)
                            System.out.print("-|-");
                        else
                            System.out.print("-");
                    else if (board[i][j] == 2)
                        System.out.print("*");
                    else
                        System.out.print("-");
                }
                else {

                }
            }
*/

            /*
                Falta implementar a lógica de printar os inimigos, o P1 e o P2, e o X (fonte viral) e o C (centro do tabuleiro)
            */
           /*
                Falta Fazer o for para printar em sim com a lógica;
            */
        

        }



    }
    
    
    
}





















/* 
        Imprime o tabulerio adicionando sua formatação correta
        em uma String auxiliar e depois exibindo a mesma;
        Foi documentado o raciocínio por linha;        

    public void imprimirBoard(Board board, Jogador P1, Jogador P2){
        Posicao posP1 = P1.getPos();
        Posicao posP2 = P2.getPos();
        
        // Linha 0;
        String linhaOut = "       1   2   3   4   5 \n";

        // Linha 1;
        linhaOut += "     |---|---|---|---|---|\n";

        // Linha 2 à 10
        for(int ind_i = 1; ind_i < 6; ind_i ++){
            auxImprimirBoard(board, ind_i, posP1, posP2, linhaOut);
        }

       System.out.println(linhaOut);
    }

    public void auxImprimirBoard(Board board, int ind_i, Posicao posP1, Posicao posP2, String linhaOut){

        Sector setor;
        Posicao posSetor;
        Boolean [] portas = new Boolean[4]; 

        
            Print linhas onde no desenho de board se iniciam com um número (1,2,3,4,5)
            A lógica baseará em printar as portas Left ou Rigth do setor, 
            Usará um método auxiliar para verificar qual o tipo de representação
            internar do tabuleiro (Se terá P, P1, P2 ou vazio);

            Ainda não pensei em como especificar a lógica para o X (fonte viral) e o
            C (centro do tabulerio), mas acho que basta complementar o método auxiliar
            "verPosBoard" -> (ver é de verifica)
        
        linhaOut += ind_i + "    |   ";
        for(int ind_c = 0; ind_c < 5; ind_c++){
            setor = board.getSetor(ind_i, ind_c+1);
            posSetor = setor.getPos();
            portas = setor.getPortas();

            // Se é um setor da coluna inicial, por definição não tem porta da esquerda;
            if(ind_c == 0){
                if(portas[Direction.RIGHT.ordinal()])
                    linhaOut += "*";
                else
                    linhaOut += "|";
                verPosBoard(posSetor, posP1, posP2, linhaOut);
            }
            // Se é um setor da coluna final, por definição não tem porta da direita;
            else if(ind_c == 4) {
                    if(portas[Direction.LEFT.ordinal()])
                        linhaOut += "*";imprimirTitulo
                    else
                        linhaOut += "|";
                    verPosBoard(posSetor, posP1, posP2, linhaOut);
            }
            // Verifica a cda setor sua esquerda e direita;
            else{
                if(portas[Direction.LEFT.ordinal()])
                    linhaOut += "*";
                else
                    linhaOut += "|";
                verPosBoard(posSetor, posP1, posP2, linhaOut);
                if(portas[Direction.RIGHT.ordinal()])
                    linhaOut += "*";
                else
                    linhaOut += "|";
                verPosBoard(posSetor, posP1, posP2, linhaOut);
            }
            linhaOut +="|\n";            
        }

        Realizando a impressão da linha logo abaixo da que detém indice na representação 
        // Inicia sem nada, somente espaço
        linhaOut += "     |";
        for(int ind_c = 0; ind_c < 5; ind_c++){imprimirTitulo
            setor = board.getSetor(ind_i, ind_c+1);
            posSetor = setor.getPos();
            portas = setor.getPortas();
            
            // Somente irá verificar se possui portas abaixo, dessa forma,
            // A lógica de verificar as superiores já estão resolvidas;
            if(portas[Direction.DOWN.ordinal()])
                linhaOut += "-*-";
            else
                linhaOut += "---";
            linhaOut += "|";
        }
        linhaOut += "\n";

    }

    public void verPosBoard(Posicao posSetor, Posicao posP1, Posicao posP2, String linhaOut){
        if(posP1.getX() == posP2.getX() && posP1.getY() == posP2.getY())
            if(posP1.getX() == posSetor.getX() && posP1.getY() == posSetor.getY())
                linhaOut += "P  ";
        else if(posP1.getX() == posSetor.getX() && posP1.getY() == posSetor.getY())
                linhaOut += "P1 ";
            else if(posP2.getX() == posSetor.getX() && posP2.getY() == posSetor.getY())
                linhaOut += "P2 ";imprimirTitulo
                else {linhaOut += "   ";}
    }

    public void imprimirSetor(Sector setor, Jogador P1, Jogador P2){
        String linhaOut;
        Posicao posSetor = setor.getPos();
        Posicao posP1 = P1.getPos();
        Posicao posP2 = P2.getPos();
        Boolean [] portas = new Boolean[4];
        portas = setor.getPortas();

        // Titulo
        linhaOut = "  Setor  [" + posSetor.getX() + ","
        + posSetor.getY() + "]  \n";
        
        // Linha 1
        linhaOut += "|-------" + (portas[Direction.UP.ordinal()].isOpen() ? "*" : "-")
        + "------|\n";
       
        // Linha 2
        linhaOut += "|";
        for (Inimigo inimigo : inimigos)
            linhaOut += " " + inimigo.getAtk() + "/" + inimigo.getDef();
        
        linhaOut += " |\n";
        
        // Linha 3
        linhaOut += "|             |";
    
        // Linha 4
        linhaOut += (portas[Direction.LEFT.ordinal()].isOpen() ? "*" : "|");
        linhaOut += "             ";
        linhaOut += (portas[Direction.RIGHT.ordinal()].isOpen() ? "*" : "|");

        // Linha 5 e 6
        linhaOut += "|  ";
        if(posP1.getX() == posP2.getX() && posP1.getY() == posP2.getY())
            if(posSetor.getX() == posP1.getX() && posSetor.getX() == posP1.getX()){
                linhaOut += "P1     P2  |\n";
                linhaOut += "|  " + P1.getAtk() + "/"+ P1.getDef();
                linhaOut += "    " + P2.getAtk() + "/" + P2.getDef() + "  |\n";
            }
        else if(posSetor.getX() == posP1.getX() && posSetor.getX() == posP1.getX()){
            linhaOut += "P1         |\n";
            linhaOut += "|  " + P1.getAtk() + "/"+ P1.getDef();
            linhaOut += "        |\n";
        }
        else {
            linhaOut += "P2         |\n";
            linhaOut += "|  " + P2.getAtk() + "/"+ P2.getDef();
            linhaOut += "        |\n";
        }

        // Linha 7
        linhaOut = "|-------" + (portas[Direction.DOWN.ordinal()].isOpen() ? "*" : "-")
        + "------|\n";

        // Imprime a String Formatada Corretamente
        System.out.println(linhaOut);
    }


}


*/