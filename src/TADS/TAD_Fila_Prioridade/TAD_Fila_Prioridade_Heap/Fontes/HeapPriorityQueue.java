package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Fontes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Excecoes.EmptyHeapPriorityQueueException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Excecoes.InvalidKeyException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces.CompleteBinaryTree;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces.EntryHeapPriorityQueue;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces.Position;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces.PriorityQueue;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do TAD FILA DE PRIORIDADE USANDO HEAP.
public class HeapPriorityQueue<KEY, VALUE> implements PriorityQueue<KEY, VALUE> {
	
	//Declaração das Variaveis.
	protected CompleteBinaryTree<EntryHeapPriorityQueue<KEY, VALUE>> heap; //Armazena os elementos em uma árvore binaria completa.
	protected Comparator<KEY> comparator; //Armazena um comparador para as chaves.
	
	//Subclasse para entradas do heap.
	protected static class MyEntry<KEY, VALUE> implements EntryHeapPriorityQueue<KEY, VALUE> {
		
		//Declaração das Variaveis da Subclasse.
		protected KEY key; //Armazena uma chave.
		protected VALUE value; //Armazena um valor.		
		
		//Método Construtor da Subclasse.
		public MyEntry(KEY key, VALUE value) {
			
			this.key = key; //Inicializa a chave com o valor informado.
			this.value = value; //Inicializa o valor com o valor informado.		
		}		
		
		//Retorna a chave pertecente a entrada.
		public KEY getKey() {return key;}

		//Retorna o valor pertecente a entrada.
		public VALUE getValue() {return value;}
		
		//Retorna uma string (chave, valor).
		public String toString() {return "(" + key + "," + value + ")";}		
	}	
	
	//Método Construtor.
	public HeapPriorityQueue() {
		
		this.heap = new ArrayListCompleteBinaryTree<EntryHeapPriorityQueue<KEY, VALUE>>(); //Cria uma árvore binaria completa vazia.
		this.comparator = new DefaultComparator<KEY>(); //Inicializa com o comparador padrão.
	}	
	
	//Método Construtor.
	public HeapPriorityQueue(Comparator<KEY> comparator) {
		
		this.heap = new ArrayListCompleteBinaryTree<EntryHeapPriorityQueue<KEY, VALUE>>(); //Cria uma árvore binaria completa vazia.
		this.comparator = comparator; //Inicializa com o comparador informado.
	}	
	
	//Altera o comparador da fila de prioridade.
	public void setComparator(Comparator<KEY> comparator) throws IllegalStateException {
		
		if (!isEmpty()) {throw new IllegalStateException("A fila de prioridade não está vazia!");} //Verifica se fila está vazia, caso não esteja libera a exceção.
		this.comparator = comparator; //Altera o comparador.		
	}	
	
	//Retorna o número de elementos contidos na fila.
	public int size() {return heap.size();}
	
	//Retorna o status da fila => True=Vazio, False=Não Vazio.
	public boolean isEmpty() {return heap.size() == 0;}	
	
	//Retorna uma entrada com a menor chave, sem remove-la.
	public EntryHeapPriorityQueue<KEY, VALUE> min() throws EmptyHeapPriorityQueueException {
		
		if (isEmpty()) {throw new EmptyHeapPriorityQueueException("A fila de prioridade está vazia!");} //Verifica se fila está vazia, caso esteje libera a exceção.
		return heap.root().element(); //Retorna o elemento da raiz da árvore.	
	}
	
	//Insere uma entrada na fila e retorna a entrada criada.
	public EntryHeapPriorityQueue<KEY, VALUE> insert(KEY key, VALUE value) throws InvalidKeyException {

		validateKey(key); //Verifica se a chave informada é valida.

		EntryHeapPriorityQueue<KEY, VALUE> entry = new MyEntry<KEY, VALUE>(key, value); //Cria a chave para a fila com os parametros informados.

		upHeap(heap.add(entry)); //Realiza o upHeap.
		
		return entry; //Retorna à entrada criada.
	}	
	
