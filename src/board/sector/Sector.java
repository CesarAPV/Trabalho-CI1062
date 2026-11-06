package board.sector;

import java.util.ArrayList;

import utils.Dado;
import utils.Posicao;
import board.Direction;

import character.Inimigo;
import character.Jogador;

public abstract class Sector{
    // Mapa, visivel apenas para a propria classe

    // Atributos
    protected Posicao pos;
    protected boolean visitado;
    protected boolean infectado;
    protected Boolean[] portas = new Boolean[4]; 
    protected ArrayList<Inimigo> inimigos;

    // Construtor
    public Sector(Posicao pos, boolean n, boolean s, boolean l, boolean o) {
        this.pos = pos;
        visitado = false;
        infectado = false;
        inimigos = new ArrayList<Inimigo>();
        portas[Direction.UP.ordinal()]    = n; // top door
        portas[Direction.RIGHT.ordinal()] = l; // right door
        portas[Direction.DOWN.ordinal()]  = s; // down door
        portas[Direction.LEFT.ordinal()]  = o; // left door
    }

    // Metodos get
    public boolean isVisitado() { return visitado; }
    public Posicao getPos() { return pos; }
    public Boolean[] getPortas() { return portas; }
    public Inimigo[] getInimigos() {
        Inimigo[] inim = new Inimigo[inimigos.size()];
        return inimigos.toArray(inim);
    }

    // Metodos set
    public void setVisitado(boolean visited) {
        this.visitado = visited;
    }

    public void Infectar() {
        this.infectado = true;
    }

    // Outros metodos
    /// Metodos referentes a inimigos
    protected void gerarInimigos() {
        int numInimigos = Dado.rolarIntervalo(1, 3);
        for(int i = 0; i < numInimigos; i++) {
            inimigos.add(new Inimigo(pos));
        }
    }

    public boolean temInimigosVivos() {
        removerInimigosMortos();
        if(inimigos.size() == 0)
            return false;
        return true;
    } 

    private void removerInimigosMortos() {
        for (Inimigo inimigo : inimigos) {
            if(!inimigo.estaVivo()) {
                inimigos.remove(inimigo);
            }
        }
    }

    public abstract void processarAtaque(Jogador atacante, Inimigo alvo);
    public abstract boolean podeProcurar();


/*     // Ver como printar os playes
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
    } */
}
