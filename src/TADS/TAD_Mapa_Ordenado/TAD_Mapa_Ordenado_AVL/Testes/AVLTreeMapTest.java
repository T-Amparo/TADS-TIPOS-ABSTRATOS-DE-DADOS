package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Fontes.AVLTreeMap;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

class AVLTreeMapTest {

	@Test
	void test() {
		
		AVLTreeMap<Integer, Integer> tree = new AVLTreeMap<Integer, Integer>();

		tree.put(44, 44);

		tree.put(17, 17);

		tree.put(78, 78);

		tree.put(32, 32);

		tree.put(50, 50);

		tree.put(88, 88);

		tree.put(48, 48);

		tree.put(62, 62);

		System.out.println("Uso da impress�o parentizada de uma �rvore AVL para visualizar \n"

		+ "que ela fica balanceada ap�s as inser��es e remo��es\n");

		System.out.println("========================================");

		System.out.println("Antes da inser��o de 54\n" + tree.printExpression(tree.root()));

		tree.put(54, 54);

		assertEquals("[17, 32, 44, 48, 50, 54, 62, 78, 88]", tree.keySet().toString());

		System.out.println("Ap�s a inser��o de 54\n" + tree.printExpression(tree.root()));

		System.out.println("========================================");

		System.out.println("Antes da remo��o de 32\n" + tree.printExpression(tree.root()));

		tree.remove(32);

		assertEquals("[17, 44, 48, 50, 54, 62, 78, 88]", tree.keySet().toString());

		System.out.println("Ap�s a remo��o de 32\n" + tree.printExpression(tree.root()));		
	}

}