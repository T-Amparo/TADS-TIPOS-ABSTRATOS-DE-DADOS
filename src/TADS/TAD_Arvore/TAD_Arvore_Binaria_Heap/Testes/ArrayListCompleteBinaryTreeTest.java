package TADS.TAD_Arvore.TAD_Arvore_Binaria_Heap.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Arvore.TAD_Arvore_Binaria_Heap.Fontes.ArrayListCompleteBinaryTree;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD ÁRVORE BINARIA COMPLETA.
class ArrayListCompleteBinaryTreeTest {

	@Test
	void testArrayListCompleteBinaryTreeIntegers() { //Testa uma árvore de inteiros.
		
		//Instancia uma árvore.
		ArrayListCompleteBinaryTree<Integer> tree = new ArrayListCompleteBinaryTree<Integer>();
		
		//Adiciona os elementos à arvore.
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(15);
		tree.add(9);
		tree.add(7);
		tree.add(20);
		tree.add(16);
		tree.add(25);
		tree.add(14);
		tree.add(12);
		tree.add(11);
		tree.add(8);
		
		//Testa o estado final da árvore.
		assertEquals("[null, [4,1], [5,2], [6,3], [15,4], [9,5], [7,6], [20,7], [16,8], "
				+ "[25,9], [14,10], [12,11], [11,12], [8,13]]", tree.toString());		
	}

}