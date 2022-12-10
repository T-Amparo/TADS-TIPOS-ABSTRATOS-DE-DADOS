package TADS.TAD_Arvore.TAD_Arvore_Binaria_Heap.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para o TAD ÁRVORE BINARIA COMPLETA.
public interface CompleteBinaryTree<TYPE> extends BinaryTree<TYPE> {
	
	//Adiciona um elemento após o ultimo nodo da árvore.
	public Position<TYPE> add(TYPE element);
	
	//Remove e retorna o elemento armazenado no último nodo da árvoere.
	public TYPE remove();

}