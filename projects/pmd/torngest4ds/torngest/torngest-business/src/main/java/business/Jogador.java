package business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * Entity implementation class for Entity: Jogador
 * Classe que representa um jogador que pode ser individual ou de equipa.
 * 
 *     NOTA:   Apesar do erro que podera eventualmente aparecer nesta classe, 
 *             ela corre corretamente. Corresponde a um erro de validacao JPA
 * 
 * @author Grupo 003
 */
@NamedQueries({
    @NamedQuery(name = Jogador.GET_PLAYER_BY_ID,
            query = "SELECT j FROM Jogador j WHERE j." + Jogador.ID_NUMBER_COL +" LIKE :" + Jogador.ID_NUMBER_COL),
    @NamedQuery(name = Jogador.GET_PLAYERS_BY_MODALIDADE,
       			query = "SELECT j FROM Jogador j WHERE j." + Jogador.MODALIDADE_COL + " =:" + Jogador.MODALIDADE_COL),
    //   		query = "SELECT j FROM Jogador j WHERE j." + Jogador.MODALIDADE_COL + " LIKE :" + Jogador.MODALIDADE_COL),
    @NamedQuery(name = Jogador.GET_ALL_PLAYERS,
    		query = "SELECT j FROM Jogador j")
})
@Entity
public class Jogador implements Serializable {

	// Constantes tabelas
	public static final String GET_PLAYER_BY_ID = "Jogador.getPlayerById";
	public static final String GET_PLAYERS_BY_MODALIDADE = "Jogador.getPlayersByModalidade";
	public static final String GET_ALL_PLAYERS = "Jogador.getAllPlayers";
	
	public static final String ID_NUMBER_COL = "primary_key";
	public static final String MODALIDADE_COL = "modalidade";
	
	// Constantes
	private static final double DOUBLE_DIFFERENCE = 0.0001;
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private int primary_key;
	
	@Version
	private int version;

	// Atributos 
	private double pontos;
	private double diferencialMaximo;
	@Column(nullable = false, unique = false)
	private String nome;
	@OneToOne
	private Modalidade modalidade;
	
	// Numero de versao por defeito
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor de um Jogador que inicializa o seu calendario de confrontos a
	 * vazio.
	 */
	public Jogador() {
		
		super();
	}
	
	/**
	 * O identificador unico deste Jogador
	 * @return A primary key unica deste jogador
	 */
	public int getId() {
		return this.primary_key;
	}
	/**
	 * Define os novos pontos do jogador
	 * @param pontos pontos do jogador no torneio
	 * @ensures Math.abs(getPontos - pontos) < 0.0001
	 */
	public void setPontos(double pontos) {
		
		this.pontos = pontos;
	}
	
	/**
	 * Define o diferencial maximo que o jogador pretende para jogar contra 
	 * outros jogadores.
	 * @param diferencial diferencial maximo de jogo
	 * @ensures Math.abs(getDiferencialMaximo() - diferencial) < 0.0001
	 */
	public void setDiferencialMaximo(int diferencial) {
		
		this.diferencialMaximo = diferencial;
	}
	
	/**
	 * Define o nome do Jogador
	 * @param nome nome do jogador
	 * @ensures getNome().equals(nome)
	 */
	public void setNome(String nome) {
		
		this.nome = nome;
	}
	
	/**
	 * Define a modalidade onde a qual o jogador participa. Um jogador pode
	 * apenas participar numa modalidade.
	 * @param modalidade modalidade do Jogador
	 * @requires modalidade != null
	 * @ensures getModalidade.equals(modalidade)
	 */
	public void setModalidade(Modalidade modalidade) {
		
		this.modalidade = modalidade;
	}
	
	/**
	 * @return o identificador unico do jogador
	 */
	public int getPrimaryKey() {
		return this.primary_key;
	}
	
	/**
	 * @return a quantidade de pontos do jogador
	 */
	public double getPontos() {
		
		return this.pontos;
	}
	
	/**
	 * @return O diferencial maximo de pontos que o jogador esta disposto a ter
	 */
	public double getDiferencialMaximo() {
		
		return this.diferencialMaximo;
	}
	
	/**
	 * @return A modalidade onde a qual o jogador pretende participar
	 */
	public Modalidade getModalidade() {
		
		return this.modalidade;
	}

	/**
	 * @return nome do jogador
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Representacao textual de um jogador.
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Jogador: " + this.nome + "\r\n");
		sb.append("Pontos: " + this.pontos + "\r\n");
		sb.append("Diferencial Maximo: " + this.diferencialMaximo + "\r\n");
		sb.append("Modalidade: " + this.modalidade + "\r\n");
		
		return sb.toString();
	}
	
	/**
	 * Verifica se a instancia do objeto atual e igual a other
	 * @return true se todos os seus atributos forem iguais, false caso contrario
	 */
	@Override
	public boolean equals(Object other) {
		
		if (this == other)
			return true;
		
		if (!(other instanceof Jogador))
			return false;
		
		Jogador segundo = (Jogador) other;
		
		boolean resultado;
		
		resultado = segundo.nome.equals(this.nome);
		resultado = resultado && (Math.abs(segundo.pontos - this.pontos) < Jogador.DOUBLE_DIFFERENCE);
		resultado = resultado && (Math.abs(segundo.diferencialMaximo - this.diferencialMaximo) < Jogador.DOUBLE_DIFFERENCE);
		resultado = resultado && (segundo.modalidade.equals(this.modalidade));
		
		return resultado;
	}
	
	/**
	 * Overrides default hashcode implementation
	 */
	@Override 
	public int hashCode() {
		
		int result = 17;
		
		long tempAux;
		
		// Calculo do nome
		result = 37 * result + (this.nome == null ? 0 : this.nome.hashCode());
		
		// Calculo dos atributos double
		tempAux = Double.doubleToLongBits(this.pontos);
		result = 37 * result + ((int) (tempAux ^ (tempAux >>> 32)));
		tempAux = Double.doubleToLongBits(this.diferencialMaximo);
		result = 37 * result + ((int) (tempAux ^ (tempAux >>> 32)));
		
		// Calculo da modalidade
		result = 37 * result + (this.modalidade == null ? 0 : this.modalidade.hashCode());
		
		return result;
	}
	
	/**
	 * Chamada ao super do clone pode obter comportamentos inesperados
	 * Devolve uma copia do Encontro atual.
	 */
	@Override
	public Jogador clone() {
		
		Jogador jogador = new Jogador();
		
		jogador.diferencialMaximo = this.diferencialMaximo;
		jogador.modalidade = this.modalidade;
		jogador.nome = this.nome;
		jogador.pontos = this.pontos;
		jogador.primary_key = this.primary_key;
		jogador.version = this.version;
		
		return jogador;
		
	}
}
