package presentation.fx.inputcontroller;

import java.util.function.UnaryOperator;

import facade.dto.TorneioShortDTO;
import facade.exceptions.TornGesException;
import facade.handlers.ITorneioServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import presentation.fx.model.RegistarParticipanteModel;

public class RegistarParticipanteController extends BaseController{
	
	@FXML private TextField numeroInscricaoTF;
	@FXML private ListView<TorneioShortDTO> torneioLV;
	
	private RegistarParticipanteModel model;
	
	private ITorneioServiceRemote tornGesService;
	
	public void setTornGesService(ITorneioServiceRemote tornGesService) {
		this.tornGesService = tornGesService;
	}
	
	/**
	 * Faz bind de todas as propriedades dos atributos da classe
	 * @param model
	 */
	public void setModel(RegistarParticipanteModel model) {
		
		this.model = model;
		this.numeroInscricaoTF.textProperty().bindBidirectional(model.getNumeroInscricaoProperty(), new NumberStringConverter());
		this.torneioLV.setItems(model.getTorneios());
	}
	
	@FXML
	private void initialize() {
		UnaryOperator<Change> integerFilter = change -> {
			String newText = change.getControlNewText();
			if (newText.matches("[0-9]*")) { 
				return change;
			}
			return null;
		};

		this.numeroInscricaoTF.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),
				0, integerFilter));
	}
	/**
	 * Submete os dados e realiza a operacao registarParticipante definida no servico
	 * @param event
	 */
	@FXML
	public void registarParticipanteAction(ActionEvent event) {
		
		try {
			tornGesService.registarParticipante(model.getTorneioEscolhido().getDesignacaoTorneio(), model.getNumeroInscricao());
			
			showInfo("O jogador " + model.getNumeroInscricao() + " foi adicionado ao torneio " + model.getTorneioEscolhido().getDesignacaoTorneio());
			model.clearProperties();
		}catch(TornGesException e) {
			showError(i18nBundle.getString("registar.participante.error") + ":\n" + e.getMessage());
		}
	}
	
	@FXML
	public void torneioSelecionado(MouseEvent event) {
		model.setTorneioEscolhido(this.torneioLV.getSelectionModel().getSelectedItem());
	}
}
