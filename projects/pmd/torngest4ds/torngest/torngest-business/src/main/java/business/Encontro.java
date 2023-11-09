package business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Version;

import facade.exceptions.TornGesException;

/**
 * Entity implementation class for Entity: Encontro
 * Representacao de um encontro entre dois jogadores
 */
@Entity
public class Encontro implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private int primary_key;
	
	@Version
	private int version;
	
	// NUmero de versao por defeito
	private static final long serialVersionUID = 1L;

	// Atributos
	@Enumerated(EnumType.STRING)
	private DesfechoEncontro desfecho;
	@Lob
	@Column(nullable=false, columnDefinition="blob")
	private Jogador jogador1;
	@Lob
	@Column(nullable=false, columnDefinition="blob")
	private Jogador jogador2;
	
	/**
	 * Construtor de um encontro de uma partida entre dois jogadores. 
	 * Ainda nao existe um desfecho deste encontro.
	 */
	public Encontro() {
		
		super();
		this.desfecho = null;
		this.jogador1 = null;
		this.jogador2 = null;
	}
   
	/**
	 * Define os jogadores que vao participar neste encontro
	 * @param jogador1 primeiro jogador participante neste encontro
	 * @param jogador2 segundo jogador participante neste encontro
	 * @requires jogador1 != null && jogador2 != null
	 * @ensures getJogadores().getKey().equals(jogador1) && 
	 * 			getJogadores().getValue().equals(jogador2)
	 */
	public void setJogadores(Jogador jogador1, Jogador jogador2) throws TornGesException {
		
		if (jogador1 == null || jogador2 == null)
			throw new TornGesException ("Os jogadores nao podem ser null!");
		
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
	}
	
	/**
	 * Define o desfecho deste encontro
	 * @param desfecho desfecho do encontro
	 * @requires desfecho != null
	 * @ensures getDesfecho.equals(desfecho)
	 */
	public void setDesfechoEncontro(DesfechoEncontro desfecho) {
		
		this.desfecho = desfecho;
		
	}
	
	/**
	 * Devolve o numero associado ao encontro atual
	 */
	public int getEncontro() {
		
		return this.primary_key;
	}
	
	/**
	 * Devolve o resultado do desfecho deste encontro. 
	 * Devolve null se o encontro ainda nao tiver ocorrido.
	*/
	public DesfechoEncontro getDesfecho() {
		
		return this.desfecho;
	}
	
	/**
	 * Devolve o jogador1 que vai jogar neste encontro
	 * Devolve null caso ainda nao se tenham definido o jogador
	 */
	public Jogador getJogador1() {
		
		return this.jogador1;
	}
	
	/**
	 * Devolve o jogador2 que vai jogar neste encontro
	 * Devolve null caso ainda nao se tenham definido o jogador
	 */
	public Jogador getJogador2() {
		
		return this.jogador2;
	}
	
	public boolean participaNoEncontro(Jogador jogador) {
		
		return jogador1.equals(jogador) || jogador2.equals(jogador);
	}
	
	/**
	 * Devolve uma copia do Encontro atual.
	 */
	@Override
	public Encontro clone() {
		
		Encontro resultado = new Encontro();

		resultado.primary_key = this.primary_key;
		resultado.version = this.version;
		resultado.desfecho = this.desfecho;
		
		resultado.jogador1 = this.jogador1.clone();
		resultado.jogador2 = this.jogador2.clone();
		
		return resultado;
	}
	
	/**
	 * Verifica se o objeto dado eh igual ah instancia atual
	 */
	@Override
	public boolean equals(Object other) {
		
		if (this == other)
			return true;
		
		if (!(other instanceof Encontro))
			return false;
		
		Encontro outro = (Encontro) other;
		
		return outro.desfecho.equals(this.desfecho) && outro.jogador1.equals(this.jogador1)
													&& outro.jogador2.equals(this.jogador2);
	}
	
	/**
	 * Overrides default hashcode implementation
	 */
	@Override
	public int hashCode() {
		
		int resultado = 17;
		
		resultado = 37 * resultado + (this.desfecho == null ? 0 : this.desfecho.hashCode());
		resultado = 37 * resultado + this.jogador1.hashCode();
		resultado = 37 * resultado + this.jogador2.hashCode();
		
		return resultado;
	}

}
