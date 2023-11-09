package business.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import facade.dto.JogadorDTO;
import facade.dto.JogadorShortDTO;
import facade.dto.VisualizarEncontroDTO;
import business.CatalogoEquipas;
import business.CatalogoJogadores;
import business.CatalogoTorneios;
import business.Confronto;
import business.Equipa;
import business.Jogador;
import business.Torneio;
import business.interfaces.IVisualizarCalendario;
import facade.exceptions.TornGesException;

/**
 * Handler responsavel pelo caso de uso de visualizacao do calendario
 * de um determinado jogador
 * @author css003
 */
@Stateless
public class VisualizarCalendarioHandler  {

	// Constantes
	private static final String DATE_PATTERN = "dd-MM-yyyy";
	private static final String ERROR_MSG_INVALID_DATA = "A data inserida encontra-se invalida.";
	private static final String ERROR_MSG_NUM_EQUIPA = "Numero de inscricao pertence a uma equipa";
	private static final String ERROR_MSG_NO_PLAYER = "\"Nao existe um jogador associado a %d";
	
	// Catalogos
	@EJB
	private CatalogoEquipas equipas;
	
	@EJB
	private CatalogoTorneios torneios;
	
	@EJB
	private CatalogoJogadores jogadores;
	
	/**
	 * Construtor
	 */
	public VisualizarCalendarioHandler() {
		// Does nothing
	}
	
	/**
	 * @see IVisualizarCalendario#verConfrontos(int, Calendar)
	 */
	public List<VisualizarEncontroDTO> verConfrontos(int numeroInscricao, String data) throws TornGesException {
		
		SimpleDateFormat format = new SimpleDateFormat(VisualizarCalendarioHandler.DATE_PATTERN);
		Calendar c = Calendar.getInstance();
		
		try {
			c.setTime(format.parse(data));
			
		} catch(ParseException e) {
			
			throw new TornGesException(VisualizarCalendarioHandler.ERROR_MSG_INVALID_DATA, e);
		}
		
		List<VisualizarEncontroDTO> resultado = new ArrayList<>();
		
		// Obtencao do jogador pretendido
		Jogador jogadorCorrente = obterJogadorCorrente(numeroInscricao); 
				
		List<Torneio> listaTorneios = this.torneios.getTorneiosOfPlayer(numeroInscricao);
		
		// Para cada torneio em que o jogador corrente joga
		for (Torneio torneio : listaTorneios) {
			// Verifico se a data eh posterior a fornecida
			if (torneio.getDataInicio().after(c)) {
										
				List<Confronto> listaConfrontos = torneio.getConfrontosJogador(jogadorCorrente);
			
				// Para cada confronto em que o jogador corrente participa
				for (Confronto confrontoAtual : listaConfrontos) {
					
					// Obtenho os adversarios e quantidade de encontros
					Map<Jogador, Integer> adversarioQtdEncontros = confrontoAtual.obterAdversarioEncontros(jogadorCorrente);
					
					// -------- Adicionar para a interface --------
					criaVisualizarEncontroDTO(resultado, torneio, jogadorCorrente, adversarioQtdEncontros);
					// --------------------------------------------
				}
			}
		}
		
		Collections.sort(resultado, VisualizarEncontroDTO.comparator());
		
		return resultado;
	}

	/**
	 * Metodo privado que realiza as preparacoes das informacoes para a camada de 
	 * apresentacao.
	 * @param torneio torneio em que ocorrem os encontros
	 * @param jogadorCorrente jogador corrente que participa nos encontros
	 * @param adversarioQtdEncontros mapa com os adversarios e qtd de encontros
	 * @requires torneio != null && jogadorCorrente != null && adversarioQtdEncontros != null
	 * @return um VisualizarEncontroDTO preparado para a camada de apresentacao
	 */
	private void criaVisualizarEncontroDTO(List<VisualizarEncontroDTO> resultado, 
			Torneio torneio, Jogador jogadorCorrente, Map<Jogador, Integer> adversarioQtdEncontros) {

		JogadorDTO correnteDTO = new JogadorDTO(jogadorCorrente.getNome(), jogadorCorrente.getPontos());
		
		for (Entry<Jogador, Integer> entry : adversarioQtdEncontros.entrySet()) {
			
			Jogador jogador = entry.getKey();
			JogadorDTO jogadorAdversario = new JogadorDTO(jogador.getNome(), jogador.getPontos());
			
			VisualizarEncontroDTO veDTO = new VisualizarEncontroDTO(torneio.getDesignacao(), torneio.getDataInicio(), 
					correnteDTO, jogadorAdversario, entry.getValue());
			
			resultado.add(veDTO);
		}
	}

	/**
	 * Metodo privado que obtem o objeto jogador corrente caso este se encontre
	 * associado a numeroInscricao
	 * @param numeroInscricao numeroInscricao do jogador
	 * @return O jogador caso este exista.
	 * @throws TornGesException caso o numero de inscricao seja associado a uma
	 * equipa ou nao exista um jogador associado a este.
	 */
	private Jogador obterJogadorCorrente(int numeroInscricao) throws TornGesException {

		List<Equipa> existeEquipa = this.equipas.findEquipaById(numeroInscricao);
		
		// Se o numeroInscricao inserido for de uma equipa
		if (!existeEquipa.isEmpty())
			throw new TornGesException(ERROR_MSG_NUM_EQUIPA);
		
		List<Jogador> existeJogador = this.jogadores.getJogadorById(numeroInscricao);
		
		// Verificar se existe o numeroInscricao de jogador entao
		if (existeJogador.isEmpty())
			throw new TornGesException(String.format(ERROR_MSG_NO_PLAYER, numeroInscricao));

		return existeJogador.get(0);
	}
	
	public List<JogadorShortDTO> getJogadores(){
		
		List<JogadorShortDTO> jogadoresShort = new ArrayList<>();
		
		List<Jogador> jogadores = this.jogadores.getAllJogadores();
		
		for(Jogador j : jogadores) {
			jogadoresShort.add(new JogadorShortDTO(j.getId(), j.getNome()));
		}
		return jogadoresShort;
	}
}
