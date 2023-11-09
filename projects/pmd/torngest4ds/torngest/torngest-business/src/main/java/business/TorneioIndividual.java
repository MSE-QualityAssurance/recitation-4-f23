package business;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

/**
 * Entity implementation class for Entity: TorneioIndividual
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "Individual")
public class TorneioIndividual extends Torneio implements Serializable {
	
	@ManyToMany(fetch = EAGER, cascade = ALL)
	private List<Jogador> jogadores;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor de um torneio individual
	 */
	public TorneioIndividual() {
		super();
		jogadores = new ArrayList<>();
	}
   
	/**
	 * Adicionar um jogador ah lista de jogadores
	 * @param j o jogador a adicionar
	 */
	public void adicionarJogador(Jogador j) {
		
		jogadores.add(j);
	}

	/**
	 * @return a lista de jogadores do torneio
	 */
	public List<Jogador> getJogadores() {
		return jogadores;
	}

	/**
	 * Define a lista de jogadores do torneio
	 * @param jogadores
	 */
	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	/**
	 * Representacao textual de um torneio de equipa
	 */
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Torneio Individual:\n");
		sb.append("Designacao: " + getDesignacao() + "\n");
		sb.append("Modalidade: " + getModalidade().toString() + "\n");
		sb.append("NumParticipantes: " + getNumParticipantes() + "\n");
		sb.append("NumEncontros: " + getNumEncontros() + "\n");
		sb.append("Estah Aberto: " + getEstahAberto() + "\n");
		sb.append("Data de inicio: " + getDataInicio().getTime().toString() + "\n");
		sb.append("Duracao: " + getDuracao() + "\n");
		sb.append("Total de confrontos: " + getConfrontos().size() + "\n");
		sb.append("Lista de Jogadores:\n");
		
		for(Jogador j : getJogadores())
			sb.append(j.toString() + "\n");
		
		return sb.toString();
	}
	
	/**
	 * Verifica se dois torneios sao iguais
	 * @param other o outro torneio
	 */	
	public boolean equals(Object other) {
		
		boolean res;
		
		if (this == other)
			return true;
		
		if(!(other instanceof TorneioEquipa))
			return false;
		
		TorneioIndividual o = (TorneioIndividual) other;
		
		res = this.getDesignacao().equals(o.getDesignacao()) &&
				this.getModalidade().equals(this.getModalidade()) &&
				this.getNumParticipantes() == o.getNumParticipantes() &&
				this.getNumEncontros() == o.getNumEncontros() &&
				this.getEstahAberto() == o.getEstahAberto() &&
				this.getDataInicio().equals(o.getDataInicio()) &&
				this.getDuracao() == o.getDuracao() &&
				this.getConfrontos().equals(o.getConfrontos()) &&
				this.getJogadores().equals(o.getJogadores());
		
		return res;
	}

	/**
	 * hashCode do torneio
	 */
	public int hashCode() {
		
		int result = 17;
		
		result = 31 * result + (this.getDesignacao() == null ? 0 : this.getDesignacao().hashCode());
		result = 31 * result + this.getModalidade().hashCode();
		result = 31 * result + this.getNumEncontros();
		result = 31 * result + this.getNumParticipantes();
		result = 31 * result + (this.getEstahAberto() ? 0 : 1);
		result = 31 * result + this.getDataInicio().hashCode();
		result = 31 * result + this.getConfrontos().hashCode();
		result = 31 * result + this.getJogadores().hashCode();
		
		return result;
	}
	
	/**
	 * Devolve uma copia deste torneio
	 */
	public TorneioIndividual clone() {
		
		TorneioIndividual t = new TorneioIndividual();
		
		t.setDesignacao(this.getDesignacao());
		t.setModalidade(this.getModalidade().clone());
		t.setNumParticipantes(this.getNumParticipantes());
		t.setNumEncontros(this.getNumEncontros());
		t.setEstahAberto(this.getEstahAberto());
		t.setDataInicio((Calendar) this.getDataInicio().clone());
		
		List<Confronto> lC = new ArrayList<>();
		
		for(Confronto c : this.getConfrontos())
			lC.add(c);
		
		t.setConfrontos(lC);
		
		List<Jogador> lJ = new ArrayList<>();
		
		for(Jogador e : this.getJogadores())
			lJ.add(e);
		
		t.setJogadores(lJ);
		
		return t;
	}
}
