package TADS.TAD_Fila.TAD_Fila_Arranjo.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exce��o de Fila vazia.
@SuppressWarnings("serial")
public class EmptyQueueExceptionArrayQueue extends RuntimeException {
	
	public EmptyQueueExceptionArrayQueue(String error) {super(error);}

}