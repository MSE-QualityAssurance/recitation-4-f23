package presentation.web.model;

import facade.dto.EncontroDTO;

/**
 * Modelo para apresentacao no jsp do registo de um resultado
 * @author css003
 */
public class RegistarResultadoModel extends Model{

	// Atributos
	private String designacaoTorneio;
	private String numeroEncontro;
	private String resultadoEncontro;
	private Iterable<EncontroDTO> listaEncontros;
	private Iterable<String> listaDesfechos;
	
	// Atributos para verificacoes no jsp
	private String primeiroEncontro;
	private String primeiroDesfechoEncontro;
	
	/**
	 * Construtor vazio
	 */
	public RegistarResultadoModel() {
		// Does nothing
	}
	
	//getters and setters
	public String getDesignacaoTorneio() {
		
		return designacaoTorneio;
	}

	public void setDesignacaoTorneio(String designacaoTorneio) {
		
		this.designacaoTorneio = designacaoTorneio;
	}
	
	public String getNumeroEncontro() {
		
		return numeroEncontro;
	}
	
	public void setNumeroEncontro(String numeroEncontro) {
		
		this.numeroEncontro = numeroEncontro;
	}
	
	public String getResultadoEncontro() {
		
		return resultadoEncontro;
	}
	
	public void setResultadoEncontro(String resultadoEncontro) {
		
		this.resultadoEncontro = resultadoEncontro;
	}
	
	public Iterable<EncontroDTO> getListaEncontros() {
		
		return listaEncontros;
	}
	
	public void setListaEncontros(Iterable<EncontroDTO> listaEncontros) {
		
		this.listaEncontros = listaEncontros;
		
		if (this.isHasEncontros())
			this.primeiroEncontro = String.valueOf(listaEncontros.iterator().next().getNumeroEncontro());
	}
	
	public Iterable<String> getDesfechosEncontros() {
		
		return this.listaDesfechos;
	}

	public void setDesfechosEncontros(Iterable<String> todosDesfechos) {
		
		this.listaDesfechos = todosDesfechos;
		
		if (isHasDesfechosEncontros())
			this.primeiroDesfechoEncontro = todosDesfechos.iterator().next();
		else
			this.primeiroDesfechoEncontro = "";
	}

	public boolean isHasEncontros() {
		
		return listaEncontros.iterator().hasNext();
	}
	
	public boolean isHasDesfechosEncontros() {
		
		return this.listaDesfechos.iterator().hasNext();
	}
	
	public String getPrimeiroEncontro() {
		
		return this.primeiroEncontro;
	}
	
	public void setPrimeiroEncontro(String encontro) {
		
		this.primeiroEncontro = encontro;
	}
	
	public String getPrimeiroDesfechoEncontro() {
		
		return this.primeiroDesfechoEncontro;
	}
	
	public void setPrimeiroDesfechoEncontro(String desfecho) {
		
		this.primeiroDesfechoEncontro = desfecho;
	}
	
	public void clearFields() {
		
		this.designacaoTorneio = "";
		this.numeroEncontro = "";
		this.resultadoEncontro = "";
		this.primeiroDesfechoEncontro = "";
		this.primeiroEncontro = "";
		this.primeiroDesfechoEncontro = "";
	}
}
