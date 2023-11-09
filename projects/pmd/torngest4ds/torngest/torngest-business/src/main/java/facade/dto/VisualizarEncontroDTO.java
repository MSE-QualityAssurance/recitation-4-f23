package facade.dto;

import java.util.Calendar;
import java.util.Comparator;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Classe responsavel por carregar os dados entre as varias camadas.
 * Deste modo objetos da camada de business ou de acessos a dados nao
 * sao acedidos pela parte responsavel pela interacao com o utilizador.
 * @author css003
 */
public class VisualizarEncontroDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4530549823553964628L;

	// Constantes
	private static final String NAO_EXISTEM_CONFRONTOS = "Nao existem encontros associados ao jogador.";
		
	// Atributos
	private String designacaoTorneio;
	private String dataInicio;
	private JogadorDTO jogadorCorrente;
	private JogadorDTO jogadorAdversario;
	private int qtdEncontros;
	
	/**
	 * Construtor de um VisualizarEncontroDTO
	 * @param desigTorneio designacao do torneio
	 * @param dataInicio data de inicio do torneio
	 * @param jogador jogador que participa nos encontros do torneio
	 * @param adversarioQtdEncontros adversario do jogador nos encontros
	 * @requires desigTorneio != null && dataInicio != null && jogador != null
	 * && adversarioQtdEncontros != null
	 */
	public VisualizarEncontroDTO(String desigTorneio, Calendar dataInicio,
			JogadorDTO jogador, JogadorDTO advers, int qtdEncontros) {
		
		this.designacaoTorneio = desigTorneio;
		this.dataInicio = convertCalendarToString(dataInicio);		
		this.jogadorCorrente = jogador;
		this.jogadorAdversario = advers;
		this.qtdEncontros = qtdEncontros;
	}
	
	public String getTorneio() {
		return designacaoTorneio;
	}

	public void setTorneio(String designacaoTorneio) {
		this.designacaoTorneio = designacaoTorneio;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = convertCalendarToString(dataInicio);
	}

	public JogadorDTO getJogador() {
		return jogadorCorrente;
	}

	public void setJogador(JogadorDTO jogadorCorrente) {
		this.jogadorCorrente = jogadorCorrente;
	}
	
	public int getQuantidadeEncontros() {
		
		return this.qtdEncontros;
	}
	
	public void setQuantidadeEncontros(int qtdEncontros) {
		
		this.qtdEncontros = qtdEncontros;
	}
	
	public JogadorDTO getJogadorAdversario() {
		
		return this.jogadorAdversario;
	}
	
	public void setJogadorAdversario(JogadorDTO adv) {
		
		this.jogadorAdversario = adv;
	}
	
	public String getDiferencaPontos() {
		
		StringBuilder sb = new StringBuilder();

		double diferenca = jogadorCorrente.getPontos() - this.jogadorAdversario.getPontos();
		
		if (diferenca < 0.0)
			sb.append((Math.round(diferenca * 100) / 100.0) + "\n");
			
		else
			sb.append("+" + (Math.round(diferenca * 100) / 100.0));
		
		return sb.toString();
	}
	
	/**
	 * Funcao que constroi a mensagem para ser visualizada pelo utilizador
	 * @return Uma mensagem no formato:
	 * Torneio | DataInicio | NomeAdversario | QtdEncontros | DiferencaPontos
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        String dataInicioFormatada = this.dataInicio;
			
		sb.append(this.designacaoTorneio + " | ");
		sb.append(dataInicioFormatada + " | ");
		sb.append(this.jogadorAdversario.getNome() + "\t| ");
		sb.append(this.qtdEncontros + " | ");
			
			
		double diferenca = jogadorCorrente.getPontos() - this.jogadorAdversario.getPontos();
			
		if (diferenca < 0.0)
			sb.append((Math.round(diferenca * 100) / 100.0) + "\n");
			
		else
			sb.append("+" + (Math.round(diferenca * 100) / 100.0));

		if (sb.length() == 0)
			sb.append(VisualizarEncontroDTO.NAO_EXISTEM_CONFRONTOS);
		
		return sb.toString();
	}
	
	/**
	 * Realiza a comparacao entre dois VisualizarEncontroDTO. O fator decisivo corresponde
	 * ah data de inicio de um torneio
	 */
	public static Comparator<VisualizarEncontroDTO> comparator() {
		
		return (d1,d2) -> d1.dataInicio.compareTo(d2.dataInicio);
	}
	
	private String convertCalendarToString(Calendar c) {
		
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		return format1.format(c.getTime());
	}
}
