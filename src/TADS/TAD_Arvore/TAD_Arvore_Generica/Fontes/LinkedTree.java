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

//Implementação do TAD ÁRVORE GENERICA.
public class LinkedTree<TYPE> implements Tree<TYPE> {
	
	//Declaração das Variaveis.
	protected TreePosition<TYPE> root; //Armazena a referencia à raiz da árvore.
	protected int size; //Número total de elementos contidos na árvore.
	
	//Método Construtor.
	public LinkedTree() {
		
		root = null; //Inicializa a referencia à raiz da árvore com null.
		size = 0; //Inicializa o size com 0.
	}
	
	//Retorna o número de elementos contidos na árvore.
	public int size() {return size;}
	
	//Retorna o status da ÁRVORE GENERICA => True=Vazia, False=Não Vazia.
	public boolean isEmpty() {return (size == 0);}
	
	//Retorna o status do nodo => True=Interno, False=Não Interno.
	public boolean isInternal(Position<TYPE> position) throws InvalidPositionException {return !isExternal(position);}
	
	//Retorna o status do nodo => True=Externo, False=Não Externo.
	public boolean isExternal(Position<TYPE> position) throws InvalidPositionException {

		TreePosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		return (positionValidate.getChildren() == null) || positionValidate.getChildren().isEmpty(); //Retorna o status do nodo.
	}	
	
	//Verifica se a posição informada é válida e a converte para TreePosition se for válida.
	protected TreePosition<TYPE> validatePosition(Position<TYPE> position) throws InvalidPositionException {

		if (position == null || !(position instanceof TreePosition)) throw new InvalidPositionException("Está posição é invalida!"); //Realiza á verificação.
		return (TreePosition<TYPE>) position; //Retorna a posição validada e convertida.
	}
	
	//Cria um novo nodo para a árvore.
	protected TreePosition<TYPE> createNode(TYPE element, TreePosition<TYPE> parent, PositionList<Position<TYPE>> children) {

		return new TreeNode<TYPE>(element, parent, children); //Retorna o nodo criado.
	}
	
	//Preenche uma lista com nodos da árvore percorrida.
	protected void preorderPositions(Position<TYPE> position, PositionList<Position<TYPE>> positionList) throws InvalidPositionException {

		positionList.addLast(position); //Adiciona o nodo na ultima posição da lista.
		for (Position<TYPE> node : children(position)) preorderPositions(node, positionList); //Percorre recursivamente a árvore e preenche a lista.
	}
	
	//Retorna o status do nodo => True=Raiz, False=Não Raiz.
	public boolean isRoot(Position<TYPE> position) throws InvalidPositionException {

		validatePosition(position); //Verifica se a posição informada é valida.
		
		return (position == root()); //Retorna o status do nodo.
	}	
	
	//Troca o elemento armazenado em um determinado nodo e retorna o elemento que estava armazenado nele.
	public TYPE replace(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		TreePosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		TYPE elementRemoved = position.element(); //Armazena o elemento antigo.
		positionValidate.setElement(element); //Define o novo elemento para o nodo conforme informado.
		
		return elementRemoved; //Retorna o elemento antigo.
	}
	
	//Retorna a raiz da árvore.
	public TreePosition<TYPE> root() throws EmptyTreeException {

		if (root == null) throw new EmptyTreeException("A árvore está vazia!"); //Verifica se a árvore está vazia.
		return root; //Retorna a raiz da árvore.
	}
	
	//Retorna o pai de um dado nodo.
	public TreePosition<TYPE> parent(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		TreePosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		TreePosition<TYPE> parent = positionValidate.getParent(); //Armazena o pai do nodo.
		
		if (parent == null) throw new BoundaryViolationException("Este nodo não tem pai!"); //Verifica se o pai do nodo é valido.
		
		return parent; //Retorna o pai do nodo.
	}	
	
	//Retorna um iterador sobre todos os elementos da árvore.
	public Iterator<TYPE> iterator() {

		Iterable<Position<TYPE>> positions = positions(); //Armazena uma coleção iterável de todos os nodos armazenados na árvore.
		
		PositionList<TYPE> elements = new NodePositionList<TYPE>(); //Cria uma lista de nodos vazia.
		for (Position<TYPE> position : positions) elements.addLast(position.element()); //Percorre a coleção de nodos da árvore e adiciona seus elementos a lista criada.
		
		return elements.iterator(); //Retorna um iterador com todos os elementos da árvore.
	}
	
	//Retorna uma coleção iterável de todos os filhos de um dado nodo da árvore.
	public Iterable<Position<TYPE>> children(Position<TYPE> position) throws InvalidPositionException {

		TreePosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		return positionValidate.getChildren(); //Retorna uma coleção iterável de todos os filhos do nodo.
	}
	
	//Retorna uma coleção iterável de todos os nodos armazenados na árvore.
	public Iterable<Position<TYPE>> positions() {

		PositionList<Position<TYPE>> positions = new NodePositionList<Position<TYPE>>(); //Cria uma lista de nodos vazia.
		
		if (size != 0) preorderPositions(root(), positions); //O algoritmo percorre a árvore e preenche a lista criada.
		return positions; //Retorna uma coleção iterável de todos os nodos da árvore.
	}
	
	//Adiciona uma raiz na árvore criada.
	public TreePosition<TYPE> addRoot(TYPE element) throws NonEmptyTreeException {

		if (!isEmpty()) throw new NonEmptyTreeException("A árvore já tem uma raiz!"); //Verifica se a árvore está vazia.
		
		size = 1; //Muda-se o tamanho da árvore para um.
		root = createNode(element, null, null); //Cria um nodo com o elemento informado e referencia ele à raiz da árvore.
		
		return root; //Retorna a raiz adicionada.
	}
	
