package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Fontes;

import java.util.ArrayList;
import java.util.Iterator;

import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Excecoes.BoundaryViolationException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Excecoes.EmptyTreeException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Excecoes.InvalidPositionException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces.CompleteBinaryTree;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces.Position;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces.PositionList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do TAD ÁRVORE BINARIA COMPLETA.
public class ArrayListCompleteBinaryTree<TYPE> implements CompleteBinaryTree<TYPE> {
	
	//Declaração das Variaveis.
	protected ArrayList<BTPosition<TYPE>> tree; //Armazena a lista com às posições da árvore.
	
	//Subclasse.
	protected static class BTPosition<TYPE> implements Position<TYPE> {
		
		//Declaração das Variaveis da Subclasse.
		protected TYPE element; //Armazena o elemento desta posição.
		protected int index; //Armazena o indice desta posição.
		
		//Método Construtor da Subclasse.
		public BTPosition(TYPE element, int index) {
			
			this.element = element; //Inicializa o elemento com o valor informado.
			this.index = index; //Inicializa o indice com o valor informado.
		}
		
		//Retorna o elemento pertecente a posição.
		public TYPE element() {return element;}

		//Retorna o indice pertecente a posição.
		public int index() {return index;}
		
		//Troca o elemento da posição.
		public TYPE setElement(TYPE element) {
			
			TYPE elementRemoved = this.element; //Armazena o elemento atual da posição.
			this.element = element; //Altera o elemento atual da posição para o valor informado.
			
			return elementRemoved; //Retorna o elemento antigo da posição.
		}
		
		//Retorna uma string [elemento, indice].
		public String toString() { return ("[" + element + "," + index + "]"); }		
	}
	
	//Método Construtor.
	public ArrayListCompleteBinaryTree() {

		tree = new ArrayList<BTPosition<TYPE>>(); //Inicializa a variavel com uma lista vazia.
		tree.add(0, null); //Inicializa a árvore com uma posição vazia.
	}	
	
	//Retorna o número de elementos contidos na árvore.
	public int size() {return tree.size() - 1;}
	
	//Retorna o status da ÁRVORE BINARIA COMPLETA => True=Vazia, False=Não Vazia.
	public boolean isEmpty() {return(size() == 0);}	
	
	//Retorna o status do nodo => True=Interno, False=Não Interno.
	public boolean isInternal(Position<TYPE> position) throws InvalidPositionException {return hasLeft(position);}
	
	//Retorna o status do nodo => True=Externo, False=Não Externo.
	public boolean isExternal(Position<TYPE> position) throws InvalidPositionException {return !isInternal(position);}
	
	//Retorna o status do nodo => True=Raiz, False=Não Raiz.
	public boolean isRoot(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		return (positionValidate.index() == 1); //Retorna o status do nodo.
	}	
	
	//Verifica se a posição informada é válida e a converte para BTPosition se for válida.
	protected BTPosition<TYPE> validatePosition(Position<TYPE> position) throws InvalidPositionException {

		if (position == null || !(position instanceof BTPosition)) throw new InvalidPositionException("Essa posição é inválida"); //Realiza á verificação.
		return (BTPosition<TYPE>) position; //Retorna a posição validada e convertida.
	}	
	
	//Troca o elemento armazenado em um determinado nodo e retorna o elemento que estava armazenado nele.
	public TYPE replace(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		return positionValidate.setElement(element); //Altera e retorna o elemento que estava no nodo.
	}	
	
	//Retorna a raiz da árvore.
	public Position<TYPE> root() throws EmptyTreeException {

		if (isEmpty()) throw new EmptyTreeException("A árvore está vazia!"); //Verifica se a árvore está vazia.
		return tree.get(1); //Retorna a raiz da árvore.
	}	
	
