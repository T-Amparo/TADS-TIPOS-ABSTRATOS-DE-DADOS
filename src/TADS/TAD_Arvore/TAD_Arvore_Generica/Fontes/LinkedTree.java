package TADS.TAD_Arvore.TAD_Arvore_Generica.Fontes;

import java.util.Iterator;

import TADS.TAD_Arvore.TAD_Arvore_Generica.Excecoes.BoundaryViolationException;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Excecoes.EmptyTreeException;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Excecoes.InvalidPositionException;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Excecoes.NonEmptyTreeException;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.Position;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.PositionList;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.Tree;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.TreePosition;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementa��o do TAD �RVORE GENERICA.
public class LinkedTree<TYPE> implements Tree<TYPE> {
	
	//Declara��o das Variaveis.
	protected TreePosition<TYPE> root; //Armazena a referencia � raiz da �rvore.
	protected int size; //N�mero total de elementos contidos na �rvore.
	
	//M�todo Construtor.
	public LinkedTree() {
		
		root = null; //Inicializa a referencia � raiz da �rvore com null.
		size = 0; //Inicializa o size com 0.
	}
	
	//Retorna o n�mero de elementos contidos na �rvore.
	public int size() {return size;}
	
	//Retorna o status da �RVORE GENERICA => True=Vazia, False=N�o Vazia.
	public boolean isEmpty() {return (size == 0);}
	
	//Retorna o status do nodo => True=Interno, False=N�o Interno.
	public boolean isInternal(Position<TYPE> position) throws InvalidPositionException {return !isExternal(position);}
	
	//Retorna o status do nodo => True=Externo, False=N�o Externo.
	public boolean isExternal(Position<TYPE> position) throws InvalidPositionException {

		TreePosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return (positionValidate.getChildren() == null) || positionValidate.getChildren().isEmpty(); //Retorna o status do nodo.
	}	
	
	//Verifica se a posi��o informada � v�lida e a converte para TreePosition se for v�lida.
	protected TreePosition<TYPE> validatePosition(Position<TYPE> position) throws InvalidPositionException {

		if (position == null || !(position instanceof TreePosition)) throw new InvalidPositionException("Est� posi��o � invalida!"); //Realiza � verifica��o.
		return (TreePosition<TYPE>) position; //Retorna a posi��o validada e convertida.
	}
	
	//Cria um novo nodo para a �rvore.
	protected TreePosition<TYPE> createNode(TYPE element, TreePosition<TYPE> parent, PositionList<Position<TYPE>> children) {

		return new TreeNode<TYPE>(element, parent, children); //Retorna o nodo criado.
	}
	
	//Preenche uma lista com nodos da �rvore percorrida.
	protected void preorderPositions(Position<TYPE> position, PositionList<Position<TYPE>> positionList) throws InvalidPositionException {

		positionList.addLast(position); //Adiciona o nodo na ultima posi��o da lista.
		for (Position<TYPE> node : children(position)) preorderPositions(node, positionList); //Percorre recursivamente a �rvore e preenche a lista.
	}
	
	//Retorna o status do nodo => True=Raiz, False=N�o Raiz.
	public boolean isRoot(Position<TYPE> position) throws InvalidPositionException {

		validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return (position == root()); //Retorna o status do nodo.
	}	
	
	//Troca o elemento armazenado em um determinado nodo e retorna o elemento que estava armazenado nele.
	public TYPE replace(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		TreePosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		TYPE elementRemoved = position.element(); //Armazena o elemento antigo.
		positionValidate.setElement(element); //Define o novo elemento para o nodo conforme informado.
		
		return elementRemoved; //Retorna o elemento antigo.
	}
	
	//Retorna a raiz da �rvore.
	public TreePosition<TYPE> root() throws EmptyTreeException {

		if (root == null) throw new EmptyTreeException("A �rvore est� vazia!"); //Verifica se a �rvore est� vazia.
		return root; //Retorna a raiz da �rvore.
	}
	
