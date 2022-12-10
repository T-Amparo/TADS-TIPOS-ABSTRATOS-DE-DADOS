package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção de Fila de prioridade vazia.
@SuppressWarnings("serial")
public class EmptyPriorityQueueException extends RuntimeException {
	
	public EmptyPriorityQueueException(String error) {super(error);}

}