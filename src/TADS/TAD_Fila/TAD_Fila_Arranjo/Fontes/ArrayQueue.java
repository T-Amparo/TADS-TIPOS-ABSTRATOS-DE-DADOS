package TADS.TAD_Fila.TAD_Fila_Arranjo.Fontes;

import TADS.TAD_Fila.TAD_Fila_Arranjo.Excecoes.EmptyQueueExceptionArrayQueue;
import TADS.TAD_Fila.TAD_Fila_Arranjo.Excecoes.FullQueueException;
import TADS.TAD_Fila.TAD_Fila_Arranjo.Interfaces.Queue;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementa��o do TAD FILA USANDO ARRANJO.
public class ArrayQueue<TYPE> implements Queue<TYPE> {	
	
	//Declara��o das Variaveis.
	private TYPE queue[]; //Arranjo que ir� armazenar os elementos da fila.
	private int capacity; //Tamanho inicial do arranjo queue.
	private int front; //Indice para a frente da fila.
	private int next; //Indice para a proxima posi��o livre da fila.
	
	private static final int CAPACITY = 15; //Tamanho padr�o do arranjo.
	
	//M�todo Construtor.
	public ArrayQueue() {this(CAPACITY);} //Inicia o arranjo com o tamanho padr�o.
	
	//M�todo Construtor.
	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		
		this.capacity = capacity + 1; //Inicia a capacidade do arranjo com o valor informado somando-se +1.
		queue = (TYPE[]) new Object[this.capacity]; //Inicia o arranjo com o tamanho inicial.
		front = next = 0; //Inicia com indice 0.
	}	
	
	//Retorna o n�mero de elementos contidos no arranjo.
	public int size() {return (capacity - front + next) % capacity;}	
	
	//Retorna o status do arranjo => True=Vazio, False=N�o Vazio.
	public boolean isEmpty() {return front == next;}	
	
	//Verifica se n�o � possivel realizar alguma opera��o na fila => (0=front, dequeue e 1=enqueue).
	protected void validateQueue(int operation) throws EmptyQueueExceptionArrayQueue, FullQueueException {
		
		if(operation == 0) { //Verifica qual o tipo de opera��o que est� sendo realizada.
			if(isEmpty()) throw new EmptyQueueExceptionArrayQueue("A fila est� vazia!"); //Realiza a verifica��o se a fila est� vazia.
		
		} else if(operation == 1) { //Verifica qual o tipo de opera��o que est� sendo realizada.
			if(size() == capacity - 1) throw new FullQueueException("A fila est� cheia!"); //Realiza a verifica��o se a fila est� cheia.
		}
	}	
	
	//Retorna o elemento � frente da fila, sem remove-lo.
	public TYPE front() throws EmptyQueueExceptionArrayQueue {
		
		validateQueue(0); //Verifica se � possivel realizar a opera��o.
		
		return queue[front]; //Retorna o elemento que est� � frente da fila.		
	}	
	
	//Insere um novo elemento no final da fila.
	public void enqueue(TYPE element) throws FullQueueException {
		
		validateQueue(1); //Verifica se � possivel realizar a opera��o.
		
		queue[next] = element; //Adiciona o elemento no final da fila.
		next = (next + 1) % capacity; //Avan�a para a proxima posi��o livre da fila.
	}	
	
	//Remove e retorna o elemento � frente da fila.
	public TYPE dequeue() throws EmptyQueueExceptionArrayQueue {
		
		validateQueue(0); //Verifica se � possivel realizar a opera��o.
		
		TYPE elementRemoved = queue[front]; //Armazena o primeiro elemento da fila.
		queue[front] = null; //Remove a referencia ao elemento � frente da fila.		
		front = (front + 1) % capacity; //Avan�a para o proxima elemento da fila.
		
		return elementRemoved; //Retorna o elemento removido.
	}	
	
	//Concatena todos os elementos do arranjo em uma string.
	public String toString() {
		
		String elements = "["; //Inicia a string com chaves aberta.
		
		for(int i=0; i < size(); i++) { //Percorre o arranjo queue.
			
			elements += queue[(front + i) % capacity]; //Concatena os elementos do arranjo queue com a string.
			if(i != (size() - 1)) {elements += ", ";} //Concatena o separador de elementos com a string.
		}
		
		elements += "]"; //Fecha a string com chaves.
		return elements; //Retorna a string de elementos do arranjo queue.		
	}	

}