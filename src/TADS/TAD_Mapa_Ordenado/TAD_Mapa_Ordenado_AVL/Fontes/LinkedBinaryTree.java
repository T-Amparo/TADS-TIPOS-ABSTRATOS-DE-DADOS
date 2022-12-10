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

//Implementa��o do TAD �RVORE BIN�RIA.
public class LinkedBinaryTree<TYPE> implements BinaryTree<TYPE> {
	
	//Declara��o das Variaveis.
	protected BTPosition<TYPE> root; //Armazena a referencia � raiz da �rvore.
	protected int size; //N�mero total de elementos contidos na �rvore.
	
	//M�todo Construtor.
	public LinkedBinaryTree() {

		root = null; //Inicializa a referencia � raiz da �rvore com null.
		size = 0; //Inicializa o size com 0.
	}
	
	//Retorna o n�mero de elementos contidos na �rvore.
	public int size() {return size;}
	
	//Retorna o status da �RVORE GENERICA => True=Vazia, False=N�o Vazia.
	public boolean isEmpty() {return (size == 0);}
	
	//Retorna o status do nodo => True=Interno, False=N�o Interno.
	public boolean isInternal(Position<TYPE> position) throws InvalidPositionException {

		validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return (hasLeft(position) || hasRight(position)); //Retorna o status do nodo.
	}
	
	//Retorna o status do nodo => True=Externo, False=N�o Externo.
	public boolean isExternal(Position<TYPE> position) throws InvalidPositionException {return !isInternal(position);}
	
	//Retorna o status do nodo => True=Raiz, False=N�o Raiz.
	public boolean isRoot(Position<TYPE> position) throws InvalidPositionException {

		validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return (position == root()); //Retorna o status do nodo.
	}	
	
	//Verifica se a posi��o informada � v�lida e a converte para BTPosition se for v�lida.
	protected BTPosition<TYPE> validatePosition(Position<TYPE> position) throws InvalidPositionException {

		if (position == null || !(position instanceof BTPosition)) throw new InvalidPositionException("Essa posi��o � inv�lida"); //Realiza � verifica��o.
		return (BTPosition<TYPE>) position; //Retorna a posi��o validada e convertida.
	}
	
	//Cria um novo nodo para a �rvore.
	protected BTPosition<TYPE> createNode(TYPE element, BTPosition<TYPE> parent, BTPosition<TYPE> left, BTPosition<TYPE> right) {
		
		return new BTNode<TYPE>(element, parent, left, right); //Retorna o nodo criado.
	}
	
	//Preenche uma lista com nodos da �rvore percorrida.
	protected void preorderPositions(Position<TYPE> position, PositionList<Position<TYPE>> positionList) throws InvalidPositionException {

		positionList.addLast(position); //Adiciona o nodo na ultima posi��o da lista.

		if (hasLeft(position)) preorderPositions(left(position), positionList); //Verifica se o nodo � o filho da esquerda e preenche a lista recursivamente.
		if (hasRight(position)) preorderPositions(right(position), positionList); //Verifica se o nodo � o filho da direita e preenche a lista recursivamente.
	}
	
	//Troca o elemento armazenado em um determinado nodo e retorna o elemento que estava armazenado nele.
	public TYPE replace(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		TYPE elementRemoved = position.element(); //Armazena o elemento antigo.
		positionValidate.setElement(element); //Define o novo elemento para o nodo conforme informado.
		
		return elementRemoved; //Retorna o elemento antigo.
	}
	
	//Retorna a raiz da �rvore.
	public Position<TYPE> root() throws EmptyTreeException {

		if (root == null) throw new EmptyTreeException("A �rvore est� vazia!"); //Verifica se a �rvore est� vazia.
		return root; //Retorna a raiz da �rvore.
	}	
	
