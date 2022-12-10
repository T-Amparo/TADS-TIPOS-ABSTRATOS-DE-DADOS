package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Fontes;

import java.util.Iterator;

import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes.BoundaryViolationException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes.EmptyTreeException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes.InvalidPositionException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes.NonEmptyTreeException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces.BTPosition;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces.BinaryTree;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces.Position;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces.PositionList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do TAD ÁRVORE BINÁRIA.
public class LinkedBinaryTree<TYPE> implements BinaryTree<TYPE> {
	
	//Declaração das Variaveis.
	protected BTPosition<TYPE> root; //Armazena a referencia à raiz da árvore.
	protected int size; //Número total de elementos contidos na árvore.
	
	//Método Construtor.
	public LinkedBinaryTree() {

		root = null; //Inicializa a referencia à raiz da árvore com null.
		size = 0; //Inicializa o size com 0.
	}
	
	//Retorna o número de elementos contidos na árvore.
	public int size() {return size;}
	
	//Retorna o status da ÁRVORE GENERICA => True=Vazia, False=Não Vazia.
	public boolean isEmpty() {return (size == 0);}
	
	//Retorna o status do nodo => True=Interno, False=Não Interno.
	public boolean isInternal(Position<TYPE> position) throws InvalidPositionException {

		validatePosition(position); //Verifica se a posição informada é valida.
		
		return (hasLeft(position) || hasRight(position)); //Retorna o status do nodo.
	}
	
	//Retorna o status do nodo => True=Externo, False=Não Externo.
	public boolean isExternal(Position<TYPE> position) throws InvalidPositionException {return !isInternal(position);}
	
	//Retorna o status do nodo => True=Raiz, False=Não Raiz.
	public boolean isRoot(Position<TYPE> position) throws InvalidPositionException {

		validatePosition(position); //Verifica se a posição informada é valida.
		
		return (position == root()); //Retorna o status do nodo.
	}	
	
	//Verifica se a posição informada é válida e a converte para BTPosition se for válida.
	protected BTPosition<TYPE> validatePosition(Position<TYPE> position) throws InvalidPositionException {

		if (position == null || !(position instanceof BTPosition)) throw new InvalidPositionException("Essa posição é inválida"); //Realiza á verificação.
		return (BTPosition<TYPE>) position; //Retorna a posição validada e convertida.
	}
	
	//Cria um novo nodo para a árvore.
	protected BTPosition<TYPE> createNode(TYPE element, BTPosition<TYPE> parent, BTPosition<TYPE> left, BTPosition<TYPE> right) {
		
		return new BTNode<TYPE>(element, parent, left, right); //Retorna o nodo criado.
	}
	
	//Preenche uma lista com nodos da árvore percorrida.
	protected void preorderPositions(Position<TYPE> position, PositionList<Position<TYPE>> positionList) throws InvalidPositionException {

		positionList.addLast(position); //Adiciona o nodo na ultima posição da lista.

		if (hasLeft(position)) preorderPositions(left(position), positionList); //Verifica se o nodo é o filho da esquerda e preenche a lista recursivamente.
		if (hasRight(position)) preorderPositions(right(position), positionList); //Verifica se o nodo é o filho da direita e preenche a lista recursivamente.
	}
	
	//Troca o elemento armazenado em um determinado nodo e retorna o elemento que estava armazenado nele.
	public TYPE replace(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		TYPE elementRemoved = position.element(); //Armazena o elemento antigo.
		positionValidate.setElement(element); //Define o novo elemento para o nodo conforme informado.
		
		return elementRemoved; //Retorna o elemento antigo.
	}
	
	//Retorna a raiz da árvore.
	public Position<TYPE> root() throws EmptyTreeException {

		if (root == null) throw new EmptyTreeException("A árvore está vazia!"); //Verifica se a árvore está vazia.
		return root; //Retorna a raiz da árvore.
	}	
	
