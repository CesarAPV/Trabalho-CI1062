package board.sector;

import java.util.ArrayList;

import utils.*;
import character.Enemy;

public abstract class Sector{
    // Atributos
    protected Position pos;
    protected boolean visited;
    protected boolean infected;
    protected Boolean[] doors = new Boolean[4]; 
    protected ArrayList<Enemy> enemies;

    // Construtor
    public Sector(Position pos, boolean n, boolean s, boolean l, boolean o) {
        this.pos = pos;
        visited = false;
        infected = false;
        enemies = new ArrayList<Enemy>();
        doors[Direction.UP.ordinal()]    = n; // top door
        doors[Direction.RIGHT.ordinal()] = l; // right door
        doors[Direction.DOWN.ordinal()]  = s; // down door
        doors[Direction.LEFT.ordinal()]  = o; // left door
    }

    // Metodos get
    public boolean isInfected() { return infected; }
    public boolean isVisited() { return visited; }
    public Position getPos() { return pos; }
    public Boolean[] getDoors() { return doors; }
    public boolean getDoor(Direction dir) { return doors[dir.ordinal()]; }

    public Enemy[] getEnemies() {
        Enemy[] enemiesArr = new Enemy[enemies.size()];
        return enemies.toArray(enemiesArr);
    }

    // Metodos set
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void infect() {
        this.infected = true;
    }

    // Outros metodos
    /// Metodos referentes a enemies
    protected void genEnemies() {
        int numenemies = Die.interval(1, 3);
        for(int i = 0; i < numenemies; i++) {
            enemies.add(new Enemy(pos));
        }
    }

    public boolean hasEnemiesAlive() {
        removeDeadEnemies();
        if(enemies.size() == 0)
            return false;
        return true;
    } 

    private void removeDeadEnemies() {
        for (Enemy enemy : enemies) {
            if(!enemy.isAlive()) {
                enemies.remove(enemy);
            }
        }
    }

    public boolean canAttack(){
        return true;
    }
    public boolean canSearch(){
        return true;
    }

    @Override
    public String toString() {
    return "Sector   " + pos.toString();
    }
}
