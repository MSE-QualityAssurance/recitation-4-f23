package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.dto.EncontroDTO;
import facade.handlers.ITorneioServiceRemote;
import presentation.web.model.RegistarResultadoModel;

/**
 * Acao responsavel pelo despachamento da pagina que contera o local para
 * colocacao dos resultados associados a um encontro de um torneio
 * @author css003
 */
@Stateless
public class RegistarResultadoAction extends Action {
	
	// Atributos
	@EJB ITorneioServiceRemote tornGesService;

	// Constantes
	private static final String ERROR_NO_ENCONTROS = "Nao foi possivel encontrar encontros associados a %s";
	private static final String ERROR_NO_DESFECHOS = "Nao foi possivel encontrar desfechos para seleccionar";
	
	/**
	 * @see Action#process(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RegistarResultadoModel model = new RegistarResultadoModel();

		request.setAttribute("model", model);
		
		String desigTorneio = request.getParameter("designacaoTorneio");
		
		try {
			
			model.clearFields();
			
			model.setDesignacaoTorneio(desigTorneio);

			Iterable<String> listaDesfechos = tornGesService.getAllDesfechos();
			model.setDesfechosEncontros(listaDesfechos);

			Iterable<EncontroDTO> listaEncontros = tornGesService.getEncontrosTorneio(model.getDesignacaoTorneio());
			model.setListaEncontros(listaEncontros);

			// Caso nao hajam encontros, enviar mensagem de erro
			if (!model.isHasEncontros())
				model.addMessage(String.format(RegistarResultadoAction.ERROR_NO_ENCONTROS, desigTorneio));

			// Caso nao hajam desfechos de encontros, enviar mensagem de erro
			if (!model.isHasDesfechosEncontros())
				model.addMessage(RegistarResultadoAction.ERROR_NO_DESFECHOS);
			
		} catch(Exception e) {
			model.addMessage(e.getMessage());
		}

		request.getRequestDispatcher("/registarResultado/registarResultado.jsp").forward(request, response);
	}
}
