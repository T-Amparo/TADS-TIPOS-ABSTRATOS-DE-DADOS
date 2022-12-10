package TADS.TAD_Pilha.TAD_Pilha_LSE.Fontes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do nodo que compoe a PILHA.
public class Node<TYPE> {
	
	//Declaração de Variaveis.	
	private TYPE element; //Armazena o elemento que compoe o nodo.
	private Node<TYPE> next; //Armazena a referencia ao proximo nodo.	
	
	//Método Construtor.
	public Node() {this(null, null);} //Inicia os atributos do nodo com null.
	
	//Método Construtor.
	public Node(TYPE element, Node<TYPE> next) {
		
		this.element = element; //Inicia o 'element=elemento' com o valor informado.
		this.next = next; //Inicia a referencia ao proximo nodo com o valor informado.
	}
	
	//Retorna o elemento contido no nodo.
	public TYPE getElement() {return element;}
	
	//Retorna a referencia ao proximo nodo.
	public Node<TYPE> getNext() {return next;}
	
	//Altera o elemento do nodo pelo novo valor informado.
	public void setElement(TYPE element) {this.element = element;}
	
	//Altera a referencia do proximo nodo pela nova referencia informada.
	public void setNext(Node<TYPE> next) {this.next = next;}

}