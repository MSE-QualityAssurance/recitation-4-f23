package presentation.fx.model;

import java.time.LocalDate;

import facade.exceptions.TornGesException;
import facade.handlers.ITorneioServiceRemote;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Modelo da pagina para criar um torneio
 * @author css003
 */
public class CriarTorneioModel {
	
	// Atributos
	private final ObservableList<String> modalidades;
	private final StringProperty modalidadeEscolhida;
	private final StringProperty designacao;
	private final IntegerProperty numeroParticipantes;
	private final ObjectProperty<LocalDate> dataEscolhida;	
	private final IntegerProperty duracao;
	private final ObservableList<String> tiposTorneio;
	private final StringProperty tipoTorneioEscolhido;
	private final IntegerProperty numEncontros;
	
	public CriarTorneioModel(ITorneioServiceRemote tornGesService) throws TornGesException {
		
		this.modalidades = FXCollections.observableArrayList();
		this.modalidadeEscolhida = new SimpleStringProperty();
		this.designacao = new SimpleStringProperty();
		this.numeroParticipantes = new SimpleIntegerProperty();
		this.dataEscolhida = new SimpleObjectProperty<>();
		this.duracao = new SimpleIntegerProperty();
		this.tipoTorneioEscolhido = new SimpleStringProperty();
		this.tiposTorneio = FXCollections.observableArrayList();
		this.numEncontros = new SimpleIntegerProperty();
		
		tornGesService.criarTorneio().forEach(m->modalidades.add(m));
		tornGesService.getTiposTorneio().forEach(t-> tiposTorneio.add(t));
	}

	// Getters e Setters
	
	public IntegerProperty getNumEncontrosProperty() {
		
		return this.numEncontros;
	}
	
	public int getNumEncontros() {
		
		return this.numEncontros.get();
	}
	
	public ObservableList<String> getModalidades() {
		
		return modalidades;
	}


	public String getModalidadeEscolhida() {
		
		return modalidadeEscolhida.get();
	}


	public StringProperty getDesignacaoProperty() {
		
		return designacao;
	}
	
	public String getDesignacao() {
		
		return designacao.get();
	}


	public IntegerProperty getNumeroParticipantesProperty() {
		
		return numeroParticipantes;
	}

	public int getNumeroParticipantes() {
		
		return numeroParticipantes.get();
	}
	
	public ObjectProperty<LocalDate> getDataEscolhidaProperty() {
		
		return dataEscolhida;
	}

	public LocalDate getDataEscolhida() {
		
		return dataEscolhida.get();
	}

	public IntegerProperty getDuracaoProperty() {
		
		return duracao;
	}

	public int getDuracao() {
		return duracao.get();
	}

	public ObservableList<String> getTiposTorneio() {
		
		return tiposTorneio;
	}

	public String getTipoTorneioEscolhido() {
		return tipoTorneioEscolhido.get();
	}
	
	public void setModalidadeEscolhida(String value) {
		
		this.modalidadeEscolhida.set(value);
	}
	
	public void setTipoTorneioEscolhido(String value) {
		
		this.tipoTorneioEscolhido.set(value);
	}
	
	public void clearProperties() {
		
		this.designacao.set("");
		this.numeroParticipantes.set(0); 
		this.duracao.set(0);
		this.numEncontros.set(0);
	}
}
