package presentation.fx.inputcontroller;

import java.time.LocalDate;
import java.util.function.UnaryOperator;

import facade.exceptions.TornGesException;
import facade.handlers.ITorneioServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import presentation.fx.model.CriarTorneioModel;


public class CriarTorneioController extends BaseController {
	
	//Atributos
	@FXML private TextField duracaoTF;
	@FXML private TextField designacaoTorneioTF;
	@FXML private TextField numeroParticipantesTF;
	@FXML private TextField numEncontros;
	@FXML private ComboBox<String> tipoTorneioCB;
	@FXML private ComboBox<String> modalidadeCB;
	@FXML private DatePicker dataDP;
	
	private CriarTorneioModel model;
	
	private ITorneioServiceRemote tornGesService;
	
	public void setTornGesService(ITorneioServiceRemote tornGesService) {
		this.tornGesService = tornGesService;
	}
	
	/**
	 * Faz bind de todas as propriedades dos atributos da classe
	 * @param model
	 */
	public void setModel(CriarTorneioModel model) {
		
		this.model = model;
		this.duracaoTF.textProperty().bindBidirectional(model.getDuracaoProperty(), new NumberStringConverter());
		this.designacaoTorneioTF.textProperty().bindBidirectional(model.getDesignacaoProperty());
		this.numeroParticipantesTF.textProperty().bindBidirectional(model.getNumeroParticipantesProperty(), new NumberStringConverter());
		this.numEncontros.textProperty().bindBidirectional(model.getNumEncontrosProperty(), new NumberStringConverter());
		this.tipoTorneioCB.setItems(model.getTiposTorneio());
		this.tipoTorneioCB.setValue(model.getTipoTorneioEscolhido());
		this.modalidadeCB.setItems(model.getModalidades());
		this.modalidadeCB.setValue(model.getModalidadeEscolhida());
		this.dataDP.valueProperty().bindBidirectional(model.getDataEscolhidaProperty());
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

		this.duracaoTF.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),
				0, integerFilter));
		this.numeroParticipantesTF.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),
				0, integerFilter));
		this.numEncontros.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),
				0, integerFilter));
	}
	
	/**
	 * Submete os dados e realiza a operacao de inserirDadosTorneio definida no servico
	 * @param event O evento
	 */
	@FXML
	public void criarTorneioAction(ActionEvent event) {
		
		String errorMessages = validateInput();
	
		if(errorMessages.isEmpty()) {
			
			try {
				LocalDate ld = model.getDataEscolhida();
				
				tornGesService.inserirDadosTorneio(model.getModalidadeEscolhida(), 
						model.getDesignacao(), model.getTipoTorneioEscolhido(), model.getNumeroParticipantes(), 
						model.getNumEncontros(),ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear(), model.getDuracao());
				
				showInfo("Torneio " + model.getDesignacao() + " criado com sucesso!");
				model.clearProperties();
				
			}catch(TornGesException e) {
				showError(i18nBundle.getString("criar.torneio.error.validating") + ":\n" + e.getMessage());
			}
		}
		else{
			showError(i18nBundle.getString("criar.torneio.error.validating") + ":\n" + errorMessages);
		}
	}
	/**
	 * Valida o input e coloca as mensagens de erro numa String
	 * @return String com os erros encontrados
	 */
	private String validateInput() {
		
		StringBuilder messages = new StringBuilder();
		if(model.getModalidadeEscolhida() == null)
			messages.append(i18nBundle.getString("criar.torneio.modalidade.invalida")+"\n");
		if(model.getTipoTorneioEscolhido() == null)
			messages.append(i18nBundle.getString("criar.torneio.tipo.torneio.invalido")+"\n");
		if(model.getDuracao() <= 0)
			messages.append(i18nBundle.getString("criar.torneio.duracao.invalida")+"\n");
		
		if(model.getNumEncontros() <= 0)
			messages.append(i18nBundle.getString("criar.torneio.numEncontros.invalido")+"\n");
		if(model.getDataEscolhida() == null) 
			messages.append(i18nBundle.getString("criar.torneio.data.invalida")+"\n");
		
		return messages.toString();
	}

	@FXML
	void modalidadeSelecionada(ActionEvent event) {
		model.setModalidadeEscolhida(this.modalidadeCB.getValue());
	}
	
	@FXML
	void tipoTorneioSelecionado(ActionEvent event) {
		model.setTipoTorneioEscolhido(this.tipoTorneioCB.getValue());
	}
}
