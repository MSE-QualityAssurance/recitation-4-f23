package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CatalogoModalidades {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Devolve todas as modalidades existentes
	 * @return Uma lista com todas as modalidades existentes
	 */
	public List<Modalidade> selectAllModalidade(){
		
		TypedQuery<Modalidade> tq = em.createNamedQuery(Modalidade.NQ_SELECT_ALL, Modalidade.class);
		return tq.getResultList();
	}
	
	/**
	 * Devolve todas as modalidades com um certo nome, por motivos de implementacao
	 * o nome eh unico
	 * @param nome O nome da modalidade
	 * @return Uma lista com modalidades que tenham um certo nome
	 */
	public List<Modalidade> findModalidadeByName(String nome) {
		
		TypedQuery<Modalidade> tq = em.createNamedQuery(Modalidade.NQ_FIND_BY_NAME, Modalidade.class);
		tq.setParameter(Modalidade.NQ_NAME, nome);
		return tq.getResultList();
	}
	
	/**
	 * Devolve o nome de todas as modalidades existentes
	 * @return Uma lista com todas as modalidades existentes
	 */
	public List<String> selectAllModalidadeName() {
		TypedQuery<String> tq = em.createNamedQuery(Modalidade.NQ_SELECT_ALL_NAME, String.class);
		return tq.getResultList();
	}
}
