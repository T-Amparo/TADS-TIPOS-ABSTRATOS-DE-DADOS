package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Fontes;

import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes.EmptyStackException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces.Stack;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do TAD PILHA USANDO LSE.
public class NodeStack<TYPE> implements Stack<TYPE> {
	
	//Declaração das Variaveis.	
	protected Node<TYPE> top; //Referencia ao nodo no topo da pilha.
	protected int size; //Número total de elementos contidos na pilha.
	
	//Método Construtor.
	public NodeStack() {
		
		top = null; //Inicializa o top com null.
		size = 0; //Inicializa o size com 0.
	}
	
	//Retorna o número de elementos contidos na pilha.
	public int size() {return size;}
	
	//Retorna o status da pilha => True=Vazio, False=Não Vazio.
	public boolean isEmpty() {return top == null;}
	
	//Verifica se é possivel realizar alguma operação na pilha.
	protected void validateStack() throws EmptyStackException {
		if(isEmpty()) throw new EmptyStackException("A pilha está vazia!"); //Realiza a verificação.
	}
	
	//Retorna o elemento do topo da pilha, sem remove-lo.
	public TYPE top() throws EmptyStackException {
		
		validateStack(); //Verifica se é possivel realizar a operação.
		
		return top.getElement(); //Retorna o elemento no topo da pilha.		
	}
	
	//Insere um novo elemento no topo da pilha.
	public void push(TYPE element) {
		
		Node<TYPE> node = new Node<TYPE>(element, top); //Cria um novo nodo com o elemento informado.
		top = node; //Referencia o novo nodo ao topo da pilha.
		size++; //Acrescenta-se +1 ao número total de elementos contidos na pilha.
	}
	
	//Remove e retorna o elemento do topo da pilha.
	public TYPE pop() throws EmptyStackException {
		
		validateStack(); //Verifica se é possivel realizar a operação.
		
		Node<TYPE> nodeRemoved = top; //Armazena o node removido.
		TYPE elementNodeRemoved = top.getElement(); //Armazena o elemento do node removido.
		
		top = top.getNext(); //Referencia o nodo anterior ao topo da pilha.
		nodeRemoved.setNext(null); //Seta com null a referencia do nodo removido.		
		size--; //Diminui-se 1 do número total de elementos contidos na pilha.
		
		return elementNodeRemoved; //Retorna o elemento removido.
	}
	
	//Concatena todos os elementos do arranjo em uma string.
	public String toString() {
		
		String elements = "["; //Inicia a string com chaves aberta.
		Node<TYPE> node = top; //Armazena o node do topo.
		
		while(node != null) { //Percorre a pilha.
			
			elements += node.getElement(); //Concatena os elementos da pilha com a string.
			node = node.getNext(); //Avança para o proximo nodo.
			
			if(node != null) {elements += ", ";} //Concatena o separador de elementos com a string.
		}
		
		elements += "]"; //Fecha a string com chaves.
		return elements; //Retorna a string de elementos do arranjo.
	}	

}