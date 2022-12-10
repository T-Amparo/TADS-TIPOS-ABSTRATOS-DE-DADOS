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

//Implementa��o do TAD FILA DE PRIORIDADE USANDO HEAP.
public class HeapPriorityQueue<KEY, VALUE> implements PriorityQueue<KEY, VALUE> {
	
	//Declara��o das Variaveis.
	protected CompleteBinaryTree<EntryHeapPriorityQueue<KEY, VALUE>> heap; //Armazena os elementos em uma �rvore binaria completa.
	protected Comparator<KEY> comparator; //Armazena um comparador para as chaves.
	
	//Subclasse para entradas do heap.
	protected static class MyEntry<KEY, VALUE> implements EntryHeapPriorityQueue<KEY, VALUE> {
		
		//Declara��o das Variaveis da Subclasse.
		protected KEY key; //Armazena uma chave.
		protected VALUE value; //Armazena um valor.		
		
		//M�todo Construtor da Subclasse.
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
	
	//M�todo Construtor.
	public HeapPriorityQueue() {
		
		this.heap = new ArrayListCompleteBinaryTree<EntryHeapPriorityQueue<KEY, VALUE>>(); //Cria uma �rvore binaria completa vazia.
		this.comparator = new DefaultComparator<KEY>(); //Inicializa com o comparador padr�o.
	}	
	
	//M�todo Construtor.
	public HeapPriorityQueue(Comparator<KEY> comparator) {
		
		this.heap = new ArrayListCompleteBinaryTree<EntryHeapPriorityQueue<KEY, VALUE>>(); //Cria uma �rvore binaria completa vazia.
		this.comparator = comparator; //Inicializa com o comparador informado.
	}	
	
	//Altera o comparador da fila de prioridade.
	public void setComparator(Comparator<KEY> comparator) throws IllegalStateException {
		
		if (!isEmpty()) {throw new IllegalStateException("A fila de prioridade n�o est� vazia!");} //Verifica se fila est� vazia, caso n�o esteja libera a exce��o.
		this.comparator = comparator; //Altera o comparador.		
	}	
	
	//Retorna o n�mero de elementos contidos na fila.
	public int size() {return heap.size();}
	
	//Retorna o status da fila => True=Vazio, False=N�o Vazio.
	public boolean isEmpty() {return heap.size() == 0;}	
	
	//Retorna uma entrada com a menor chave, sem remove-la.
	public EntryHeapPriorityQueue<KEY, VALUE> min() throws EmptyHeapPriorityQueueException {
		
		if (isEmpty()) {throw new EmptyHeapPriorityQueueException("A fila de prioridade est� vazia!");} //Verifica se fila est� vazia, caso esteje libera a exce��o.
		return heap.root().element(); //Retorna o elemento da raiz da �rvore.	
	}
	
	//Insere uma entrada na fila e retorna a entrada criada.
	public EntryHeapPriorityQueue<KEY, VALUE> insert(KEY key, VALUE value) throws InvalidKeyException {

		validateKey(key); //Verifica se a chave informada � valida.

		EntryHeapPriorityQueue<KEY, VALUE> entry = new MyEntry<KEY, VALUE>(key, value); //Cria a chave para a fila com os parametros informados.

		upHeap(heap.add(entry)); //Realiza o upHeap.
		
		return entry; //Retorna � entrada criada.
	}	
	
	//Remove e retorna uma entrada(key, value) com a menor chave.
	public EntryHeapPriorityQueue<KEY, VALUE> removeMin() throws EmptyHeapPriorityQueueException {

		if (isEmpty()) throw new EmptyHeapPriorityQueueException("A fila de prioridade est� vazia!"); //Verifica se fila est� vazia, caso esteje libera a exce��o.
		
		EntryHeapPriorityQueue<KEY, VALUE> elementRemoved = heap.root().element(); //Armazena o elemento removido.
		
		if(size() == 1) {heap.remove();} //Verifica se o tamanho da �rvore � 1, caso seja remove a raiz.
		else { //Caso contrario,
			
			heap.replace(heap.root(), heap.remove()); //Remove o menor elemento da �rvore e substitui com a raiz.
			downHeap(heap.root()); //Realiza o dowHeap.			
		}
		
		return elementRemoved; //Retorna o elemento removido.
	}	
	
