package business.interfaces;

import facade.dto.EncontroDTO;
import facade.dto.TorneioShortDTO;
import facade.exceptions.TornGesException;

/**
 * Interface que define as operacoes necessarias para registar um 
 * resultado de um encontro
 * @author css003
 */
public interface IRegistarResultado {
	
	/**
	 * Funcao que devolve um objeto DTO que corresponde a todos os torneios
	 * que existem no sistema
	 * @throws TornGesException
	 */
	public Iterable<TorneioShortDTO> getAllTorneios() throws TornGesException;
	
	/**
	 * Funcao que realiza a listagem de todos os encontros sem resultados
	 * associados ah designacaoTorneio inserida
	 * @param designacaoTorneio designacao do torneio 
	 * @return uma lista com todos encontros do torneio sem resultados
	 * @throws TornGesException
	 */
	public Iterable<EncontroDTO> getEncontrosTorneio(String designacaoTorneio) throws TornGesException;
	
	/**
	 * Adiciona um resultado a um determinado numero de encontro caso este exista
	 * @param numeroEncontro numero do encontro
	 * @param resultado desfecho da partida
	 * @param designacaoTorneio designacao do torneio cujo encontro pertence
	 * @throws TornGesException
	 */
	public void adicionaResultado(String designacaoTorneio, int numeroEncontro, String resultado) throws TornGesException;
}
