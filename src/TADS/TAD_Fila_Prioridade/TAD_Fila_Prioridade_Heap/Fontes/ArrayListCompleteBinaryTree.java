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

//Implementa��o do TAD �RVORE BINARIA COMPLETA.
public class ArrayListCompleteBinaryTree<TYPE> implements CompleteBinaryTree<TYPE> {
	
	//Declara��o das Variaveis.
	protected ArrayList<BTPosition<TYPE>> tree; //Armazena a lista com �s posi��es da �rvore.
	
	//Subclasse.
	protected static class BTPosition<TYPE> implements Position<TYPE> {
		
		//Declara��o das Variaveis da Subclasse.
		protected TYPE element; //Armazena o elemento desta posi��o.
		protected int index; //Armazena o indice desta posi��o.
		
		//M�todo Construtor da Subclasse.
		public BTPosition(TYPE element, int index) {
			
			this.element = element; //Inicializa o elemento com o valor informado.
			this.index = index; //Inicializa o indice com o valor informado.
		}
		
		//Retorna o elemento pertecente a posi��o.
		public TYPE element() {return element;}

		//Retorna o indice pertecente a posi��o.
		public int index() {return index;}
		
		//Troca o elemento da posi��o.
		public TYPE setElement(TYPE element) {
			
			TYPE elementRemoved = this.element; //Armazena o elemento atual da posi��o.
			this.element = element; //Altera o elemento atual da posi��o para o valor informado.
			
			return elementRemoved; //Retorna o elemento antigo da posi��o.
		}
		
		//Retorna uma string [elemento, indice].
		public String toString() { return ("[" + element + "," + index + "]"); }		
	}
	
	//M�todo Construtor.
	public ArrayListCompleteBinaryTree() {

		tree = new ArrayList<BTPosition<TYPE>>(); //Inicializa a variavel com uma lista vazia.
		tree.add(0, null); //Inicializa a �rvore com uma posi��o vazia.
	}	
	
	//Retorna o n�mero de elementos contidos na �rvore.
	public int size() {return tree.size() - 1;}
	
	//Retorna o status da �RVORE BINARIA COMPLETA => True=Vazia, False=N�o Vazia.
	public boolean isEmpty() {return(size() == 0);}	
	
	//Retorna o status do nodo => True=Interno, False=N�o Interno.
	public boolean isInternal(Position<TYPE> position) throws InvalidPositionException {return hasLeft(position);}
	
	//Retorna o status do nodo => True=Externo, False=N�o Externo.
	public boolean isExternal(Position<TYPE> position) throws InvalidPositionException {return !isInternal(position);}
	
	//Retorna o status do nodo => True=Raiz, False=N�o Raiz.
	public boolean isRoot(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return (positionValidate.index() == 1); //Retorna o status do nodo.
	}	
	
	//Verifica se a posi��o informada � v�lida e a converte para BTPosition se for v�lida.
	protected BTPosition<TYPE> validatePosition(Position<TYPE> position) throws InvalidPositionException {

		if (position == null || !(position instanceof BTPosition)) throw new InvalidPositionException("Essa posi��o � inv�lida"); //Realiza � verifica��o.
		return (BTPosition<TYPE>) position; //Retorna a posi��o validada e convertida.
	}	
	
	//Troca o elemento armazenado em um determinado nodo e retorna o elemento que estava armazenado nele.
	public TYPE replace(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return positionValidate.setElement(element); //Altera e retorna o elemento que estava no nodo.
	}	
	
	//Retorna a raiz da �rvore.
	public Position<TYPE> root() throws EmptyTreeException {

		if (isEmpty()) throw new EmptyTreeException("A �rvore est� vazia!"); //Verifica se a �rvore est� vazia.
		return tree.get(1); //Retorna a raiz da �rvore.
	}	
	
