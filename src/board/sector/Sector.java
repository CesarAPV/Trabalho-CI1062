package board.sector;

import java.util.Random;

public abstract class Sector{
    protected int x;
    protected int y;
    protected boolean passed;
    protected Door[] doors = new Door[4]; 
    protected Inimigo enemys[];

    public Sector(int x, int y) {
        this.x = x;    
        this.y = y; 
    }

    // Metodo que ataca o inimigo escolhido no setor
    public void atackEnemy(int id, int damage){
        enemys[id].takeDmg(damage);
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
                for (int i = 0; i < this.enemys.length; i++) {
                    if(this.enemys[i] != null){
                        enemys[i].takeDmg(1);
                    }
                }
                return 0;
            default:
                return 0;
        }
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
    @Override
    public String toString(){
        String sector = "";
        sector += "|------"+ (this.doors[Direction.UP.ordinal()].isOpen()? "*" : "-") +"------|\n";
        
        sector += "| ";
        for(int i = 0; i <= numero inimigos; i++)
            sector += this.enemys[i].toString()+" ";
        for (int i = enemy.length; i < 4; i++)
            sector += "    ";
        sector += "|";

        sector += "|             |\n";

        sector += (this.doors[3].isOpen()? "*" : "|") + "             " + (this.doors[1].isOpen()? "*" : "|") + "\n";

        // Provisório, depois ver como printar os players
        sector += "|  P1    P2   |\n";
        sector += "|  2/6   1/7  |\n";
        
        return sector;
    }

    // Provavelmete será necessário mudar ficou meio gambiara 
    public boolean isOpen(Direction direcao) {
        return this.doors[direcao.ordinal()].isOpen();
    }
}
