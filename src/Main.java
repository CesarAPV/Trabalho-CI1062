/*
Nome                            |   GRR
------------------------------------------------
Gustavo Henrique Oliveira Silva |   20255614
Kauê Nonemacher Felau           |   20252007
Cesar Augusto Précoma Vendramin |   20251941
Nathalie de Godoi Stefankowski  |   20253518
*/

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