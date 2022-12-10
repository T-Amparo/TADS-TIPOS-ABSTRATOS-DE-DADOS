package TADS.TAD_Pilha.TAD_Pilha_LSE.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Pilha.TAD_Pilha_LSE.Excecoes.EmptyStackExceptionNodeStack;
import TADS.TAD_Pilha.TAD_Pilha_LSE.Fontes.NodeStack;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD PILHA USANDO LSE.
class NodeStackTest {	

	@Test
	void testNodeStackIntegers() { //Testa uma pilha de inteiros.
		
		//Instancia uma pilha.
		NodeStack<Integer> pilha = new NodeStack<Integer>();
		
		//Testa o estado atual da pilha.
		assertEquals("[]", pilha.toString(), "Deve retornar []");		
		
		//Testa se o tamanho da pilha é 0.
		assertEquals(0, pilha.size(), "Deve retornar 0");
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionNodeStack.class, () ->{pilha.top();});
		
		//Testa se adicionou ao topo da pilha o número 5.
		pilha.push(5);
		assertEquals("[5]", pilha.toString(), "Deve retornar [5]");
		
		//Testa se adicionou ao topo da pilha o número 3.
		pilha.push(3);
		assertEquals("[3, 5]", pilha.toString(), "Deve retornar [3, 5]");
		
		//Testa se removeu do topo da pilha o número 3.
		assertEquals(3, (int)pilha.pop(), "Deve retornar 3");
		
		//Testa se adicionou ao topo da pilha o número 7.
		pilha.push(7);
		assertEquals("[7, 5]", pilha.toString(), "Deve retornar [7, 5]");
		
		//Testa se removeu do topo da pilha o número 7.
		assertEquals(7, (int)pilha.pop(), "Deve retornar 7");
		
		//Testa se o numero no topo da pilha é 5.
		assertEquals(5, (int)pilha.top(), "Deve retornar 5");
		
