package facade.handlers;

import javax.ejb.Remote;

import facade.dto.EncontroDTO;
import facade.dto.TorneioShortDTO;
import facade.exceptions.TornGesException;

/**
 * Interface para ser utilizada pelos servicos remota
 * @author css003
 */
@Remote
public interface ITorneioServiceRemote {
			
	/**
	 * Devolve um iteravel de strings que corresponde aos nomes das modalidades que 
	 * existem no servico do tornges
	 * @throws TornGesException
	 */
	public Iterable<String> criarTorneio() throws TornGesException;

	/**
	 * Funcao que insere e regista os dados de um torneio
	 * @throws TornGesException
	 */
	public void inserirDadosTorneio(String string, String tornA, String string2, int i, int j, int k, int l, int m,
			int n) throws TornGesException;

	/**
	 * Devolve os encontros associados a um determinado torneio
	 * @param designacaoTorneio designacao do torneio
	 * @throws TornGesException
	 */
	public Iterable<EncontroDTO> getEncontrosTorneio(String designacaoTorneio) throws TornGesException;
	
	/**
	 * Funcao que regista um participante num determinado torneio
	 * @param tornA torneio em que o participante vai participar
	 * @param i numero de inscricao do participar
	 * @throws TornGesException caso ocorra um erro
	 */
	public void registarParticipante(String tornA, int i) throws TornGesException;

	/**
	 * Funcao que devolve todos os torneios existentes no servico tornges
	 * @throws TornGesException
	 */
	public Iterable<TorneioShortDTO> getAllTorneios() throws TornGesException;
	
	/**
	 * Funcao adiciona um resultado de um encontro a um torneio
	 * @param designacaoTorneio designacao do torneio
	 * @param numEncontro numero do encontro do torneio
	 * @param resultado resultado do encontro
	 * @throws TornGesException caso nao seja possivel adicionar o resultado do
	 * encontro ao torneio
	 */
	public void adicionaResultado(String designacaoTorneio, int numEncontro, String resultado) throws TornGesException;
	
	public Iterable<String> getTiposTorneio() throws TornGesException;
	
	public Iterable<String> getAllDesfechos() throws TornGesException;
}
