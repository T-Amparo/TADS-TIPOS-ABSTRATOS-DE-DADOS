package TADS.TAD_Fila.TAD_Fila_Arranjo.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exce��o de Fila cheia.
@SuppressWarnings("serial")
public class FullQueueException extends RuntimeException {
	
	public FullQueueException(String error) {super(error);}

}