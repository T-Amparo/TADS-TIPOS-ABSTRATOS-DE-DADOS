package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para a entrada do TAD FILA DE PRIORIDADE.
public interface EntryHeapPriorityQueue<KEY, VALUE> {
	
	//Retorna a chave pertecente uma dada entrada.
	public KEY getKey();
	
	//Retorna o valor pertecente uma dada entrada.
	public VALUE getValue();
	
}