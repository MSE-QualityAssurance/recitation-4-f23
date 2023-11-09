package business.interfaces;

import facade.exceptions.TornGesException;

public interface IRegistarParticipante {

	public void registarParticipante(String designacaoTorneio, int idParticipante) throws TornGesException;
}
