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

//Implementa��o do TAD LISTA DE NODOS.
public class NodePositionList<TYPE> implements PositionList<TYPE> {
	
	//Declara��o das Variaveis.
	protected int size; //N�mero total de elementos contidos na lista.
	protected DNode<TYPE> header, trailer; //Sentinelas da lista, fazem referencia ao inicio e ao fim da lista.
	
	//M�todo Construtor.
	public NodePositionList() {
		
		size = 0; //Inicializa o size com 0.
		header = new DNode<TYPE>(null, null, null); //Cria a sentinela da cabe�a da lista.
		trailer = new DNode<TYPE>(header, null, null); //Cria a sentinela da cauda da lista.
		header.setNext(trailer); //Faz a sentinela da cabe�a da lista apontar para a cauda da lista.
	}
	
	//Verifica se a posi��o � v�lida para esta lista e a converte para DNode se for v�lida
	protected DNode<TYPE> validatePosition(Position<TYPE> position) throws InvalidPositionException {
		
		if (position == null) throw new InvalidPositionException("A posi��o informada tem valor null"); //Verifica se a posi��o � nula.
		if (position == header) throw new InvalidPositionException("A cabe�a da lista n�o � uma posi��o valida"); //Verifica se a posi��o � a sentinela da cabe�a da lista.
		if (position == trailer) throw new InvalidPositionException("A cauda da lista n�o � uma posi��o valida"); //Verifica se a posi��o � a sentinela da cauda da lista.		
		
		try { //Caso ocorra um erro, trata a exce��o gerada.
			
			DNode<TYPE> positionTemporary = (DNode<TYPE>) position; //Converte a posi��o para o tipo DNode.
			
			if ((positionTemporary.getPrev() == null) || (positionTemporary.getNext() == null)) { //Verifica se a posi��o informada pertence a uma lista de nodos.
				throw new InvalidPositionException("A posi��o n�o pertence a uma lista de nodos v�lida");} //Libera uma exce��o.
			
			return positionTemporary; //Retorna a posi��o validada.
			
		} catch (ClassCastException element) {throw new InvalidPositionException("A posi��o � do tipo errado para esta lista");} //Libera uma exce��o em caso de erro.
	}
	
	//Retorna o n�mero de elementos contidos na lista.
	public int size() {return size;}
	
	//Retorna o status da lista => True=Vazio, False=N�o Vazio.
	public boolean isEmpty() {return size() == 0;}
	
	//Retorna a primeira posi��o da lista, sem remove-l�.
	public Position<TYPE> first() throws EmptyListException {
		
		if (isEmpty()) throw new EmptyListException("A lista est� vazia!"); //Verifica se a lista est� vazia.
		return header.getNext(); //Retorna a primeira posi��o da lista.
	}
	
	//Retorna a �ltima posi��o da lista, sem remove-l�.
	public Position<TYPE> last() throws EmptyListException {
		
		if (isEmpty()) throw new EmptyListException("A lista est� vazia!"); //Verifica se a lista est� vazia.
		return trailer.getPrev(); //Retorna a �ltima posi��o da lista.
	}
	
	//Retorna o proximo nodo apartir de um determinado nodo da lista.
	public Position<TYPE> next(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		DNode<TYPE> next = positionValidate.getNext(); //Armazena a proxima posi��o apartir da posi��o informada.
		
		if (next == trailer) throw new BoundaryViolationException("N�o � poss�vel avan�ar al�m do final da lista"); //Verifica se a posi��o � a sentinela da cauda da lista.
		return next; //Retorna o proximo nodo.
	}
	
	//Retorna o nodo anterior apartir de um determinado nodo da lista.
	public Position<TYPE> prev(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		DNode<TYPE> prev = positionValidate.getPrev(); //Armazena a posi��o anterior apartir da posi��o informada.
		
		if (prev == header) throw new BoundaryViolationException("N�o � poss�vel avan�ar al�m do in�cio da lista"); //Verifica se a posi��o � a sentinela da cabe�a da lista.
		return prev; //Retorna o nodo anterior.
	}
	
	//Adiciona o elemento no inicio da lista.
	public void addFirst(TYPE element) {
		
		DNode<TYPE> newNode = new DNode<TYPE>(header, header.getNext(), element); //Cria um nodo com o elemento informado e suas devidas referencias.
		header.getNext().setPrev(newNode); //O prev do proximo elemento da cabe�a da lista aponta para o nodo criado.
		header.setNext(newNode); //O next da cabe�a da lista aponta para o nodo criado.
		
		size++; //Acrescenta-se +1 ao n�mero total de elementos contidos na fila.
	}
	
