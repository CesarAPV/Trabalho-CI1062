import java.util.Scanner;

import javax.print.DocFlavor.STRING;

import board.Board;
import character.Jogador;
import character.JogadorSimples;
import character.JogadorSuporte;
import character.Inimigo;
import utils.Posicao;

public class Game{
    private Board board;
    private Jogador[] players;
    private int round;

    public Game (int numPlayers){
        this.board = new Board();
        
        this.players = new Jogador[numPlayers];
        this.players[0] = new JogadorSimples();
        if(numPlayers > 1)
            this.players[1] = new JogadorSuporte();
        
        this.round = 0;
    }


    private void runPlayer (Jogador player){
        Scanner scanner = new Scanner(System.in);
        char input;
        do{
            input = scanner.next().charAt(0);
        } while(input != 'w' || input != 'a' || input != '1' || input != '2' || input != '3' || input != 'p');
        
        switch (input){
            case 'w', 'a', 's', 'd' -> {
                Posicao pos = player.getPos();
                
            }
            case '1', '2', '3' -> {
                
            }
            case 'p'->{

            }
        }
    }
   
    private void runInimigo (Jogador player){
        Posicao pos = player.getPos();
        Sector setor = board.getSetor(pos.getX(),pos.getY());

        for (Inimigo setor.inimigos : inimigos) {
            inimigo.atacar(player);
        }
    }

    public int runRound (){
        for (Jogador player : jogadores) {
            runPlayer(player);
            runPlayer(player);
        }

        for (Jogador player : jogadores) {
            runInimigo(player);
        }
        
        return 0;
    }
}
