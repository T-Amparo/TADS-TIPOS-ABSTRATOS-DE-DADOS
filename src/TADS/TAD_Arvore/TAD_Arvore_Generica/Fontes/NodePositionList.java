package TADS.TAD_Arvore.TAD_Arvore_Generica.Fontes;

import java.util.Iterator;

import TADS.TAD_Arvore.TAD_Arvore_Generica.Excecoes.BoundaryViolationException;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Excecoes.EmptyListException;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Excecoes.InvalidPositionException;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.Position;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.PositionList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do TAD LISTA DE NODOS.
public class NodePositionList<TYPE> implements PositionList<TYPE> {
	
	//Declaração das Variaveis.
	protected int size; //Número total de elementos contidos na lista.
	protected DNode<TYPE> header, trailer; //Sentinelas da lista, fazem referencia ao inicio e ao fim da lista.
	
	//Método Construtor.
	public NodePositionList() {
		
		size = 0; //Inicializa o size com 0.
		header = new DNode<TYPE>(null, null, null); //Cria a sentinela da cabeça da lista.
		trailer = new DNode<TYPE>(header, null, null); //Cria a sentinela da cauda da lista.
		header.setNext(trailer); //Faz a sentinela da cabeça da lista apontar para a cauda da lista.
	}
	
	//Verifica se a posição é válida para esta lista e a converte para DNode se for válida
	protected DNode<TYPE> validatePosition(Position<TYPE> position) throws InvalidPositionException {
		
		if (position == null) throw new InvalidPositionException("A posição informada tem valor null"); //Verifica se a posição é nula.
		if (position == header) throw new InvalidPositionException("A cabeça da lista não é uma posição valida"); //Verifica se a posição é a sentinela da cabeça da lista.
		if (position == trailer) throw new InvalidPositionException("A cauda da lista não é uma posição valida"); //Verifica se a posição é a sentinela da cauda da lista.		
		
		try { //Caso ocorra um erro, trata a exceção gerada.
			
			DNode<TYPE> positionTemporary = (DNode<TYPE>) position; //Converte a posição para o tipo DNode.
			
			if ((positionTemporary.getPrev() == null) || (positionTemporary.getNext() == null)) { //Verifica se a posição informada pertence a uma lista de nodos.
				throw new InvalidPositionException("A posição não pertence a uma lista de nodos válida");} //Libera uma exceção.
			
			return positionTemporary; //Retorna a posição validada.
			
		} catch (ClassCastException element) {throw new InvalidPositionException("A posição é do tipo errado para esta lista");} //Libera uma exceção em caso de erro.
	}
	
	//Retorna o número de elementos contidos na lista.
	public int size() {return size;}
	
	//Retorna o status da lista => True=Vazio, False=Não Vazio.
	public boolean isEmpty() {return size() == 0;}
	
	//Retorna a primeira posição da lista, sem remove-lá.
	public Position<TYPE> first() throws EmptyListException {
		
		if (isEmpty()) throw new EmptyListException("A lista está vazia!"); //Verifica se a lista está vazia.
		return header.getNext(); //Retorna a primeira posição da lista.
	}
	
	//Retorna a última posição da lista, sem remove-lá.
	public Position<TYPE> last() throws EmptyListException {
		
		if (isEmpty()) throw new EmptyListException("A lista está vazia!"); //Verifica se a lista está vazia.
		return trailer.getPrev(); //Retorna a última posição da lista.
	}
	
	//Retorna o proximo nodo apartir de um determinado nodo da lista.
	public Position<TYPE> next(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		DNode<TYPE> next = positionValidate.getNext(); //Armazena a proxima posição apartir da posição informada.
		
		if (next == trailer) throw new BoundaryViolationException("Não é possível avançar além do final da lista"); //Verifica se a posição é a sentinela da cauda da lista.
		return next; //Retorna o proximo nodo.
	}
	
	//Retorna o nodo anterior apartir de um determinado nodo da lista.
	public Position<TYPE> prev(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		DNode<TYPE> prev = positionValidate.getPrev(); //Armazena a posição anterior apartir da posição informada.
		
		if (prev == header) throw new BoundaryViolationException("Não é possível avançar além do início da lista"); //Verifica se a posição é a sentinela da cabeça da lista.
		return prev; //Retorna o nodo anterior.
	}
	
