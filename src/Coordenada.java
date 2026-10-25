private class Coordenada {
	private int x;
	private int y;
	// podemos fazer com byte, para economizar
	// memória, mas seria necessária fazer várias manipulações com
	// casting, é mais "modular", porém mais complicado;
	
	// Somente o contrutor default, porque será manipulado mais com
	// get e set, pois será quase sempre atualizado;
	public Coordenada(){}
	
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
	
	// Aqui eu amassei na lógica; usando reuso de métodos;
	public void atualizaCordenada(int x, int y){
		this.setX(x);
		this.setY(y);
	}
	
	/* Não consigo pensar em mais métodos para essa classe */
	/* Se houver só colocar aqui em baixo que implemento   */
	
}
