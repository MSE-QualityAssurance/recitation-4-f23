package facade.handlers;

import java.util.List;

import javax.ejb.Remote;

import facade.dto.JogadorShortDTO;
import facade.dto.VisualizarEncontroDTO;
import facade.exceptions.TornGesException;

/**
 * Interface para ser utilizada pelos servicos remota
 * @author css003
 */
@Remote
public interface ICalendarioServiceRemote {
		
	/**
	 * Devolve os confrontos associados a um dado numero de inscricao apos uma data
	 * @param numeroInscricao numero de inscricao de um participante
	 * @param data data a partir da qual pretendemos ver os encontros
	 * @return iteravel com os confrontos
	 * @throws TornGesException
	 */
	public List<VisualizarEncontroDTO> verConfrontos(int numeroInscricao, String data) throws TornGesException;
	
	/**
	 * Devolve todos os jogadores existentes no servico tornges
	 * @throws TornGesException
	 */
	public List<JogadorShortDTO> getJogadores() throws TornGesException;
	
}