		//Testa se removeu do topo da pilha o número 5.
		assertEquals(5, (int)pilha.pop(), "Deve retornar 5");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionNodeStack.class, () -> {pilha.pop();});
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");
		
		//Testa se adicionou ao topo da pilha o número 9.
		pilha.push(9);
		assertEquals("[9]", pilha.toString(), "Deve retornar [9]");
		
		//Testa se adicionou ao topo da pilha o número 7.
		pilha.push(7);
		assertEquals("[7, 9]", pilha.toString(), "Deve retornar [7, 9]");		
		
		//Testa se adicionou ao topo da pilha o número 3.
		pilha.push(3);
		assertEquals("[3, 7, 9]", pilha.toString(), "Deve retornar [3, 7, 9]");
		
		//Testa se adicionou ao topo da pilha o número 5.
		pilha.push(5);
		assertEquals("[5, 3, 7, 9]", pilha.toString(), "Deve retornar [5, 3, 7, 9]");
		
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");
		
		//Testa se removeu do topo da pilha o número 5.
		assertEquals(5, (int)pilha.pop(), "Deve retornar 5");
		
		//Testa se adicionou ao topo da pilha o número 8.
		pilha.push(8);
		assertEquals("[8, 3, 7, 9]", pilha.toString(), "Deve retornar [8, 3, 7, 9]");
		
		//Testa se removeu do topo da pilha o número 8.
		assertEquals(8, (int)pilha.pop(), "Deve retornar 8");
		
		//Testa se removeu do topo da pilha o número 3.
		assertEquals(3, (int)pilha.pop(), "Deve retornar 3");
		
		//Testa se adicionou ao topo da pilha o número 6.
		pilha.push(6);
		assertEquals("[6, 7, 9]", pilha.toString(), "Deve retornar [6, 7, 9]");	
		
		//Testa se adicionou ao topo da pilha o número 4.
		pilha.push(4);
		assertEquals("[4, 6, 7, 9]", pilha.toString(), "Deve retornar [4, 6, 7, 9]");		
		
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");
	}
	
	@Test
	void testNodeStackStrings() { //Testa uma pilha de strings.
		
		//Instancia uma pilha.
		NodeStack<String> pilha = new NodeStack<String>();
		
		//Testa o estado atual da pilha.
		assertEquals("[]", pilha.toString(), "Deve retornar []");
		
		//Testa se o tamanho da pilha é 0.
		assertEquals(0, pilha.size(), "Deve retornar 0");
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionNodeStack.class, () ->{pilha.top();});
		
		//Testa se adicionou ao topo da pilha a string "Bruce".
		pilha.push("Bruce");
		assertEquals("[Bruce]", pilha.toString(), "Deve retornar [Bruce]");
		
		//Testa se adicionou ao topo da pilha a string "Diana".
		pilha.push("Diana");
		assertEquals("[Diana, Bruce]", pilha.toString(), "Deve retornar [Diana, Bruce]");
		
		//Testa se removeu o topo da pilha "Diana".
		assertEquals("Diana", pilha.pop(), "Deve retornar Diana");
		
		//Testa se adicionou ao topo da pilha a string "Clark".
		pilha.push("Clark");
		assertEquals("[Clark, Bruce]", pilha.toString(), "Deve retornar [Clark, Bruce]");		
		
		//Testa se removeu o topo da pilha "Clark".
		assertEquals("Clark", pilha.pop(), "Deve retornar Clark");
		
		//Testa se o elemento no topo da pilha é "Bruce"
		assertEquals("Bruce", pilha.top(), "Deve retornar Bruce");		
		
		//Testa se removeu o topo da pilha "Bruce".
		assertEquals("Bruce", pilha.pop(), "Deve retornar Bruce");		

		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionNodeStack.class, () ->{pilha.pop();});
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");		
		
		//Testa se adicionou ao topo da pilha a string "Barry".
		pilha.push("Barry");
		assertEquals("[Barry]", pilha.toString(), "Deve retornar [Barry]");		
		
		//Testa se adicionou ao topo da pilha a string "Clark".
		pilha.push("Clark");
		assertEquals("[Clark, Barry]", pilha.toString(), "Deve retornar [Clark, Barry]");
		
		//Testa se adicionou ao topo da pilha a string "Diana".
		pilha.push("Diana");
		assertEquals("[Diana, Clark, Barry]", pilha.toString(), "Deve retornar [Diana, Clark, Barry]");
		
		//Testa se adicionou ao topo da pilha a string "Bruce".
		pilha.push("Bruce");
		assertEquals("[Bruce, Diana, Clark, Barry]", pilha.toString(), "Deve retornar [Bruce, Diana, Clark, Barry]");
				
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");		
		
		//Testa se removeu o topo da pilha "Bruce".
		assertEquals("Bruce", pilha.pop(), "Deve retornar Bruce");		
		
		//Testa se adicionou ao topo da pilha a string "Dick".
		pilha.push("Dick");
		assertEquals("[Dick, Diana, Clark, Barry]", pilha.toString(), "Deve retornar [Dick, Diana, Clark, Barry]");		
		
		//Testa se removeu o topo da pilha "Dick".
		assertEquals("Dick", pilha.pop(), "Deve retornar Dick");		
		
		//Testa se removeu o topo da pilha "Diana".
		assertEquals("Diana", pilha.pop(), "Deve retornar Diana");
		
		//Testa se adicionou ao topo da pilha a string "Damian".
		pilha.push("Damian");
		assertEquals("[Damian, Clark, Barry]", pilha.toString(), "Deve retornar [Damian, Clark, Barry]");		
		
		//Testa se adicionou ao topo da pilha a string "Victor".
		pilha.push("Victor");
		assertEquals("[Victor, Damian, Clark, Barry]", pilha.toString(), "Deve retornar [Victor, Damian, Clark, Barry]");
		
		//Testa se a pilha não está vazia.
		assertFalse(pilha.isEmpty(), "Deve retornar false");		
		
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");		
		
		//Testa o estado atual da pilha.
		assertEquals("[Victor, Damian, Clark, Barry]", pilha.toString(), "Deve retornar [Victor, Damian, Clark, Barry]");
	}

}