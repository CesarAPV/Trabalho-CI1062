import character.Inimigo;
import character.Jogador;
import board.sector.*;
import board.*;
import character.Player;
import utils.*;


public class GameOutput {

    public void imprimirTitulo(){
        String linhaOut = "----------------------------\n";
        linhaOut +=       "|    Antivírus por um dia    |\n";
        linhaOut +=       "----------------------------\n";
        System.out.print(linhaOut);

    }
    
    /* 
        Imprime o tabulerio adicionando sua formatação correta
        em uma String auxiliar e depois exibindo a mesma;
        Foi documentado o raciocínio por linha;        
    */
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

        /*
            Print linhas onde no desenho de board se iniciam com um número (1,2,3,4,5)
            A lógica baseará em printar as portas Left ou Rigth do setor, 
            Usará um método auxiliar para verificar qual o tipo de representação
            internar do tabuleiro (Se terá P, P1, P2 ou vazio);

            Ainda não pensei em como especificar a lógica para o X (fonte viral) e o
            C (centro do tabulerio), mas acho que basta complementar o método auxiliar
            "verPosBoard" -> (ver é de verifica)
        */
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
                        linhaOut += "*";
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

        /* Realizando a impressão da linha logo abaixo da que detém indice na representação */
        // Inicia sem nada, somente espaço
        linhaOut += "     |";
        for(int ind_c = 0; ind_c < 5; ind_c++){
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
Jogador P1, J
    public void verPosBoard(Posicao posSetor, Posicao posP1, Posicao posP2, String linhaOut){
        if(posP1.getX() == posP2.getX() && posP1.getY() == posP2.getY())
            if(posP1.getX() == posSetor.getX() && posP1.getY() == posSetor.getY())
                linhaOut += "P  ";
        else if(posP1.getX() == posSetor.getX() && posP1.getY() == posSetor.getY())
                linhaOut += "P1 ";
            else if(posP2.getX() == posSetor.getX() && posP2.getY() == posSetor.getY())
                linhaOut += "P2 ";
                else {linhaOut += "   ";}
    }

    /*public void imprimirSetor(Sector setor, Jogador P1, Jogador P2){
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
    }*/

    private String SectorOutput (Player P1, Player P2, Board board){
        if(P1.getPos() == P2.getPos()){
            System.out.println("  Setor " + P1.getPos().toString() + "  ");
            StringBuilder sector = new StringBuilder(board.getSetor(P1.getPos()).toString());
            sector.replace(67, 69, "P1");
            sector.replace(83, 86, P1.toString());
            sector.replace(74, 76, "P2");
            sector.replace(90, 93, P2.toString());
        } else {
            System.out.println("  Setor " + P1.getPos().toString() + "  ");
            StringBuilder sector = new StringBuilder(board.getSetor(P1.getPos()).toString());
            sector.replace(67, 69, "P1");
            sector.replace(83, 86, P1.toString());
            sector.replace(74, 76, "P2");
            sector.replace(90, 93, P2.toString());
        }
    }   
}


