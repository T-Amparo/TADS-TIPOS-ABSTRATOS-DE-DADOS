package TADS.TAD_Fila.TAD_Fila_LSE.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Fila.TAD_Fila_LSE.Excecoes.EmptyQueueExceptionNodeQueue;
import TADS.TAD_Fila.TAD_Fila_LSE.Fontes.NodeQueue;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

class NodeQueueTest {	

	@Test
	void testNodeQueueIntegers() { //Testa uma fila de inteiros.
		
		//Instancia uma fila usando arranjo.
		NodeQueue<Integer> fila = new NodeQueue<Integer>();
		
		//Testa o estado atual da fila.
		assertEquals("[]", fila.toString(), "Deve retornar []");		
		
		//Testa se o tamanho da fila é 0.
		assertEquals(0, fila.size(), "Deve retornar 0");
		
		//Testa se a fila está vazia.
		assertTrue(fila.isEmpty(), "Deve retornar true");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyQueueExceptionNodeQueue.class, () ->{fila.front();});
		
		//Testa se adicionou ao final da fila o número 5.
		fila.enqueue(5);
		assertEquals("[5]", fila.toString(), "Deve retornar [5]");
		
		//Testa se adicionou ao final da fila o número 3.
		fila.enqueue(3);
		assertEquals("[5, 3]", fila.toString(), "Deve retornar [5, 3]");
		
		//Testa se removeu da frente da fila o número 5.
		assertEquals(5, (int)fila.dequeue(), "Deve retornar 5");
		
		//Testa se adicionou ao final da fila o número 7.
		fila.enqueue(7);
		assertEquals("[3, 7]", fila.toString(), "Deve retornar [3, 7]");
		
		//Testa se removeu da frente da fila o número 3.
		assertEquals(3, (int)fila.dequeue(), "Deve retornar 3");
		
		//Testa se o numero na frente da fila é 7.
		assertEquals(7, (int)fila.front(), "Deve retornar 7");
		
