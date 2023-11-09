package business;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * Entity implementation class for Entity: Confronto
 * Um confronto corresponde a um numero N de encontros entre cada par de oponentes
 */
@Entity
public class Confronto implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private int primary_key;

	@Version
	private int version;
	
	// Atributos
	private int quantidadeEncontros;
	@OneToMany(cascade = ALL, fetch = LAZY)
	private List<Encontro> encontros;
	
	// Numero de versao por defeito
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de um confronto com os seus encontros
	 */
	public Confronto() {
		
		super();
		this.quantidadeEncontros = 0;
		this.encontros = new ArrayList<>();
	}
	
	/**
	 * @return Devolve a lista de encontros do confronto atual
	 */
	public List<Encontro> getTodosEncontros() {

		return this.encontros;
	}
	
	/**
	 * @return Devolve a lista de encontros do confronto atual
	 */
	public List<Encontro> getEncontrosJogador(Jogador jogador) {
		
		 List<Encontro> resultado = new ArrayList<>();
		 
		 for (Encontro encontro : this.encontros)
		 	if (encontro.participaNoEncontro(jogador))
		 		resultado.add(encontro);
				
		return resultado; 
	}
	
	/**
	 * Super do clone encontra-se desatualizado.
	 * Devolve uma copia da instancia atual
	 */
	@Override
	public Confronto clone() {
		
		Confronto copia = new Confronto();
		
		copia.primary_key = this.primary_key;
		copia.version = this.version;
		
		copia.quantidadeEncontros = this.quantidadeEncontros;
		
		for (Encontro encontro : this.encontros)
			copia.addEncontro(encontro.clone());
		
		return copia;
	}
	
	/**
	 * Verifica se o objeto passado eh igual ao da instancia atual. 
	 * Dois confrontos sao iguais quando tem os mesmos encontros
	 */
	@Override
	public boolean equals(Object other) {
		
		if (this == other)
			return true;
		
		if (!(other instanceof Confronto))
			return false;
		
		Confronto outro = (Confronto) other; 
		
		if (this.quantidadeEncontros != outro.quantidadeEncontros)
			return false;
		
		outro.encontros.retainAll(this.encontros);
		
		return outro.encontros.size() == this.encontros.size();
	}
	
	/**
	 * Overrides default hashcode implementation
	 */
	@Override
	public int hashCode() {
		
		int result = 17;
		
		result = 37 * result + this.quantidadeEncontros;
		
		for (Encontro encontro : this.encontros)
			result = 37 * result + encontro.hashCode();
		
		return result;
	}
	
	/**
	 * Devolve uma representacao textual do confronto com os seus encontros.
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Encontros do Confronto: ");
		
		for (Encontro encontro: this.encontros)
			sb.append(encontro.toString() + "\n");
		
		return sb.toString();
	}

	/**
	 * Obtem um mapa com os adversarios de um determinado jogador e a quantidade de encontros
	 * a si associados
	 * @param jogadorCorrente jogador que participa nos encontros
	 * @requires jogadorCorrente != null
	 * @return mapa com os adversarios de jogadorCorrente e a quantidade de partidas.
	 */
	public Map<Jogador, Integer> obterAdversarioEncontros(Jogador jogadorCorrente) {
		
		Map<Jogador, Integer> result = new HashMap<>();
		
		for (Encontro encontro : this.encontros) {
			if (encontro.participaNoEncontro(jogadorCorrente)) {
				Jogador segundoJogador = encontro.getJogador1().equals(jogadorCorrente)
									  ? encontro.getJogador2() : encontro.getJogador1();
				if (result.containsKey(segundoJogador))
					result.put(segundoJogador, result.get(segundoJogador) + 1);
				
				else
					result.put(segundoJogador, 1);
			}
		}
		
		return result;
	}
	
	/**
	 * Funcao que verifica se um dado encontro associado a numeroEncontro existe.
	 * Caso existe devolve esse mesmo encontro
	 * @param numeroEncontro numero do encontro
	 * @return Encontro a ser devolvido, null caso nao exista
	 */
	public Encontro getEncontro(int numeroEncontro) {
		
		Encontro resultado = null;
		
		for (Encontro encontro : this.encontros)
			if (encontro.getEncontro() == numeroEncontro) {
				
				resultado = encontro;
				break;
			}
		
		return resultado;
	}
	  
	/**
	 * Adiciona um novo encontro ah lista de encontros do confronto
	 * @param encontro encontro a ser adicionado
	 * @requires encontro != null
	 * @ensures getEncontros.contains(encontro)
	 */
	public void addEncontro(Encontro encontro) {
		
		this.encontros.add(encontro);
		this.quantidadeEncontros = this.encontros.size();
	}
}
