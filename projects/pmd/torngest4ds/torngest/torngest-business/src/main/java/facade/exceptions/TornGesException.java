package facade.exceptions;

public class TornGesException extends Exception{
	
	
	private static final long serialVersionUID = 1903337251239160172L;

	public TornGesException(String message) {
		super(message);
	}
	/**
	 * Permite embrulhar excecoes de baixo nivel
	 * @param message A mensagem que vai complementar a excecao	
	 * @param e Excecao que venha de camadas inferiores
	 */
	public TornGesException(String message, Exception e) {
		super(message, e);
	}
}
