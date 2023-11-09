package presentation.web.model;

import facade.dto.JogadorShortDTO;

public class VisualizarCalendarioModel extends Model {
	
	//Atributos
	private String numeroInscricao;
	private String data;
	private Iterable<JogadorShortDTO> jogadores;
	private String primeiro;

	public VisualizarCalendarioModel() {
		// Does nothing
	}
	
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
	
	public Iterable<JogadorShortDTO> getJogadores() {
		return this.jogadores;
	}
	
	public void setJogadores(Iterable<JogadorShortDTO> jogadores) {
		this.jogadores = jogadores;
		
		if (jogadores.iterator().hasNext())
			this.primeiro = String.valueOf(jogadores.iterator().next().getId());
	}
	
	public boolean isHasJogadores() {
		
		return this.jogadores.iterator().hasNext();
	}
	
	public String getPrimeiro() {
		
		return this.primeiro;
	}
	
	public void clearFields() {
		numeroInscricao = "";
		data = "";
	}
}
