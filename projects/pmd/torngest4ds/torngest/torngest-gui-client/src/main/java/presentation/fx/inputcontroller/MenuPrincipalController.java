package presentation.fx.inputcontroller;

import java.util.Locale;
import java.util.ResourceBundle;

import facade.handlers.ITorneioServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.fx.model.CriarTorneioModel;
import presentation.fx.model.RegistarParticipanteModel;

public class MenuPrincipalController extends BaseController {
	
	private ResourceBundle i18nBundle;
	private ITorneioServiceRemote tornGesService;
	
	public void setTornGesService(ITorneioServiceRemote tornGesService) {
		this.tornGesService = tornGesService;
		i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("pt", "PT"));
	}
	/**
	 * Realiza a criacao de uma nova janela onde se vai realizar o caso de uso 
	 * criarTorneio 
	 * @param event O evento
	 */
	@FXML
	public void criarTorneioAction(ActionEvent event) {
		
    	FXMLLoader criarTorneioLoader = new FXMLLoader(getClass().getResource("/fxml/criarTorneio.fxml"), i18nBundle);
    	Parent root = null;
		try {
			
			root = criarTorneioLoader.load();
    	    	
	    	CriarTorneioController ctc = criarTorneioLoader.getController();
	    	
	    	CriarTorneioModel ctm = new CriarTorneioModel(tornGesService);
	    	ctc.setModel(ctm);
	    	ctc.setTornGesService(tornGesService);
	    	ctc.setI18NBundle(i18nBundle);
	    	
	    	Stage stage = new Stage();
	        Scene scene = new Scene(root, 443, 485);
	       
	        stage.setTitle(i18nBundle.getString("application.title.criarTorneio"));
	        stage.setScene(scene);
	        stage.show();
	        
		} catch (Exception e) {
			showError(i18nBundle.getString("error.dialog.title"));
		}
	}
	
	/**
	 * Realiza a criacao de uma nova janela onde se vai realizar o caso de uso 
	 * registarParticipante
	 * @param event
	 */
	@FXML
	public void registarParticipanteAction(ActionEvent event) {

		FXMLLoader registarParticipanteLoader = new FXMLLoader(getClass().getResource("/fxml/registarParticipante.fxml"), i18nBundle);
		Parent root = null;
		try {
			root = registarParticipanteLoader.load();

			RegistarParticipanteController rpc = registarParticipanteLoader.getController();

			RegistarParticipanteModel rpm = new RegistarParticipanteModel(tornGesService);

			rpc.setModel(rpm);
			rpc.setTornGesService(tornGesService);
			rpc.setI18NBundle(i18nBundle);

			Stage stage = new Stage();
			Scene scene = new Scene(root, 678, 351);

			stage.setTitle(i18nBundle.getString("application.title.registarParticipante"));
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			showError(i18nBundle.getString("error.dialog.title"));
		}
	}
}
