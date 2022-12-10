package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Fontes.BinarySearchTree;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

class BinarySearchTreeTest {

	@Test
	void testBinarySearchTree() {
		
		BinarySearchTree<Integer, Integer> A = new BinarySearchTree<Integer, Integer>();

		A.put(44, 44); A.put(17, 17);

		A.put(88, 88); A.put(32, 32);

		A.put(65, 65); A.put(97, 97);

		A.put(28, 28); A.put(54, 54);

		A.put(82, 82); A.put(29, 29);

		A.put(76, 76); A.put(80, 80);

		System.out.println("Uso da impressão parentizada de uma árvore binária de busca \n"

		+ "para visualizar sua estrutura após as inserções e remoções");

		System.out.println("========================================");

		System.out.println("Antes da inserção de 78\n" + A.printExpression(A.root()));

		A.put(78, 78);

		assertEquals("[17, 28, 29, 32, 44, 54, 65, 76, 78, 80, 82, 88, 97]", A.keySet().toString());

		System.out.println("Após a inserção de 78\n" + A.printExpression(A.root()));

		A.remove(32);

		assertEquals("[17, 28, 29, 44, 54, 65, 76, 78, 80, 82, 88, 97]", A.keySet().toString());

		System.out.println("Após a remoção de 32\n" + A.printExpression(A.root()));

		A.remove(65);

		assertEquals("[17, 28, 29, 44, 54, 76, 78, 80, 82, 88, 97]", A.keySet().toString());

		System.out.println("Após a remoção de 65\n" + A.printExpression(A.root()));

		A.put(78, 0);

		assertEquals("[17, 28, 29, 44, 54, 76, 78, 80, 82, 88, 97]", A.keySet().toString());

		assertEquals("[17, 28, 29, 44, 54, 76, 0, 80, 82, 88, 97]", A.values().toString());

		System.out.println("Após inserção de 78\n" + A.printExpression(A.root()));		
	}

}