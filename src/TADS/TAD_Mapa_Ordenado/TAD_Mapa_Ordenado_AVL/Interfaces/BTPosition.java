package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para a posi��o do nodo contido na �RVORE BIN�RIA.
public interface BTPosition<TYPE> extends Position<TYPE> {
	
	//Retorna o elemento que est� armazenado em uma dada posi��o da �RVORE.
	public TYPE element();
	
	//Define o elemento que ser� armazenado em uma dada posi��o da �RVORE.
	public void setElement(TYPE element);
	
	//Retorna o filho � esquerda de um dado nodo.
	public BTPosition<TYPE> getLeft();
	
	//Define o filho � esquerda de um dado nodo.
	public void setLeft(BTPosition<TYPE> position);
	
	//Retorna o filho � direita de um dado nodo.
	public BTPosition<TYPE> getRight();
	
	//Define o filho � direita de um dado nodo.
	public void setRight(BTPosition<TYPE> position);
	
	//Retorna o pai de um dado nodo.
	public BTPosition<TYPE> getParent();
	
	//Define o pai de um dado nodo.
	public void setParent(BTPosition<TYPE> position);
	
}