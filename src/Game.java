import board.Board;
import board.Direction;
import board.sector.HiddenSector;
import board.sector.PrivateSector;
import character.Jogador;
import character.JogadorSimples;
import character.JogadorSuporte;
import java.util.Scanner;
import utils.Posicao;
import board.sector.Sector;
import character.Inimigo;

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
        } while(input != 'w' || input != 'a' || input != 's' || input != 'd' || input != '1' || input != '2' || input != '3' || input != 'p');
        Posicao pos = player.getPos();
        Sector setor = board.getSetor(pos.getX(), pos.getY());
        
        switch (input){
            case 'w', 'a', 's', 'd' -> {
                Boolean portas[] = setor.getPortas();
                if(input == 'w' && portas[Direction.UP.ordinal()])
                    player.movimentar(board.getSetor(pos.getX()+1, pos.getY()));
                else if(input == 'a' && portas[Direction.LEFT.ordinal()])
                    player.movimentar(board.getSetor(pos.getX(), pos.getY()-1));
                else if(input == 's' && portas[Direction.DOWN.ordinal()])
                    player.movimentar(board.getSetor(pos.getX()-1, pos.getY()));
                else if(input == 'd' && portas[Direction.RIGHT.ordinal()])
                    player.movimentar(board.getSetor(pos.getX(), pos.getY()+1));
            }
            case '1', '2', '3' -> {
                if(setor.podeAtacar()){
                    Inimigo inimigos[] = setor.getInimigos();
                    int tamanho = inimigos.length;
                    if(input == '1' && tamanho >= 1)
                        player.atacar(inimigos[0]);
                    else if (input == '1' && tamanho >= 2)
                        player.atacar(inimigos[1]);
                    else if (input == '1' && tamanho == 3)
                        player.atacar(inimigos[2]);
                }else{
                    System.out.println("Setor Escondido");
                }
            }
            case 'p'->{
                if(setor.podeProcurar()){
                    player.procurar(setor);
                }else{
                    System.out.println("Setor Privado");
                }
            }
        }
    }

    public int runRound (){
        for (Jogador player : jogadores) {
            runPlayer(player);
        }
        return 0;
    }
}