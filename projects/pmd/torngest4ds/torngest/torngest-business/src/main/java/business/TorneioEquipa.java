package business;

import static javax.persistence.CascadeType.ALL;

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
 * Entity implementation class for Entity: TorneioEquipa
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "Equipa")
public class TorneioEquipa extends Torneio implements Serializable {
	
	@ManyToMany( cascade = ALL)
	private List<Equipa> equipas;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contrutor de um torneio de equipa
	 */
	public TorneioEquipa() {
		super();
		equipas = new ArrayList<>();
	}
	
	/**
	 * Adiciona uma equipa ah lista de equipas
	 * @param e a equipa
	 */
	public void adicionarEquipa(Equipa e) {
		
		equipas.add(e);
	}

	/**
	 * @return a lista de equipas do torneio
	 */
	public List<Equipa> getEquipas() {
		return equipas;
	}

	/**
	 * Define a lista de equipas do torneio
	 * @param equipas
	 */
	public void setEquipas(List<Equipa> equipas) {
		this.equipas = equipas;
	}
	
	/**
	 * Representacao textual de um torneio de equipa
	 */
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Torneio Equipa:\n");
		sb.append("Designacao: " + getDesignacao() + "\n");
		sb.append("Modalidade: " + getModalidade().toString() + "\n");
		sb.append("NumParticipantes: " + getNumParticipantes() + "\n");
		sb.append("NumEncontros: " + getNumEncontros() + "\n");
		sb.append("Estah Aberto: " + getEstahAberto() + "\n");
		sb.append("Data de inicio: " + getDataInicio().getTime().toString() + "\n");
		sb.append("Duracao: " + getDuracao() + "\n");
		sb.append("Total de confrontos: " + getConfrontos().size() + "\n");
		sb.append("Lista de equipas:\n");
		
		for(Equipa e : getEquipas())
			sb.append(e.toString() + "\n");
		
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
		
		TorneioEquipa o = (TorneioEquipa) other;
		
		res = this.getDesignacao().equals(o.getDesignacao()) &&
				this.getModalidade().equals(o.getModalidade()) &&
				this.getNumParticipantes() == o.getNumParticipantes() &&
				this.getNumEncontros() == o.getNumEncontros() &&
				this.getEstahAberto() == o.getEstahAberto() &&
				this.getDataInicio().equals(o.getDataInicio()) &&
				this.getDuracao() == o.getDuracao() &&
				this.getConfrontos().equals(o.getConfrontos()) &&
				this.getEquipas().equals(o.getEquipas());
		
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
		result = 31 * result + this.getEquipas().hashCode();
		
		return result;
	}
	
	/**
	 * Devolve uma copia deste torneio
	 */
	public TorneioEquipa clone() {
		
		TorneioEquipa t = new TorneioEquipa();
		
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
		
		List<Equipa> lE = new ArrayList<>();
		
		for(Equipa e : this.getEquipas())
			lE.add(e);
		
		t.setEquipas(lE);
		
		return t;
	}
}
