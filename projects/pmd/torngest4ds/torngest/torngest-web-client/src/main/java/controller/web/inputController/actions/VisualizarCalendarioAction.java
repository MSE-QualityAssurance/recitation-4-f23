package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.dto.JogadorShortDTO;
import facade.handlers.ICalendarioServiceRemote;
import presentation.web.model.VisualizarCalendarioModel;

/**
 * Acao responsavel por construir a pagina principal onde o 
 * utilizador podera seleccionar o jogador cujos encontros pretende
 * visualizar
 * @author css003
 */
@Stateless
public class VisualizarCalendarioAction extends Action{
 
	// Atributos Injetados
	@EJB private ICalendarioServiceRemote calendarioService;
	
	// Constantes
	private static final String NO_PLAYERS_FOUND = "Nao foi possivel encontrar jogadores";
	
	/**
	 * @see Action#process(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VisualizarCalendarioModel model = new VisualizarCalendarioModel();	
		request.setAttribute("model", model);
		
		try {
			
			Iterable<JogadorShortDTO> jogadores = calendarioService.getJogadores();
			model.setJogadores(jogadores);

			// Caso nao hajam jogadores, enviar mensagem de erro
			if (!jogadores.iterator().hasNext())
				model.addMessage(VisualizarCalendarioAction.NO_PLAYERS_FOUND);
			
		} catch (Exception e) {
			model.addMessage(e.getMessage());
		}
		
		request.getRequestDispatcher("/visualizarCalendario/visualizarCalendario.jsp").forward(request, response);
	}
}
