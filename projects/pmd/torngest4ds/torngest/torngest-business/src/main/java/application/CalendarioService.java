package application;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import business.handlers.VisualizarCalendarioHandler;
import facade.dto.JogadorShortDTO;
import facade.dto.VisualizarEncontroDTO;
import facade.exceptions.TornGesException;
import facade.handlers.ICalendarioServiceRemote;

/**
 * Classe que implementa os servicos remotos relacionados com a 
 * visualização do calendario dos jogadores
 * @author cs003
 */
@Stateless
@WebService
public class CalendarioService implements ICalendarioServiceRemote {
	
	@EJB
	private VisualizarCalendarioHandler vch;
	
	public CalendarioService() {
		// Does nothing
	}
	
 
	/**
	 * @see ICalendarioServiceRemote#verConfrontos(int, String)
	 */
	public List<VisualizarEncontroDTO> verConfrontos(int numIns, String data)
			throws TornGesException {
		
		return vch.verConfrontos(numIns, data);
	}
	
	/**
	 * @see ICalendarioServiceRemote#getJogadores()
	 */
	public List<JogadorShortDTO> getJogadores() throws TornGesException{
		return vch.getJogadores();
	}

}
