package presentation.web.model;

import facade.dto.VisualizarEncontroDTO;

public class VerConfrontosModel extends Model {
	
	//Atributos
	private String numeroInscricao;
	private String data;
	private Iterable<VisualizarEncontroDTO> confrontos;
	
	//getters and setters
	public String getNumeroInscricao() {
		return this.numeroInscricao;
	}
	
	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}
	
	public String getData() {
		
		return this.data;
	}
	
	public void setData(String data) {
		
		this.data = data;
	}
	
	public boolean isHasConfrontos() {
		
		return this.confrontos.iterator().hasNext();
	}
	
	public void setConfrontos(Iterable<VisualizarEncontroDTO> confrontos) {
		this.confrontos = confrontos;
	}
	
	public Iterable<VisualizarEncontroDTO> getConfrontos(){
		return this.confrontos;
	}
	
	public void clearFields() {
		numeroInscricao = "";
		data = "";
	}
}
