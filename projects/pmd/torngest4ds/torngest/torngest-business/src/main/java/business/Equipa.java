package business;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * Entity implementation class for Entity: Equipa
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name=Equipa.NQ_FIND_BY_ID,
					query="SELECT e FROM Equipa e WHERE e.numInscricao LIKE :" + Equipa.NQ_NR_INSCRICAO),
		@NamedQuery(name=Equipa.NQ_FIND_JOGADOR_IN_EQUIPA,
						query="SELECT e FROM Equipa e JOIN e.listaJogadores j WHERE j.primary_key LIKE :" + Jogador.ID_NUMBER_COL)
})

public class Equipa implements Serializable {
	public static final String NQ_FIND_JOGADOR_IN_EQUIPA = "Equipa.findJogadorInEquipa";
	public static final String NQ_FIND_BY_ID = "Equipa.findByID";
	public static final String NQ_NR_INSCRICAO = "numInscricao";

	@Id 
	@GeneratedValue(strategy = TABLE)
	private long numInscricao;
	
	@Column(nullable = false, unique = false)
	private String nome;
	
	private double numPontos;
	
	@OneToOne(cascade = { PERSIST, MERGE, REFRESH, DETACH })
	private Modalidade modalidade;
	
	//relacao unidirecional
	@OneToMany(fetch = EAGER, cascade = { ALL }) //just to be safe
	private List<Jogador> listaJogadores;
	
	@Version
	private long version;
	
	private static final long serialVersionUID = 1L;

	public Equipa() {
		super();
		listaJogadores = new ArrayList<>();
		this.numPontos = 1000;
	}
	
	public boolean adicionarJogador(Jogador j) {
		
		boolean result = false;
		if(modalidade.equals(j.getModalidade())) 
			result = listaJogadores.add(j);
		
		return result;
		
	}
	//getters and setters
	public long getNumeroInscricao() {
		return numInscricao;
	}

	public void setNumeroInscricao(long n) {
		this.numInscricao = n;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPontos() {
		return numPontos;
	}

	public void setPontos(double n) {
		this.numPontos = n;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public void setListaJogadores(List<Jogador> listaJogadores) {
		this.listaJogadores = listaJogadores;
	}

	public List<Jogador> getElementosEquipa(){
		
		List<Jogador> elementos = new ArrayList<>();
		elementos.addAll(listaJogadores);

		return elementos;
	}
	@Override
	public int hashCode() {

		int result = 17;
		long temp;
		result = result * 31 +(int) (numInscricao ^ (numInscricao >>> 32));
		result = result * 31 + (nome == null? 0 : nome.hashCode());
		temp = Double.doubleToLongBits(numPontos);
		result = result * 31 + (int) (temp ^ (temp >>> 32));
		result = result * 31 + (modalidade == null? 0: modalidade.hashCode());
		result = result * 31 + (listaJogadores == null? 0 : listaJogadores.hashCode());
		result = result * 31 + (int) (version ^ (version >>> 32));
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) 
			return true;
		if(!(obj instanceof Equipa))
			return false;
		Equipa other = (Equipa) obj;
		return Long.compare(this.numInscricao, other.numInscricao) == 0 && this.nome.equals(other.nome) &&
			   Double.compare(numPontos, other.numPontos) == 0 && this.listaJogadores.equals(other.listaJogadores) &&
			   Long.compare(this.version, other.version) == 0;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		
		sb.append("NumInscricao: " + this.numInscricao +"\n");
		sb.append("Nome: " + this.nome + "\n");
		sb.append("Pontos: " + this.numPontos + "\n");
		sb.append("Modalidade: " + this.modalidade + "\n");
		
		sb.append("Jogadores: \n" );
		for(Jogador j: this.listaJogadores)
			sb.append(j);
		
		sb.replace(sb.length()-2, sb.length()-1, "]");
		
		return sb.toString();	
	}
	
	@Override
	public Equipa clone() {
		
		Equipa e = new Equipa();
		
		e.numInscricao = this.numInscricao;
		e.nome = this.nome;
		e.numPontos = this.numPontos;
		e.version = this.version;
		e.modalidade = this.modalidade.clone();
		
		List<Jogador> lista = new ArrayList<>();
		for(Jogador j: this.listaJogadores) 
			lista.add(j.clone());
	
		return e;
	}

}
