package facade.dto;

import java.io.Serializable;

/**
 * Classe responsavel por carregar os dados entre as varias camadas.
 * Deste modo objetos da camada de business ou de acessos a dados nao
 * sao acedidos pela parte responsavel pela interacao com o utilizador.
 * @author css003
 */
public class TorneioShortDTO implements Serializable {

	// Versao de serie
	private static final long serialVersionUID = 8845139742485624339L;
	
	// Atributos
	private String designacaoTorneio;
	
	/**
	 * Construtor de um DTO para um Torneio.
	 */
	public TorneioShortDTO() {
		// Does nothing
	}
	
	/**
	 * Faz set da designacao do torneio
	 * @param designacaoTorneio
	 */
	public void setDesignacaoTorneio(String designacaoTorneio) {
		
		this.designacaoTorneio = designacaoTorneio;
	}
	
	/**
	 * @return Obtem a designacao do torneio
	 */
	public String getDesignacaoTorneio() {
		
		return this.designacaoTorneio;
	}
	
	/**
	 * Devolve uma representacao textual do objeto.
	 * ##DebugPurposes
	 */
	@Override
	public String toString() {
		
		return this.getDesignacaoTorneio();
	}
}
