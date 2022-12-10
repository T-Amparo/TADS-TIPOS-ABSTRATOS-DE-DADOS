package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces;

import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Excecoes.EmptyHeapPriorityQueueException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Excecoes.InvalidKeyException;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para o TAD FILA DE PRIORIDADE.
public interface PriorityQueue<KEY, VALUE> {	
	
	//Retorna um inteiro com a quantidade de elementos contidos na FILA DE PRIORIDADE.
	public int size();
	
	//Retorna um valor booleano representando o status da FILA DE PRIORIDADE => True=Vazia, False=Não Vazia.
	public boolean isEmpty();
	
	//Retorna uma entrada com a menor chave, sem remove-la.
	public EntryHeapPriorityQueue<KEY, VALUE> min() throws EmptyHeapPriorityQueueException;
	
	//Adiciona uma entrada(key, value) e retorna a entrada criada.
	public EntryHeapPriorityQueue<KEY, VALUE> insert(KEY key, VALUE value) throws InvalidKeyException;
	
	//Remove e retorna uma entrada(key, value) com a menor chave.
	public EntryHeapPriorityQueue<KEY, VALUE> removeMin() throws EmptyHeapPriorityQueueException;

}