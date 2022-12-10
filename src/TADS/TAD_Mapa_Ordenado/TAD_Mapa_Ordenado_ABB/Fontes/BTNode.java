package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Fontes;

import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces.BTPosition;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementa��o do nodo duplo que compoe a �RVORE BIN�RIA.
public class BTNode<TIPO> implements BTPosition<TIPO>{
	
	//Declara��o de Variaveis.
	private TIPO element; //Armazena o elemento contido no nodo.
	private BTPosition<TIPO> left, right, parent; //Armazena a referencia aos nodo adjacentes.
	
	//M�todo Construtor.
	public BTNode(TIPO element, BTPosition<TIPO> parent, BTPosition<TIPO> left, BTPosition<TIPO> right) {

		this.element = element; //Inicia o elemento com o valor informado.
		this.parent = parent; //Inicia � referencia ao pai do nodo com o valor informado.
		this.left = left; //Inicia � referencia ao filho esquerdo do nodo com o valor informado.
		this.right = right; //Inicia � referencia ao filho direito do nodo com o valor informado.
	}
	
	//Retorna o elemento contido no nodo.
	public TIPO element() {return element;}
	
	//Define o elemento contido no nodo.
	public void setElement(TIPO element) {this.element = element;}
	
	//Retorna � referencia ao filho esquerdo do nodo.
	public BTPosition<TIPO> getLeft() {return left;}
	
	//Define � referencia ao filho esquerdo do nodo.
	public void setLeft(BTPosition<TIPO> left) {this.left = left;}
	
	//Retorna � referencia ao filho direito do nodo.
	public BTPosition<TIPO> getRight() {return right;}
	
	//Define � referencia ao filho direito do nodo.
	public void setRight(BTPosition<TIPO> right) {this.right = right;}
	
	//Retorna a referencia ao pai do nodo.
	public BTPosition<TIPO> getParent() {return parent;}
	
	//Define � referencia ao pai do nodo.
	public void setParent(BTPosition<TIPO> parent) {this.parent = parent;}
	
}