	//Retorna o pai de um dado nodo.
	public Position<TYPE> parent(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		Position<TYPE> parent = (Position<TYPE>) positionValidate.getParent(); //Armazena o pai do nodo.
		
		if (parent == null) throw new BoundaryViolationException("N�o h� pai para esse nodo!"); //Verifica se o pai do nodo � valido.		
		return parent; //Retorna o pai do nodo.
	}
	
	//Retorna um iterador sobre todos os elementos da �rvore.
	public Iterator<TYPE> iterator() {

		Iterable<Position<TYPE>> positions = positions(); //Armazena uma cole��o iter�vel de todos os nodos armazenados na �rvore.
		
		PositionList<TYPE> elements = new NodePositionList<TYPE>(); //Cria uma lista de nodos vazia.		
		for (Position<TYPE> position : positions) elements.addLast(position.element()); //Percorre a cole��o de nodos da �rvore e adiciona seus elementos a lista criada.
		
		return elements.iterator(); //Retorna um iterador com todos os elementos da �rvore.
	}
	
	//Retorna uma cole��o iter�vel de todos os nodos armazenados na �rvore.
	public Iterable<Position<TYPE>> positions() {

		PositionList<Position<TYPE>> positions = new NodePositionList<Position<TYPE>>(); //Cria uma lista de nodos vazia.
		
		if (size != 0) preorderPositions(root(), positions); //O algoritmo percorre a �rvore recursivamente e preenche a lista criada.
		return positions; //Retorna uma cole��o iter�vel de todos os nodos da �rvore.
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

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		Position<TYPE> left = (Position<TYPE>) positionValidate.getLeft(); //Armazena o filho esquerdo do nodo.
		
		if (left == null) throw new BoundaryViolationException("N�o h� filhos a esquerda!"); //Verifica se o filho esquerdo do nodo � valido.		
		return left; //Retorna o filho esquerdo do nodo.
	}	
	
	//Retorna o filho direito do nodo.
	public Position<TYPE> right(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		Position<TYPE> right = (Position<TYPE>) positionValidate.getRight(); //Armazena o filho direito do nodo.
		
		if (right == null) throw new BoundaryViolationException("N�o h� filhos a direita!"); //Verifica se o filho direito do nodo � valido.
		return right; //Retorna o filho direito do nodo.
	}
	
	//Retorna o valor booleano representando se o nodo tem filho a esquerda.
	public boolean hasLeft(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return (positionValidate.getLeft() != null); //Retorna o status do nodo.
	}
	
	//Retorna o valor booleano representando se o nodo tem filho a direita.
	public boolean hasRight(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		
		return (positionValidate.getRight() != null); //Retorna o status do nodo.
	}
	
	//Retorna uma cole��o iter�vel contendo todos os nodos da �rvore usando o algoritmo inorder.
	public Iterable<Position<TYPE>> positionsInorder() {

		PositionList<Position<TYPE>> positions = new NodePositionList<Position<TYPE>>(); //Cria uma lista de posi��es vazia.
		
		if (size != 0) inorderPositions(root(), positions); //Chama recursivamente o m�todo inorderPositions.		
		return positions; //Retorna a cole��o com todos os nodos da �rvore.
	}
	
	//Retorna uma cole��o iter�vel contendo todos os nodos da �rvore.
	public void inorderPositions(Position<TYPE> position, PositionList<Position<TYPE>> positionList) throws InvalidPositionException{
		
		if(hasLeft(position)) {inorderPositions(left(position), positionList);} //Verifica se o nodo � o filho da esquerda e preenche a lista com o filho esquerdo do nodo recursivamente.
		positionList.addLast(position); //Adiciona o nodo na ultima posi��o da lista.
		if(hasRight(position)) {inorderPositions(right(position), positionList);} //Verifica se o nodo � o filho da direita e preenche a lista com o filho direito do nodo recursivamente.
	}
	
