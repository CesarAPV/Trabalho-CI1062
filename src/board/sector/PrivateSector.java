public class PrivateSector extends Sector { 
    public PrivateSector(int x, int y) {
        super(x, y);
    }

    // Lógica diferencial do privado para a procura de itens 
    @Override
    public int search(){
        return 0;
    }
}