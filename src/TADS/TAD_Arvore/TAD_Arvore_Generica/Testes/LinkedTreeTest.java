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

//Realiza o teste da implemetação do TAD ÁRVORE GENERICA.
class LinkedTreeTest {	
	
	//O Método irá criar os nodos da árvore.
	private TreeNode<String> createNodeChildren(TreeNode<String> root, String element) {

		PositionList<Position<String>> children; //Instancia uma lista de posições.
		TreeNode<String> newNode; //Novo nodo que será criado.

		children = root.getChildren(); //Armazena os filhos da raiz.

		newNode = new TreeNode<String>(); //Cria um nodo vazio.

		newNode.setElement(element); //Define o elemento do nodo.
		newNode.setParent(root); //Define o pai do nodo.
		newNode.setChildren(new NodePositionList<Position<String>>()); //Cria uma lista de posições para os filhos do nodo.
		children.addLast(newNode); //Adiciona o nodo criado a lista de filhos da raiz.

		return newNode; //Retorna o nodo criado.
	}
	
	//O Método irá criar uma árvore especifica.
	public LinkedTree<String> createTreeEletronics() {

		LinkedTree<String> tree = new LinkedTree<String>(); //Instancia uma árvore.
		TreeNode<String> root, vendas, manufatura, internacional, ultramar;	//Instancia os nodos que a árvore terá.
		
		tree.addRoot("Eletronics R'Us"); //Adiciona uma raiz na árvore.		
		root = (TreeNode<String>) tree.root(); //Armazena a raiz da árvore.
		root.setChildren(new NodePositionList<Position<String>>()); //Cria uma lista para os filhos da árvore.

		// Filhos da raiz: Eletronic R'Us
		createNodeChildren(root, "P&D");
		vendas = createNodeChildren(root, "Vendas");
		
		createNodeChildren(root, "Compras");
		manufatura = createNodeChildren(root, "Manufatura");

		// Filhos de Vendas
		internacional = createNodeChildren(vendas, "Internacional");
		createNodeChildren(vendas, "Nacional");

		// Filhos de Internacional
		createNodeChildren(internacional, "Canadá");
		
		createNodeChildren(internacional, "América do Sul");
		ultramar = createNodeChildren(internacional, "Ultramar");

		// Filhos de Ultramar
		createNodeChildren(ultramar, "África");
		createNodeChildren(ultramar, "Europa");
		createNodeChildren(ultramar, "Ásia");
		createNodeChildren(ultramar, "Austrália");

		// Filhos de Manufatura
		createNodeChildren(manufatura, "TV");
		createNodeChildren(manufatura, "CD");
		createNodeChildren(manufatura, "Tuner");

		return tree;
	}

	@Test
	void testLinkedTreeStrings() { //Testa uma árvore de strings.
		
		TreePosition<String> root; //Armazena a referencia da raiz da árvore.
		Position<Position<String>> first, second; //Armazena a referencia de dois nodos da árvore.
		PositionList<Position<String>> children; //Armazena uma lista de nodos da árvore.
		LinkedTree<String> tree = createTreeEletronics(); //Cria uma árvore de strings.
		
		assertFalse(tree.isEmpty()); //Testa se a árvore não está vazia.
		assertEquals(4, tree.height1(tree), "Altura da Árvore T"); //Testa à altura da árvore.
		assertEquals(4, tree.height2(tree, tree.root()), "Altura da Árvore T"); //Testa à altura da árvore.
		
		//Testa o estado da árvore.
		assertEquals("[Eletronics R'Us, P&D, Vendas, Internacional, Canadá, América do Sul, "
				+ "Ultramar, África, Europa, Ásia, Austrália, Nacional, Compras, Manufatura, TV, CD, Tuner]", tree.toString(), "Pré-ordem da Árvore T ");
		
		root = tree.root(); //Armazena a raiz da árvore.
		children = root.getChildren(); //Armazena um lista de filhos a partir da raiz da árvore.
		first = children.first(); //Armazena o primeiro filho da lista de filhos.
		
		assertEquals("P&D", first.element().element(), "P&D"); //Testa o primeiro elemento da árvore.
		assertTrue(tree.isExternal(first.element())); //Testa se o primeiro elemento da árvore é externo.
		assertEquals(root, tree.parent(first.element()), "Deve ser a raiz"); //Testa se o primeiro elemento da árvore é a raiz.

		second = children.next(first); //Armazena o segundo filho da lista de filhos.

		assertEquals("Vendas", second.element().element(), "Vendas"); //Testa o segundo elemento da árvore.
		assertTrue(tree.isInternal(second.element())); //Testa se o primeiro elemento da árvore é interno.
		assertEquals(1, tree.depth(tree, second.element()), "");
		
		tree.replace(first.element(), "Pesquisa e Desenvolvimento"); //Troca o elemento do primeiro nodo da árvore.

		//Testa o estado da árvore após a substituição.
		assertEquals("[Eletronics R'Us, Pesquisa e Desenvolvimento, Vendas, Internacional, Canadá, América do Sul, "
				+ "Ultramar, África, Europa, Ásia, Austrália, Nacional, Compras, Manufatura, TV, CD, Tuner]",tree.toString(), "Pré-ordem da Árvore T ");
		
		assertTrue(tree.isRoot(root)); //Testa se à árvore tem uma raiz.
		
		tree.swapElements(first.element(), second.element()); //Troca o elemento do primeiro nodo da árvore com o segundo.
		
		//Testa o estado final da árvore.
		assertEquals("[Eletronics R'Us, Vendas, Pesquisa e Desenvolvimento, Internacional, Canadá, América do Sul, "
				+ "Ultramar, África, Europa, Ásia, Austrália, Nacional, Compras, Manufatura, TV, CD, Tuner]",tree.toString(), "Pré-ordem da Árvore T ");		
	}	

}