package business.interfaces;

import java.util.List;

import facade.exceptions.TornGesException;

public interface ICriarTorneio {
	
	/**
	 * Devolve uma lista com os nomes das modalidades disponiveis
	 * @return A lista com os nomes das modalidades disponiveis
	 */
	public List<String> criaTorneio();
	
	/**
	 * Cria e insere os dados num torneio
	 * @param m O nome da modalidade 
	 * @param d A designacao do torneio a criar
	 * @param t O tipo de torneio 
	 * @param nP O numero de participantes
	 * @param nE O numero de encontros
	 * @param dia O dia em que este se realiza
	 * @param mes O mes em que este se realiza
	 * @param ano O ano em que este se realiza
	 * @param dD A duracao em dias deste torneio
	 * @throws TornGesException Quando nao é possivel a criacao do novo torneio
	 */
	public void inserirDadosTorneio(String m, String d, String t, int nP,
			int nE, int dia, int mes, int ano, int dD) throws TornGesException;
}
