package utils;
public class Coordenada {
	private int x;
	private int y;
	

	public Coordenada(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	
	public void setX(int x){
		if (x>=1 && x < 6)
			this.x = x;
	}
	
	public void setY(int y){
		if (y>=1 && y<6)
			this.y = y;
	}
	
	public void atualizaCordenada(int x, int y){
		setX(x);
		setY(y);
	}
}