	//Retorna o pai de um dado nodo.
	public Position<TYPE> parent(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {
		
		if (isRoot(position)) throw new BoundaryViolationException("Não há pai para esse nodo!"); //Verifica se o pai do nodo é valido.

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.		
				
		return tree.get((positionValidate.index() / 2)); //Retorna o pai do nodo.
	}	
	
	//Retorna um iterador sobre todos os elementos da árvore.
	public Iterator<TYPE> iterator() {
		
		ArrayList<TYPE> list = new ArrayList<TYPE>(); //Cria uma lista vazia.
		
		Iterator<BTPosition<TYPE>> iterator = tree.iterator(); //Armazena o iterador da árvore.
		iterator.next(); //Avança para o segundo elemento da árvore.
		
		while(iterator.hasNext()) {list.add(iterator.next().element());} //Percorre o iterador da árvore e adiciona os elementos a lista vazia.
		
		return list.iterator(); //Retorna um iterador com todos os elementos da árvore.
	}	
	
	//Retorna uma coleção iterável de todos os nodos armazenados na árvore.
	public Iterable<Position<TYPE>> positions() {
		
		ArrayList<Position<TYPE>> positions = new ArrayList<Position<TYPE>>(); //Cria uma lista de posições vazia.
		
		Iterator<BTPosition<TYPE>> iterator = tree.iterator(); //Armazena o iterador da árvore.
		iterator.next(); //Avança para o segundo elemento da árvore.
		
		while(iterator.hasNext()) {positions.add(iterator.next());} //Percorre o iterador da árvore e adiciona as posições a lista vazia.
		
		return positions; //Retorna um iterador com uma coleção iterável de todos os nodos armazenados na árvore.
	}
	
	//Retorna uma coleção iterável de todos os filhos de um dado nodo da árvore.
	public Iterable<Position<TYPE>> children(Position<TYPE> position) throws InvalidPositionException {

		PositionList<Position<TYPE>> children = new NodePositionList<Position<TYPE>>(); //Cria uma lista de posições vazia.
		
		if (hasLeft(position)) children.addLast(left(position)); //Verifica se o nodo é o filho da esquerda e preenche a lista com o filho esquerdo do nodo.
		if (hasRight(position)) children.addLast(right(position)); //Verifica se o nodo é o filho da direita e preenche a lista com o filho direito do nodo.
		
		return children; //Retorna a lista de filhos criada.
	}	
	
	//Retorna o filho esquerdo do nodo.
	public Position<TYPE> left(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		if (!hasLeft(position)) throw new BoundaryViolationException("Não há filhos a esquerda!"); //Verifica se o filho esquerdo do nodo é valido.
		
		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.		
				
		return tree.get((2 * positionValidate.index())); //Retorna o filho esquerdo do nodo.
	}	
	
	//Retorna o filho direito do nodo.
	public Position<TYPE> right(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		if (!hasRight(position)) throw new BoundaryViolationException("Não há filhos a direita!"); //Verifica se o filho direito do nodo é valido.
		
		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.		
		
		return tree.get((2 * positionValidate.index() + 1)); //Retorna o filho direito do nodo.
	}	
	
	//Retorna o valor booleano representando se o nodo tem filho a esquerda.
	public boolean hasLeft(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		return (2 * positionValidate.index() <= size()); //Retorna o status do nodo.
	}
	
	//Retorna o valor booleano representando se o nodo tem filho a direita.
	public boolean hasRight(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		return (2 * positionValidate.index() + 1 <= size()); //Retorna o status do nodo.
	}	
	
	//Retorna o irmão de um determinado nodo.
	public Position<TYPE> sibling(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {
		
		try { //Trata a exceção caso aconteça um possivel erro.
			
			Position<TYPE> parent = parent(position); //Armazena a referencia do pai da posição informada.
			Position<TYPE> left = left(parent); //Armazena a referencia do filho esquerdo do pai da posição informada.
			
			if (parent == left) {return right(parent);} //Verifica se o pai da posição informada e o filho esquerdo do pai da posição informada são iguais.
			else {return left;} //Caso não sejam iguais, retorna o filho esquerdo do pai da posição informada.
			
		} catch (BoundaryViolationException error) {throw new BoundaryViolationException("Não há irmão para esse nodo!");} //Caso gere algun erro, libera uma exceção.		
	}
	
	//Adiciona um elemento no final da árvore.
	public Position<TYPE> add(TYPE element) {
		
		int index = size() + 1; //Armazena o indice da posição que o elemento será inserido.
		
		//Cria uma posição com os valores informados.
		BTPosition<TYPE> position = new BTPosition<TYPE>(element, index);
		tree.add(index, position); //Adiciona a posição criada na árvore.
		
		return position; //Retorna a posição criada.		
	}
	
	//Remove e retorna o elemento do último nodo da árvore.
	public TYPE remove() throws EmptyTreeException {

		if (isEmpty()) throw new EmptyTreeException("A árvore está vazia!"); //Verifica se a árvore está vazia.
		return tree.remove(size()).element(); //Remove e retorna o elemento da árvore.
	}
	
	//Troca os elementos de dois nodos entre eles.
	public void swapElements(Position<TYPE> positionOne, Position<TYPE> positionTwo) throws InvalidPositionException {

		BTPosition<TYPE> positionOneValidate = validatePosition(positionOne); //Verifica se a posição informada é valida.
		BTPosition<TYPE> positionTwoValidate = validatePosition(positionTwo); //Verifica se a posição informada é valida.

		TYPE changed = positionOneValidate.element(); //Armazena o elemento da primeira posição informada.
		
		positionOneValidate.setElement(positionTwoValidate.element()); //Troca o elemento da primeira posição informada pelo da segunda posição.
		positionTwoValidate.setElement(changed); //Troca o elemento da segunda posição informada pelo elemento armazenado da primeira posição.
	}
	
	//Retorna uma string com todos os elementos da árvore concatenados.
	public String toString() {return tree.toString();}	

}