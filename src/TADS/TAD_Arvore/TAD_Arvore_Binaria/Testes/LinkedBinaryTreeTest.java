package TADS.TAD_Arvore.TAD_Arvore_Binaria.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Arvore.TAD_Arvore_Binaria.Fontes.BTNode;
import TADS.TAD_Arvore.TAD_Arvore_Binaria.Fontes.LinkedBinaryTree;
import TADS.TAD_Arvore.TAD_Arvore_Binaria.Interfaces.BTPosition;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD ÁRVORE BINARIA.
class LinkedBinaryTreeTest {	
	
	//Método que irá criar os nodos da árvore binaria.
	public BTPosition<String> createNodeChildren(String element, BTPosition<String> parent, BTPosition<String> left, BTPosition<String> right) {
		
		//Retorna o nodo criado.
		return new BTNode<String>(element, parent, left, right);
	}
	
	//O Método irá criar uma árvore binaria.
	public LinkedBinaryTree<String> createTreeBinary() {
		
		//Instancia uma árvore binaria.
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		
		//Adiciona uma raiz a árvore.
		tree.addRoot("Davi");
		
		//Armazena a referencia da raiz da árvore.
		BTNode<String> root = (BTNode<String>) tree.root();
		
		//Cria o filho left e o filho right do nodo raiz.
		root.setLeft(createNodeChildren("Fernanda", root, null, null));
		root.setRight(createNodeChildren("Pedro", root, null, null));
		
		//Armazena a referencia dos filhos da raiz.
		BTNode<String> fernanda = (BTNode<String>) root.getLeft();
		BTNode<String> pedro = (BTNode<String>) root.getRight();
		
		//Cria o filho left e o filho right do filho esquerdo do nodo raiz (Fernanda).
		root.getLeft().setLeft(createNodeChildren("João", fernanda, null, null));
		root.getLeft().setRight(createNodeChildren("Maria", fernanda, null, null));		
		
		//Cria o filho left e o filho right do filho direito do nodo raiz (Pedro).
		root.getRight().setLeft(createNodeChildren("Paulo", pedro, null, null));
		root.getRight().setRight(createNodeChildren("Paula", pedro, null, null));
		
		//Armazena a referencia do joão.
		BTNode<String> joao = (BTNode<String>) fernanda.getLeft();
		
		//Armazena a referencia da paula.
		BTNode<String> paula = (BTNode<String>) pedro.getRight();
		
		//Cria o filho esquerdo do joão.		
		joao.setLeft(createNodeChildren("David", joao, null, null));
		
		//Cria o filho direito da paula.
		paula.setRight(createNodeChildren("Patrick", paula, null, null));
		
		//Retorna a árvore binaria criada.
		return tree;
	}	
	
	//O Método irá criar uma árvore binaria de busca.
	public LinkedBinaryTree<String> makerBinaryTreeSearch(){
		
		//Instancia uma árvore binaria.
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();		
		
		//Adiciona uma raiz a árvore.
		tree.addRoot("58");		
		
		//Armazena a referencia da raiz da árvore.
		BTNode<String> root = (BTNode<String>) tree.root();
		
		//Cria o filho left e o filho right do nodo raiz.
		root.setLeft(createNodeChildren("31", root, null, null));
		root.setRight(createNodeChildren("90", root, null, null));
		
		//Armazena a referencia dos filhos da raiz.
		BTNode<String> F1 = (BTNode<String>) root.getLeft();
		BTNode<String> F2 = (BTNode<String>) root.getRight();
		
		//Cria e armazena a referencia dos filhos de F1.
		F1.setLeft(createNodeChildren("25", F1, null, null));
		F1.setRight(createNodeChildren("42", F1, null, null));
		
		//Armazena a referencia dos netos de F1.
		BTNode<String> F3 = (BTNode<String>) F1.getLeft();
		BTNode<String> F4 = (BTNode<String>) F1.getRight();
		
		//Cria e armazena a referencia dos filhos de F2.
		F3.setLeft(createNodeChildren("12", F3, null, null));
		F4.setLeft(createNodeChildren("36", F4, null, null));
		
		//Cria e armazena a referencia dos filhos de F2.
		F2.setLeft(createNodeChildren("62", F2, null, null));
		
		//Armazena a referencia dos netos de F2.
		BTNode<String> F5 = (BTNode<String>) F2.getLeft();
		
		F5.setRight(createNodeChildren("75", F5, null, null));
		
		//Retorna a árvore binaria criada.
		return tree;		
	}
	