	//Retorna o pai de um dado nodo.
	public Position<TYPE> parent(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {
		
		if (isRoot(position)) throw new BoundaryViolationException("N�o h� pai para esse nodo!"); //Verifica se o pai do nodo � valido.

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.		
				
		return tree.get((positionValidate.index() / 2)); //Retorna o pai do nodo.
	}	
	
	//Retorna um iterador sobre todos os elementos da �rvore.
	public Iterator<TYPE> iterator() {
		
		ArrayList<TYPE> list = new ArrayList<TYPE>(); //Cria uma lista vazia.
		
		Iterator<BTPosition<TYPE>> iterator = tree.iterator(); //Armazena o iterador da �rvore.
		iterator.next(); //Avan�a para o segundo elemento da �rvore.
		
		while(iterator.hasNext()) {list.add(iterator.next().element());} //Percorre o iterador da �rvore e adiciona os elementos a lista vazia.
		
		return list.iterator(); //Retorna um iterador com todos os elementos da �rvore.
	}	
	
	//Retorna uma cole��o iter�vel de todos os nodos armazenados na �rvore.
	public Iterable<Position<TYPE>> positions() {
		
		ArrayList<Position<TYPE>> positions = new ArrayList<Position<TYPE>>(); //Cria uma lista de posi��es vazia.
		
		Iterator<BTPosition<TYPE>> iterator = tree.iterator(); //Armazena o iterador da �rvore.
		iterator.next(); //Avan�a para o segundo elemento da �rvore.
		
		while(iterator.hasNext()) {positions.add(iterator.next());} //Percorre o iterador da �rvore e adiciona as posi��es a lista vazia.
		
		return positions; //Retorna um iterador com uma cole��o iter�vel de todos os nodos armazenados na �rvore.
	}
	
	//Retorna uma cole��o iter�vel de todos os filhos de um dado nodo da �rvore.
	public Iterable<Position<TYPE>> children(Position<TYPE> position) throws InvalidPositionException {

		PositionList<Position<TYPE>> children = new NodePositionList<Position<TYPE>>(); //Cria uma lista de posi��es vazia.
		
		if (hasLeft(position)) children.addLast(left(position)); //Verifica se o nodo � o filho da esquerda e preenche a lista com o filho esquerdo do nodo.
		if (hasRight(position)) children.addLast(right(position)); //Verifica se o nodo � o filho da direita e preenche a lista com o filho direito do nodo.
		
		return children; //Retorna a lista de filhos criada.
	}	
	
	//Retorna o filho esquerdo do nodo.
	public Position<TYPE> left(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		if (!hasLeft(position)) throw new BoundaryViolationException("N�o h� filhos a esquerda!"); //Verifica se o filho esquerdo do nodo � valido.
		
		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.		
				
		return tree.get((2 * positionValidate.index())); //Retorna o filho esquerdo do nodo.
	}	
	
	//Retorna o filho direito do nodo.
	public Position<TYPE> right(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		if (!hasRight(position)) throw new BoundaryViolationException("N�o h� filhos a direita!"); //Verifica se o filho direito do nodo � valido.
		
		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.		
		
		return tree.get((2 * positionValidate.index() + 1)); //Retorna o filho direito do nodo.
	}	
	
	//Retorna o valor booleano representando se o nodo tem filho a esquerda.
	public boolean hasLeft(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return (2 * positionValidate.index() <= size()); //Retorna o status do nodo.
	}
	
	//Retorna o valor booleano representando se o nodo tem filho a direita.
	public boolean hasRight(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return (2 * positionValidate.index() + 1 <= size()); //Retorna o status do nodo.
	}	
	
	//Retorna o irm�o de um determinado nodo.
	public Position<TYPE> sibling(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {
		
		try { //Trata a exce��o caso aconte�a um possivel erro.
			
			Position<TYPE> parent = parent(position); //Armazena a referencia do pai da posi��o informada.
			Position<TYPE> left = left(parent); //Armazena a referencia do filho esquerdo do pai da posi��o informada.
			
			if (parent == left) {return right(parent);} //Verifica se o pai da posi��o informada e o filho esquerdo do pai da posi��o informada s�o iguais.
			else {return left;} //Caso n�o sejam iguais, retorna o filho esquerdo do pai da posi��o informada.
			
		} catch (BoundaryViolationException error) {throw new BoundaryViolationException("N�o h� irm�o para esse nodo!");} //Caso gere algun erro, libera uma exce��o.		
	}
	
	//Adiciona um elemento no final da �rvore.
	public Position<TYPE> add(TYPE element) {
		
		int index = size() + 1; //Armazena o indice da posi��o que o elemento ser� inserido.
		
		//Cria uma posi��o com os valores informados.
		BTPosition<TYPE> position = new BTPosition<TYPE>(element, index);
		tree.add(index, position); //Adiciona a posi��o criada na �rvore.
		
		return position; //Retorna a posi��o criada.		
	}
	
	//Remove e retorna o elemento do �ltimo nodo da �rvore.
	public TYPE remove() throws EmptyTreeException {

		if (isEmpty()) throw new EmptyTreeException("A �rvore est� vazia!"); //Verifica se a �rvore est� vazia.
		return tree.remove(size()).element(); //Remove e retorna o elemento da �rvore.
	}
	
	//Troca os elementos de dois nodos entre eles.
	public void swapElements(Position<TYPE> positionOne, Position<TYPE> positionTwo) throws InvalidPositionException {

		BTPosition<TYPE> positionOneValidate = validatePosition(positionOne); //Verifica se a posi��o informada � valida.
		BTPosition<TYPE> positionTwoValidate = validatePosition(positionTwo); //Verifica se a posi��o informada � valida.

		TYPE changed = positionOneValidate.element(); //Armazena o elemento da primeira posi��o informada.
		
		positionOneValidate.setElement(positionTwoValidate.element()); //Troca o elemento da primeira posi��o informada pelo da segunda posi��o.
		positionTwoValidate.setElement(changed); //Troca o elemento da segunda posi��o informada pelo elemento armazenado da primeira posi��o.
	}
	
	//Retorna uma string com todos os elementos da �rvore concatenados.
	public String toString() {return tree.toString();}	

}