package utils;

/* Classe responsável pela Posição dos objetos no jogo */
public class Position {
	/******Atributos******/
	private int x, y;

	/*****Construtor*****/
	public Position(int x, int y) {
		if(1 <= x && x <= 5 && 1 <= y && y <= 5) {
			this.x = x;
			this.y = y;
		}
		else {
			this.x = 0;
			this.y = 0;
		}
	}
	
	/*****Getters*****/
	public int getX(){return x;}
	public int getY(){return y;}

	public Position shift(Direction dir) {
		int x = getX();
		int y = getY();
		switch (dir) {
			case UP:
				x--;
				break;
			case DOWN:
				x++;
				break;
			case RIGHT:
				y++;
				break;
			case LEFT:
				y--;
				break;
			default:
				break;
		}
		return new Position(x, y);
	}
}