	//Retorna o irm�o de um determinado nodo.
	public Position<TYPE> sibling(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		BTPosition<TYPE> parent = positionValidate.getParent(); //Armazena o pai do nodo.

		if (parent != null) { //Verifica se o pai do nodo � valido.

			BTPosition<TYPE> sibling; //Armazena a referencia ao irm�o do nodo.	
			BTPosition<TYPE> left = parent.getLeft(); //Armazena o filho esquerdo do pai do nodo.
	
			if (left == positionValidate) {sibling = parent.getRight();} //Verifica se o filho esquerdo do pai do nodo � o mesmo nodo informado, caso seja o irm�o do nodo � o filho direito do pai do nodo informado.
			else {sibling = parent.getLeft();} //Caso contrario, o irm�o do nodo � o filho esquerdo do pai do nodo informado.
	
			if (sibling != null) {return sibling;} //Se houver um irm�o, o m�todo ir� retornar.
		}
		throw new BoundaryViolationException("N�o h� irm�o para esse nodo!"); //Caso o pai do nodo informado seja null, libera uma exce��o.
	}
	
	//Adiciona uma raiz na �rvore criada.
	public Position<TYPE> addRoot(TYPE element) throws NonEmptyTreeException {

		if (!isEmpty()) {throw new NonEmptyTreeException("A �rvore j� tem uma raiz!");} //Verifica se a �rvore est� vazia.

		size = 1; //Muda-se o tamanho da �rvore para um.
		root = createNode(element, null, null, null); //Cria um nodo com o elemento informado e referencia ele � raiz da �rvore.
		
		return root; //Retorna a raiz adicionada.
	}
	
	//Adiciona o filho esquerdo em um determinado nodo.
	public Position<TYPE> insertLeft(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		Position<TYPE> left = (Position<TYPE>) positionValidate.getLeft(); //Armazena o filho esquerdo do pai do nodo.

		if (left != null) {throw new InvalidPositionException("Esse n� j� tem um filho esquerdo!");} //Verifica se o nodo j� tem um filho a esquerda.

		BTPosition<TYPE> newNode = createNode(element, positionValidate, null, null); //Cria um nodo com o elemento informado.
		positionValidate.setLeft(newNode); //Referencia o nodo criado ao filho esquerdo do nodo informado.

		size++; //Acrescenta-se +1 ao n�mero total de elementos contidos na �rvore.
		return newNode; //Retorna o nodo criado.
	}	
	
	//Adiciona o filho direito em um determinado nodo.
	public Position<TYPE> insertRight(Position<TYPE> position, TYPE element) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.
		Position<TYPE> right = (Position<TYPE>) positionValidate.getRight(); //Armazena o filho direito do pai do nodo.

		if (right != null) {throw new InvalidPositionException("Esse n� j� tem um filho direito!");} //Verifica se o nodo j� tem um filho a direita.

		BTPosition<TYPE> newNode = createNode(element, positionValidate, null, null); //Cria um nodo com o elemento informado.
		positionValidate.setRight(newNode); //Referencia o nodo criado ao filho direito do nodo informado.

