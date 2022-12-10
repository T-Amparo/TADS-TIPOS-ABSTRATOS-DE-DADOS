package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Fontes;

import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces.BTPosition;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do nodo duplo que compoe a ÁRVORE BINÁRIA.
public class BTNode<TIPO> implements BTPosition<TIPO>{
	
	//Declaração de Variaveis.
	private TIPO element; //Armazena o elemento contido no nodo.
	private BTPosition<TIPO> left, right, parent; //Armazena a referencia aos nodo adjacentes.
	
	//Método Construtor.
	public BTNode(TIPO element, BTPosition<TIPO> parent, BTPosition<TIPO> left, BTPosition<TIPO> right) {

		this.element = element; //Inicia o elemento com o valor informado.
		this.parent = parent; //Inicia à referencia ao pai do nodo com o valor informado.
		this.left = left; //Inicia à referencia ao filho esquerdo do nodo com o valor informado.
		this.right = right; //Inicia à referencia ao filho direito do nodo com o valor informado.
	}
	
	//Retorna o elemento contido no nodo.
	public TIPO element() {return element;}
	
	//Define o elemento contido no nodo.
	public void setElement(TIPO element) {this.element = element;}
	
	//Retorna à referencia ao filho esquerdo do nodo.
	public BTPosition<TIPO> getLeft() {return left;}
	
	//Define à referencia ao filho esquerdo do nodo.
	public void setLeft(BTPosition<TIPO> left) {this.left = left;}
	
	//Retorna à referencia ao filho direito do nodo.
	public BTPosition<TIPO> getRight() {return right;}
	
	//Define à referencia ao filho direito do nodo.
	public void setRight(BTPosition<TIPO> right) {this.right = right;}
	
	//Retorna a referencia ao pai do nodo.
	public BTPosition<TIPO> getParent() {return parent;}
	
	//Define à referencia ao pai do nodo.
	public void setParent(BTPosition<TIPO> parent) {this.parent = parent;}
	
}