	//Retorna o pai de um dado nodo.
	public TreePosition<TYPE> parent(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		TreePosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		TreePosition<TYPE> parent = positionValidate.getParent(); //Armazena o pai do nodo.
		
		if (parent == null) throw new BoundaryViolationException("Este nodo n�o tem pai!"); //Verifica se o pai do nodo � valido.
		
		return parent; //Retorna o pai do nodo.
	}	
	
	//Retorna um iterador sobre todos os elementos da �rvore.
	public Iterator<TYPE> iterator() {

		Iterable<Position<TYPE>> positions = positions(); //Armazena uma cole��o iter�vel de todos os nodos armazenados na �rvore.
		
		PositionList<TYPE> elements = new NodePositionList<TYPE>(); //Cria uma lista de nodos vazia.
		for (Position<TYPE> position : positions) elements.addLast(position.element()); //Percorre a cole��o de nodos da �rvore e adiciona seus elementos a lista criada.
		
		return elements.iterator(); //Retorna um iterador com todos os elementos da �rvore.
	}
	
	//Retorna uma cole��o iter�vel de todos os filhos de um dado nodo da �rvore.
	public Iterable<Position<TYPE>> children(Position<TYPE> position) throws InvalidPositionException {

		TreePosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return positionValidate.getChildren(); //Retorna uma cole��o iter�vel de todos os filhos do nodo.
	}
	
	//Retorna uma cole��o iter�vel de todos os nodos armazenados na �rvore.
	public Iterable<Position<TYPE>> positions() {

		PositionList<Position<TYPE>> positions = new NodePositionList<Position<TYPE>>(); //Cria uma lista de nodos vazia.
		
		if (size != 0) preorderPositions(root(), positions); //O algoritmo percorre a �rvore e preenche a lista criada.
		return positions; //Retorna uma cole��o iter�vel de todos os nodos da �rvore.
	}
	
	//Adiciona uma raiz na �rvore criada.
	public TreePosition<TYPE> addRoot(TYPE element) throws NonEmptyTreeException {

		if (!isEmpty()) throw new NonEmptyTreeException("A �rvore j� tem uma raiz!"); //Verifica se a �rvore est� vazia.
		
		size = 1; //Muda-se o tamanho da �rvore para um.
		root = createNode(element, null, null); //Cria um nodo com o elemento informado e referencia ele � raiz da �rvore.
		
		return root; //Retorna a raiz adicionada.
	}
	
	//Troca o elemento de dois nodos da �rvore.
	public void swapElements(Position<TYPE> positionP, Position<TYPE> positionS) throws InvalidPositionException {

		TreePosition<TYPE> positionPValidate = validatePosition(positionP); //Verifica se a posi��o informada � valida.
		TreePosition<TYPE> positionSValidate = validatePosition(positionS); //Verifica se a posi��o informada � valida.

		TYPE elementPositionS = positionS.element(); //Armazena temporariamente o elemento da segunda posi��o informada.

		positionSValidate.setElement(positionP.element()); //Altera o elemento da segunda posi��o para o elemento da primeira posi��o.
		positionPValidate.setElement(elementPositionS); //Altera o elemento da primeira posi��o para o elemento da segunda posi��o armazenado.
	}
	
	//Imprimir� a representa��o da �rvore Generica.
	public String parentheticRepresentation (Tree<TYPE> tree, Position<TYPE> position) {
		
		String representation = position.element().toString(); //Variavel que armazenar� a string da �rvore, inicia com a raiz da arvore.
		
		if (tree.isInternal(position)) { //Verifica se o nodo da �rvore � interno.
			
			Boolean one = true; //Variavel flag, que ser� true quando for o primeiro filho de uma sub raiz da �rvore.
			int depth = 0; //Contar� a profundidade de cada nodo da �rvore, usada para identar os elementos da �rvore.
			
			for (Position<TYPE> children : tree.children(position)) { //La�o de intera��o com cada filho da �rvore.
				
				depth = depth(tree, position); //Armazena a profundidade do filho atual da �rvore.
				
				if (one) { //Verifica se o filho atual � o primeiro filho da sub raiz da �rvore.
					
					representation += "(\n\t"; //Concatena a variavel de impress�o com a identa��o(Enter + Tabula��o).					
					for(int i = 0; i < depth; i++) {representation += "\t";} //De acordo com a profundidade do nodo aplica a tabula��o.					
					representation += parentheticRepresentation(tree, children); //Concatena com o elemento do nodo da �rvore.
					
					one = false; //Altera a variavel flag para false.
					
				}else { //Caso n�o seja o primeiro filho.
					
					representation += ",\n\t"; //Concatena a variavel de impress�o com a identa��o(Enter + Tabula��o).
					for(int i = 0; i < depth; i++) {representation += "\t";} //De acordo com a profundidade do nodo aplica a tabula��o.
					representation += parentheticRepresentation(tree, children);} //Concatena com o elemento do nodo da �rvore.		
			}
			
			representation += "\n"; //Concatena a variavel de impress�o com a identa��o(Enter).
			for(int i = 0; i < depth; i++) {representation += "\t";} //De acordo com a profundidade do nodo aplica a tabula��o.
			representation += ")"; //Concatena com o fechamento do parenteses.
		}		
		return representation; // Retorna a variavel que armazena a representa��o da �rvore.
	}
	
