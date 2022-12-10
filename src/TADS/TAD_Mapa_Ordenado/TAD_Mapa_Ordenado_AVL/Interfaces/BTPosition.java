package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para a posição do nodo contido na ÁRVORE BINÁRIA.
public interface BTPosition<TYPE> extends Position<TYPE> {
	
	//Retorna o elemento que está armazenado em uma dada posição da ÁRVORE.
	public TYPE element();
	
	//Define o elemento que será armazenado em uma dada posição da ÁRVORE.
	public void setElement(TYPE element);
	
	//Retorna o filho à esquerda de um dado nodo.
	public BTPosition<TYPE> getLeft();
	
	//Define o filho à esquerda de um dado nodo.
	public void setLeft(BTPosition<TYPE> position);
	
	//Retorna o filho à direita de um dado nodo.
	public BTPosition<TYPE> getRight();
	
	//Define o filho à direita de um dado nodo.
	public void setRight(BTPosition<TYPE> position);
	
	//Retorna o pai de um dado nodo.
	public BTPosition<TYPE> getParent();
	
	//Define o pai de um dado nodo.
	public void setParent(BTPosition<TYPE> position);
	
}