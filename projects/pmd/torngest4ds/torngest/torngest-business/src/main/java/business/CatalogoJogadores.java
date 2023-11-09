package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Catalogo de Jogadores
 * @author css003
 */
@Stateless
public class CatalogoJogadores {
	
	@PersistenceContext
	private EntityManager em;
	
	public CatalogoJogadores() {
		// Does nothing
	}
	
	/**
	 * Devolve uma lista de jogadores associados a id. Sendo id um valor unico,
	 * devolvera uma lista vazia ou uma lista com um elemento
	 * @param id identificador do Jogador
	 * @return lista de jogadores associados a id
	 */
	public List<Jogador> getJogadorById(int id) {
		
		TypedQuery<Jogador> query = em.createNamedQuery(Jogador.GET_PLAYER_BY_ID, Jogador.class);
		
		query.setParameter(Jogador.ID_NUMBER_COL, id);
		
		return query.getResultList();
	}
	
	/**
	 * Procura todos os jogadores associados a uma determinada modalidade
	 * @param modalidade modalidade a ser procurada
	 * @return uma lista com os jogadores que têm a modalidade apresentada
	 */
	public List<Jogador> findJogadorByModalidade(Modalidade modalidade) {
		
		TypedQuery<Jogador> query = em.createNamedQuery(Jogador.GET_PLAYERS_BY_MODALIDADE, Jogador.class);
		
		query.setParameter(Jogador.MODALIDADE_COL, modalidade);
		
		return query.getResultList();
	}
	
	/**
	 * Funcao que devolve todos os jogadores do sistema
	 */
	public List<Jogador> getAllJogadores(){
		
		TypedQuery<Jogador> query = em.createNamedQuery(Jogador.GET_ALL_PLAYERS, Jogador.class);
		
		return query.getResultList();
	}
}
