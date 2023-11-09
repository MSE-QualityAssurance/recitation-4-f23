package business.handlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import business.CatalogoModalidades;
import business.CatalogoTorneios;
import business.Modalidade;
import business.Torneio;
import business.TorneioEquipa;
import business.TorneioIndividual;
import business.interfaces.ICriarTorneio;
import facade.exceptions.TornGesException;

@Stateless
public class CriarTorneioHandler {
	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private CatalogoModalidades catModalidades;
	
	@EJB
	private CatalogoTorneios catTorneios;
	
	public CriarTorneioHandler() {
		// Does nothing
	}
	
	/**
	 * @see ICriarTorneio#criaTorneio()
	 */
	public List<String> criaTorneio() {
		
		return catModalidades.selectAllModalidadeName();
	}
	
	/**
	 * @see ICriarTorneio#inserirDadosTorneio(String, String, String, int, int, int, int, int, int)
	 */
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void inserirDadosTorneio(String m, String d, String t, int nP, int nE, int dia, int mes, int ano, int dD) throws TornGesException{
		Torneio currTorneio;
		
		List<Modalidade> modalidadesDisponiveis = catModalidades.selectAllModalidade();
		
		Optional<Modalidade> modNome = modalidadesDisponiveis.stream()
														     .filter(e-> m.equals(e.getNome()))
				                                             .findFirst();
		//1. a modalidade indicada é válida
		if(!modNome.isPresent()) 
			throw new TornGesException("Nao existe "+ m +" como modalidade disponivel");
		
		//2. não existe outro torneio com a mesma designação
		if(!catTorneios.getTorneioByDesig(d).isEmpty())
			throw new TornGesException("Ja existe um torneio com o mesmo nome");
		
		if(d == null || d.isEmpty())
			throw new TornGesException("Designacao invalida");
		
		//3. o número de participantes e o número de encontros entre cada par de oponentes são maiores que zero e pares,
		if(!verificaPartipantesEncontros(nP, nE))
			throw new TornGesException("O numero de participantes e encontros tem de ser pares e maiores que 0");
		
		//4. no caso de torneio ser de equipa, o torneio dura pelo menos 2 dias e apanha um fim-de-semana
		
		if(t != null && t.equals("Equipa")) {
			currTorneio = new TorneioEquipa();
		}else if (t != null && t.equals("Individual")) {
			currTorneio = new TorneioIndividual();
		}else
			throw new TornGesException("Tipo de torneio invalido");
		
		if(!verificaTorneioFimSemana(currTorneio, dia, mes, ano, dD)) 
			throw new TornGesException("O torneio nao verifica as condicoes necessarias");	

		try {
			
			currTorneio.setModalidade(modNome.get());
			currTorneio.setDesignacao(d);
			currTorneio.setNumParticipantes(nP);
			currTorneio.setNumEncontros(nE);
			currTorneio.setEstahAberto(true);
			Calendar c = (Calendar) Calendar.getInstance().clone();
			c.set(ano, mes, dia);
			currTorneio.setDataInicio(c);
			currTorneio.setDuracao(dD);
			
			em.persist(currTorneio);	
		} catch(Exception e) {
			throw new TornGesException("Erro a criar torneio", e);
		}
	}
	/**
	 * Verifica o numero de participantes e encontros 
	 * @param nP O numero de participantes 
	 * @param nE O numero de encontros
	 * @return True se o numero de participantes e encontros for valido, false cc
	 */
	private boolean verificaPartipantesEncontros(int nP, int nE) {
		return nP > 0 && nP % 2 == 0 && nE > 0 && nE % 2 == 0;
	}
	
	/**
	 * Verifica se a data abrange um fim-de-semana
	 * @param dia O dia de inicio
	 * @param mes O mes de inicio
	 * @param ano O ano de inicio
	 * @param dD A duracao em dias 
	 * @return True se o intervalo de dias abranger um fim-de-semana
	 */
	private boolean verificaTorneioFimSemana(Torneio currTorneio, int dia, int mes, int ano, int dD) {
		boolean result = dD > 0;
		
		if(currTorneio instanceof TorneioEquipa) {
			
			if(dD >= 2) {
				
				LocalDate startDay = LocalDate.of(ano, mes + 1, dia);
				LocalDate endDay = startDay.plusDays(dD);
				
				int start = startDay.getDayOfWeek().getValue();
				int end = endDay.getDayOfWeek().getValue();
				
				//forma negativa de db >= 7 && start + end >= 7
				if(dD < 7 && start + end < 7) 
					result = false;
			}else
				result = false;
			
		}
	
		return result;
	}
	
	/**
	 * Funcao que devolve todos os torneios existentes 
	 */
	public Iterable<String> getTiposTorneio() {
		
		List<String> tipos = new ArrayList<>();
		
		tipos.add("Equipa");
		tipos.add("Individual");
		
		return tipos;
	}
}
