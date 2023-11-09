package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.dto.TorneioShortDTO;
import facade.handlers.ITorneioServiceRemote;
import presentation.web.model.VisualizarTorneiosModel;

/**
 * Acao responsavel por construir a primeira pagina do RegistarResultado.
 * Esta acao mostra todos os torneios existentes no servico do tornges
 * @author css003
 */
@Stateless
public class VisualizarTorneiosAction extends Action {
	
	// Atributos
	@EJB private ITorneioServiceRemote tornGesService;
	
	// Constantes
	private static final String ERROR_NO_TORNEIOS = "Nao foi possivel encontrar torneios no sistema";
	
	/**
	 * @see Action#process(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VisualizarTorneiosModel model = new VisualizarTorneiosModel();
		request.setAttribute("model", model);
		
		try {
			
			Iterable<TorneioShortDTO> torneios = tornGesService.getAllTorneios();
			
			model.setListaTorneios(torneios);

			// Caso nao hajam torneios, enviar mensagem de erro
			if(!model.isHasTorneios())
				model.addMessage(VisualizarTorneiosAction.ERROR_NO_TORNEIOS);
			
		} catch (Exception e) {
			model.addMessage(e.getMessage());
		}
		
		request.getRequestDispatcher("/registarResultado/visualizarTorneios.jsp").forward(request, response);	
	}

}
