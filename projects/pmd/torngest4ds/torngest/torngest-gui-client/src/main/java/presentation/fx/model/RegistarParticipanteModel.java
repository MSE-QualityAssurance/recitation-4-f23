package presentation.fx.model;

import facade.dto.TorneioShortDTO;
import facade.exceptions.TornGesException;
import facade.handlers.ITorneioServiceRemote;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Modelo para registar um participante
 * @author css003
 */
public class RegistarParticipanteModel {

	// Atributos
	private final ObservableList<TorneioShortDTO> torneios;
	private final ObjectProperty<TorneioShortDTO> torneioEscolhido;
	private final IntegerProperty numeroInscricao;
	
	public RegistarParticipanteModel(ITorneioServiceRemote tornGesService) throws TornGesException {
		
		this.numeroInscricao = new SimpleIntegerProperty();
		this.torneios = FXCollections.observableArrayList();
		this.torneioEscolhido = new SimpleObjectProperty<>();

		tornGesService.getAllTorneios().forEach(t -> this.torneios.add(t));
	}

	// Getters e Setters
	
	public ObservableList<TorneioShortDTO> getTorneios() {
		return torneios;
	}

	public IntegerProperty getNumeroInscricaoProperty() {
		return numeroInscricao;
	}
	
	public int getNumeroInscricao() {
		return numeroInscricao.get();
	}
	
	public void clearProperties() {
		
		this.numeroInscricao.set(0);		
	}

	public final TorneioShortDTO getTorneioEscolhido() {
		return this.torneioEscolhido.get();
	}
	
	public final void setTorneioEscolhido(TorneioShortDTO value) {
		this.torneioEscolhido.set(value);
	}
}
