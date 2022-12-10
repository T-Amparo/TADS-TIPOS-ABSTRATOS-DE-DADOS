package TADS.TAD_Arvore.TAD_Arvore_Generica.Fontes;

import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.Position;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.PositionList;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.TreePosition;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementa��o do nodo que compoe a �RVORE GENERICA.
public class TreeNode<TYPE> implements TreePosition<TYPE> {
	
	//Declara��o de Variaveis.
	private TYPE element; //Armazena o elemento contido no nodo.
	private TreePosition<TYPE> parent; //Armazena � referencia ao pai do nodo.
	private PositionList<Position<TYPE>> children; //Armazena uma lista com �s referencias aos filhos do nodo.

	//M�todo Construtor.
	public TreeNode() {}

	//M�todo Construtor.
	public TreeNode(TYPE element, TreePosition<TYPE> parent, PositionList<Position<TYPE>> children) {
	
		this.element = element; //Inicia o elemento com o valor informado.
		this.parent = parent; //Inicia � referencia ao pai do nodo com o valor informado.
		this.children = children; //Inicia � lista com �s referencias aos filhos do nodo com o valor informado.
	}

	//Retorna o elemento contido no nodo.
	public TYPE element() {return element;}
	
	//Retorna o elemento contido no nodo.
	public TYPE getElement() {return element;}

	//Define o elemento contido no nodo.
	public void setElement(TYPE element) {this.element = element;}	

	//Retorna a lista com �s referencias aos filhos do nodo.
	public PositionList<Position<TYPE>> getChildren() {return children;}

	//Define a lista com �s referencias aos filhos do nodo.
	public void setChildren(PositionList<Position<TYPE>> children) {this.children = children;}

	//Retorna a referencia ao pai do nodo.
	public TreePosition<TYPE> getParent() {return parent;}

	//Define � referencia ao pai do nodo.
	public void setParent(TreePosition<TYPE> parent) {this.parent = parent;}
	
}