package business.handlers;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import business.CatalogoEquipas;
import business.CatalogoJogadores;
import business.CatalogoTorneios;
import business.Confronto;
import business.Encontro;
import business.Equipa;
import business.Jogador;
import business.Torneio;
import business.TorneioEquipa;
import business.TorneioIndividual;
import facade.exceptions.TornGesException;

@Stateless
public class RegistarParticipanteHandler {

	@EJB
	private CatalogoEquipas catEq;
	
	@EJB
	private CatalogoTorneios catTor;
	
	@EJB
	private CatalogoJogadores catJog;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Construtor de um handler para registar um participante
	 */
	public RegistarParticipanteHandler() {
		// Does nothing
	}
	
	/**
	 * Regista um participante num torneio
	 * @param designacaoTorneio - a designacao do torneio
	 * @param idParticipante - o id do participante
	 * @throws TornGesException
	 */
	public void registarParticipante(String designacaoTorneio, int idParticipante) throws TornGesException {
				
		List<Torneio> torList = catTor.getTorneioByDesig(designacaoTorneio);
		
		if(!torList.isEmpty()) {
			
			if(torList.get(0) instanceof TorneioEquipa)
				registarEmTorneioEquipa((TorneioEquipa) torList.get(0), idParticipante);
			else
				registarEmTorneioIndividual((TorneioIndividual) torList.get(0), idParticipante);
		}
		
		else
			throw new TornGesException("Torneio invalido!");
	}

	/**
	 * Metodo auxiliar para registar um participante num torneio individual
	 * @throws TornGesException
	 */
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	private void registarEmTorneioIndividual(TorneioIndividual torI, int idPart) throws TornGesException {
		
		if(!torI.getEstahAberto())
			throw new TornGesException("O torneio estah fechado!");
		
		List<Jogador> jogList = catJog.getJogadorById(idPart);
		
		if(jogList.isEmpty())
			throw new TornGesException("O jogador nao eh do mesmo tipo do torneio");
			
		Jogador jog = jogList.get(0);
		
		if(!jog.getModalidade().equals(torI.getModalidade()))
			throw new TornGesException("O Jogador nao eh da mesma modalidade do torneio");
		
		Calendar dataInicio = torI.getDataInicio();
		Calendar dataFim = Calendar.getInstance();	
		
		dataFim.setTime(dataInicio.getTime());
		dataFim.add(Calendar.DAY_OF_MONTH, torI.getDuracao());
		
		List<Torneio> torList = catTor.getTorneios();
		
		int count = 0;
		if(!torList.isEmpty()) {
			for(int i = 0; i < torList.size(); i++) {
				
				Torneio t = torList.get(i);
				
				Calendar dataInicioOutro = t.getDataInicio();
				Calendar dataFimOutro = Calendar.getInstance();
				
				dataFimOutro.setTime(dataInicioOutro.getTime());
				dataFimOutro.add(Calendar.DAY_OF_MONTH, t.getDuracao());
				
				if((dataFimOutro.after(dataInicio) && dataFimOutro.before(dataFim)) ||
						(dataInicioOutro.after(dataInicio) && dataInicioOutro.before(dataFim)) ||
						dataInicioOutro.equals(dataInicio) ||
						dataFimOutro.equals(dataFim)) {
					
					if(torList.get(i) instanceof TorneioIndividual) {

						TorneioIndividual ti = (TorneioIndividual) torList.get(i);
						
						if(ti.getJogadores().contains(jog))
							count++;
					}
					else if(torList.get(i) instanceof TorneioEquipa) {
						
						TorneioEquipa te = (TorneioEquipa) torList.get(i);
						
						Equipa e = catEq.getEquipaByJogador(jog.getPrimaryKey()).get(0);
						
						if(te.getEquipas().contains(e))
							count++;
					}
				}
			}
		}

		if(count > 0)
			throw new TornGesException("O jogador esta registado em mais de um torneio na mesma data");
		
		try {
			
			torI.adicionarJogador(jog);
			torI = em.merge(torI);
				
			if(torI.getJogadores().size() == torI.getNumParticipantes()) {
				torI.setEstahAberto(false);
				gerarConfrontosIndividuais(torI);
				torI = em.merge(torI);
			}			
		} catch(Exception e) {
			throw new TornGesException("Erro ao registar o participante!", e);
		}
	}

