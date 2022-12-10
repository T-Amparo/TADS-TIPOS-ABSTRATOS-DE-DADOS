package TADS.TAD_Fila.TAD_Fila_LSE.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção de Fila vazia.
@SuppressWarnings("serial")
public class EmptyQueueExceptionNodeQueue extends RuntimeException {
	
	public EmptyQueueExceptionNodeQueue(String error) {super(error);}

}