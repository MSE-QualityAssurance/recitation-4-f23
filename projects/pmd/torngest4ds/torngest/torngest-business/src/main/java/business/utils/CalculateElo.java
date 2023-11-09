package business.utils;

import business.DesfechoEncontro;

/**
 * Classe auxiliar responsavel por realizar o calculo do elo
 * @author css003
 */
public class CalculateElo {
	
	private static final int K = 32;
	
	/**
	 * Funcao para calcular o elo dos jogadores apos um encontro
	 * @param points1 Os pontos do jogador1
	 * @param points2 Os pontos do jogador 2
	 * @param desForP1 O desfecho do encontro
	 * @return Um vetor com os novos pontos calculados atraves do sistema de elo
	 */
	public static double[] calculateElo(double points1, double points2, DesfechoEncontro desForP1) {
		
		double r1 = Math.pow(10, points1 / 400);
		double r2 = Math.pow(10, points2 / 400);
		
		
		double e1 = r1 / (r1 + r2);
		double e2 = r2 / (r1 + r2);
		
		double s1 = desForP1.getValorDesfecho();
		double s2;
		
		if (desForP1 == DesfechoEncontro.EMPATE)
			s2 = DesfechoEncontro.EMPATE.getValorDesfecho();
		else
			s2 = desForP1 == DesfechoEncontro.VITORIA ? 
					DesfechoEncontro.DERROTA.getValorDesfecho() : DesfechoEncontro.VITORIA.getValorDesfecho();
		
		double[] result = new double[2];
		
		result[0] = Math.round( (points1 + K * (s1 - e1)) * 100) / 100.0;
		result[1] = Math.round( (points2 + K * (s2 - e2)) * 100) / 100.0;
		
		return result;
	}
}
