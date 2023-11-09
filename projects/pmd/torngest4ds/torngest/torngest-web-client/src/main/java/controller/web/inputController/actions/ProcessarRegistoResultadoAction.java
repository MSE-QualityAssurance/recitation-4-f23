package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.dto.EncontroDTO;
import facade.dto.TorneioShortDTO;
import facade.handlers.ITorneioServiceRemote;
import presentation.web.model.RegistarResultadoModel;
import presentation.web.model.VisualizarTorneiosModel;

/**
 * Acao responsavel por processar um pedido de registo de um resultado
 * num encontro de um determinado Torneio.
 * @author css003
 */
@Stateless
public class ProcessarRegistoResultadoAction extends Action {
	
	@EJB private ITorneioServiceRemote tornGesService;
	
	// Constantes
	private static final String SUCCESS_ADDED_RESULT = "O resultado do encontro %s foi adicionado com sucesso!";
	private static final String ERROR_NO_TORNEIOS = "Nao foi possivel encontrar torneios no sistema";
	private static final String ERROR_NO_ENCONTROS = "Nao foi possivel encontrar encontros associados a %s";
	private static final String ERROR_NO_DESFECHOS = "Nao foi possivel encontrar desfechos para seleccionar";
	
	/**
	 * @see Action#process(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String desigTorneio = request.getParameter("designacaoTorneio");
		String numeroEncontro = request.getParameter("numeroEncontro");
		String resultado = request.getParameter("resultado");
		
		try {
			int num = Integer.parseInt(numeroEncontro);
			
			VisualizarTorneiosModel visualizarModel = new VisualizarTorneiosModel();
			request.setAttribute("model", visualizarModel);

			// Realiza a operacao sobre o serviço
			tornGesService.adicionaResultado(desigTorneio, num, resultado);
			
			// Adiciona a mensagem de sucesso de adicionar o resultado
			visualizarModel.addMessage(String.format(ProcessarRegistoResultadoAction.SUCCESS_ADDED_RESULT, desigTorneio));
			
			Iterable<TorneioShortDTO> torneios = tornGesService.getAllTorneios();
			
			visualizarModel.setListaTorneios(torneios);
			
			if(!visualizarModel.isHasTorneios())
				visualizarModel.addMessage(ProcessarRegistoResultadoAction.ERROR_NO_TORNEIOS);
			
			request.getRequestDispatcher("/registarResultado/visualizarTorneios.jsp").forward(request, response);
			
		} catch(Exception e) {
			
			// Caso ocorra erro fica na mesma pagina
			RegistarResultadoModel registarModel = new RegistarResultadoModel();
			registarModel.clearFields();

			request.setAttribute("model", registarModel);

			if (!(e instanceof NumberFormatException)) 
				registarModel.addMessage(e.getMessage());
			
			try {
				
				registarModel.setDesignacaoTorneio(desigTorneio);
				
				// Re-obter as informacoes para preencher a pagina
				Iterable<String> listaDesfechos = tornGesService.getAllDesfechos();
				registarModel.setDesfechosEncontros(listaDesfechos);

				Iterable<EncontroDTO> listaEncontros = tornGesService.getEncontrosTorneio(registarModel.getDesignacaoTorneio());
				registarModel.setListaEncontros(listaEncontros);
				
				registarModel.setPrimeiroEncontro(numeroEncontro);
				registarModel.setPrimeiroDesfechoEncontro(resultado);
				
				// Caso nao hajam encontros, enviar mensagem de erro
				if (!registarModel.isHasEncontros())
					registarModel.addMessage(String.format(ProcessarRegistoResultadoAction.ERROR_NO_ENCONTROS, desigTorneio));

				// Caso nao hajam desfechos de encontros, enviar mensagem de erro
				if (!registarModel.isHasDesfechosEncontros())
					registarModel.addMessage(ProcessarRegistoResultadoAction.ERROR_NO_DESFECHOS);
				
			} catch(Exception e2) {
				registarModel.addMessage(e2.getMessage());
			}

			// Despacha para a mesma pagina
			request.getRequestDispatcher("/registarResultado/registarResultado.jsp").forward(request, response);
		}
	}

}