		size++; //Acrescenta-se +1 ao n�mero total de elementos contidos na �rvore.
		return newNode; //Retorna o nodo criado.
	}
	
	//Remove o nodo informado caso n�o tenha filhos ou apenas um.
	public TYPE remove(Position<TYPE> position) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.

		BTPosition<TYPE> left = positionValidate.getLeft(); //Armazena o filho esquerdo do nodo.
		BTPosition<TYPE> right = positionValidate.getRight(); //Armazena o filho direito do nodo.

		if (left != null && right != null) {throw new InvalidPositionException("N�o � poss�vel remover um n� com dois filhos!");} //Verifica se o nodo tem filhos em ambas posi��es.

		BTPosition<TYPE> one; //Referencia ao filho do nodo.

		if (left != null) {one = left;} //Verifica se o filho do nodo � o filho esquerdo.
		else if (right != null) {one = right;} //Verifica se o filho do nodo � o filho direito.
		else {one = null;} //Caso n�o seja o filho esquerdo nem o filho direito a variavel recebe null.

		if (positionValidate == root) { //Verifica se a posi��o informada se refere a raiz da �rvore.
	
			if (one != null) {one.setParent(null);}	//Altera a referencia do pai sobre o nodo filho para null.
			root = one; //O filho do nodo informado passa a ser a raiz.
			
		} else { //Caso contrario.

			BTPosition<TYPE> parent = positionValidate.getParent(); //Armazena o pai do nodo.
	
			if (positionValidate == parent.getLeft()) {parent.setLeft(one);} //Verifica se o nodo informado � o filho esquerdo, caso seja, o pai do nodo informado recebe como filho esquerdo o filho nodo informado.
			else {parent.setRight(one);} //Caso contrario o pai do nodo informado recebe como filho direito o filho nodo informado.
	
			if (one != null) {one.setParent(parent);} //Altera a referencia de pai do filho do nodo informado.
		}
		
		size--; //Diminui-se 1 do n�mero total de elementos contidos na �rvore.
		return position.element(); //Retorna o elemento contido no nodo removido.
	}
	
	//Conecta duas �rvores para serem sub�rvores de um nodo externo.
	public void attach(Position<TYPE> position, BinaryTree<TYPE> treeOne, BinaryTree<TYPE> treeTwo) throws InvalidPositionException {

		BTPosition<TYPE> positionValidate = validatePosition(position); //Verifica se a posi��o informada � valida.

		if (isInternal(position)) throw new InvalidPositionException("N�o � poss�vel anexar sobre n�s internos"); //Verifica se a posi��o informada � um nodo interno, caso seja libera uma exce��o.

		if (!treeOne.isEmpty()) { //Verifica se a primeira sub�rvore est� vazia.
	
			BTPosition<TYPE> rootOne = validatePosition(treeOne.root()); //Verifica se a raiz da sub�rvore 1 � uma posi��o � valida.
			positionValidate.setLeft(rootOne);	//Adiciona ao filho esquerdo do nodo informado a raiz da sub�rvore informada.
			rootOne.setParent(positionValidate); //Altera a referncia do pai da raiz da sub�rvore para o nodo informado.
		}
		if (!treeTwo.isEmpty()) { //Verifica se a segunda sub�rvore est� vazia.

			BTPosition<TYPE> rootTwo = validatePosition(treeTwo.root()); //Verifica se a raiz da sub�rvore 2 � uma posi��o � valida.
			positionValidate.setRight(rootTwo);	//Adiciona ao filho direito do nodo informado a raiz da sub�rvore informada.
			rootTwo.setParent(positionValidate); //Altera a referncia do pai da raiz da sub�rvore para o nodo informado.
		}
	}
	
	//Constroi uma �rvore binaria de express�es.
	public LinkedBinaryTree<String> buildExpression(String expression){
		
		//Pilha que ir� armazenar os elementos da express�o passada no paramentro.
		NodeStack<LinkedBinaryTree<String>> stack = new NodeStack<LinkedBinaryTree<String>>();
		
		//Cria um arranjo de chars que recebe a string(expression) informada.
		char characters[] = expression.toCharArray();
		
		//La�o que percorre os elementos do arranjo de chars.
		for(int i = 0; i < expression.length(); i++) {
			
			//Verifica se o caracterer atual da express�o � uma variavel ou operador.
			if(characters[i] != '(' && characters[i] != ')') {
				
				//Cria uma nova �rvore binaria vazia.
				LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
				
				//Adiciona o elemento da express�o na raiz da �rvore.
				tree.addRoot(String.valueOf(characters[i]));
				
				//Adiciona a �rvore criada na pilha.
				stack.push(tree);
			
			//Verifica se o caracterer atual da express�o � um '(' (Parenteses aberto), caso seja o la�o continua.
			}else if(characters[i] == '(') {}
			
			//Caso seja um ')' (Parenteses fechado).
			else {
				
				//Coloca o ultimo elemento da pilha na variavel, (uma variavel da express�o).
				LinkedBinaryTree<String> treeTwo = stack.pop();
				
				//Coloca o ultimo elemento da pilha na variavel, (um operador da express�o).
				LinkedBinaryTree<String> operator = stack.pop();
				
				//Coloca o ultimo elemento da pilha na variavel, (uma variavel da express�o).
				LinkedBinaryTree<String> treeOne = stack.pop();
				
				//Cria uma nova �rvore com variaveis e operador.
				operator.attach(operator.root(), treeOne, treeTwo);
				
				//Adiciona a nova �rvore criada na pilha.
				stack.push(operator);
			}
		}
		return stack.pop(); //Retorna a �rvore de express�es, que est� no topo da pilha.
	}
	
	//Seleciona os elementos da �rvore binaria e concatena-os em uma String utilizando o preOrder.
	public String binaryPreorder(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expression = position.element().toString();
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += binaryPreorder(tree, children);
		}
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += binaryPreorder(tree, children);			
		}
		
		//Retorna a variavel com os elementos da arvore.
		return expression;		
	}
	
	//Seleciona os elementos da �rvore binaria e concatena-os em uma String utilizando o posToOrder.
	public String binaryPostorder(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expression = "";
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += binaryPostorder(tree, children);
		}
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += binaryPostorder(tree, children);			
		}
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		expression += position.element().toString();			
		return expression; //Retorna a variavel com os elementos da arvore.
	}
	
	//Calcula os elementos da �rvore binaria de express�es.
	public double evaluateExpression(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//Verifica se o nodo tem filhos.
		if(tree.isInternal(position)) {
			
			//Armazena o elemento do nodo na variavel, tal elemento deve ser um operador aritmetico.
			String operator = position.element().toString();
			
			//Chama recursivamente o m�todo evaluateExpression at� ele retornar um o elemento do ultimo filho da esquerda, tal elemento deve ser uma variavel.
			Double x = evaluateExpression(tree, tree.left(position));
			
			//Chama recursivamente o m�todo evaluateExpression at� ele retornar um o elemento do ultimo filho da direita, tal elemento deve ser uma variavel.
			Double y = evaluateExpression(tree, tree.right(position));
			
			//Variavel que ir� armazenar o calculo dos filhos.
			double operation = 0;
			
			//Verifica qual ser� o tipo de opera��o aritmetica a ser realizada.
			switch(operator) {				
				case "+": //Soma.
					operation = x + y; //Armazena o valor da soma.
					break;
				case "-": //Subtra��o.
					operation =  x - y; //Armazena o valor da subtra��o.
					break;
				case "*": //Multiplica��o.
					operation =  x * y; //Armazena o valor da multiplica��o.
					break;
				case "/": // Divis�o.
					operation = x / y; //Armazena o valor da divis�o.
					break;
			}
			
			return operation; //Retorna o valor calculado dos filhos.
			
		//Caso seja o ultimo filho.		
		}else {
			
			return Double.valueOf(position.element().toString()); //Retorna o valor convertido para double.
		}		
	}
	
	//Seleciona os elementos da �rvore binaria e concatena-os em uma String utilizando o inorder.
	public String inorder(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expression = "";
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += inorder(tree, children);
		}
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		expression += position.element().toString();
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += inorder(tree, children);			
		}
		return expression; //Retorna a variavel com os elementos da arvore.
	}	
	
	//Seleciona os elementos da �rvore binaria e concatena-os em uma String utilizando o positionsInorder.
	public String toStringInOrder(LinkedBinaryTree<String> tree) {
		
		//String que armazenar� os elementos dos nodos da �rvore.
		String elements = "";
		
		//La�o de repeti��o que percorer� os nodos da �rvore em ordem.
		for(Position<String> filho: tree.positionsInorder()) {
			
			//Concatena os nodos da �rvore seguida de uma ",".
			elements += ", " + filho.element().toString();
		}
		
		//Remove a virgula e o espaco do ultimo elemento da string.
		elements = (elements.length() == 0 ? elements : elements.substring(2));
		
		//Retorna a string de elementos.
		return "[" + elements + "]";
	}
	
	//Imprimi no console a �rvore binaria, de acordo com seus nodos.
	public void pintTreeBinary(LinkedBinaryTree<TYPE> tree) {
		
		//Instancia uma lista de nodos, para armazenar os nodos.
		PositionList<Position<TYPE>> positions = new NodePositionList<Position<TYPE>>();
		
		//Usando o m�todo inorderPositions preenche a lista com os nodos em ordem (inOrden).
		if (size != 0) inorderPositions(root(), positions);
		
		//Cria uma matriz de acordo com o tamanho da �rvore, Linhas (altura da �rvoe) Colunas(tamanho da lista de nodos).
		String flat[][] = new String[height(tree, tree.root()) + 1][positions.size()];		
        
		//Percorre a matriz preenchendo ela com espa�os vazios.
        for(int i = 0; i < flat.length; i++)
            for(int j = 0; j < flat[0].length; j++){flat[i][j] = " ";}
        
        //Variavel que guarda a quantidade de nodos que foram visitados.
        int visits = 0;
        
        //Esse la�o percorrer� a lista de nodos.
        for(Position<TYPE> children: positions) {
        	
        	//Para cada nodo, preenche-se a matriz, sendo a profundidade do nodo a linha e quantidade de visits a coluna que ser� guardada o elemento do nodo.
        	flat[depth(tree, children)][visits] = children.element().toString();
        	
        	//Acrescenta +1 na variavel visits.
        	visits++;
        }
        
      //Percorre a matriz imprimindo a na tela do console.
        for(int i = 0; i < flat.length; i++){
            for(int j = 0; j < flat[0].length; j++){
                
                System.out.print(flat[i][j] + " "); //Imprimi o elemento da matriz seguido de um espa�o em branco.
                
            }            
            System.out.println(); //A cada final de linha simula uma quebra de linha.             
        } 				
	}
	
	//Calcula a profundidade de um nodo da �rvore.
	public int depth(LinkedBinaryTree<TYPE> tree, Position<TYPE> position) {
		
		if(tree.isRoot(position)) {return 0;} //Verifica se posi��o informada � a raiz da arvore, caso seja retorna 0.
		else {return 1 + depth(tree, tree.parent(position));} //Caso contrario, chama recursivamente at� encontrar a raiz, sempre somando +1 e retorna essa soma.		
	}
	
	//Ccalcula a altura de uma �rvore.
	public int height(LinkedBinaryTree<TYPE> tree, Position<TYPE> position) {
		
		if(tree.isExternal(position)) {return 0;} //Verifica se o nodo da �rvore � interno, caso seja retorna 0.
		else {
			
			int height = 0; //Armazena a soma recursiva da altura dos nodos.
			
			//Percorre a �rvore e utiliza o Math.max para calcular a altura junto com uma chamada recursiva.
			for(Position<TYPE> children : tree.children(position)) {height = Math.max(height, height(tree, children));}
			
			return 1 + height; //Retorna a altura da �rvore.
		}
	}
	
	//Seleciona os elementos da �rvore binaria e concatena-os em uma String utilizando o eulerTour.	
	public String eulerTour(BinaryTree<TYPE> tree, Position<TYPE> position) {		
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expression = "";		
		expression += position.element().toString();
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += eulerTour(tree, children);
		}
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		expression += position.element().toString();

		//Verifica se o nodo da �rvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o m�todo recursivamente, passando o filho do nodo e concatena o retorno com a string.
			expression += eulerTour(tree, children);			
		}
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		expression += position.element().toString();
		return expression; //Retorna a variavel com os elementos da arvore.
	}
	
	//Imprimi a express�o representada pela �rvore.
	public String printExpression(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//String que armazenar� e concatenar� os elementos da �rvore.
		String expression = "";	
		
		//Verifica se o nodo � interno, caso seja concatena a string com "(" (parenteses aberto).
		if(tree.isInternal(position)) {expression += "(";}
		
		//Verifica se o nodo � filho da esquerda, caso seja concatena o elemento recursivamente com a string.
		if(tree.hasLeft(position)) {expression += printExpression(tree, tree.left(position));}
		
		//Verifica se o nodo � interno, caso seja concatena o elemento recursivamente com a string.
		if(tree.isInternal(position)) {expression += position.element().toString();}
		
		//caso contrario, concatena apenas o elemento do nodo com a string.
		else {expression += position.element().toString();}
		
		//Verifica se o nodo � filho da direita, caso seja concatena o elemento recursivamente com a string.
		if(tree.hasRight(position)) {expression += printExpression(tree, tree.right(position));}
		
		//Verifica se o nodo � interno, caso seja concatena a string com ")" (parenteses fechado).
		if(tree.isInternal(position)) {expression += ")";}
		
		//Retorna a variavel com a espress�o que representa a �rvore.
		return expression;		
	}
	
	//Conta a quantidade de nodos externos e do lado esquerdo de uma �rvore binaria.
	public int countLeft(BinaryTree<TYPE> tree, Position<TYPE> position) {
		
		//Variavel armazenar� a quantidade de nodos esquerdos e externos da arvore.
		int amounts = 0;		
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o m�todo recursivamente e soma o valor recebido.
			amounts += countLeft(tree, children);
		}
		
		//Verifica se o nodo atual � a raiz da �rvore.
		if(position == tree.root()) {
			
			//Caso seja, retorna a quantidade de nodos da �rvore.
			return amounts;
		
		//Caso contrario.
		}else {
			
			//Verifica se o nodo atual � externo.
			if(tree.isExternal(position)) {
				
				//Caso seja soma + 1;
				amounts += 1;
			}
		}
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o m�todo recursivamente e soma o valor recebido.
			amounts += countLeft(tree, children);			
		}
		
		//Retorna a quantidade de nodos da �rvore.
		return amounts;
	}
	
	//Conta a quantidade de nodos externos e do lado direito de uma �rvore binaria.
	public int countRight(BinaryTree<TYPE> tree, Position<TYPE> position) {		
		
		//Variavel armazenar� a quantidade de nodos esquerdos e externos da arvore.
		int amounts = 0;			
		
		//Verifica se o nodo da �rvore tem um filho a direita.
		if(tree.hasRight(position)) {
			
			//Guarda a referencia do filho na variavel.
			Position<TYPE> children = tree.right(position);
			
			//Chama novamente o m�todo recursivamente e soma o valor recebido.
			amounts += countLeft(tree, children);			
		}		
		
		//Verifica se o nodo atual � a raiz da �rvore.
		if(position == tree.root()) {
			
			//Caso seja, retorna a quantidade de nodos da �rvore.
			return amounts;
		
		//Caso contrario.
		}else {
			
			//Verifica se o nodo atual � externo.
			if(tree.isExternal(position)) {
				
				//Caso seja soma + 1;
				amounts += 1;
			}
		}		
		
		//Verifica se o nodo da �rvore tem um filho a esquerda.
		if(tree.hasLeft(position)) {
			
			//Guarda a referencia do filho na variavel. 
			Position<TYPE> children = tree.left(position);
			
			//Chama novamente o m�todo recursivamente e soma o valor recebido.
			amounts += countLeft(tree, children);
		}
		
		//Retorna a quantidade de nodos da �rvore.
		return amounts;
	}	
	
	//Concatena todos os elementos da lista em uma string.
	public static <TYPE> String toString(LinkedBinaryTree<TYPE> tree) {
		
		String elements = ""; //Inicia a string.
		
		for(TYPE element : tree) {elements += ", " + element;} //Percorre a �rvore e concatena os elementos e o separador � string.
		
		elements = (elements.length() == 0 ? elements : elements.substring(2)); //Remove o �ltimo separador da string.		
		
		return "[" + elements + "]"; //Retorna a string de elementos da �rvore entre chaves.		
	}	
	
	//M�todo toString sem parametro.
	public String toString() {return toString(this);}
	
}