		//Testa se removeu da frente da fila o número 7.
		assertEquals(7, (int)fila.dequeue(), "Deve retornar 7");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyQueueExceptionNodeQueue.class, () -> {fila.dequeue();});
		
		//Testa se a fila está vazia.
		assertTrue(fila.isEmpty(), "Deve retornar true");
		
		//Testa se adicionou ao final da fila o número 9.
		fila.enqueue(9);
		assertEquals("[9]", fila.toString(), "Deve retornar [9]");
		
		//Testa se adicionou ao final da fila o número 7.
		fila.enqueue(7);
		assertEquals("[9, 7]", fila.toString(), "Deve retornar [9, 7]");		
		
		//Testa se adicionou ao final da fila o número 3.
		fila.enqueue(3);
		assertEquals("[9, 7, 3]", fila.toString(), "Deve retornar [9, 7, 3]");
		
		//Testa se adicionou ao final da fila o número 5.
		fila.enqueue(5);
		assertEquals("[9, 7, 3, 5]", fila.toString(), "Deve retornar [9, 7, 3, 5]");
		
		//Testa se o tamanho da fila é 4.
		assertEquals(4, fila.size(), "Deve retornar 4");
		
		//Testa se removeu da frente da fila o número 9.
		assertEquals(9, (int)fila.dequeue(), "Deve retornar 9");
		
		//Testa se adicionou ao final da fila o número 8.
		fila.enqueue(8);
		assertEquals("[7, 3, 5, 8]", fila.toString(), "Deve retornar [7, 3, 5, 8]");
		
		//Testa se removeu da frente da fila o número 7.
		assertEquals(7, (int)fila.dequeue(), "Deve retornar 7");
		
		//Testa se removeu da frente da fila o número 3.
		assertEquals(3, (int)fila.dequeue(), "Deve retornar 3");
		
		//Testa se adicionou ao final da fila o número 6.
		fila.enqueue(6);
		assertEquals("[5, 8, 6]", fila.toString(), "Deve retornar [5, 8, 6]");	
		
		//Testa se adicionou ao final da fila o número 4.
		fila.enqueue(4);
		assertEquals("[5, 8, 6, 4]", fila.toString(), "Deve retornar [5, 8, 6, 4]");		
		
		//Testa se o tamanho da fila é 4.
		assertEquals(4, fila.size(), "Deve retornar 4");
	}
	
	@Test
	void testNodeQueueStrings() { //Testa uma fila de strings.
		
		//Instancia uma fila usando arranjo.
		NodeQueue<String> fila = new NodeQueue<String>();
		
		//Testa o estado atual da fila.
		assertEquals("[]", fila.toString(), "Deve retornar []");
		
		//Testa se o tamanho da fila é 0.
		assertEquals(0, fila.size(), "Deve retornar 0");
		
		//Testa se a fila está vazia.
		assertTrue(fila.isEmpty(), "Deve retornar true");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyQueueExceptionNodeQueue.class, () ->{fila.front();});
		
		//Testa se adicionou ao final da fila a string "Bruce".
		fila.enqueue("Bruce");
		assertEquals("[Bruce]", fila.toString(), "Deve retornar [Bruce]");
		
		//Testa se adicionou ao final da fila a string "Diana".
		fila.enqueue("Diana");
		assertEquals("[Bruce, Diana]", fila.toString(), "Deve retornar [Bruce, Diana]");
		
		//Testa se removeu a frente da fila "Bruce".
		assertEquals("Bruce", fila.dequeue(), "Deve retornar Bruce");
		
		//Testa se adicionou ao final da fila a string "Clark".
		fila.enqueue("Clark");
		assertEquals("[Diana, Clark]", fila.toString(), "Deve retornar [Diana, Clark]");		
		
		//Testa se removeu a frente da fila "Diana".
		assertEquals("Diana", fila.dequeue(), "Deve retornar Diana");
		
		//Testa se o elemento na frente da fila é "Clark"
		assertEquals("Clark", fila.front(), "Deve retornar Clark");		
		
		//Testa se removeu a frente da fila "Clark".
		assertEquals("Clark", fila.dequeue(), "Deve retornar Clark");		

		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyQueueExceptionNodeQueue.class, () ->{fila.dequeue();});
		
		//Testa se a fila está vazia.
		assertTrue(fila.isEmpty(), "Deve retornar true");		
		
		//Testa se adicionou ao final da fila a string "Barry".
		fila.enqueue("Barry");
		assertEquals("[Barry]", fila.toString(), "Deve retornar [Barry]");		
		
		//Testa se adicionou ao final da fila a string "Clark".
		fila.enqueue("Clark");
		assertEquals("[Barry, Clark]", fila.toString(), "Deve retornar [Barry, Clark]");
		
		//Testa se adicionou ao final da fila a string "Diana".
		fila.enqueue("Diana");
		assertEquals("[Barry, Clark, Diana]", fila.toString(), "Deve retornar [Barry, Clark, Diana]");
		
		//Testa se adicionou ao final da fila a string "Bruce".
		fila.enqueue("Bruce");
		assertEquals("[Barry, Clark, Diana, Bruce]", fila.toString(), "Deve retornar [Barry, Clark, Diana, Bruce]");
				
		//Testa se o tamanho da fila é 4.
		assertEquals(4, fila.size(), "Deve retornar 4");		
		
		//Testa se removeu a frente da fila "Barry".
		assertEquals("Barry", fila.dequeue(), "Deve retornar Barry");		
		
		//Testa se adicionou ao final da fila a string "Dick".
		fila.enqueue("Dick");
		assertEquals("[Clark, Diana, Bruce, Dick]", fila.toString(), "Deve retornar [Clark, Diana, Bruce, Dick]");		
		
		//Testa se removeu a frente da fila "Clark".
		assertEquals("Clark", fila.dequeue(), "Deve retornar Clark");		
		
		//Testa se removeu a frente da fila "Diana".
		assertEquals("Diana", fila.dequeue(), "Deve retornar Diana");
		
		//Testa se adicionou ao final da fila a string "Damian".
		fila.enqueue("Damian");
		assertEquals("[Bruce, Dick, Damian]", fila.toString(), "Deve retornar [Bruce, Dick, Damian]");		
		
		//Testa se adicionou ao final da fila a string "Victor".
		fila.enqueue("Victor");
		assertEquals("[Bruce, Dick, Damian, Victor]", fila.toString(), "Deve retornar [Bruce, Dick, Damian, Victor]");
		
		//Testa se a fila não está vazia.
		assertFalse(fila.isEmpty(), "Deve retornar false");		
		
		//Testa se o tamanho da fila é 4.
		assertEquals(4, fila.size(), "Deve retornar 4");		
		
		//Testa o estado atual da fila.
		assertEquals("[Bruce, Dick, Damian, Victor]", fila.toString(), "Deve retornar [Bruce, Dick, Damian, Victor]");
	}

}