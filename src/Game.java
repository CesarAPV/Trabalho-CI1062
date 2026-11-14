import java.util.Scanner;

import board.Board;
import board.sector.*;
import character.Player;
import character.SimplePlayer;
import character.SupportPlayer;
import character.Enemy;
import utils.*;

public class Game {
    private Board board;
    private Player[] players;
    private int numPlayers;
    private int round;
    private Scanner scanner;
    private GameApresentation gameOut;

    public Game() {
        this.board = new Board();
        board.getSector(new Position(3, 3)).setVisited(true);
        this.numPlayers = 2;
        this.players = new Player[numPlayers];
        this.players[0] = new SimplePlayer();
        if (numPlayers > 1)
            this.players[1] = new SupportPlayer();
        this.round = 0;
        this.gameOut = new GameApresentation(board.getInfectionPosition());
        this.scanner = new Scanner(System.in);
    }

    /*
     * Iterar o atributo round, e executar a rodada de cada jogador,
     * e depois a rodada dos inimigos.
     */
    public int runRound() {
        System.out.println("\n============== Ciclo " + (round + 1) + " / 25 ==============");

        this.gameOut.printGame(this.board,this.players[0],this.players[1]);
    
        for (Player player : players) {
            runPlayer(player);
            runPlayer(player);
        }

        System.out.printf("\n\n======Estatisticas da rodada======\n\n");
        System.out.println("Player 1 Life: (" + players[0].toString() +
                ") Pos: (" + players[0].getPos().toString() + ")");
        System.out.println("Player 2 Life: (" + players[1].toString() +
                ") Pos: (" + players[1].getPos().toString() + ")");

        for (Player player : players) {
            runInimigo(player);
        }
        System.out.printf("\n\n======Fim da rodada======\n\n");
        this.round++;

        return 0;
    }

    public boolean isGameOver() {
        Position posSectorInfected = board.getInfectionPosition();

        /* Verificar se esse equals está implementado */
        for(int i = 0; i < numPlayers; i++) {
            if(players[i].isAlive() && players[i].getPos().equals(posSectorInfected)) {
                System.out.println("\nVitoria!!! O jogador P"+ (i+1) + " encontrou a fonte da infeccao (X)!");
                return true;
            }
        }
        if(!players[0].isAlive() || (!players[0].isAlive() && !players[1].isAlive())) {
            System.out.println("\nDerrota!!! Os jogadores foram eliminados pelos virus!");
            return true;
        }
        if(round >= 25) {
            System.out.println("\nDerrota!!! O limite de 25 turnos foi ultrapassado!");
            return true; 
        }
        return false;
    }

    private void runPlayer(Player player) {
        if (!player.isAlive())
            return;

        char input;
        do {
            input = scanner.next().charAt(0);
        } while (input != 'w' && input != 'a' && input != 's' &&
                input != 'd' && input != '1' && input != '2' && input != '3' &&
                input != 'p' && input != '4' && input != '5');

        Position pos = player.getPos();
        Sector sector = board.getSector(pos);

        switch (input) {
            case 'w', 'a', 's', 'd' -> {
                if (!sector.hasEnemiesAlive()) {
                    if (input == 'w' && sector.getDoor(Direction.UP)) {
                        player.move(board.getSector(pos.shift(Direction.UP)));
                    } else if (input == 'a' && sector.getDoor(Direction.LEFT)) {
                        player.move(board.getSector(pos.shift(Direction.LEFT)));
                    } else if (input == 's' && sector.getDoor(Direction.DOWN)) {
                        player.move(board.getSector(pos.shift(Direction.DOWN)));
                    } else if (input == 'd' && sector.getDoor(Direction.RIGHT)) {
                        player.move(board.getSector(pos.shift(Direction.RIGHT)));
                    }

                    sector = board.getSector(player.getPos());
                    /* Por quê dessa verificação aqui ? */
                    if(sector.isInfected()) {
                        
                    }

                    if (!sector.isVisited()) {
                        sector.setVisited(true);
                        sector.genEnemies();
                    }
                }
                else {
                    System.out.println("Tem inimigos vivos");
                }
            }
            case '1', '2', '3' -> {
                if (sector.canAttack()) {
                    Enemy enemies[] = sector.getEnemies();
                    int tamanho = enemies.length;
                    if (input == '1' && tamanho >= 1)
                        player.attack(enemies[0]);
                    else if (input == '2' && tamanho >= 2)
                        player.attack(enemies[1]);
                    else if (input == '3' && tamanho == 3)
                        player.attack(enemies[2]);

                    sector.hasEnemiesAlive(); /* Donada aqui ? */
                } else {
                    System.out.println("Setor Escondido");
                }
            }
            case 'p' -> {
                if (sector.canSearch()) {
                    player.search(sector);
                } else {
                    System.out.println("Setor Privado");
                }
            }
            case '4', '5' -> {
                if (player instanceof SupportPlayer) {
                    if (input == '4' && (players[0].getPos().equals(players[1].getPos()))) {
                        ((SupportPlayer) player).heal(players[0]);
                    } else {
                        ((SupportPlayer) player).heal(player);
                    }
                }
            }
        }

    }

    private void runInimigo(Player player) {
        Position pos = player.getPos();
        Sector sector = board.getSector(pos);

        if (sector.hasEnemiesAlive()) {
            System.out.printf("\n");

            for (Enemy enemy : sector.getEnemies()) {
                enemy.attack(player);
                //System.out.println("Inimigo " + enemy);
            }
        }
    }
}
