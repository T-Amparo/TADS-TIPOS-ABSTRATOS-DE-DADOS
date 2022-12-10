package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para o TAD �RVORE BINARIA COMPLETA.
public interface CompleteBinaryTree<TYPE> extends BinaryTree<TYPE> {
	
	//Adiciona um elemento ap�s o ultimo nodo da �rvore.
	public Position<TYPE> add(TYPE element);
	
	//Remove e retorna o elemento armazenado no �ltimo nodo da �rvoere.
	public TYPE remove();

}