	//Imprimir� a representa��o da �rvore Generica com base no algoritmo P�s-ordem.
	public String toStringPostorder(Tree<TYPE> tree, Position<TYPE> position) {
		
		String representation = ""; //Variavel que armazenar� a string da �rvore, inicia vazia.
		
		//La�o de intera��o com cada filho da �rvore.			  
		for (Position<TYPE> filho : tree.children(position)) {representation += toStringPostorder(tree, filho);} //Concatena recursivamente com os elementos atuais dos nodos
		representation += position.element() + ",\n"; //Concatena com virgula e quebra de linha.		
		
		return representation; // Retorna a variavel que armazena a representa��o da �rvore.
	}
	
	//M�todo depth - Calcula a profundidade de um nodo da �rvore.
	public int depth(Tree<TYPE> tree, Position<TYPE> position) {
		
		if(tree.isRoot(position)) {return 0;} //Verifica se posi��o informada � a raiz da arvore, caso seja retorna 0.
		else {return 1 + depth(tree, tree.parent(position));} //Caso contrario, chama recursivamente at� encontrar a raiz, sempre somando +1 e retorna essa soma.		
	}
	
	//M�todo height1 - Calcula a altura de uma �rvore (m�todo menos eficiente).
	public int height1(Tree<TYPE> tree) {
		
		int height = 0; //Ir� armazenar a altura da �rvore.
		
		for(Position<TYPE> children : tree.positions()) { //La�o que percorre todas as posi��es da �rvore.
			
			//Verifica se a posi��o filho � um nodo externo, caso seja utiliza o Math.max para calcular a altura junto com uma chamada recursiva do depth.
			if(tree.isExternal(children)) {height = Math.max(height, depth(tree, children));}
		}
		
		return height; //Retorna a altura da �rvore.
	}
	
	//M�todo height2 - Calcula a altura de uma �rvore (m�todo mais eficiente).
	public int height2(Tree<TYPE> tree, Position<TYPE> position) {
		
		if(tree.isExternal(position)) {return 0;} //Verifica se o nodo da �rvore � interno, caso seja retorna 0.
		
		else {
			
			int height = 0; //Armazena a soma recursiva da altura dos nodos.
			
			//Percorre a �rvore e utiliza o Math.max para calcular a altura junto com uma chamada recursiva.
			for(Position<TYPE> children : tree.children(position)) {height = Math.max(height, height2(tree, children));}
			
			return 1 + height; //Retorna a altura da �rvore.
		}
	}	
	
	//Concatena todos os elementos da lista em uma string.
	public static <TYPE> String toString(LinkedTree<TYPE> tree) {
		
		String elements = ""; //Inicia a string.
		
		for(TYPE element : tree) {elements += ", " + element;} //Percorre a �rvore e concatena os elementos e o separador � string.
		
		elements = (elements.length() == 0 ? elements : elements.substring(2)); //Remove o �ltimo separador da string.		
		
		return "[" + elements + "]"; //Retorna a string de elementos da �rvore entre chaves.		
	}	
	
	//M�todo toString sem parametro.
	public String toString() {return toString(this);}
}