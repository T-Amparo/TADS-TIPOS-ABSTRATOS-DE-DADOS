package TADS.TAD_Arvore.TAD_Arvore_Generica.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Arvore.TAD_Arvore_Generica.Fontes.LinkedTree;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Fontes.NodePositionList;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Fontes.TreeNode;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.Position;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.PositionList;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.TreePosition;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemeta��o do TAD �RVORE GENERICA.
class LinkedTreeTest {	
	
	//O M�todo ir� criar os nodos da �rvore.
	private TreeNode<String> createNodeChildren(TreeNode<String> root, String element) {

		PositionList<Position<String>> children; //Instancia uma lista de posi��es.
		TreeNode<String> newNode; //Novo nodo que ser� criado.

		children = root.getChildren(); //Armazena os filhos da raiz.

		newNode = new TreeNode<String>(); //Cria um nodo vazio.

		newNode.setElement(element); //Define o elemento do nodo.
		newNode.setParent(root); //Define o pai do nodo.
		newNode.setChildren(new NodePositionList<Position<String>>()); //Cria uma lista de posi��es para os filhos do nodo.
		children.addLast(newNode); //Adiciona o nodo criado a lista de filhos da raiz.

		return newNode; //Retorna o nodo criado.
	}
	
	//O M�todo ir� criar uma �rvore especifica.
	public LinkedTree<String> createTreeEletronics() {

		LinkedTree<String> tree = new LinkedTree<String>(); //Instancia uma �rvore.
		TreeNode<String> root, vendas, manufatura, internacional, ultramar;	//Instancia os nodos que a �rvore ter�.
		
		tree.addRoot("Eletronics R'Us"); //Adiciona uma raiz na �rvore.		
		root = (TreeNode<String>) tree.root(); //Armazena a raiz da �rvore.
		root.setChildren(new NodePositionList<Position<String>>()); //Cria uma lista para os filhos da �rvore.

		// Filhos da raiz: Eletronic R'Us
		createNodeChildren(root, "P&D");
		vendas = createNodeChildren(root, "Vendas");
		
		createNodeChildren(root, "Compras");
		manufatura = createNodeChildren(root, "Manufatura");

		// Filhos de Vendas
		internacional = createNodeChildren(vendas, "Internacional");
		createNodeChildren(vendas, "Nacional");

		// Filhos de Internacional
		createNodeChildren(internacional, "Canad�");
		
		createNodeChildren(internacional, "Am�rica do Sul");
		ultramar = createNodeChildren(internacional, "Ultramar");

		// Filhos de Ultramar
		createNodeChildren(ultramar, "�frica");
		createNodeChildren(ultramar, "Europa");
		createNodeChildren(ultramar, "�sia");
		createNodeChildren(ultramar, "Austr�lia");

		// Filhos de Manufatura
		createNodeChildren(manufatura, "TV");
		createNodeChildren(manufatura, "CD");
		createNodeChildren(manufatura, "Tuner");

		return tree;
	}

	@Test
	void testLinkedTreeStrings() { //Testa uma �rvore de strings.
		
		TreePosition<String> root; //Armazena a referencia da raiz da �rvore.
		Position<Position<String>> first, second; //Armazena a referencia de dois nodos da �rvore.
		PositionList<Position<String>> children; //Armazena uma lista de nodos da �rvore.
		LinkedTree<String> tree = createTreeEletronics(); //Cria uma �rvore de strings.
		
		assertFalse(tree.isEmpty()); //Testa se a �rvore n�o est� vazia.
		assertEquals(4, tree.height1(tree), "Altura da �rvore T"); //Testa � altura da �rvore.
		assertEquals(4, tree.height2(tree, tree.root()), "Altura da �rvore T"); //Testa � altura da �rvore.
		
		//Testa o estado da �rvore.
		assertEquals("[Eletronics R'Us, P&D, Vendas, Internacional, Canad�, Am�rica do Sul, "
				+ "Ultramar, �frica, Europa, �sia, Austr�lia, Nacional, Compras, Manufatura, TV, CD, Tuner]", tree.toString(), "Pr�-ordem da �rvore T ");
		
		root = tree.root(); //Armazena a raiz da �rvore.
		children = root.getChildren(); //Armazena um lista de filhos a partir da raiz da �rvore.
		first = children.first(); //Armazena o primeiro filho da lista de filhos.
		
		assertEquals("P&D", first.element().element(), "P&D"); //Testa o primeiro elemento da �rvore.
		assertTrue(tree.isExternal(first.element())); //Testa se o primeiro elemento da �rvore � externo.
		assertEquals(root, tree.parent(first.element()), "Deve ser a raiz"); //Testa se o primeiro elemento da �rvore � a raiz.

		second = children.next(first); //Armazena o segundo filho da lista de filhos.

		assertEquals("Vendas", second.element().element(), "Vendas"); //Testa o segundo elemento da �rvore.
		assertTrue(tree.isInternal(second.element())); //Testa se o primeiro elemento da �rvore � interno.
		assertEquals(1, tree.depth(tree, second.element()), "");
		
		tree.replace(first.element(), "Pesquisa e Desenvolvimento"); //Troca o elemento do primeiro nodo da �rvore.

		//Testa o estado da �rvore ap�s a substitui��o.
		assertEquals("[Eletronics R'Us, Pesquisa e Desenvolvimento, Vendas, Internacional, Canad�, Am�rica do Sul, "
				+ "Ultramar, �frica, Europa, �sia, Austr�lia, Nacional, Compras, Manufatura, TV, CD, Tuner]",tree.toString(), "Pr�-ordem da �rvore T ");
		
		assertTrue(tree.isRoot(root)); //Testa se � �rvore tem uma raiz.
		
		tree.swapElements(first.element(), second.element()); //Troca o elemento do primeiro nodo da �rvore com o segundo.
		
		//Testa o estado final da �rvore.
		assertEquals("[Eletronics R'Us, Vendas, Pesquisa e Desenvolvimento, Internacional, Canad�, Am�rica do Sul, "
				+ "Ultramar, �frica, Europa, �sia, Austr�lia, Nacional, Compras, Manufatura, TV, CD, Tuner]",tree.toString(), "Pr�-ordem da �rvore T ");		
	}	

}