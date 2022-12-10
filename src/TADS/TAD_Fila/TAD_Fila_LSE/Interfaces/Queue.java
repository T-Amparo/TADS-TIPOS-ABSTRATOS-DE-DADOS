package TADS.TAD_Fila.TAD_Fila_LSE.Interfaces;

import TADS.TAD_Fila.TAD_Fila_Arranjo.Excecoes.EmptyQueueExceptionArrayQueue;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para o TAD FILA USANDO LSE.
public interface Queue<TYPE> {	
	
	//Retorna um inteiro com a quantidade de elementos contidos na FILA.
	public int size();
	
	//Retorna um valor booleano representando o status da FILA => True=Vazia, False=Não Vazia.
	public boolean isEmpty();
	
	//Retorna o elemento à frente da FILA, sem remove-lo.
	public TYPE front() throws EmptyQueueExceptionArrayQueue;
	
	//Adiciona um 'element=elemento' no final da FILA.
	public void enqueue(TYPE element);
	
	//Remove e retorna o elemento à frente da FILA.
	public TYPE dequeue() throws EmptyQueueExceptionArrayQueue;

}