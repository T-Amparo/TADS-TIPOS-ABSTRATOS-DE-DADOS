package TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para à ÁRVORE GENERICA.
public interface TreePosition<TYPE> extends Position<TYPE>{	
	
	//Retorna o elemento que está armazenado em uma dada posição da ÁRVORE.
	public TYPE getElement();
	
	//Define o elemento que será armazenado em uma dada posição da ÁRVORE.
	public void setElement(TYPE element);

	//Retorna uma lista de filhos de uma dada posição da ÁRVORE.
	public PositionList<Position<TYPE>> getChildren();

	//Define os filhos de uma dada posição da ÁRVORE.
	public void setChildren(PositionList<Position<TYPE>> children);

	//Retorna o pai de uma dada posição da ÁRVORE.
	public TreePosition<TYPE> getParent();

	//Define o pai de uma dada posição da ÁRVORE.
	public void setParent(TreePosition<TYPE> parent);
	
}