	//Verifica se a chave informada � valida.
	protected void validateKey(KEY key) throws InvalidKeyException {		

		try {comparator.compare(key, key);} //Realiza � compara��o.
		catch (ClassCastException error) {throw new InvalidKeyException("A chave n�o pode ser comparada!");} //Libera a exce��o caso � chave n�o possa ser comparada.
	}
	
	//M�todo que realizar� o up-Heap-Bubbling.
	protected void upHeap(Position<EntryHeapPriorityQueue<KEY, VALUE>> position) {

		Position<EntryHeapPriorityQueue<KEY, VALUE>> parent; //Espa�o para armazena o pai da posi��o informada.

		while (!heap.isRoot(position)) { //Percorre � �rvore enquanto a posi��o informada for diferente da raiz.

			parent = heap.parent(position); //Armazena o pai da posi��o informada.

			//Compara o pai da posi��o informada e a posi��o informada.
			if (comparator.compare(parent.element().getKey(), position.element().getKey()) <= 0) {break;}

			swap(parent, position); //Troca o pai com a posi��o informada.
			position = parent; //Faz a posi��o informada apontar para o pai da mesma.
		}
	}
	
	//M�todo que realizar� o down-Heap-Bubbling.
	protected void downHeap(Position<EntryHeapPriorityQueue<KEY, VALUE>> position) {

		while (heap.isInternal(position)) { //Percorre � �rvore enquanto a posi��o for um nodo interno.

			Position<EntryHeapPriorityQueue<KEY, VALUE>> min; //Espa�o para armazena a posi��o do menor filho da �rvore.

			if (!heap.hasRight(position)) {min = heap.left(position);} //Caso a posi��o informada n�o tenha filho a direita, a variavel min recebe o filho a esquerda da �rvore.
			
			//Realiza a compara��o da posi��o e a variavel min recebe o filho esquerdo da posi��o informada.
			else if (comparator.compare(heap.left(position).element().getKey(), heap.right(position).element().getKey()) <= 0) {min = heap.left(position);}

			//Caso contrario a variavel min recebe o filho direito da posi��o informada.
			else {min = heap.right(position);}

			//Compara o filho menor da �rvore e a posi��o informada.
			if (comparator.compare(min.element().getKey(), position.element().getKey()) < 0) {

				swap(position, min); //Troca o filho menor com a posi��o informada.
				position = min; //Faz a posi��o informada apontar para o filho menor.

			} else {break;} //Caso contrario, interrompe o la�o.
		}
	}
	
	//Troca os elementos de dois nodos entre eles.
	protected void swap(Position<EntryHeapPriorityQueue<KEY, VALUE>> positionOne, Position<EntryHeapPriorityQueue<KEY, VALUE>> positionTwo) {

		EntryHeapPriorityQueue<KEY, VALUE> changed = positionOne.element(); //Armazena o elemento da primeira posi��o informada.

		heap.replace(positionOne, positionTwo.element()); //Troca o elemento da primeira posi��o informada pelo da segunda posi��o.
		heap.replace(positionTwo, changed); //Troca o elemento da segunda posi��o informada pelo elemento armazenado da primeira posi��o.
	}
	
	//Retorna uma cole��o iteravel de todas as posi��es da fila de prioridade.
	public ArrayList<EntryHeapPriorityQueue<KEY, VALUE>> positions(){
		
		ArrayList<EntryHeapPriorityQueue<KEY, VALUE>> positions = new ArrayList<EntryHeapPriorityQueue<KEY, VALUE>>(); //Cria uma lista vazia.
		
		Iterator<EntryHeapPriorityQueue<KEY, VALUE>> iterator = heap.iterator(); //Armazena o iterador da �rvore.		
		
		while(iterator.hasNext()) {positions.add(iterator.next());} //Percorre o iterador da �rvore e adiciona as posi��es a lista vazia.
		
		return positions; //Retorna a cole��o com todas as posi��es da fila de prioridade.
	}	
	
	//Retorna uma string com todos os elementos da �rvore concatenados.
	public String toString() {return heap.toString();}	

}