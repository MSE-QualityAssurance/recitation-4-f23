package application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.handlers.CriarTorneioHandler;
import business.handlers.RegistarParticipanteHandler;
import business.handlers.RegistarResultadoHandler;
import facade.dto.EncontroDTO;
import facade.dto.TorneioShortDTO;
import facade.exceptions.TornGesException;
import facade.handlers.ITorneioServiceRemote;

/**
 * Classe que implementa os Servicos remotos
 * @author cs003
 */
@Stateless
public class TorneioService implements ITorneioServiceRemote {
	
	@EJB
	private CriarTorneioHandler cth;
	
	@EJB
	private RegistarParticipanteHandler rph;
	
	@EJB
	private RegistarResultadoHandler rrh;
	
	public TorneioService() {
		// Does nothing
	}
	
	/**
	 * @see ITorneioServiceRemote#criarTorneio()
	 */
	public Iterable<String> criarTorneio() {
		
		return cth.criaTorneio();
	}
	
	/**
	 * @see ITorneioServiceRemote#inserirDadosTorneio(String, String, String, int, int, int, int, int, int)
	 */
	public void inserirDadosTorneio(String m, String d, String t, int nP,
			int nE, int dia, int mes, int ano, int dD) throws TornGesException {
		
		cth.inserirDadosTorneio(m, d, t, nP, nE, dia, mes-1, ano, dD);
	}

	/**
	 * @see ITorneioServiceRemote#registarParticipante(String, int)
	 */
	public void registarParticipante(String desigTorneio, int idPart) 
			throws TornGesException {
		rph.registarParticipante(desigTorneio, idPart);
	}
	
	/**
	 * @see ITorneioServiceRemote#getEncontrosTorneio(String)
	 */
	public Iterable<EncontroDTO> getEncontrosTorneio(String desigTorneio)
			throws TornGesException {
		
		return rrh.getEncontrosTorneio(desigTorneio);
	}
	
	/**
	 * @see ITorneioServiceRemote#adicionaResultado(String, int, String)
	 */
	public void adicionaResultado(String designacaoTorneio, int numEncontro, String resultado)
			throws TornGesException {
		
		rrh.adicionaResultado(designacaoTorneio, numEncontro, resultado);
	}
	

	/**
	 * @see ITorneioServiceRemote#getAllTorneios()
	 */
	public Iterable<TorneioShortDTO> getAllTorneios() throws TornGesException {
		return rrh.getAllTorneios();
	}

	/**
	 * @see ITorneioServiceRemote#getTiposTorneio()
	 */
	public Iterable<String> getTiposTorneio() throws TornGesException{
		return cth.getTiposTorneio();
	}

	/**
	 * @see ITorneioServiceRemote#getAllDesfechos()
	 */
	public Iterable<String> getAllDesfechos() throws TornGesException {
		return rrh.getAllDesfechos();
	}
}