	//Retorna o pai de um dado nodo.
	public Position<TYPE> parent(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		Position<TYPE> parent = (Position<TYPE>) positionValidate.getParent(); //Armazena o pai do nodo.
		
		if (parent == null) throw new BoundaryViolationException("Não há pai para esse nodo!"); //Verifica se o pai do nodo é valido.		
		return parent; //Retorna o pai do nodo.
	}
	
	//Retorna um iterador sobre todos os elementos da árvore.
	public Iterator<TYPE> iterator() {

		Iterable<Position<TYPE>> positions = positions(); //Armazena uma coleção iterável de todos os nodos armazenados na árvore.
		
		PositionList<TYPE> elements = new NodePositionList<TYPE>(); //Cria uma lista de nodos vazia.		
		for (Position<TYPE> position : positions) elements.addLast(position.element()); //Percorre a coleção de nodos da árvore e adiciona seus elementos a lista criada.
		
		return elements.iterator(); //Retorna um iterador com todos os elementos da árvore.
	}
	
	//Retorna uma coleção iterável de todos os nodos armazenados na árvore.
	public Iterable<Position<TYPE>> positions() {

		PositionList<Position<TYPE>> positions = new NodePositionList<Position<TYPE>>(); //Cria uma lista de nodos vazia.
		
		if (size != 0) preorderPositions(root(), positions); //O algoritmo percorre a árvore recursivamente e preenche a lista criada.
		return positions; //Retorna uma coleção iterável de todos os nodos da árvore.
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

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		Position<TYPE> left = (Position<TYPE>) positionValidate.getLeft(); //Armazena o filho esquerdo do nodo.
		
		if (left == null) throw new BoundaryViolationException("Não há filhos a esquerda!"); //Verifica se o filho esquerdo do nodo é valido.		
		return left; //Retorna o filho esquerdo do nodo.
	}	
	
	//Retorna o filho direito do nodo.
	public Position<TYPE> right(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		Position<TYPE> right = (Position<TYPE>) positionValidate.getRight(); //Armazena o filho direito do nodo.
		
		if (right == null) throw new BoundaryViolationException("Não há filhos a direita!"); //Verifica se o filho direito do nodo é valido.
		return right; //Retorna o filho direito do nodo.
	}
	
	//Retorna o valor booleano representando se o nodo tem filho a esquerda.
	public boolean hasLeft(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		return (positionValidate.getLeft() != null); //Retorna o status do nodo.
	}
	
	//Retorna o valor booleano representando se o nodo tem filho a direita.
	public boolean hasRight(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		
		return (positionValidate.getRight() != null); //Retorna o status do nodo.
	}
	
	//Retorna uma coleção iterável contendo todos os nodos da árvore usando o algoritmo inorder.
	public Iterable<Position<TYPE>> positionsInorder() {

		PositionList<Position<TYPE>> positions = new NodePositionList<Position<TYPE>>(); //Cria uma lista de posições vazia.
		
		if (size != 0) inorderPositions(root(), positions); //Chama recursivamente o método inorderPositions.		
		return positions; //Retorna a coleção com todos os nodos da árvore.
	}
	
	//Retorna uma coleção iterável contendo todos os nodos da árvore.
	public void inorderPositions(Position<TYPE> position, PositionList<Position<TYPE>> positionList) throws InvalidPositionException{
		
		if(hasLeft(position)) {inorderPositions(left(position), positionList);} //Verifica se o nodo é o filho da esquerda e preenche a lista com o filho esquerdo do nodo recursivamente.
		positionList.addLast(position); //Adiciona o nodo na ultima posição da lista.
		if(hasRight(position)) {inorderPositions(right(position), positionList);} //Verifica se o nodo é o filho da direita e preenche a lista com o filho direito do nodo recursivamente.
	}
	