	//Adiciona o elemento no final da lista.
	public void addLast(TYPE element) {
		
		DNode<TYPE> newNode = new DNode<TYPE>(trailer.getPrev(), trailer, element); //Cria um nodo com o elemento informado e suas devidas referencias.
		trailer.getPrev().setNext(newNode); //O next do elemento anterior da cauda da lista aponta para o nodo criado.
		trailer.setPrev(newNode); //O prev da cauda da lista aponta para o nodo criado.
		
		size++; //Acrescenta-se +1 ao n�mero total de elementos contidos na fila.
	}
	
	//Adiciona um elemento ap�s um dado elemento da lista.
	public void addAfter(Position<TYPE> position, TYPE element) throws InvalidPositionException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		DNode<TYPE> newNode = new DNode<TYPE>(positionValidate, positionValidate.getNext(), element); //Cria um nodo com o elemento informado e suas devidas referencias.
		
		positionValidate.getNext().setPrev(newNode); //O prev do proximo elemento da posi��o informada aponta para o nodo criado.
		positionValidate.setNext(newNode); //O next da posi��o informada aponta para o nodo criado.
		
		size++; //Acrescenta-se +1 ao n�mero total de elementos contidos na fila.
	}
	
	//Adiciona um elemento antes da posi��o informada.
	public void addBefore(Position<TYPE> position, TYPE element) throws InvalidPositionException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		DNode<TYPE> newNode = new DNode<TYPE>(positionValidate.getPrev(), positionValidate, element); //Cria um nodo com o elemento informado e suas devidas referencias.
		
		positionValidate.getPrev().setNext(newNode); //O next do elemento anterior da posi��o informada aponta para o nodo criado.
		positionValidate.setPrev(newNode); //O prev da posi��o informada aponta para o nodo criado.
		
		size++; //Acrescenta-se +1 ao n�mero total de elementos contidos na fila.
	}
	
	//Remove da lista a posi��o informada.
	public TYPE remove(Position<TYPE> position) throws InvalidPositionException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		DNode<TYPE> prev = positionValidate.getPrev(); //Armazena a referencia do elemento anterior � posi��o informada.
		DNode<TYPE> next = positionValidate.getNext(); //Armazena a referencia do proximo elemento da posi��o informada.
		
		prev.setNext(next); //O next do nodo da referencia prev aponta para o nodo da referencia next.
		next.setPrev(prev); //O prev do nodo da referencia next aponta para o nodo da referencia prev.
		
		TYPE elementRemoved = positionValidate.element(); //Armazena o elemento removido da lista.
		
		positionValidate.setPrev(null); //Altera a referencia do nodo removido para null.
		positionValidate.setNext(null); //Altera a referencia do nodo removido para null.
		
		size--; //Diminui-se 1 do n�mero total de elementos contidos na fila.
		return elementRemoved; //Retorna o elemento removido.		
	}
	
	//Troca o elemento da posi��o informada pelo novo elemento informado.
	public TYPE set(Position<TYPE> position, TYPE element) throws InvalidPositionException {
		
		DNode<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		TYPE elementRemoved = positionValidate.element(); //Armazena o elemento removido da lista.
		positionValidate.setElement(element); //Altera o elemento da posi��o pelo novo elemento.
		
		return elementRemoved; //Retorna o elemento removido.
	}
	
	//Concatena todos os elementos da lista em uma string.
	public static <TYPE> String toString(PositionList<TYPE> list) {
		
		String elements = ""; //Inicia a string.
		
		for(TYPE element : list) {elements += ", " + element;} //Percorre a lista e concatena os elementos e o separador � string.
		
		elements = (elements.length() == 0 ? elements : elements.substring(2)); //Remove o �ltimo separador da string.		
		
		return "[" + elements + "]"; //Retorna a string de elementos da lista entre chaves.		
	}	
	
	//M�todo toString sem parametro.
	public String toString() {return toString(this);}
	
	//Retorna um iterator a partir do ElemenIterator.
	public Iterator<TYPE> iterator() {return new ElementIterator<TYPE>(this);}
	
}