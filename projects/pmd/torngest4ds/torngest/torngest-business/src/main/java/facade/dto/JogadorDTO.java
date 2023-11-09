package facade.dto;

import java.io.Serializable;

/**
 * Classe responsavel por carregar os dados entre as varias camadas.
 * Deste modo objetos da camada de business ou de acessos a dados nao
 * sao acedidos pela parte responsavel pela interacao com o utilizador.
 * @author css003
 */
public class JogadorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6369952180487074398L;
	
	// Atributos
	private String nome;
	private double pontos;
	
	/**
	 * Construtor de um JogadorDTO dado o seu nome e quantidade de pontos
	 * @param nome nome do jogador
	 * @param pontos pontos do jogador
	 * @requires nome != null && pontos != null
	 * @ensures getNome().equals(nome) && getPontos() == pontos
	 */
	public JogadorDTO(String nome, double pontos) {
		
		this.nome = nome;
		this.pontos = pontos;
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
	public double getPontos() {
		
		return this.pontos;
	}
	
	/**
	 * Funcao que devolve a representacao textual de um JogadorDTO
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("(" + nome + ", " + pontos + ")");
		
		return sb.toString();
	}
}
