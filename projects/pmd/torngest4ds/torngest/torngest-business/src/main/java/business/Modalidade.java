package business;

import static javax.persistence.GenerationType.TABLE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

/**
 * Entity implementation class for Entity: Modalidade
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Modalidade.NQ_FIND_BY_NAME,
			query="SELECT m FROM Modalidade m WHERE m." + Modalidade.NQ_NAME +" LIKE :" + Modalidade.NQ_NAME),
	@NamedQuery(name=Modalidade.NQ_SELECT_ALL,
			query="SELECT m FROM Modalidade m"),
	@NamedQuery(name=Modalidade.NQ_SELECT_ALL_NAME,
			query="SELECT m." + Modalidade.NQ_NAME +" FROM Modalidade m")
})
public class Modalidade implements Serializable {
	
	public static final String NQ_SELECT_ALL = "Modalidade.selectAll";
	public static final String NQ_SELECT_ALL_NAME ="Modalidade.selectAllName";
	public static final String NQ_FIND_BY_NAME = "Modalidade.findByName";
	public static final String NQ_NAME = "nome";
	
	@Id 
	@GeneratedValue(strategy = TABLE)
	private long modalidadeId;
	
	
	@Column(unique = true, nullable = false)
	private String nome;
	
	
	private int minJogadores;
	
	@Version
	private long version;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constroi um nova modalidade
	 */
	public Modalidade() {
		super();
	}
	
	/*Getters e Setters*/
	
	/**
	 * @return O nome desta modalidade
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * @return O minimo de jogadores desta modalidade
	 */
	public int getMinimoJogadores() {
		return this.minJogadores;
	}
	
	/**
	 * @param nome Define um nome para esta modalidade
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @param min Define um minimo de jogadores para esta modalidade
	 */
	public void setMinimoJogadores(int min) {
		this.minJogadores = min;
	}
	
	 @Override
	public String toString() {
		return this.nome;
	}
	
	@Override
	public int hashCode() {
		
		int result = 17;
		result = result * 31 + (int) (modalidadeId ^ (modalidadeId >>> 32));
		result = result * 31 + (nome == null? 0 : nome.hashCode());
		result = result * 31 + minJogadores;
		result = result * 31 + (int) (version ^(version >>> 32));
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj)
			return true;
		if(!(obj instanceof Modalidade))
			return false;
		Modalidade other = (Modalidade) obj;
		
		return Long.compare(this.modalidadeId, other.modalidadeId) == 0 && this.nome.equals(other.nome) &&
			   this.minJogadores == other.minJogadores && Long.compare(this.version, other.version) == 0;
	}
	
	@Override
	public Modalidade clone() {
		
		Modalidade m = new Modalidade();
		m.modalidadeId = this.modalidadeId;
		m.nome = this.nome;
		m.minJogadores = this.minJogadores;
		m.version = this.version;
		
		return m;
	}
	
}
