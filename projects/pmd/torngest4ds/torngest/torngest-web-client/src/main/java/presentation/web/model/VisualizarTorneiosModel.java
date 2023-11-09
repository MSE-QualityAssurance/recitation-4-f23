package presentation.web.model;

import facade.dto.TorneioShortDTO;

public class VisualizarTorneiosModel extends Model {
	
	//Atributos
	private Iterable<TorneioShortDTO> listaTorneios;
	private String designacaoTorneio;
	private String primeiroTorneio;
	
	public VisualizarTorneiosModel() {
		//Does nothing
	}
	
	//getters and setters
	public void setListaTorneios(Iterable<TorneioShortDTO> listaTorneios) {
		
		this.listaTorneios = listaTorneios;
		
		if (listaTorneios.iterator().hasNext())
			this.primeiroTorneio = listaTorneios.iterator().next().getDesignacaoTorneio();
	}
	
	public Iterable<TorneioShortDTO> getListaTorneios() {
		
		return this.listaTorneios;
	}
	
	public void setDesignacaoTorneio(String designacaoTorneio) {
		
		this.designacaoTorneio = designacaoTorneio;
	}
	
	public String getDesignacaoTorneio() {
		
		return this.designacaoTorneio;
	}
	
	public boolean isHasTorneios() {
		
		return this.listaTorneios.iterator().hasNext();
	}
	
	public String getPrimeiroTorneio() {
		
		return this.primeiroTorneio;
	}
	
	public void clearFields() {
		
		this.designacaoTorneio = "";
		this.primeiroTorneio = "";
	}
}
