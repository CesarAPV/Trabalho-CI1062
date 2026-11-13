import board.Board;
import board.sector.Sector;
import character.Enemy;
import character.Player;
import character.SimplePlayer;
import character.SupportPlayer;
import java.util.Scanner;
import utils.Direction;
import utils.Position;

public class Game {
    private  Board board;
    private  Player[] players;
    private  int numPlayers;
    private  int round;

    public Game (int numPlayers){
        this.board = new Board();
        this.numPlayers = numPlayers;
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
        } while(input != 'w' || input != 'a' || input != 's' || 
        input != 'd' || input != '1' || input != '2' || input != '3' ||
        input != 'p');

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

    /*
        Iterar o atributo round, e executar a rodada de cada jogador,
         e depois a rodada dos inimigos.
    */
    public int runRound (){
        this.round++;
        for (Player player : players) {
            runPlayer(player);
            runPlayer(player);
        }

        for (Player player : players) {
            runInimigo(player);
        }
        
        return 0;
    }

    /*
        Como o jogo pode continuar caso o jogador suporte morra,
        para o jogo acabar, verifico se o jogador simples morreu
        ou se o round passou de 25.
    */
    public boolean isGameOver() {
        if (this.round > 25 || !this.players[0].isAlive()) {
            return true;
        }
        return false;
    }

    public void playGame(){
        Scanner input = new Scanner(System.in);
        int numPlayers;
        Game game;
        System.out.println("Bem-vindo ao jogo!");
        System.out.println("Selecione o número de jogadores (1 ou 2):");
        
        do {
            numPlayers = input.nextInt();
            if (numPlayers < 1 || numPlayers > 2) {
                System.out.println("Número de jogadores inválido. Por favor, selecione 1 ou 2.");
            }
        } while (numPlayers < 1 || numPlayers > 2);
        
        game = new Game(numPlayers);
        while (!game.isGameOver()) {
            game.runRound();
        }
        System.out.println("Fim de jogo!");
    }
}
