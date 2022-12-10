package TADS.TAD_Fila.TAD_Fila_LSE.Fontes;

import TADS.TAD_Fila.TAD_Fila_LSE.Excecoes.EmptyQueueExceptionNodeQueue;
import TADS.TAD_Fila.TAD_Fila_LSE.Interfaces.Queue;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementa��o do TAD FILA.
public class NodeQueue<TYPE> implements Queue<TYPE> {	
	
	//Declara��o das Variaveis.	
	protected Node<TYPE> head; //Referencia ao primeiro nodo da fila.
	protected Node<TYPE> tail; //Referencia ao ultimo nodo da fila.
	protected int size; //N�mero total de elementos contidos na fila.
	
	//M�todo Construtor.
	public NodeQueue() {
		
		head = null; //Inicializa o head com null.
		tail = null; //Inicializa o tail com null.
		size = 0; //Inicializa o size com 0.		
	}
	
	//Retorna o n�mero de elementos contidos na FILA.
	public int size() {return size;}
	
	//Retorna o status da FILA => True=Vazio, False=N�o Vazio.
	public boolean isEmpty() {return size() == 0;}
	
	//Verifica se � possivel realizar alguma opera��o na FILA.
	protected void validateQueue() throws EmptyQueueExceptionNodeQueue {
		if(isEmpty()) throw new EmptyQueueExceptionNodeQueue("A fila est� vazia!"); //Realiza a verifica��o.
	}
	
	//Retorna o elemento na frente da FILA, sem remove-lo.
	public TYPE front() throws EmptyQueueExceptionNodeQueue {
		
		validateQueue(); //Verifica se � possivel realizar a opera��o.
		
		return head.getElement(); //Retorna o elemento na frente da FILA.
	}	
	
	//Insere um novo elemento no final da FILA.
	public void enqueue(TYPE elemento) {
		
		Node<TYPE> node = new Node<TYPE>(); //Cria um novo nodo vazio.
		
		node.setElement(elemento); //Informa o elemento do nodo.
		node.setNext(null); //Informa a refencia ao proximo nodo.
		
		//Verifica a posi��o que o nodo ir� ocupar, em caso de lista vazia o nodo ocupa a posi��o na frente da fila.
		if(size() == 0) {head = node;} else {tail.setNext(node);} //Caso contrario o nodo ocupa a posi��o no final da fila.
		
		tail = node; //Atualiza refer�ncia ao nodo do final
		size++; //Acrescenta-se +1 ao n�mero total de elementos contidos na FILA.
	}	
	
	//Remove e retorna o elemento na frente da FILA.
	public TYPE dequeue() throws EmptyQueueExceptionNodeQueue {
		
		validateQueue(); //Verifica se � possivel realizar a opera��o.
		
		TYPE elementNodeRemoved = head.getElement(); //Armazena o elemento do node removido.
		head = head.getNext(); //Referencia o proximo nodo a frente da FILA.
		size--; //Diminui-se 1 do n�mero total de elementos contidos na FILA.
		
		if(size == 0) {tail = null;} //Define a FILA como vazia.	
		
		return elementNodeRemoved; //Retorna o elemento removido.
	}	
	
	//Concatena todos os elementos da FILA em uma string.
	public String toString() {
		
		String elements = "["; //Inicia a string com chaves aberta.
		Node<TYPE> node = head; //Armazena o node da frente da FILA.
		
		while(node != null) { //Percorre a FILA.
			
			elements += node.getElement(); //Concatena os elementos da FILA com a string.
			node = node.getNext(); //Avan�a para o proximo node da FILA.
			
			if(node != null) {elements += ", ";} //Concatena o separador de elementos com a string.
		}
		
		elements += "]"; //Fecha a string com chaves.
		return elements; //Retorna a string de elementos da FILA.
	}	

}