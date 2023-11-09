package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Catalogo de Equipas
 * @author css003
 */
@Stateless
public class CatalogoEquipas {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Devolve uma lista com uma equipa que tenha um determinado numero de
	 * inscricao, eh devolvida uma lista por motivos de implementacao
	 * @param em O entity manager responsavel pela query
	 * @param id O identificador unico de uma equipa
	 * @return Uma lista de equipas que tenham um certo id
	 */
	public List<Equipa> findEquipaById(int id) {
		
		TypedQuery<Equipa> tq = em.createNamedQuery(Equipa.NQ_FIND_BY_ID, Equipa.class);
		tq.setParameter(Equipa.NQ_NR_INSCRICAO, (long) id);
		
		return tq.getResultList();
	}
	
	/**
	 * Devolve uma lista com uma equipa que tenha um determinado jogador
	 * @param em O entity manager responsavel pela query
	 * @param idJogador O identificador unico do jogador
	 * @return Uma lista de equipas que contenham o jogador com idJogador
	 */
	public List<Equipa> getEquipaByJogador(int idJogador){
		
		TypedQuery<Equipa> tq = em.createNamedQuery(Equipa.NQ_FIND_JOGADOR_IN_EQUIPA, Equipa.class);
		tq.setParameter(Jogador.ID_NUMBER_COL, idJogador);
		
		return tq.getResultList();
	}
}