	//Retorna o irmão de um determinado nodo.
	public Position<TYPE> sibling(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		BTPosition<TYPE> parent = positionValidate.getParent(); //Armazena o pai do nodo.

		if (parent != null) { //Verifica se o pai do nodo é valido.

			BTPosition<TYPE> sibling; //Armazena a referencia ao irmão do nodo.	
			BTPosition<TYPE> left = parent.getLeft(); //Armazena o filho esquerdo do pai do nodo.
	
			if (left == positionValidate) {sibling = parent.getRight();} //Verifica se o filho esquerdo do pai do nodo é o mesmo nodo informado, caso seja o irmão do nodo é o filho direito do pai do nodo informado.
			else {sibling = parent.getLeft();} //Caso contrario, o irmão do nodo é o filho esquerdo do pai do nodo informado.
	
			if (sibling != null) {return sibling;} //Se houver um irmão, o método irá retornar.
		}
		throw new BoundaryViolationException("Não há irmão para esse nodo!"); //Caso o pai do nodo informado seja null, libera uma exceção.
	}
	
	//Adiciona uma raiz na árvore criada.
	public Position<TYPE> addRoot(TYPE element) throws NonEmptyTreeException {

		if (!isEmpty()) {throw new NonEmptyTreeException("A árvore já tem uma raiz!");} //Verifica se a árvore está vazia.

		size = 1; //Muda-se o tamanho da árvore para um.
		root = createNode(element, null, null, null); //Cria um nodo com o elemento informado e referencia ele à raiz da árvore.
		
		return root; //Retorna a raiz adicionada.
	}
	
	//Adiciona o filho esquerdo em um determinado nodo.
	public Position<TYPE> insertLeft(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		Position<TYPE> left = (Position<TYPE>) positionValidate.getLeft(); //Armazena o filho esquerdo do pai do nodo.

		if (left != null) {throw new InvalidPositionException("Esse nó já tem um filho esquerdo!");} //Verifica se o nodo já tem um filho a esquerda.

		BTPosition<TYPE> newNode = createNode(element, positionValidate, null, null); //Cria um nodo com o elemento informado.
		positionValidate.setLeft(newNode); //Referencia o nodo criado ao filho esquerdo do nodo informado.

		size++; //Acrescenta-se +1 ao número total de elementos contidos na árvore.
		return newNode; //Retorna o nodo criado.
	}	
	
	//Adiciona o filho direito em um determinado nodo.
	public Position<TYPE> insertRight(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.
		Position<TYPE> right = (Position<TYPE>) positionValidate.getRight(); //Armazena o filho direito do pai do nodo.

		if (right != null) {throw new InvalidPositionException("Esse nó já tem um filho direito!");} //Verifica se o nodo já tem um filho a direita.

		BTPosition<TYPE> newNode = createNode(element, positionValidate, null, null); //Cria um nodo com o elemento informado.
		positionValidate.setRight(newNode); //Referencia o nodo criado ao filho direito do nodo informado.

		size++; //Acrescenta-se +1 ao número total de elementos contidos na árvore.
		return newNode; //Retorna o nodo criado.
	}
	
	//Remove o nodo informado caso não tenha filhos ou apenas um.
	public TYPE remove(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.

		BTPosition<TYPE> left = positionValidate.getLeft(); //Armazena o filho esquerdo do nodo.
		BTPosition<TYPE> right = positionValidate.getRight(); //Armazena o filho direito do nodo.

		if (left != null && right != null) {throw new InvalidPositionException("Não é possível remover um nó com dois filhos!");} //Verifica se o nodo tem filhos em ambas posições.

		BTPosition<TYPE> one; //Referencia ao filho do nodo.

		if (left != null) {one = left;} //Verifica se o filho do nodo é o filho esquerdo.
		else if (right != null) {one = right;} //Verifica se o filho do nodo é o filho direito.
		else {one = null;} //Caso não seja o filho esquerdo nem o filho direito a variavel recebe null.

		if (positionValidate == root) { //Verifica se a posição informada se refere a raiz da árvore.
	
			if (one != null) {one.setParent(null);}	//Altera a referencia do pai sobre o nodo filho para null.
			root = one; //O filho do nodo informado passa a ser a raiz.
			
		} else { //Caso contrario.

			BTPosition<TYPE> parent = positionValidate.getParent(); //Armazena o pai do nodo.
	
			if (positionValidate == parent.getLeft()) {parent.setLeft(one);} //Verifica se o nodo informado é o filho esquerdo, caso seja, o pai do nodo informado recebe como filho esquerdo o filho nodo informado.
			else {parent.setRight(one);} //Caso contrario o pai do nodo informado recebe como filho direito o filho nodo informado.
	
			if (one != null) {one.setParent(parent);} //Altera a referencia de pai do filho do nodo informado.
		}
		
		size--; //Diminui-se 1 do número total de elementos contidos na árvore.
		return position.element(); //Retorna o elemento contido no nodo removido.
	}
	
