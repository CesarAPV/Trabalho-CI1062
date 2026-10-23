public class Board {
    private Sector[5][5] sectors;

    public Board() {
        this.sectors = new Sector[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.sectors[i][j] = new Sector( i, j);
            }
        }
    }
}