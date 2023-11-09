package facade.dto;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Classe responsavel por carregar os dados entre as varias camadas.
 * Deste modo objetos da camada de business ou de acessos a dados nao
 * sao acedidos pela parte responsavel pela interacao com o utilizador.
 * @author css003
 */
public class EncontroDTO implements Serializable {

	/**
	 * Valor serializable
	 */
	private static final long serialVersionUID = -6239790933177481127L;
	
	// Atributos
	private int numeroEncontro;
	private String nomeJogador1;
	private String nomeJogador2;
	
	/**
	 * Construtor de um EncontroDTO
	 * @param encontro encontro entre dois jogadores
	 * @requires encontro != null
	 */
	public EncontroDTO(int numeroEncontro, String jog1, String jog2) {
		
		this.numeroEncontro = numeroEncontro;
		this.nomeJogador1 = jog1;
		this.nomeJogador2 = jog2;
	}
	
	/**
	 * @return o numero do encontro
	 */
	public int getNumeroEncontro() {
		
		return this.numeroEncontro;
	}
	
	/**
	 * @return o nome do jogador 1
	 */
	public String getNomeJogador1() {
		
		return this.nomeJogador1;
	}
	
	/**
	 * @return o nome do jogador 2
	 */
	public String getNomeJogador2() {
		
		return this.nomeJogador2;
	}
	/**
	 * Devolve uma representacao textual de um EncontroDTO no formato:
	 * idEncontro | jogador1 | jogador2
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.numeroEncontro + " | ");
		sb.append(this.nomeJogador1 + " | ");
		sb.append(this.nomeJogador2 + "\n");
		
		return sb.toString();
	}
	
	/**
	 * Realiza a comparacao entre dois EncontroDTO. 
	 * O fato decisivo corresponde ao identificador do encontro.
	 */
	public static Comparator<EncontroDTO> comparator() {
		
		return (d1, d2) -> d1.numeroEncontro - d2.numeroEncontro;
	}
}
