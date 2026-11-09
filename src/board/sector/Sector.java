package board.sector;

import java.util.ArrayList;

import utils.Dado;
import utils.Direction;
import utils.Posicao;
import character.Inimigo;
import character.Personagem;

public abstract class Sector{
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
    
    public void processarAtaque(Personagem atacante, Personagem alvo){
        atacante.atacar(alvo);
    }
   
    // Implementei aqui esse método e deixei sobreposição somente no PrivateSector;
    public boolean podeProcurar(){
        return true;
    }
}
