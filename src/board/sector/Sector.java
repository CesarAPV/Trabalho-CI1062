import java.util.Random;

public abstract class Sector{
    protected int x;
    protected int y;
    protected boolean passed;
    protected Door[] doors = new Door[4]; 
    
    protected Enemy enemys[];

    public Sector(int x, int y) {
        this.x = x;    
        this.y = y; 
    }

    // Metodo que ataca o inimigo escolhido no setor
    public int atackEnemy(int id, int damage){
        // Ataca o inimigo de alguma forma;

        return damage;
    }

    // Metodo que procura "itens" no setor
    public int search(){
        Random random = new Random();
        int  num =  random.nextInt(6)+1;

        switch (num) {
            case 4:
                // Faz ganhar 1 def
                return 1;
            case 5:
                // Faz ganhar 2 def
                return 2; 
            case 6:
                // Faz
                break;
            default:
                return 0;
        }
        return 0;
    }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    public boolean isPassed() {
        return this.passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    // Ver como printar os playes
    public void printSector(){

        System.out.println("|------"+ (this.doors[0].isOpen()? "*" : "-") +"------|");
        
        System.out.print("| ");
        for(int i = 0; i <= numero inimigos; i++)
            System.out.print(this.enemys[i].toString()+" ");
        
        for (int i = enemy.length; i < 4; i++)
            System.out.print("    ");
        System.out.println("|");

        System.out.println("|             |");

        System.out.println((this.doors[3].isOpen()? "*" : "|") + "             " + (this.doors[1].isOpen()? "*" : "|"));

        // Provisório, depois ver como printar os players
        System.out.println("|  P1    P2   |");
        System.out.println("|  2/6   1/7  |");
        
    }

    // Provavelmete será necessário mudar ficou meio gambiara 
    public boolean isOpen(Direction direcao) {
        return this.doors[direcao.ordinal()].isOpen();
    }
}
