package business.handlers;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import business.CatalogoEquipas;
import business.CatalogoTorneios;
import business.Equipa;
import business.Jogador;
import business.Torneio;
import business.TorneioEquipa;
import business.Encontro;
import business.Confronto;
import business.DesfechoEncontro;
import business.interfaces.IRegistarResultado;
import business.utils.CalculateElo;

import facade.dto.EncontroDTO;
import facade.dto.TorneioShortDTO;

import facade.exceptions.TornGesException;

/**
 * Handler responsavel por registar o resultado do desfecho de um encontro entre
 * dois jogadores
 * 
 * @author css003
 */
@Stateless
public class RegistarResultadoHandler {

	// Constantes
	private static final String ERROR_MSG_NO_TORNEIOS = "Nao existem encontros associados ao Torneio %s";
	private static final String ERROR_MSG_GET_TORNEIOS = "Ocorreu um erro ao obter todos os torneios";
	private static final String ERROR_MSG_NULL_DESIGNACAO = "A designacao inserida eh invalida.";
	private static final String ERROR_MSG_NO_TORN_DESIGNATION = "Nao existe um torneio associado ah designacao inserida.";
	private static final String ERROR_MSG_DATA_ANTERIOR = "O torneio pretendido ainda nao comecou.";
	private static final String ERROR_MSG_NO_ENCONTRO_FOUND = "O numero de encontro %d nao existe neste torneio.";
	private static final String ERROR_MSG_NO_DESFECHO_FOUND = "O desfecho %s nao eh valido.";
	private static final String ERROR_MSG_EM_SET_DESFECHO = "Ocorreu um erro ao tentar adicionar um desfecho ao encontro %d";

	// Atributos
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private CatalogoTorneios tornCatalog;
	
	@EJB
	private CatalogoEquipas equipaCatalog;

	/**
	 * Construtor do Handler de Registar o resultado
	 */
	public RegistarResultadoHandler() {
		// Does nothing
	}


	/**
	 * @see IRegistarResultado#getAllTorneios()
	 */
	@Transactional(Transactional.TxType.REQUIRED)
	public Iterable<TorneioShortDTO> getAllTorneios() throws TornGesException {
		
		List<TorneioShortDTO> resultado = new ArrayList<>();
		
		try {
			
			List<Torneio> todosTorneios = this.tornCatalog.getTorneios();
			
			for (Torneio t : todosTorneios) {
				
				TorneioShortDTO torneioDTO = new TorneioShortDTO();
				torneioDTO.setDesignacaoTorneio(t.getDesignacao());
				
				resultado.add(torneioDTO);
			}
			
		} catch (Exception e) {
			throw new TornGesException(RegistarResultadoHandler.ERROR_MSG_GET_TORNEIOS,e);
		}
		
		return resultado;
	}
	
	/**
	 * @see IRegistarResultado#registarResultado(String)
	 */
	@Transactional(Transactional.TxType.REQUIRED)
	public Iterable<EncontroDTO> getEncontrosTorneio(String designacaoTorneio) throws TornGesException {

		Torneio torneioCorrente = obterTorneio(designacaoTorneio);

		List<Confronto> listaConfrontos = torneioCorrente.getConfrontos();

		if (listaConfrontos == null)
			throw new TornGesException(String.format(ERROR_MSG_NO_TORNEIOS, torneioCorrente.getDesignacao()));
		
		return constroiEncontrosDTO(listaConfrontos);
	}

	/**
	 * Funcao privada que constroi uma lista dos encontrosDTO iteraveis para
	 * o Client
	 * @param listaConfrontos lista com os confrontos a ser gerado
	 * @return Encontro'sDTO cujos encontros ainda nao tem desfecho
	 */
	private Iterable<EncontroDTO> constroiEncontrosDTO(List<Confronto> listaConfrontos) {
		
		List<EncontroDTO> listaEncontrosDTO = new ArrayList<>();

		for (Confronto confronto : listaConfrontos) {
			for (Encontro encontro : confronto.getTodosEncontros())
				if (encontro.getDesfecho() == null)
					listaEncontrosDTO.add(new EncontroDTO(encontro.getEncontro(), encontro.getJogador1().getNome(),
							encontro.getJogador2().getNome()));
		}

		Collections.sort(listaEncontrosDTO, EncontroDTO.comparator());
		
		return listaEncontrosDTO;
	}
	