	@Test
	void testLinkedBinaryTreeStrings() { //Testa uma árvore de strings.
		
		//Chama o método createTreeBinary para criar a árvore binaria.
		LinkedBinaryTree<String> tree = createTreeBinary();
		
		//Testa se Davi é o root da árvore.
		assertEquals("Davi", tree.root().element(), "Deve retornar Davi");
		
		//Testa se a raiz tem filho, isso é se ela é um nodo interno.
		assertTrue(tree.isInternal(tree.root()));
		
		//Armazena a referencia do filho esquerdo da raiz
		BTNode<String> fernanda = (BTNode<String>) tree.left(tree.root());
		
		//Testa se o filho esquerdo da raiz é a Fernanda.
		assertEquals("Fernanda", fernanda.element(), "Deve retornar Fernanda");
		
		//Armazena a referencia do filho esquerdo da Fernanda.
		BTNode<String> joao = (BTNode<String>) fernanda.getLeft();
		
		//Testa se o filho da Fernanda é João.
		assertEquals("João", joao.element().toString(), "Deve retornar João");
		
		//Testa se o nodo pai de João é a Fernanda.
		assertEquals("Fernanda", joao.getParent().element());
		
		//Testa se a ordem da árvore binaria está correta.
		assertEquals("[Davi, Fernanda, João, David, Maria, Pedro, Paulo, Paula, Patrick]", tree.toString());		
	}
	
	@Test
	void testMakerBinaryTreeSearch() { //Testa uma árvore busca.		
		
		//Chama o método makerBinaryTreeSearch para criar a árvore binaria.
		LinkedBinaryTree<String> tree = makerBinaryTreeSearch();
		
		//Testa se 58 é o root da árvore.
		assertEquals("58", tree.root().element(), "Deve retornar 58");		
		
		//Testa se a ordem da árvore binaria está correta.
		assertEquals("[58, 31, 25, 12, 42, 36, 90, 62, 75]", tree.toString());
		
		//Exercicio 5 - Questão F): Percorre a árvore InOrdem.
		assertEquals("[12, 25, 31, 36, 42, 58, 62, 75, 90]", tree.toStringInOrder(tree));		
	}

	@Test
	void testBuildExpression() { //Testa uma árvore de expressões.
		
		//Instancia uma árvore binaria.
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		
		//Cria uma árvore binaria de expressões.
		LinkedBinaryTree<String> expressionTree = tree.buildExpression("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))");
		
		//Testa se a ordem da árvore binaria de expressões está correta.
		assertEquals("[-, /, *, +, 3, 1, 3, +, -, 9, 5, 2, +, *, 3, -, 7, 4, 6]", expressionTree.toString());
		
		//Testa se o método binaryPreorder está funcionando corretamente.
		assertEquals("-/*+313+-952+*3-746", expressionTree.binaryPreorder(expressionTree, expressionTree.root()));		
		
		//Testa se o método binaryPostorder está funcionando corretamente.
		assertEquals("31+3*95-2+/374-*6+-", expressionTree.binaryPostorder(expressionTree, expressionTree.root()));		
		
		//Testa se o método evaluateExpression está funcionando corretamente.
		assertEquals(-13.0, expressionTree.evaluateExpression(expressionTree, expressionTree.root()));		
		
		//Testa se o método inorder está funcionando corretamente.
		assertEquals("3+1*3/9-5+2-3*7-4+6", expressionTree.inorder(expressionTree, expressionTree.root()));		
		
		//Testa se o método eulerTour está funcionando corretamente.
		assertEquals("-/*+333+111+*333*/+-999-555-+222+/-+*333*-777-444-*+666+-", expressionTree.eulerTour(expressionTree, expressionTree.root()));
		
		//Testa se o método printExpression está funcionando corretamente.
		assertEquals("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))", expressionTree.printExpression(expressionTree, expressionTree.root()));		
		
		//Testa se o método countLeft está funcionando corretamente (Conta os nodes esquerdos e sem filhos da árvore binaria).
		assertEquals(6, expressionTree.countLeft(expressionTree, expressionTree.root()));
		
		//Testa se o método countRight está funcionando corretamente (Conta os nodes esquerdos e sem filhos da árvore binaria).
		assertEquals(4, expressionTree.countRight(expressionTree, expressionTree.root()));
	}
	
}