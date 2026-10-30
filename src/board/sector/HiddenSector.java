import java.util.Random;

public class HiddenSector extends Sector { 
    public HiddenSector(int x, int y) {
        super(x, y);
    }

    // Lógica diferencial do setor escondido na hora do ataque 
    public int atackEnemy(int id, int damage){
        Random random = new Random();
        int  num =  random.nextInt(2);

        if(num == 1){
            // logica de atacar 
            return damage;
        }   

        // "errou"
        return 0;
    }
}
