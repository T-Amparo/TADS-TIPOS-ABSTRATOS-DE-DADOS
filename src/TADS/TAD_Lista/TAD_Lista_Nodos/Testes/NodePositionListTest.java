package TADS.TAD_Lista.TAD_Lista_Nodos.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Lista.TAD_Lista_Nodos.Fontes.NodePositionList;
import TADS.TAD_Lista.TAD_Lista_Nodos.Interfaces.Position;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD LISTA DE NODOS.
class NodePositionListTest {	

	@Test
	void testNodePositionListIntegers() {
		
		//Instancia uma lista de nodos.
		NodePositionList<Integer> lista = new NodePositionList<Integer>();
		
		//Testa o estado atual da lista.
		assertEquals("[]", lista.toString(), "Deve retornar []");		
		
		//Testa se o tamanho da lista é 0.
		assertEquals(0, lista.size(), "Deve retornar 0");
		
		//Testa se a lista está vazia.
		assertTrue(lista.isEmpty(), "Deve retornar true");
		
		//Testa se adicionou no inicio da lista o número 8.
		lista.addFirst(8);
		assertEquals("[8]", lista.toString(), "Deve retornar [8]");
		
		//Testa se o primeiro elemento da lista é o numero 8. 
		Position<Integer> node1 = lista.first(); //Armazena o primeiro elemento da lista.
		assertEquals(8, (int)node1.element(), "Deve retornar 8");
		
		//Testa o estado atual da lista.
		assertEquals("[8]", lista.toString(), "Deve retornar [8]");
		
		//Testa se adicionou o numero 5 depois do primeiro elemento da lista.
		lista.addAfter(node1, 5);
		assertEquals("[8, 5]", lista.toString(), "Deve retornar [8, 5]");
		
		//Testa se o segundo elemento da lista é o numero 5. 
		Position<Integer> node2 = lista.next(node1); //Armazena o segundo elemento da lista.
		assertEquals(5, (int)node2.element(), "Deve retornar 5");

		//Testa o estado atual da lista.
		assertEquals("[8, 5]", lista.toString(), "Deve retornar [8, 5]");
		
		//Testa se adicionou o numero 3 antes do segundo elemento da lista.
		lista.addBefore(node2, 3);
		assertEquals("[8, 3, 5]", lista.toString(), "Deve retornar [8, 3, 5]");
		
		//Testa se o segundo elemento da lista é o numero 3. 
		Position<Integer> node3 = lista.prev(node2); //Armazena o segundo elemento da lista.
		assertEquals(3, (int)node3.element(), "3");

		//Testa o estado atual da lista.
		assertEquals("[8, 3, 5]", lista.toString(), "Deve retornar [8, 3, 5]");
		
		//Testa se adicionou no inicio da lista o número 9.
		lista.addFirst(9);
		assertEquals("[9, 8, 3, 5]", lista.toString(), "Deve retornar [9, 8, 3, 5]");
		
		//Testa se o último elemento da lista é o numero 5. 
		node2 = lista.last(); //Armazena o último elemento da lista.
		assertEquals(5, (int)node2.element(), "Deve retornar 5");

		//Testa o estado atual da lista.
		assertEquals("[9, 8, 3, 5]", lista.toString(), "Deve retornar [9, 8, 3, 5]");
		
		//Testa se removeu o primeiro elemento da lista, número 9.
		assertEquals(9, (int)lista.remove(lista.first()), "Deve retornar 9");
		assertEquals("[8, 3, 5]", lista.toString(), "Deve retornar [8, 3, 5]");
		
		//Testa se trocou o elemento da segunda posição da lista.
		assertEquals(3, (int)lista.set(node3, 7), "Deve retornar 3");
		assertEquals("[8, 7, 5]", lista.toString(), "Deve retornar [8, 7, 5]");
		
		//Testa se adicionou o numero 2 depois do primeiro elemento da lista.
		lista.addAfter(lista.first(), 2);
		assertEquals("[8, 2, 7, 5]", lista.toString(), "Deve retornar [8, 2, 7, 5]");
	}	
	
}