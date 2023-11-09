package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * Entity implementation class for Entity: Torneio
 *
 */
@Entity
@DiscriminatorColumn(name = "TIPO_TORNEIO")

@NamedQueries({
	
	@NamedQuery(name="Torneio.getTorneios",
				query="SELECT t FROM Torneio t"),
	
	@NamedQuery(name="Torneio.findByDesig",
				query="SELECT t FROM Torneio t WHERE t.designacao LIKE :" + Torneio.NQ_DESIGNACAO_COL)
})

public abstract class Torneio implements Serializable {
	
	public static final String NQ_GET_TORNEIOS = "Torneio.getTorneios";
	public static final String NQ_GET_TORNEIO_BY_DESIG = "Torneio.findByDesig";
	
	public static final String NQ_DESIGNACAO_COL = "designacao";
	public static final String NQ_DATA_INICIO_COL = "dataInicio";
	public static final String NQ_DATA_FIM = "dataFim";

	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	private Modalidade modalidade;
	
	@Column(unique = true, nullable = false)
	private String designacao;
	
	private int numParticipantes;
	
	private int numEncontros;
	
	private int duracao;
	
	private boolean estahAberto;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio;
	
	@OneToMany(cascade = ALL, fetch = LAZY)
	private List<Confronto> confrontos;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de um torneio
	 */
	public Torneio() {
		super();
	}

	/**
	 * @return a modalidade do torneio
	 */
	public Modalidade getModalidade() {
		return modalidade;
	}
	
	/**
	 * Define uma modalidade para o torneio
	 * @param modalidade a modalidade do torneio 
	 */
	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}
	
	/**
	 * @return designacao do torneio
	 */
	public String getDesignacao() {
		return designacao;
	}

	/**
	 * Define a designacao do torneio
	 * @param designacao a designacao a dar
	 */
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	/**
	 * @return o numero de participantes de um torneio
	 */
	public int getNumParticipantes() {
		return numParticipantes;
	}

	/**
	 * Define o numero de participantes maximo do torneio
	 * @param numParticipantes o numero de participantes do torneio
	 */
	public void setNumParticipantes(int numParticipantes) {
		this.numParticipantes = numParticipantes;
	}
	
	/**
	 * @return o numero de encontros do torneio
	 */
	public int getNumEncontros() {
		return numEncontros;
	}
	
	/**
	 * Define o numero de encontros por confronto do torneio
	 * @param numEncontros o numero de encontros por confronto
	 */
	public void setNumEncontros(int numEncontros) {
		this.numEncontros = numEncontros;
	}

	/**
	 * @return a data de inicio do torneio
	 */
	public Calendar getDataInicio() {
		return dataInicio;
	}

	/**
	 * Define a data de inicio do torneio
	 * @param dataInicio
	 */
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return a duracao do torneio em dias
	 */
	public int getDuracao() {
		return duracao;
	}

	/**
	 * Define a duracao do torneio em dias
	 * @param duracao
	 */
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	/**
	 * @return true caso o torneio esteja aberto ou false caso contrario
	 */
	public boolean getEstahAberto() {
		return estahAberto;
	}
	
	/**
	 * Permite abrir ou fechar um torneio
	 * @param estahAberto o boolean que determina se o torneio estah aberto
	 * ou fehcado
	 */
	public void setEstahAberto(boolean estahAberto) {
		this.estahAberto = estahAberto;
	} 
	
	/**
	 * Define os confrontos do torneio
	 * @param confrontos a lista de confrontos entre os varios jogadores
	 */
	public void setConfrontos(List<Confronto> confrontos) {
		this.confrontos = confrontos;
	}
	
	/**
	 * Adiciona um confront ah lista de confrontos
	 * @param c
	 */
	public void addConfronto(Confronto c) {
		this.confrontos.add(c);
	}
	
	/**
	 * @return os confrontos do torneio
	 */
	public List<Confronto> getConfrontos(){
		return this.confrontos;
	}
	
	/**
	 * Obter a lista de confrontos de um jogador no torneio
	 * @param jogadorCorrente o jogador em questao
	 * @return a lista de confrontos do jogador
	 */
	public List<Confronto> getConfrontosJogador(Jogador jogadorCorrente) {
		
		List<Confronto> result = new ArrayList<>();
		
		for(Confronto c : getConfrontos())
			if(!c.getEncontrosJogador(jogadorCorrente).isEmpty())
				result.add(c);
		
		return result;
	}
	
	/**
	 * Obter um encontro a partir do seu id
	 * @param numeroEncontro o id do encontro
	 * @return o encontro
	 */
	public Encontro getEncontro(int numeroEncontro) {
		
		Encontro res = null;
		
		List<Confronto> confs = this.getConfrontos();
		
		for(int c = 0; c < confs.size() && res == null; c++) {
			
			List<Encontro> enc = confs.get(c).getTodosEncontros();
			
			for(int e = 0; e < enc.size() && res == null; e++)
				
				if(enc.get(e).getEncontro() == numeroEncontro)
					res = enc.get(e);
		}
		
		return res;
	}
}