	/**
	 * @see IRegistarResultado#adicionaResultado(int, String)
	 */
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void adicionaResultado(String designacaoTorneio, int numeroEncontro, String resultado) throws TornGesException {
		
		Torneio torneioCorrente = obterTorneio(designacaoTorneio);
		
		Encontro encontroCorrente = torneioCorrente.getEncontro(numeroEncontro);

		// Verificar se o numero de encontro fornecido existe
		if (encontroCorrente == null)
			throw new TornGesException(
					String.format(RegistarResultadoHandler.ERROR_MSG_NO_ENCONTRO_FOUND, numeroEncontro));

		try {
			DesfechoEncontro desfecho = DesfechoEncontro.valueOf(resultado);

			encontroCorrente.setDesfechoEncontro(desfecho);
			encontroCorrente = em.merge(encontroCorrente);

			// Obtencao do jogador 1
			Jogador j1 = encontroCorrente.getJogador1();

			// Obtencao do jogador 2
			Jogador j2 = encontroCorrente.getJogador2();

			j1 = em.find(Jogador.class, j1.getId());
			j2 = em.find(Jogador.class, j2.getId());
			
			// Calcular o elo para cada um dos jogadores
			double[] resultados = CalculateElo.calculateElo(j1.getPontos(), j2.getPontos(), desfecho);

			j1.setPontos(resultados[0]);
			j2.setPontos(resultados[1]);

			// Se o torneio for de Equipa atualizar os pontos de equipa
			if (torneioCorrente instanceof TorneioEquipa) {
				
				Equipa e1 = equipaCatalog.getEquipaByJogador(j1.getPrimaryKey()).get(0);
				Equipa e2 = equipaCatalog.getEquipaByJogador(j2.getPrimaryKey()).get(0);

				e1 = em.find(Equipa.class, e1.getNumeroInscricao());
				e2 = em.find(Equipa.class, e2.getNumeroInscricao());
				
				if (e1 != null && e2 != null) {

					double[] resultadosEquipa = CalculateElo.calculateElo(e1.getPontos(), e2.getPontos(), desfecho);
					e1.setPontos(resultadosEquipa[0]);
					e2.setPontos(resultadosEquipa[1]);
					
					em.merge(e1);
					em.merge(e2);
					em.merge(torneioCorrente);
				}
			}
			
			em.merge(j1);
			em.merge(j2);

		} catch (IllegalArgumentException e) {
			throw new TornGesException(String.format(RegistarResultadoHandler.ERROR_MSG_NO_DESFECHO_FOUND, resultado));

		} catch (Exception e) {
			e.printStackTrace();
			throw new TornGesException(
					String.format(RegistarResultadoHandler.ERROR_MSG_EM_SET_DESFECHO, numeroEncontro));
		}	
	}
	
	/**
	 * Funcao privada que obtem o torneio dada a sua designacao
	 * @param designacaoTorneio designacao do torneio
	 * @return Torneio com a determinada designacao
	 * @throws TornGesException lanca excecao caso ocorra um erro.
	 */
	private Torneio obterTorneio(String designacaoTorneio) throws TornGesException {
		
		if (designacaoTorneio == null)
			throw new TornGesException(RegistarResultadoHandler.ERROR_MSG_NULL_DESIGNACAO);

		List<Torneio> listaTorneios = tornCatalog.getTorneioByDesig(designacaoTorneio);

		if (listaTorneios.isEmpty())
			throw new TornGesException(RegistarResultadoHandler.ERROR_MSG_NO_TORN_DESIGNATION);

		Torneio torneioCorrente = listaTorneios.get(0);

		if (torneioCorrente.getDataInicio().after(Calendar.getInstance()))
			throw new TornGesException(RegistarResultadoHandler.ERROR_MSG_DATA_ANTERIOR);
		
		return torneioCorrente;
	}

	/**
	 * Devolve uma lista de Strings com todos os tipos de desfechos possiveis
	 */
	public Iterable<String> getAllDesfechos() {
		
		List<String> listaDesfechos = new ArrayList<>();
		
		for (DesfechoEncontro desfecho : DesfechoEncontro.values())
			listaDesfechos.add(desfecho.toString());
		
		return listaDesfechos;
	}
}
