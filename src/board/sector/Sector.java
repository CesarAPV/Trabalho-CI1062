package board.sector;

import java.util.ArrayList;

import utils.*;
import character.Enemy;
import java.nio.file.DirectoryIteratorException;

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
    public String toString(){
        String sector = "";
        sector += "|------"+ ((this.doors[Direction.UP.ordinal()])? "*" : "-") +"------|\n";
        
        sector += "| ";
        for(int i = 0; i <= enemies.size(); i++)
            sector += this.enemies.get(i).toString()+" ";
        for (int i = enemies.size(); i < 4; i++)
            sector += "    ";
        sector += "|";

        sector += "|             |\n";

        sector += (this.doors[Direction.LEFT.ordinal()]? "*" : "|") + "             " + (this.doors[Direction.RIGHT.ordinal()] ? "*" : "|") + "\n";

        sector += "|             |\n";
        sector += "|             |\n";
        
        sector += "|------"+ ((this.doors[Direction.DOWN.ordinal()])? "*" : "-") +"------|\n";

        return sector;
    }

    public char[][] scheme(){
        char[][] scheme = new char[7][15];

        for(int i = 1; i < 6; i++)
            for(int j = 1; j < 14; j++)
                scheme[i][j] = ' ';
        for(int i = 1; i<14; i++){
            scheme[0][i] = '-';
            scheme[6][i] = '-';
        }
        for(int i=0; i<7; i++){
            scheme[i][0] = '|';
            scheme[i][14] = '|';
        }
        
        int col = 2;
        for (Enemy e : enemies) {
            scheme[1][col] = e.toString.charAt(0); 
            scheme[1][col+1] = e.toString.charAt(1);
            scheme[1][col+2] = e.toString.charAt(2);
            col += 4;
        }

        if(this.doors[Direction.UP.ordinal()]) scheme[0][7] = '*';
        if(this.doors[Direction.RIGHT.ordinal()]) scheme[3][14] = '*';
        if(this.doors[Direction.LEFT.ordinal()]) scheme[3][0] = '*';
        if(this.doors[Direction.DOWN.ordinal()]) scheme[6][7] = '*';
        
        return schame;
    }
}
