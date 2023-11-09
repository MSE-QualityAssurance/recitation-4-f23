package business;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.TypedQuery;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Catalogo de Torneios
 * @author css003
 */
@Stateless
public class CatalogoTorneios {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Construtor de um catalogo de torneios
	 */
	public CatalogoTorneios() {
		// Does nothing
	}
	
	/**
	 * Obtem um Torneio a partir da sua designacao
	 * @param em o entity manager
	 * @param designacaoTorneio
	 * @return a lista de torneios com a designacao fornecida
	 */
	public List<Torneio> getTorneioByDesig(String designacaoTorneio) {
		
		TypedQuery<Torneio> q = em.createNamedQuery(Torneio.NQ_GET_TORNEIO_BY_DESIG, Torneio.class);
		q.setParameter(Torneio.NQ_DESIGNACAO_COL, designacaoTorneio);
		
		return q.getResultList();
	}
	
	/**
	 * Obter os torneios onde um determinado jogador participa
	 * @param em o entity manager
	 * @param idJogador o id do jogador
	 * @return a lista de torneios onde o dado jogador participa
	 */
	public List<Torneio> getTorneiosOfPlayer(int idJogador){

		List<Torneio> torList = null;
		List<Torneio> result = new ArrayList<>();
		Jogador currJog = null;
		
		TypedQuery<Torneio> q = em.createNamedQuery(Torneio.NQ_GET_TORNEIOS, Torneio.class);
		torList = q.getResultList();
		
		TypedQuery<Jogador> p = em.createNamedQuery(Jogador.GET_PLAYER_BY_ID, Jogador.class);
		p.setParameter(Jogador.ID_NUMBER_COL, idJogador);
		currJog = p.getResultList().get(0);
		
		if(!torList.isEmpty()) {
			
			result = new ArrayList<>();
			
			for(Torneio t : torList) {
				for(Confronto c : t.getConfrontos()) 
					if(!c.getEncontrosJogador(currJog).isEmpty()) {
						result.add(t);
						break;
					}
			}
		}
		
		return result;
	}

	/**
	 * Funcao que devolve todos os torneios eistentes no sistema 
	 */
	public List<Torneio> getTorneios() {
		
		TypedQuery<Torneio> q = em.createNamedQuery(Torneio.NQ_GET_TORNEIOS, Torneio.class);
		return q.getResultList();
	}
}
