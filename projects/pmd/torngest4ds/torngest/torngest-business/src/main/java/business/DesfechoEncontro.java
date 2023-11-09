package business;

/**
 * Enumerado com os tipos de desfechos possiveis para um encontro.
 * @author css003
 */
public enum DesfechoEncontro {
	VITORIA(1.0), 
	EMPATE(0.5), 
	DERROTA(0.0);
	
	// Atributos
	private final double valorDesfecho;
	
	/**
	 * Construtor privado inicializador de um DesfechoEncontro
	 * @param valor valor do desfecho do encontro
	 */
	private DesfechoEncontro(double valor) {
		
		this.valorDesfecho = valor;
	}
	
	/**
	 * Funcao que retorna o valor associado ao tipo de desfecho
	 */
	public double getValorDesfecho() {
		
		return valorDesfecho;
	}
}
