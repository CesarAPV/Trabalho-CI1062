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
            this.players[1] = new SupportPlayer((SimplePlayer) players[0]);

        this.round = 0;
        this.gameOut = new GameApresentation(board.getInfectionPosition());
        this.scanner = new Scanner(System.in);
    }

    /*
     * Iterar o atributo round, e executar a rodada de cada jogador,
     * e depois a rodada dos inimigos.
     */
    public int runRound() {
        this.gameOut.printGame(this.board,this.players[0],this.players[1], round);

        for(int i = 0; i < numPlayers; i++) {
            this.gameOut.printGame(this.board,this.players[0],this.players[1], round);

            Player player = players[i];
            if(!player.isAlive()) continue;

            Sector sector = board.getSector(player.getPos());

            if(!sector.hasEnemiesAlive()) {
                movePlayer(player);
                if(someoneFoundInfection())
                    return 0;

                this.gameOut.printGame(this.board,this.players[0],this.players[1], round);
            }
            actionPlayer(player);
            this.gameOut.printGame(this.board,this.players[0],this.players[1], round);
            actionPlayer(player);
        }

        this.gameOut.printGame(this.board,this.players[0],this.players[1], round);

        if (!someoneFoundInfection()) {
            for (Player player : players) {
                if (player.isAlive())
                    runInimigo(player);
            }
        }
        this.round++;
        return 0;
    }

    public boolean isGameOver() {
        for (int i = 0; i < numPlayers; i++) {
            if (players[i].isAlive() && board.getInfectionPosition().equals(player[i].getPos())) {
                System.out.println("\nVitoria!!! O jogador P" + (i + 1) + " encontrou a fonte da infeccao (X)!");
                return true;
            }
        }
        if (!players[0].isAlive() && (!players[1].isAlive())) {
            System.out.println("\nDerrota!!! Os jogadores foram eliminados pelo virus!");
            return true;
        }
        if (round >= 25) {
            System.out.println("\nDerrota!!! O limite de 25 turnos foi ultrapassado!");
            return true;
        }
        return false;
    }
    
    private void movePlayer(Player player) {
        Position pos = player.getPos();
        Sector sector = board.getSetor(pos);
        boolean validAction = false;

        while (!validAction) {
            System.out.println("Movimento: escolha uma porta (w=Cima, a=Esq, s=Baixo, d=Dir)");
            char input = scanner.next().toLowerCase().charAt(0);

            Position pos = player.getPos();
            Sector sector = board.getSector(pos);
            Direction dir;
            
            switch (input) {
                case 'w' -> dir = Direction.UP;
                case 'a' -> dir = Direction.LEFT;
                case 'd' -> dir = Direction.RIGHT;
                case 's' -> dir = Direction.DOWN;
                default -> dir = null;
            }

            if (dir == null) {
                System.out.println("Tecla Invalida! Use w, a, s, ou d");
                continue;
            }

            if (!sector.getDoor(dir)) {
                System.out.println("Movimento invalido! Ha uma porta nessa direcao");
                continue;
            }

            player.move(board.getSetor(pos.shift(dir)));
            sector = board.getSetor(player.getPos());
            if (!sector.isVisited()) {
                sector.setVisited(true);
                if (!sector.isInfected()) {
                    sector.genEnemies();
                }
            }
            validAction = true;
        }
    }

    private void actionPlayer(Player player) {
        Sector sector = board.getSetor(player.getPos());

        boolean validAction = false;
        while (!validAction) {
            System.out.println("Acao: (1/2/3=atacar, p=procurar, 4/5=curar, 0=passar)");
            char input = scanner.next().toLowerCase().charAt(0);

            switch (input) {
                case '0' -> {
                    System.out.println("Passou a vez");
                    validAction = true;
                }
                case '1', '2', '3' -> {
                    Enemy[] enemies = sector.getEnemies();
                    int index = input - '1'; // '1' -> 0, '2' -> 1, '3' -> 2
                    if (index >= enemies.length || !enemies[index].isAlive()) {
                        System.out.println("Esse inimigo nao existe! Escolha outro");
                        break;
                    }

                    if (sector.canAttack()) {
                        player.attack(enemies[index]);
                        sector.hasEnemiesAlive(); // limpa inimigos mortos
                    } else {
                        System.out.println("Setor oculto!");
                    }
                    validAction = true;
                }
                case '4', '5' -> {
                    if (!(player instanceof SupportPlayer)) {
                        System.out.println("Apenas o jogador de suporte pode curar!");
                        break;
                    }

                    SupportPlayer sup = (SupportPlayer) player;
                    if (input == '4') {
                        if (players[0].getPos().equals(players[1].getPos())) {
                            sup.heal(players[0]);
                            validAction = true;
                        } else {
                            System.out.println("P1 nao esta no mesmo setor que P2");
                        }
                    } else {
                        sup.heal(player);
                        validAction = true;
                    }
                }
                case 'p' -> {
                    if (sector.canSearch()) {
                        player.search(sector);
                        sector.hasEnemiesAlive(); // se tirou 6 e matou algum inimigo
                        validAction = true;
                    } else {
                        System.out.println("Setor privado, nao eh permitido procurar aqui!");
                    }
                }
                default -> System.out.println("Tecla invalida!");
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
            }
        }
    }

    private void printEnemies(Sector sector) {
        int i = 1;
        for (Enemy enemy : sector.getEnemies()) {
            System.out.println("Inimigo " + i + " "+ enemy);
            i++;
        }
    }

    private boolean someoneFoundInfection() {
        for (Player player : players) {
            if(player.isAlive() && board.getInfectionPosition().equals(player.getPos())) {
                return true;
            }
        }
        return false;
    }
}