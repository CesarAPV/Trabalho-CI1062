public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        
        System.out.println("Bem-vindo ao jogo!");

        while (!game.isGameOver()) {
            game.runRound();
        }

        System.out.println("Fim de jogo!");
    }
}