	//Adiciona o elemento no inicio da lista.
	public void addFirst(TYPE element) {
		
		DNode<TYPE> newNode = new DNode<TYPE>(header, header.getNext(), element); //Cria um nodo com o elemento informado e suas devidas referencias.
		header.getNext().setPrev(newNode); //O prev do proximo elemento da cabeça da lista aponta para o nodo criado.
		header.setNext(newNode); //O next da cabeça da lista aponta para o nodo criado.
		
		size++; //Acrescenta-se +1 ao número total de elementos contidos na fila.
	}
	
	//Adiciona o elemento no final da lista.
	public void addLast(TYPE element) {
		
		DNode<TYPE> newNode = new DNode<TYPE>(trailer.getPrev(), trailer, element); //Cria um nodo com o elemento informado e suas devidas referencias.
		trailer.getPrev().setNext(newNode); //O next do elemento anterior da cauda da lista aponta para o nodo criado.
		trailer.setPrev(newNode); //O prev da cauda da lista aponta para o nodo criado.
		
		size++; //Acrescenta-se +1 ao número total de elementos contidos na fila.
	}
	
	//Adiciona um elemento após um dado elemento da lista.
	public void addAfter(Position<TYPE> position, TYPE element) throws InvalidPositionException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		DNode<TYPE> newNode = new DNode<TYPE>(positionValidate, positionValidate.getNext(), element); //Cria um nodo com o elemento informado e suas devidas referencias.
		
		positionValidate.getNext().setPrev(newNode); //O prev do proximo elemento da posição informada aponta para o nodo criado.
		positionValidate.setNext(newNode); //O next da posição informada aponta para o nodo criado.
		
		size++; //Acrescenta-se +1 ao número total de elementos contidos na fila.
	}
	
	//Adiciona um elemento antes da posição informada.
	public void addBefore(Position<TYPE> position, TYPE element) throws InvalidPositionException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		DNode<TYPE> newNode = new DNode<TYPE>(positionValidate.getPrev(), positionValidate, element); //Cria um nodo com o elemento informado e suas devidas referencias.
		
		positionValidate.getPrev().setNext(newNode); //O next do elemento anterior da posição informada aponta para o nodo criado.
		positionValidate.setPrev(newNode); //O prev da posição informada aponta para o nodo criado.
		
		size++; //Acrescenta-se +1 ao número total de elementos contidos na fila.
	}
	
	//Remove da lista a posição informada.
	public TYPE remove(Position<TYPE> position) throws InvalidPositionException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		DNode<TYPE> prev = positionValidate.getPrev(); //Armazena a referencia do elemento anterior à posição informada.
		DNode<TYPE> next = positionValidate.getNext(); //Armazena a referencia do proximo elemento da posição informada.
		
		prev.setNext(next); //O next do nodo da referencia prev aponta para o nodo da referencia next.
		next.setPrev(prev); //O prev do nodo da referencia next aponta para o nodo da referencia prev.
		
		TYPE elementRemoved = positionValidate.element(); //Armazena o elemento removido da lista.
		
		positionValidate.setPrev(null); //Altera a referencia do nodo removido para null.
		positionValidate.setNext(null); //Altera a referencia do nodo removido para null.
		
		size--; //Diminui-se 1 do número total de elementos contidos na fila.
		return elementRemoved; //Retorna o elemento removido.		
	}
	
	//Troca o elemento da posição informada pelo novo elemento informado.
	public TYPE set(Position<TYPE> position, TYPE element) throws InvalidPositionException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		TYPE elementRemoved = positionValidate.element(); //Armazena o elemento removido da lista.
		positionValidate.setElement(element); //Altera o elemento da posição pelo novo elemento.
		
		return elementRemoved; //Retorna o elemento removido.
	}
	
	//Concatena todos os elementos da lista em uma string.
	public static <TYPE> String toString(PositionList<TYPE> list) {
		
		String elements = ""; //Inicia a string.
		
		for(TYPE element : list) {elements += ", " + element;} //Percorre a lista e concatena os elementos e o separador à string.
		
		elements = (elements.length() == 0 ? elements : elements.substring(2)); //Remove o último separador da string.		
		
		return "[" + elements + "]"; //Retorna a string de elementos da lista entre chaves.		
	}	
	
	//Método toString sem parametro.
	public String toString() {return toString(this);}
	
	//Retorna um iterator a partir do ElemenIterator.
	public Iterator<TYPE> iterator() {return new ElementIterator<TYPE>(this);}
	
}