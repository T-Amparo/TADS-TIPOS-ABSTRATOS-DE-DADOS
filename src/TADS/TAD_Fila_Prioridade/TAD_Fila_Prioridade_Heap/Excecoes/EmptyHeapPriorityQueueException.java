package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exce��o de Fila de prioridade vazia.
@SuppressWarnings("serial")
public class EmptyHeapPriorityQueueException extends RuntimeException {
	
	public EmptyHeapPriorityQueueException(String error) {super(error);}

}