	//Troca o elemento de dois nodos da árvore.
	public void swapElements(Position<TYPE> positionP, Position<TYPE> positionS) throws InvalidPositionException {

		TreePosition<TYPE> positionPValidate = validatePosition(positionP); //Verifica se a posição informada é valida.
		TreePosition<TYPE> positionSValidate = validatePosition(positionS); //Verifica se a posição informada é valida.

		TYPE elementPositionS = positionS.element(); //Armazena temporariamente o elemento da segunda posição informada.

		positionSValidate.setElement(positionP.element()); //Altera o elemento da segunda posição para o elemento da primeira posição.
		positionPValidate.setElement(elementPositionS); //Altera o elemento da primeira posição para o elemento da segunda posição armazenado.
	}
	
	//Imprimirá a representação da Árvore Generica.
	public String parentheticRepresentation (Tree<TYPE> tree, Position<TYPE> position) {
		
		String representation = position.element().toString(); //Variavel que armazenará a string da árvore, inicia com a raiz da arvore.
		
		if (tree.isInternal(position)) { //Verifica se o nodo da árvore é interno.
			
			Boolean one = true; //Variavel flag, que será true quando for o primeiro filho de uma sub raiz da árvore.
			int depth = 0; //Contará a profundidade de cada nodo da árvore, usada para identar os elementos da árvore.
			
			for (Position<TYPE> children : tree.children(position)) { //Laço de interação com cada filho da árvore.
				
				depth = depth(tree, position); //Armazena a profundidade do filho atual da árvore.
				
				if (one) { //Verifica se o filho atual é o primeiro filho da sub raiz da árvore.
					
					representation += "(\n\t"; //Concatena a variavel de impressão com a identação(Enter + Tabulação).					
					for(int i = 0; i < depth; i++) {representation += "\t";} //De acordo com a profundidade do nodo aplica a tabulação.					
					representation += parentheticRepresentation(tree, children); //Concatena com o elemento do nodo da árvore.
					
					one = false; //Altera a variavel flag para false.
					
				}else { //Caso não seja o primeiro filho.
					
					representation += ",\n\t"; //Concatena a variavel de impressão com a identação(Enter + Tabulação).
					for(int i = 0; i < depth; i++) {representation += "\t";} //De acordo com a profundidade do nodo aplica a tabulação.
					representation += parentheticRepresentation(tree, children);} //Concatena com o elemento do nodo da árvore.		
			}
			
			representation += "\n"; //Concatena a variavel de impressão com a identação(Enter).
			for(int i = 0; i < depth; i++) {representation += "\t";} //De acordo com a profundidade do nodo aplica a tabulação.
			representation += ")"; //Concatena com o fechamento do parenteses.
		}		
		return representation; // Retorna a variavel que armazena a representação da árvore.
	}
	
	//Imprimirá a representação da Árvore Generica com base no algoritmo Pós-ordem.
	public String toStringPostorder(Tree<TYPE> tree, Position<TYPE> position) {
		
		String representation = ""; //Variavel que armazenará a string da árvore, inicia vazia.
		
		//Laço de interação com cada filho da árvore.			  
		for (Position<TYPE> filho : tree.children(position)) {representation += toStringPostorder(tree, filho);} //Concatena recursivamente com os elementos atuais dos nodos
		representation += position.element() + ",\n"; //Concatena com virgula e quebra de linha.		
		
		return representation; // Retorna a variavel que armazena a representação da árvore.
	}
	
	//Método depth - Calcula a profundidade de um nodo da árvore.
	public int depth(Tree<TYPE> tree, Position<TYPE> position) {
		
		if(tree.isRoot(position)) {return 0;} //Verifica se posição informada é a raiz da arvore, caso seja retorna 0.
		else {return 1 + depth(tree, tree.parent(position));} //Caso contrario, chama recursivamente até encontrar a raiz, sempre somando +1 e retorna essa soma.		
	}
	
	//Método height1 - Calcula a altura de uma árvore (método menos eficiente).
	public int height1(Tree<TYPE> tree) {
		
		int height = 0; //Irá armazenar a altura da árvore.
		
		for(Position<TYPE> children : tree.positions()) { //Laço que percorre todas as posições da árvore.
			
			//Verifica se a posição filho é um nodo externo, caso seja utiliza o Math.max para calcular a altura junto com uma chamada recursiva do depth.
			if(tree.isExternal(children)) {height = Math.max(height, depth(tree, children));}
		}
		
		return height; //Retorna a altura da árvore.
	}
	
	//Método height2 - Calcula a altura de uma árvore (método mais eficiente).
	public int height2(Tree<TYPE> tree, Position<TYPE> position) {
		
		if(tree.isExternal(position)) {return 0;} //Verifica se o nodo da árvore é interno, caso seja retorna 0.
		
		else {
			
			int height = 0; //Armazena a soma recursiva da altura dos nodos.
			
			//Percorre a árvore e utiliza o Math.max para calcular a altura junto com uma chamada recursiva.
			for(Position<TYPE> children : tree.children(position)) {height = Math.max(height, height2(tree, children));}
			
			return 1 + height; //Retorna a altura da árvore.
		}
	}	
	
	//Concatena todos os elementos da lista em uma string.
	public static <TYPE> String toString(LinkedTree<TYPE> tree) {
		
		String elements = ""; //Inicia a string.
		
		for(TYPE element : tree) {elements += ", " + element;} //Percorre a árvore e concatena os elementos e o separador à string.
		
		elements = (elements.length() == 0 ? elements : elements.substring(2)); //Remove o último separador da string.		
		
		return "[" + elements + "]"; //Retorna a string de elementos da árvore entre chaves.		
	}	
	
	//Método toString sem parametro.
	public String toString() {return toString(this);}
}