	/**
	 * Metodo auxiliar para registar um participante num torneio de equipa
	 * @throws TornGesException
	 */
	private void registarEmTorneioEquipa(TorneioEquipa torE, int idPart) throws TornGesException {
		
		if(!torE.getEstahAberto())
			throw new TornGesException("O torneio estah fechado");
		
		List<Equipa> eqList = catEq.findEquipaById(idPart);
		
		if(eqList.isEmpty())
			throw new TornGesException("O jogador nao eh do mesmo tipo do torneio");
		
		Equipa eq = eqList.get(0);
		
		if(!eq.getModalidade().equals(torE.getModalidade()))
			throw new TornGesException("O Jogador nao eh da mesma modalidade do torneio");
		
		Calendar dataInicio = torE.getDataInicio();
		Calendar dataFim = Calendar.getInstance();	
		
		dataFim.setTime(dataInicio.getTime());
		dataFim.add(Calendar.DAY_OF_MONTH, torE.getDuracao());
		
		List<Torneio> torList = catTor.getTorneios();
		
		int count = 0;
		if(!torList.isEmpty()) {
			for(int i = 0; i < torList.size(); i++) {
				
				Torneio t = torList.get(i);
				
				Calendar dataInicioOutro = t.getDataInicio();
				Calendar dataFimOutro = Calendar.getInstance();
				
				dataFimOutro.setTime(dataInicioOutro.getTime());
				dataFimOutro.add(Calendar.DAY_OF_MONTH, t.getDuracao());
				
				if((dataFimOutro.after(dataInicio) && dataFimOutro.before(dataFim)) ||
						(dataInicioOutro.after(dataInicio) && dataInicioOutro.before(dataFim)) ||
						dataInicioOutro.equals(dataInicio) ||
						dataFimOutro.equals(dataFim)) {
					
					if(torList.get(i) instanceof TorneioEquipa) {
						
						TorneioEquipa te = (TorneioEquipa) torList.get(i);
						
						if(te.getEquipas().contains(eq))
							count++;
					}
				}
			}
		}

		if(count > 0)
			throw new TornGesException("A equipa esta registada em mais de um torneio na mesma data");
		
		if(eq.getElementosEquipa().size() < eq.getModalidade().getMinimoJogadores())
			throw new TornGesException("A equipa tem um numero de elementos insuficiente");
		
		try {
			
			torE = em.merge(torE);
			torE.adicionarEquipa(eq);
			
			if(torE.getEquipas().size() == torE.getNumParticipantes()) {
				torE.setEstahAberto(false);
				gerarConfrontosEquipa(torE);
				torE = em.merge(torE);
			}	
		} catch(Exception e) {
			throw new TornGesException("Erro ao registar o participante!");
		}
	}
	
	/**
	 * Metodo auxiliar para gerao so confrontos entre as varias equipas
	 * @throws TornGesException
	 */
	private void gerarConfrontosEquipa(TorneioEquipa torE) throws TornGesException {
		
		int n = torE.getNumEncontros();
		int m = torE.getModalidade().getMinimoJogadores();
		List<Equipa> listaEquipas = torE.getEquipas();
		
		for(int i = 0; i < listaEquipas.size(); i++) {
			
			Equipa e1 = listaEquipas.get(i);
			
			for(int j = (i+1); j < listaEquipas.size(); j++) {
				
				Equipa e2 = listaEquipas.get(j);
				
				List<Jogador> l1 = e1.getElementosEquipa();
				List<Jogador> l2 = e2.getElementosEquipa();
				
				l1.stream().sorted(Comparator.comparing(Jogador::getPontos));
				l2.stream().sorted(Comparator.comparing(Jogador::getPontos));
				
				for(int c = 0; c < m; c++) {
					Confronto confronto = new Confronto();
					geraEncontros(confronto, l1.get(c), l2.get(c), n);
					torE.addConfronto(confronto);
					em.persist(confronto);
				}
			}
		}
	}
	
	/**
	 * Metodo auxiliar para gerar so confrontos entre os varios jogadores
	 * @throws TornGesException
	 */
	private void gerarConfrontosIndividuais(TorneioIndividual torI) throws TornGesException {
		int n = torI.getNumEncontros();
		
		List<Jogador> listaJogadores = torI.getJogadores();
		
		for(int i = 0; i < listaJogadores.size(); i++) {
			
			Jogador j1 = listaJogadores.get(i);
			
			for(int j = (i+1); j < listaJogadores.size(); j++) {
				
				Jogador j2 = listaJogadores.get(j);
				
				double diff = Math.abs(j1.getPontos() - j2.getPontos());
				
				if(Double.compare(diff, j1.getDiferencialMaximo()) <= 0 &&
						Double.compare(diff, j2.getDiferencialMaximo()) <= 0) {
					
					Confronto confronto = new Confronto();
					
					geraEncontros(confronto, j1, j2, n);
					torI.addConfronto(confronto);
					em.persist(confronto);
				}
			}
		}
	}
	
	//TODO: public --- really?
	public void geraEncontros(Confronto c, Jogador jog1, Jogador jog2, int n) throws TornGesException {
		
		for (int i = 0; i < n; i++) {
			
			Encontro encontroCorrente = new Encontro();
			
			if (i % 2 == 0)
				encontroCorrente.setJogadores(jog1, jog2);
			
			else
				encontroCorrente.setJogadores(jog2, jog1);
			
			c.addEncontro(encontroCorrente);
			//estava comentado porque?
			em.persist(encontroCorrente);
		}
	}
}
