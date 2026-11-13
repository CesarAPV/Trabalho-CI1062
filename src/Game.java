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
    private Board board;
    private Player[] players;
    private int numPlayers;
    private int round;
    private Scanner scanner;

    public Game() {
        this.board = new Board();
        board.getSetor(new Position(3, 3)).setVisited(true);
        this.numPlayers = 2;
        this.players = new Player[numPlayers];
        this.players[0] = new SimplePlayer();
        if (numPlayers > 1)
            this.players[1] = new SupportPlayer();

        this.round = 0;
        this.scanner = new Scanner(System.in);
    }

    /*
     * Iterar o atributo round, e executar a rodada de cada jogador,
     * e depois a rodada dos inimigos.
     */
    public int runRound() {
        for (Player player : players) {
            runPlayer(player);
            runPlayer(player);
        }

        System.out.println("Player 1 Life: (" + players[0].getAtk() + "," + players[0].getDef() +
                ") Pos: (" + players[0].getPos().getX() + ", " + players[0].getPos().getY() + ")");

        System.out.println("Player 2 Life: (" + players[1].getAtk() + "," + players[1].getDef() +
                ") Pos: (" + players[1].getPos().getX() + ", " + players[1].getPos().getY() + ")");

        for (Player player : players) {
            runInimigo(player);
        }

        this.round++;

        return 0;
    }

    public boolean isGameOver() {
        if (this.round >= 6 || !this.players[0].isAlive()) {
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
        Sector sector = board.getSetor(pos);

        switch (input) {
            case 'w', 'a', 's', 'd' -> {
                if (!sector.hasEnemiesAlive()) {
                    if (input == 'w' && sector.getDoor(Direction.UP)) {
                        player.move(board.getSetor(pos.shift(Direction.UP)));
                    } else if (input == 'a' && sector.getDoor(Direction.LEFT)) {
                        player.move(board.getSetor(pos.shift(Direction.LEFT)));
                    } else if (input == 's' && sector.getDoor(Direction.DOWN)) {
                        player.move(board.getSetor(pos.shift(Direction.DOWN)));
                    } else if (input == 'd' && sector.getDoor(Direction.RIGHT)) {
                        player.move(board.getSetor(pos.shift(Direction.RIGHT)));
                    }

                    sector = board.getSetor(player.getPos());
                    if (!sector.isVisited()) {
                        sector.setVisited(true);
                        sector.genEnemies();
                    }
                }
            }
            case '1', '2', '3' -> {
                if (sector.canAttack()) {
                    Enemy enemies[] = sector.getEnemies();
                    int tamanho = enemies.length;
                    if (input == '1' && tamanho >= 1)
                        player.attack(enemies[0]);
                    else if (input == '1' && tamanho >= 2)
                        player.attack(enemies[1]);
                    else if (input == '1' && tamanho == 3)
                        player.attack(enemies[2]);
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
        Sector sector = board.getSetor(pos);

        if (sector.hasEnemiesAlive()) {
            for (Enemy enemy : sector.getEnemies()) {
                enemy.attack(player);
                System.out.println("Inimigo " + enemy);

            }
        }
    }
}
