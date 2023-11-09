package facade.dto;

import java.io.Serializable;

/**
 * Classe responsavel por carregar os dados entre as varias camadas.
 * Deste modo objetos da camada de business ou de acessos a dados nao
 * sao acedidos pela parte responsavel pela interacao com o utilizador.
 * @author css003
 */
public class JogadorShortDTO implements Serializable {

	/**
	 * Valor serializable
	 */
	private static final long serialVersionUID = 5131930931931720749L;
	
	// Atributos
	private int id;
	private String nome;

	/**
	 * Construtor de um JogadorDTO dado o seu nome e quantidade de pontos
	 * @param nome nome do jogador
	 * @param pontos pontos do jogador
	 * @requires nome != null && pontos != null
	 * @ensures getNome().equals(nome) && getPontos() == pontos
	 */
	public JogadorShortDTO(int id, String nome) {
		
		this.id = id;
		this.nome = nome;
		
	}
	
	/**
	 * Funcao que obtem o nome do jogador
	 */
	public String getNome() {
		
		return this.nome;
	}
	
	/**
	 * Funcao que obtem a quantidade de pontos do jogador
	 */
	public int getId() {
		
		return this.id;
	}
	
	/**
	 * Funcao que devolve a representacao textual de um JogadorDTO
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("(" + nome + ", " + id + ")");
		
		return sb.toString();
	}
}
