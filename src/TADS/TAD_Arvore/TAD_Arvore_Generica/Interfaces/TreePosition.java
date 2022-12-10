package TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para � �RVORE GENERICA.
public interface TreePosition<TYPE> extends Position<TYPE>{	
	
	//Retorna o elemento que est� armazenado em uma dada posi��o da �RVORE.
	public TYPE getElement();
	
	//Define o elemento que ser� armazenado em uma dada posi��o da �RVORE.
	public void setElement(TYPE element);

	//Retorna uma lista de filhos de uma dada posi��o da �RVORE.
	public PositionList<Position<TYPE>> getChildren();

	//Define os filhos de uma dada posi��o da �RVORE.
	public void setChildren(PositionList<Position<TYPE>> children);

	//Retorna o pai de uma dada posi��o da �RVORE.
	public TreePosition<TYPE> getParent();

	//Define o pai de uma dada posi��o da �RVORE.
	public void setParent(TreePosition<TYPE> parent);
	
}