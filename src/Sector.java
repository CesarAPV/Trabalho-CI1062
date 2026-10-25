public abstract class Sector{
    protected int x;
    protected int y;
    protected boolean passed;
    protected boolean doors[4]; 
    // 0 - Norte, 1 - Leste, 2 - Sul, 3 - Oeste
    // Faço uma coleção? no caso uma lista? 
    
    /*
    Acha melhor manter o Sector em vez de uma classe Coordenada?	
    Na minha cabeça, como tabuleiro precisa de coordenada, os 
    personagem (jogadores e inimigos), vão precisar, achei 
    interessante de fazer um atributo tipo classe Coordenada...
    ATT:. Gustavo
    */
    
    protected Enemy enemys[];

    public Sector(int x, int y) {
        this.x = x;
        this.y = y; 
    }

    // Metodo que ataca o inimigo escolhido no setor, continuar depois
    public Enemy atackEnemy(){

    }

    // Metodo que procura "itens" no setor, continuar depois
    public int search(){

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

        System.out.println("|------"+ this.doors[0].isOpen()? "*" : "-" +"------|");
        
        System.out.print("| ")
        for(int i = 0; i <= numero inimigos; i++)
            System.out.print(this.enemys[i].toString()+" ");
        
        for (int i = enemy.length; i < 4; i++)
            System.out.print("    ");
        System.out.println("|");

        System.out.println("|             |");

        System.out.println(this.doors[3].isOpen()? "*" : "|" + "             " + this.doors[1].isOpen()? "*" : "|" );

        // Provisório, depois ver como printar os players
        System.out.println("|  P1    P2   |"+ );
        System.out.println("|  2/6   1/7  |"+ );
        
    }

    public 
}
