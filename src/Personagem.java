/*
Pensei em fazer de início uma interface, entretanto, como as 
características de personagem são divididas entre jogadores e 
inimigos, acho melhor optar com **abstrac class**, se não, podem
me explicar do porquê mudar ?
-> Aqui pensei em já deixar os métodos abstratos que são iguais
tanto para jogadores e inimigos, mas a implementação muda de um 
para o outro, mas "o nome" o "método" é o mesmo, herdada daqui de 
Personagem.
ATT:. Gustavo
*/

public abstrac class Personagem{
	// Atributos do personagem
	private int ataque; //ATK
	private int defesa; //DEF
	Coordenada Pos;
	
	/* Segue a mesma ideia de usar byte aqui, como dito na classe 
	Coordenada.java
	*/

	public Personagem(int ataque, int defesa, int x, int y){
		Coordenada Pos = new Coordenada(x,y);
		this.POS = Pos;
		this.setAtaque(ataque);
		this.setDefesa(defesa);
	}
	
	public int getAtaque(){return this.ataque;}
	public int getDefesa(){return this.defesa;}
	public Coordenada getPos(){
		Coordenada pos = new Cordenada(this.Pos.getX, this.Pos.getY);
		return pos;
	}
	
	/* Nesses métodos set e get aqui fiquei em um impasse, porque
	eu acho que as proporções de ATK e DEF não é igual para todos
	os personagem e também não notei um intervalo fechado para os
	métodos set, ai coloquei só uma restrição básica
	*/
	
	public void setAtaque(int ataque){
		if (ataque >=1)
			this.ataque = ataque;
	}
	
	public void setDefesa(int defesa){
		if (defesa >=1)
			this.defesa = defesa;
	}
	
	public void setPos(int x, int y){
		this.Pos.setX(x);
		this.Pos.setY(y);
	}
	
	// Unica ação coletiva de todos dos personagem, mas deve ser
	// implementada de uma forma específica dependendo de quem a 
	// herda.
	public abstrac actionAtaque(){}; //
	


}
