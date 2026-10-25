public class Jogador extends Personagem {
	// Será importante na condição de Existencia do Suporte;
	public boolean vivo;
	
	public boolean getVivo(){return this.vivo;}
	public void isVivo(boolean vivo){this.vivo = vivo;}
	
	
	// Métodos que intercedem tanto o Jogador simples e suporte
	public void actionMovimentar(){}
	public void actionAtacar(){}
	public void actionProcurar(){}


}