	//Remove e retorna uma entrada(key, value) com a menor chave.
	public EntryHeapPriorityQueue<KEY, VALUE> removeMin() throws EmptyHeapPriorityQueueException {

		if (isEmpty()) throw new EmptyHeapPriorityQueueException("A fila de prioridade está vazia!"); //Verifica se fila está vazia, caso esteje libera a exceção.
		
		EntryHeapPriorityQueue<KEY, VALUE> elementRemoved = heap.root().element(); //Armazena o elemento removido.
		
		if(size() == 1) {heap.remove();} //Verifica se o tamanho da árvore é 1, caso seja remove a raiz.
		else { //Caso contrario,
			
			heap.replace(heap.root(), heap.remove()); //Remove o menor elemento da árvore e substitui com a raiz.
			downHeap(heap.root()); //Realiza o dowHeap.			
		}
		
		return elementRemoved; //Retorna o elemento removido.
	}	
	
	//Verifica se a chave informada é valida.
	protected void validateKey(KEY key) throws InvalidKeyException {		

		try {comparator.compare(key, key);} //Realiza à comparação.
		catch (ClassCastException error) {throw new InvalidKeyException("A chave não pode ser comparada!");} //Libera a exceção caso à chave não possa ser comparada.
	}
	
	//Método que realizará o up-Heap-Bubbling.
	protected void upHeap(Position<EntryHeapPriorityQueue<KEY, VALUE>> position) {

		Position<EntryHeapPriorityQueue<KEY, VALUE>> parent; //Espaço para armazena o pai da posição informada.

		while (!heap.isRoot(position)) { //Percorre à árvore enquanto a posição informada for diferente da raiz.

			parent = heap.parent(position); //Armazena o pai da posição informada.

			//Compara o pai da posição informada e a posição informada.
			if (comparator.compare(parent.element().getKey(), position.element().getKey()) <= 0) {break;}

			swap(parent, position); //Troca o pai com a posição informada.
			position = parent; //Faz a posição informada apontar para o pai da mesma.
		}
	}
	
	//Método que realizará o down-Heap-Bubbling.
	protected void downHeap(Position<EntryHeapPriorityQueue<KEY, VALUE>> position) {

		while (heap.isInternal(position)) { //Percorre à árvore enquanto a posição for um nodo interno.

			Position<EntryHeapPriorityQueue<KEY, VALUE>> min; //Espaço para armazena a posição do menor filho da árvore.

			if (!heap.hasRight(position)) {min = heap.left(position);} //Caso a posição informada não tenha filho a direita, a variavel min recebe o filho a esquerda da árvore.
			
			//Realiza a comparação da posição e a variavel min recebe o filho esquerdo da posição informada.
			else if (comparator.compare(heap.left(position).element().getKey(), heap.right(position).element().getKey()) <= 0) {min = heap.left(position);}

			//Caso contrario a variavel min recebe o filho direito da posição informada.
			else {min = heap.right(position);}

			//Compara o filho menor da árvore e a posição informada.
			if (comparator.compare(min.element().getKey(), position.element().getKey()) < 0) {

				swap(position, min); //Troca o filho menor com a posição informada.
				position = min; //Faz a posição informada apontar para o filho menor.

			} else {break;} //Caso contrario, interrompe o laço.
		}
	}
	
	//Troca os elementos de dois nodos entre eles.
	protected void swap(Position<EntryHeapPriorityQueue<KEY, VALUE>> positionOne, Position<EntryHeapPriorityQueue<KEY, VALUE>> positionTwo) {

		EntryHeapPriorityQueue<KEY, VALUE> changed = positionOne.element(); //Armazena o elemento da primeira posição informada.

		heap.replace(positionOne, positionTwo.element()); //Troca o elemento da primeira posição informada pelo da segunda posição.
		heap.replace(positionTwo, changed); //Troca o elemento da segunda posição informada pelo elemento armazenado da primeira posição.
	}
	
	//Retorna uma coleção iteravel de todas as posições da fila de prioridade.
	public ArrayList<EntryHeapPriorityQueue<KEY, VALUE>> positions(){
		
		ArrayList<EntryHeapPriorityQueue<KEY, VALUE>> positions = new ArrayList<EntryHeapPriorityQueue<KEY, VALUE>>(); //Cria uma lista vazia.
		
		Iterator<EntryHeapPriorityQueue<KEY, VALUE>> iterator = heap.iterator(); //Armazena o iterador da árvore.		
		
		while(iterator.hasNext()) {positions.add(iterator.next());} //Percorre o iterador da árvore e adiciona as posições a lista vazia.
		
		return positions; //Retorna a coleção com todas as posições da fila de prioridade.
	}	
	
	//Retorna uma string com todos os elementos da árvore concatenados.
	public String toString() {return heap.toString();}	

}