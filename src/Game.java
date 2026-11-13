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
    private Player[] players;
    private int round;

    public Game (int numPlayers){
        this.board = new Board();
        
        this.players = new Player[numPlayers];
        this.players[0] = new SimplePlayer();
        if(numPlayers > 1)
            this.players[1] = new SupportPlayer();
        
        this.round = 0;
    }


    private void runPlayer (Player player){
        Scanner scanner = new Scanner(System.in);
        char input;
        do{
            input = scanner.next().charAt(0);
        } while(input != 'w' || input != 'a' || input != 's' || input != 'd' || input != '1' || input != '2' || input != '3' || input != 'p');
        Position pos = player.getPos();
        Sector sector = board.getSetor(pos);
        
        switch (input){
            case 'w', 'a', 's', 'd' -> {
                if(input == 'w' && sector.getDoor(Direction.UP)) {
                    player.move(board.getSetor(pos.shift(Direction.UP)));
                }
                else if(input == 'a' && sector.getDoor(Direction.LEFT)) {
                    player.move(board.getSetor(pos.shift(Direction.LEFT)));
                }
                else if(input == 's' && sector.getDoor(Direction.DOWN)) {
                    player.move(board.getSetor(pos.shift(Direction.DOWN)));
                }
                else if(input == 'd' && sector.getDoor(Direction.RIGHT)) {
                    player.move(board.getSetor(pos.shift(Direction.RIGHT)));
                }
            }
            case '1', '2', '3' -> {
                if(sector.canAttack()){
                    Enemy enemies[] = sector.getEnemies();
                    int tamanho = enemies.length;
                    if(input == '1' && tamanho >= 1)
                        player.attack(enemies[0]);
                    else if (input == '1' && tamanho >= 2)
                        player.attack(enemies[1]);
                    else if (input == '1' && tamanho == 3)
                        player.attack(enemies[2]);
                }
                else{
                    System.out.println("Setor Escondido");
                }
            }
            case 'p'->{
                if(sector.canSearch()){
                    player.search(sector);
                }else{
                    System.out.println("Setor Privado");
                }
            }
        }

        scanner.close();
    }
   
    private void runInimigo (Player player){
        Position pos = player.getPos();
        Sector sector = board.getSetor(pos);

        for(Enemy enemy : sector.getEnemies()) {
            enemy.attack(player);
        }
    }

    public int runRound (){
        for (Player player : players) {
            runPlayer(player);
            runPlayer(player);
        }

        for (Player player : players) {
            runInimigo(player);
        }
        
        return 0;
    }
}
