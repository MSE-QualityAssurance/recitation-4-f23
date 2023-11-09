package business.interfaces;

import java.util.Calendar;

import facade.dto.VisualizarEncontroDTO;
import facade.exceptions.TornGesException;

public interface IVisualizarCalendario {

	/**
	 * Caso de uso para visualizar todos os confrontos e encontros associados a
	 * um determinado jogador em torneios apos uma determinada data. 
	 * @param numeroInscricao numero de inscricao do jogador
	 * @param data data ah qual os torneios tem de ser posteriores
	 * @return uma lista de Visualizacoes de encontros DTO
	 * @throws TornGesException
	 */
	public Iterable<VisualizarEncontroDTO> verConfrontos(int numeroInscricao, Calendar data) throws TornGesException;
}
