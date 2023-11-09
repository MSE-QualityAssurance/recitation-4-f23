package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.dto.JogadorShortDTO;
import facade.dto.VisualizarEncontroDTO;
import facade.handlers.ICalendarioServiceRemote;
import presentation.web.model.VerConfrontosModel;
import presentation.web.model.VisualizarCalendarioModel;

/**
 * Acao responsavel por verificao dos encontros associados a um determinado
 * jogador a partir de uma data dada pelo utilizador
 * @author css003
 */
@Stateless
public class VerConfrontosAction extends Action {
	
	// Atributos
	@EJB private ICalendarioServiceRemote calendarioService;
	
	// Constantes
	private static final String NO_ENCONTROS_FOUND = "Nao foi possivel encontrar encontros associados ao jogador %s";
	private static final String NO_PLAYERS_FOUND = "Nao foi possivel encontrar jogadores";
	
	/**
	 * @see Action#process(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String numInscricao = request.getParameter("numeroInscricao");
		String data = request.getParameter("data");
		
		try {
			
			VerConfrontosModel verConfrontosModel = new VerConfrontosModel();
			
			verConfrontosModel.setNumeroInscricao(numInscricao);
			verConfrontosModel.setData(data);

			Iterable<VisualizarEncontroDTO> listaEncontros = calendarioService.verConfrontos(Integer.parseInt(numInscricao), data);
			verConfrontosModel.setConfrontos(listaEncontros);
			
			// Caso nao existam encontros, notificar utilizador
			if (!listaEncontros.iterator().hasNext())
				verConfrontosModel.addMessage(String.format(VerConfrontosAction.NO_ENCONTROS_FOUND, numInscricao));

			request.setAttribute("model", verConfrontosModel);
			
			request.getRequestDispatcher("/visualizarCalendario/verConfrontos.jsp").forward(request, response);
			
		} catch (Exception e) {
			
			// Caso ocorra erro, o utilizador permanece na mesma pagina.
			VisualizarCalendarioModel visCalendarioModel = new VisualizarCalendarioModel();
			
			visCalendarioModel.addMessage(e.getMessage());
			
			// Realizamos novamente a obtencao dos jogadores para o utilizador
			try {
				Iterable<JogadorShortDTO> jogadores = calendarioService.getJogadores();
				visCalendarioModel.setJogadores(jogadores);
				
				// Se nao forem encontrados jogadores, notificamos o utilizador
				if (!jogadores.iterator().hasNext())
					visCalendarioModel.addMessage(VerConfrontosAction.NO_PLAYERS_FOUND);
				
			} catch (Exception e2) {
				visCalendarioModel.addMessage(e2.getMessage());
			}

			request.setAttribute("model", visCalendarioModel);
			
			request.getRequestDispatcher("/visualizarCalendario/visualizarCalendario.jsp").forward(request, response);
		}
		
	}

}
