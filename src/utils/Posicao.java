package utils;
public class Posicao {
	private int x, y;

	public Posicao(int x, int y) {
		if(1 <= x && x <= 5 && 1 <= y && y <= 5) {
			this.x = x;
			this.y = y;
		}
		else {
			this.x = 0;
			this.y = 0;
		}
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
}