	//Conecta duas árvores para serem subárvores de um nodo externo.
	public void attach(Position<TYPE> position, BinaryTree<TYPE> treeOne, BinaryTree<TYPE> treeTwo) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posição informada é valida.

		if (isInternal(position)) throw new InvalidPositionException("Não é possível anexar sobre nós internos"); //Verifica se a posição informada é um nodo interno, caso seja libera uma exceção.

		if (!treeOne.isEmpty()) { //Verifica se a primeira subárvore está vazia.
	
			BTPosition<TYPE> rootOne = validatePosition(treeOne.root()); //Verifica se a raiz da subárvore 1 é uma posição é valida.
			positionValidate.setLeft(rootOne);	//Adiciona ao filho esquerdo do nodo informado a raiz da subárvore informada.
			rootOne.setParent(positionValidate); //Altera a referncia do pai da raiz da subárvore para o nodo informado.
		}
		if (!treeTwo.isEmpty()) { //Verifica se a segunda subárvore está vazia.

			BTPosition<TYPE> rootTwo = validatePosition(treeTwo.root()); //Verifica se a raiz da subárvore 2 é uma posição é valida.
			positionValidate.setRight(rootTwo);	//Adiciona ao filho direito do nodo informado a raiz da subárvore informada.
			rootTwo.setParent(positionValidate); //Altera a referncia do pai da raiz da subárvore para o nodo informado.
		}
	}
	
	//Constroi uma árvore binaria de expressões.
	public LinkedBinaryTree<String> buildExpression(String expression){
		
		//Pilha que irá armazenar os elementos da expressão passada no paramentro.
		NodeStack<LinkedBinaryTree<String>> stack = new NodeStack<LinkedBinaryTree<String>>();
		
		//Cria um arranjo de chars que recebe a string(expression) informada.
		char characters[] = expression.toCharArray();
		
		//Laço que percorre os elementos do arranjo de chars.
		for(int i = 0; i < expression.length(); i++) {
			
			//Verifica se o caracterer atual da expressão é uma variavel ou operador.
			if(characters[i] != '(' && characters[i] != ')') {
				
				//Cria uma nova árvore binaria vazia.
				LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
				
				//Adiciona o elemento da expressão na raiz da árvore.
				tree.addRoot(String.valueOf(characters[i]));
				
				//Adiciona a árvore criada na pilha.
				stack.push(tree);
			
			//Verifica se o caracterer atual da expressão é um '(' (Parenteses aberto), caso seja o laço continua.
			}else if(characters[i] == '(') {}
			
			//Caso seja um ')' (Parenteses fechado).
			else {
				
				//Coloca o ultimo elemento da pilha na variavel, (uma variavel da expressão).
				LinkedBinaryTree<String> treeTwo = stack.pop();
				
				//Coloca o ultimo elemento da pilha na variavel, (um operador da expressão).
				LinkedBinaryTree<String> operator = stack.pop();
				
				//Coloca o ultimo elemento da pilha na variavel, (uma variavel da expressão).
				LinkedBinaryTree<String> treeOne = stack.pop();
				
				//Cria uma nova árvore com variaveis e operador.
				operator.attach(operator.root(), treeOne, treeTwo);
				
				//Adiciona a nova árvore criada na pilha.
				stack.push(operator);
			}
		}
		return stack.pop(); //Retorna a árvore de expressões, que está no topo da pilha.
	}
	
	//Seleciona os elementos da árvore binaria e concatena-os em uma String utilizando o preOrder.
	public String binaryPreorder(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//String que armazenará e concatenará os elementos da árvore.
		String expression = position.element().toString();
		
		//Verifica se o nodo da árvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o método recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += binaryPreorder(tree, children);
		}
		
		//Verifica se o nodo da árvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o método recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += binaryPreorder(tree, children);			
		}
		
		//Retorna a variavel com os elementos da arvore.
		return expression;		
	}
	
	//Seleciona os elementos da árvore binaria e concatena-os em uma String utilizando o posToOrder.
	public String binaryPostorder(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//String que armazenará e concatenará os elementos da árvore.
		String expression = "";
		
		//Verifica se o nodo da árvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o método recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += binaryPostorder(tree, children);
		}
		
		//Verifica se o nodo da árvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o método recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += binaryPostorder(tree, children);			
		}
		
		//String que armazenará e concatenará os elementos da árvore.
		expression += position.element().toString();			
		return expression; //Retorna a variavel com os elementos da arvore.
	}
	
	//Calcula os elementos da árvore binaria de expressões.
	public double evaluateExpression(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//Verifica se o nodo tem filhos.
		if(tree.isInternal(position)) {
			
			//Armazena o elemento do nodo na variavel, tal elemento deve ser um operador aritmetico.
			String operator = position.element().toString();
			
			//Chama recursivamente o método evaluateExpression até ele retornar um o elemento do ultimo filho da esquerda, tal elemento deve ser uma variavel.
			Double x = evaluateExpression(tree, tree.left(position));
			
			//Chama recursivamente o método evaluateExpression até ele retornar um o elemento do ultimo filho da direita, tal elemento deve ser uma variavel.
			Double y = evaluateExpression(tree, tree.right(position));
			
			//Variavel que irá armazenar o calculo dos filhos.
			double operation = 0;
			
			//Verifica qual será o tipo de operação aritmetica a ser realizada.
			switch(operator) {				
				case "+": //Soma.
					operation = x + y; //Armazena o valor da soma.
					break;
				case "-": //Subtração.
					operation =  x - y; //Armazena o valor da subtração.
					break;
				case "*": //Multiplicação.
					operation =  x * y; //Armazena o valor da multiplicação.
					break;
				case "/": // Divisão.
					operation = x / y; //Armazena o valor da divisão.
					break;
			}
			
			return operation; //Retorna o valor calculado dos filhos.
			
		//Caso seja o ultimo filho.		
		}else {
			
			return Double.valueOf(position.element().toString()); //Retorna o valor convertido para double.
		}		
	}
	
	//Seleciona os elementos da árvore binaria e concatena-os em uma String utilizando o inorder.
	public String inorder(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//String que armazenará e concatenará os elementos da árvore.
		String expression = "";
		
		//Verifica se o nodo da árvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o método recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += inorder(tree, children);
		}
		
		//String que armazenará e concatenará os elementos da árvore.
		expression += position.element().toString();
		
		//Verifica se o nodo da árvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o método recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += inorder(tree, children);			
		}
		return expression; //Retorna a variavel com os elementos da arvore.
	}	
	
	//Seleciona os elementos da árvore binaria e concatena-os em uma String utilizando o positionsInorder.
	public String toStringInOrder(LinkedBinaryTree<String> tree) {
		
		//String que armazenará os elementos dos nodos da árvore.
		String elements = "";
		
		//Laço de repetição que percorerá os nodos da árvore em ordem.
		for(Position<String> filho: tree.positionsInorder()) {
			
			//Concatena os nodos da árvore seguida de uma ",".
			elements += ", " + filho.element().toString();
		}
		
		//Remove a virgula e o espaco do ultimo elemento da string.
		elements = (elements.length() == 0 ? elements : elements.substring(2));
		
		//Retorna a string de elementos.
		return "[" + elements + "]";
	}
	
	//Imprimi no console a árvore binaria, de acordo com seus nodos.
	public void pintTreeBinary(LinkedBinaryTree<TYPE> tree) {
		
		//Instancia uma lista de nodos, para armazenar os nodos.
		PositionList<Position<TYPE>> positions = new NodePositionList<Position<TYPE>>();
		
		//Usando o método inorderPositions preenche a lista com os nodos em ordem (inOrden).
		if (size != 0) inorderPositions(root(), positions);
		
		//Cria uma matriz de acordo com o tamanho da árvore, Linhas (altura da árvoe) Colunas(tamanho da lista de nodos).
		String flat[][] = new String[height(tree, tree.root()) + 1][positions.size()];		
        
		//Percorre a matriz preenchendo ela com espaços vazios.
        for(int i = 0; i < flat.length; i++)
            for(int j = 0; j < flat[0].length; j++){flat[i][j] = " ";}
        
        //Variavel que guarda a quantidade de nodos que foram visitados.
        int visits = 0;
        
        //Esse laço percorrerá a lista de nodos.
        for(Position<TYPE> children: positions) {
        	
        	//Para cada nodo, preenche-se a matriz, sendo a profundidade do nodo a linha e quantidade de visits a coluna que será guardada o elemento do nodo.
        	flat[depth(tree, children)][visits] = children.element().toString();
        	
        	//Acrescenta +1 na variavel visits.
        	visits++;
        }
        
      //Percorre a matriz imprimindo a na tela do console.
        for(int i = 0; i < flat.length; i++){
            for(int j = 0; j < flat[0].length; j++){
                
                System.out.print(flat[i][j] + " "); //Imprimi o elemento da matriz seguido de um espaço em branco.
                
            }            
            System.out.println(); //A cada final de linha simula uma quebra de linha.             
        } 				
	}
	
	//Calcula a profundidade de um nodo da árvore.
	public int depth(LinkedBinaryTree<TYPE> tree, Position<TYPE> position) {
		
		if(tree.isRoot(position)) {return 0;} //Verifica se posição informada é a raiz da arvore, caso seja retorna 0.
		else {return 1 + depth(tree, tree.parent(position));} //Caso contrario, chama recursivamente até encontrar a raiz, sempre somando +1 e retorna essa soma.		
	}
	
	//Ccalcula a altura de uma árvore.
	public int height(LinkedBinaryTree<TYPE> tree, Position<TYPE> position) {
		
		if(tree.isExternal(position)) {return 0;} //Verifica se o nodo da árvore é interno, caso seja retorna 0.
		else {
			
			int height = 0; //Armazena a soma recursiva da altura dos nodos.
			
			//Percorre a árvore e utiliza o Math.max para calcular a altura junto com uma chamada recursiva.
			for(Position<TYPE> children : tree.children(position)) {height = Math.max(height, height(tree, children));}
			
			return 1 + height; //Retorna a altura da árvore.
		}
	}
	
	//Seleciona os elementos da árvore binaria e concatena-os em uma String utilizando o eulerTour.	
	public String eulerTour(BinaryTree<TYPE> tree, Position<TYPE> position) {		
		
		//String que armazenará e concatenará os elementos da árvore.
		String expression = "";		
		expression += position.element().toString();
		
		//Verifica se o nodo da árvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o método recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += eulerTour(tree, children);
		}
		
		//String que armazenará e concatenará os elementos da árvore.
		expression += position.element().toString();

		//Verifica se o nodo da árvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o método recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += eulerTour(tree, children);			
		}
		
		//String que armazenará e concatenará os elementos da árvore.
		expression += position.element().toString();
		return expression; //Retorna a variavel com os elementos da arvore.
	}
	
	//Imprimi a expressão representada pela árvore.
	public String printExpression(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//String que armazenará e concatenará os elementos da árvore.
		String expression = "";	
		
		//Verifica se o nodo é interno, caso seja concatena a string com "(" (parenteses aberto).
		if(tree.isInternal(position)) {expression += "(";}
		
		//Verifica se o nodo é filho da esquerda, caso seja concatena o elemento recursivamente com a string.
		if(tree.hasLeft(position)) {expression += printExpression(tree, tree.left(position));}
		
		//Verifica se o nodo é interno, caso seja concatena o elemento recursivamente com a string.
		if(tree.isInternal(position)) {expression += position.element().toString();}
		
		//caso contrario, concatena apenas o elemento do nodo com a string.
		else {expression += position.element().toString();}
		
		//Verifica se o nodo é filho da direita, caso seja concatena o elemento recursivamente com a string.
		if(tree.hasRight(position)) {expression += printExpression(tree, tree.right(position));}
		
		//Verifica se o nodo é interno, caso seja concatena a string com ")" (parenteses fechado).
		if(tree.isInternal(position)) {expression += ")";}
		
		//Retorna a variavel com a espressão que representa a árvore.
		return expression;		
	}
	
	//Conta a quantidade de nodos externos e do lado esquerdo de uma árvore binaria.
	public int countLeft(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//Variavel armazenará a quantidade de nodos esquerdos e externos da arvore.
		int amounts = 0;		
		
		//Verifica se o nodo da árvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o método recursivamente e soma o valor recebido.
			amounts += countLeft(tree, children);
		}
		
		//Verifica se o nodo atual é a raiz da árvore.
		if(position == tree.root()) {
			
			//Caso seja, retorna a quantidade de nodos da árvore.
			return amounts;
		
		//Caso contrario.
		}else {
			
			//Verifica se o nodo atual é externo.
			if(tree.isExternal(position)) {
				
				//Caso seja soma + 1;
				amounts += 1;
			}
		}
		
		//Verifica se o nodo da árvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o método recursivamente e soma o valor recebido.
			amounts += countLeft(tree, children);			
		}
		
		//Retorna a quantidade de nodos da árvore.
		return amounts;
	}
	
	//Conta a quantidade de nodos externos e do lado direito de uma árvore binaria.
	public int countRight(BinaryTree<TYPE> tree, Position<TYPE> position) {		
		
		//Variavel armazenará a quantidade de nodos esquerdos e externos da arvore.
		int amounts = 0;			
		
		//Verifica se o nodo da árvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o método recursivamente e soma o valor recebido.
			amounts += countLeft(tree, children);			
		}		
		
		//Verifica se o nodo atual é a raiz da árvore.
		if(position == tree.root()) {
			
			//Caso seja, retorna a quantidade de nodos da árvore.
			return amounts;
		
		//Caso contrario.
		}else {
			
			//Verifica se o nodo atual é externo.
			if(tree.isExternal(position)) {
				
				//Caso seja soma + 1;
				amounts += 1;
			}
		}		
		
		//Verifica se o nodo da árvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o método recursivamente e soma o valor recebido.
			amounts += countLeft(tree, children);
		}
		
		//Retorna a quantidade de nodos da árvore.
		return amounts;
	}	
	
	//Concatena todos os elementos da lista em uma string.
	public static <TYPE> String toString(LinkedBinaryTree<TYPE> tree) {
		
		String elements = ""; //Inicia a string.
		
		for(TYPE element : tree) {elements += ", " + element;} //Percorre a árvore e concatena os elementos e o separador à string.
		
		elements = (elements.length() == 0 ? elements : elements.substring(2)); //Remove o último separador da string.		
		
		return "[" + elements + "]"; //Retorna a string de elementos da árvore entre chaves.		
	}	
	
	//Método toString sem parametro.
	public String toString() {return toString(this);}
	
}