package TADS.TAD_Pilha.TAD_Pilha_Arranjo.Teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes.EmptyStackExceptionArrayStack;
import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes.FullStackException;
import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Fontes.ArrayStack;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD PILHA USANDO ARRANJO.
class ArrayStackTest {

	@Test
	void testArrayStackIntegersCapacityNotDefined() { //Testa uma pilha de inteiros com capacidade não definida.
		
		//Instancia uma pilha.
		ArrayStack<Integer> pilha = new ArrayStack<Integer>();
		
		//Testa o estado atual da pilha.
		assertEquals("[]", pilha.toString(), "Deve retornar []");		
		
		//Testa se o tamanho da pilha é 0.
		assertEquals(0, pilha.size(), "Deve retornar 0");
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionArrayStack.class, () ->{pilha.top();});
		
		//Testa se adicionou ao topo da pilha o número 5.
		pilha.push(5);
		assertEquals("[5]", pilha.toString(), "Deve retornar [5]");
		
		//Testa se adicionou ao topo da pilha o número 3.
		pilha.push(3);
		assertEquals("[5, 3]", pilha.toString(), "Deve retornar [5, 3]");
		
		//Testa se removeu do topo da pilha o número 3.
		assertEquals(3, (int)pilha.pop(), "Deve retornar 3");
		
		//Testa se adicionou ao topo da pilha o número 7.
		pilha.push(7);
		assertEquals("[5, 7]", pilha.toString(), "Deve retornar [5, 7]");
		
		//Testa se removeu do topo da pilha o número 7.
		assertEquals(7, (int)pilha.pop(), "Deve retornar 7");
		
		//Testa se o numero no topo da pilha é 5.
		assertEquals(5, (int)pilha.top(), "Deve retornar 5");
		
		//Testa se removeu do topo da pilha o número 5.
		assertEquals(5, (int)pilha.pop(), "Deve retornar 5");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionArrayStack.class, () -> {pilha.pop();});
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");
		
		//Testa se adicionou ao topo da pilha o número 9.
		pilha.push(9);
		assertEquals("[9]", pilha.toString(), "Deve retornar [9]");
		
		//Testa se adicionou ao topo da pilha o número 7.
		pilha.push(7);
		assertEquals("[9, 7]", pilha.toString(), "Deve retornar [9, 7]");		
		
		//Testa se adicionou ao topo da pilha o número 3.
		pilha.push(3);
		assertEquals("[9, 7, 3]", pilha.toString(), "Deve retornar [9, 7, 3]");
		
		//Testa se adicionou ao topo da pilha o número 5.
		pilha.push(5);
		assertEquals("[9, 7, 3, 5]", pilha.toString(), "Deve retornar [9, 7, 3, 5]");
		
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");
		
		//Testa se removeu do topo da pilha o número 5.
		assertEquals(5, (int)pilha.pop(), "Deve retornar 5");
		
		//Testa se adicionou ao topo da pilha o número 8.
		pilha.push(8);
		assertEquals("[9, 7, 3, 8]", pilha.toString(), "Deve retornar [9, 7, 3, 8]");
		
		//Testa se removeu do topo da pilha o número 8.
		assertEquals(8, (int)pilha.pop(), "Deve retornar 8");
		
		//Testa se removeu do topo da pilha o número 3.
		assertEquals(3, (int)pilha.pop(), "Deve retornar 3");
		
		//Testa se adicionou ao topo da pilha o número 6.
		pilha.push(6);
		assertEquals("[9, 7, 6]", pilha.toString(), "Deve retornar [9, 7, 6]");	
		
		//Testa se adicionou ao topo da pilha o número 4.
		pilha.push(4);
		assertEquals("[9, 7, 6, 4]", pilha.toString(), "Deve retornar [9, 7, 6, 4]");		
		
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");
	}
	
	@Test
	void testArrayStackStringsCapacityNotDefined() { //Testa uma pilha de strings com capacidade não definida.
		
		//Instancia uma pilha.
		ArrayStack<String> pilha = new ArrayStack<String>();
		
		//Testa o estado atual da pilha.
		assertEquals("[]", pilha.toString(), "Deve retornar []");
		
		//Testa se o tamanho da pilha é 0.
		assertEquals(0, pilha.size(), "Deve retornar 0");
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionArrayStack.class, () ->{pilha.top();});
		
		//Testa se adicionou ao topo da pilha a string "Bruce".
		pilha.push("Bruce");
		assertEquals("[Bruce]", pilha.toString(), "Deve retornar [Bruce]");
		
		//Testa se adicionou ao topo da pilha a string "Diana".
		pilha.push("Diana");
		assertEquals("[Bruce, Diana]", pilha.toString(), "Deve retornar [Bruce, Diana]");
		
		//Testa se removeu o topo da pilha "Diana".
		assertEquals("Diana", pilha.pop(), "Deve retornar Diana");
		
		//Testa se adicionou ao topo da pilha a string "Clark".
		pilha.push("Clark");
		assertEquals("[Bruce, Clark]", pilha.toString(), "Deve retornar [Bruce, Clark]");		
		
		//Testa se removeu o topo da pilha "Clark".
		assertEquals("Clark", pilha.pop(), "Deve retornar Clark");
		
		//Testa se o elemento no topo da pilha é "Bruce"
		assertEquals("Bruce", pilha.top(), "Deve retornar Bruce");		
		
		//Testa se removeu o topo da pilha "Bruce".
		assertEquals("Bruce", pilha.pop(), "Deve retornar Bruce");		

		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionArrayStack.class, () ->{pilha.pop();});
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");		
		
		//Testa se adicionou ao topo da pilha a string "Barry".
		pilha.push("Barry");
		assertEquals("[Barry]", pilha.toString(), "Deve retornar [Barry]");		
		
		//Testa se adicionou ao topo da pilha a string "Clark".
		pilha.push("Clark");
		assertEquals("[Barry, Clark]", pilha.toString(), "Deve retornar [Barry, Clark]");
		
		//Testa se adicionou ao topo da pilha a string "Diana".
		pilha.push("Diana");
		assertEquals("[Barry, Clark, Diana]", pilha.toString(), "Deve retornar [Barry, Clark, Diana]");
		
		//Testa se adicionou ao topo da pilha a string "Bruce".
		pilha.push("Bruce");
		assertEquals("[Barry, Clark, Diana, Bruce]", pilha.toString(), "Deve retornar [Barry, Clark, Diana, Bruce]");
				
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");		
		
		//Testa se removeu o topo da pilha "Bruce".
		assertEquals("Bruce", pilha.pop(), "Deve retornar Bruce");		
		
		//Testa se adicionou ao topo da pilha a string "Dick".
		pilha.push("Dick");
		assertEquals("[Barry, Clark, Diana, Dick]", pilha.toString(), "Deve retornar [Barry, Clark, Diana, Dick]");		
		
		//Testa se removeu o topo da pilha "Dick".
		assertEquals("Dick", pilha.pop(), "Deve retornar Dick");		
		
		//Testa se removeu o topo da pilha "Diana".
		assertEquals("Diana", pilha.pop(), "Deve retornar Diana");
		
		//Testa se adicionou ao topo da pilha a string "Damian".
		pilha.push("Damian");
		assertEquals("[Barry, Clark, Damian]", pilha.toString(), "Deve retornar [Barry, Clark, Damian]");		
		
		//Testa se adicionou ao topo da pilha a string "Victor".
		pilha.push("Victor");
		assertEquals("[Barry, Clark, Damian, Victor]", pilha.toString(), "Deve retornar [Barry, Clark, Damian, Victor]");
		
		//Testa se a pilha não está vazia.
		assertFalse(pilha.isEmpty(), "Deve retornar false");		
		
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");		
		
		//Testa o estado atual da pilha.
		assertEquals("[Barry, Clark, Damian, Victor]", pilha.toString(), "Deve retornar [Barry, Clark, Damian, Victor]");
	}	

	@Test
	void testArrayStackIntegersCapacityDefined() { //Testa uma pilha de inteiros com capacidade definida.
		
		//Instancia uma pilha.
		ArrayStack<Integer> pilha = new ArrayStack<Integer>(4);
		
		//Testa o estado atual da pilha.
		assertEquals("[]", pilha.toString(), "Deve retornar []");		
		
		//Testa se o tamanho da pilha é 0.
		assertEquals(0, pilha.size(), "Deve retornar 0");
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionArrayStack.class, () ->{pilha.top();});
		
		//Testa se adicionou ao topo da pilha o número 5.
		pilha.push(5);
		assertEquals("[5]", pilha.toString(), "Deve retornar [5]");
		
		//Testa se adicionou ao topo da pilha o número 3.
		pilha.push(3);
		assertEquals("[5, 3]", pilha.toString(), "Deve retornar [5, 3]");
		
		//Testa se removeu do topo da pilha o número 3.
		assertEquals(3, (int)pilha.pop(), "Deve retornar 3");
		
		//Testa se adicionou ao topo da pilha o número 7.
		pilha.push(7);
		assertEquals("[5, 7]", pilha.toString(), "Deve retornar [5, 7]");
		
		//Testa se removeu do topo da pilha o número 7.
		assertEquals(7, (int)pilha.pop(), "Deve retornar 7");
		
		//Testa se o numero no topo da pilha é 5.
		assertEquals(5, (int)pilha.top(), "Deve retornar 5");
		
		//Testa se removeu do topo da pilha o número 5.
		assertEquals(5, (int)pilha.pop(), "Deve retornar 5");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionArrayStack.class, () -> {pilha.pop();});
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");
		
		//Testa se adicionou ao topo da pilha o número 9.
		pilha.push(9);
		assertEquals("[9]", pilha.toString(), "Deve retornar [9]");
		
		//Testa se adicionou ao topo da pilha o número 7.
		pilha.push(7);
		assertEquals("[9, 7]", pilha.toString(), "Deve retornar [9, 7]");		
		
		//Testa se adicionou ao topo da pilha o número 3.
		pilha.push(3);
		assertEquals("[9, 7, 3]", pilha.toString(), "Deve retornar [9, 7, 3]");
		
		//Testa se adicionou ao topo da pilha o número 5.
		pilha.push(5);
		assertEquals("[9, 7, 3, 5]", pilha.toString(), "Deve retornar [9, 7, 3, 5]");
		
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");		

		//Testa se a classe de excessão está funcionando. 
		assertThrows(FullStackException.class, () ->{pilha.push(4);});
		
		//Testa se removeu do topo da pilha o número 5.
		assertEquals(5, (int)pilha.pop(), "Deve retornar 5");
		
		//Testa se adicionou ao topo da pilha o número 8.
		pilha.push(8);
		assertEquals("[9, 7, 3, 8]", pilha.toString(), "Deve retornar [9, 7, 3, 8]");
		
		//Testa se removeu do topo da pilha o número 8.
		assertEquals(8, (int)pilha.pop(), "Deve retornar 8");
		
		//Testa se removeu do topo da pilha o número 3.
		assertEquals(3, (int)pilha.pop(), "Deve retornar 3");
		
		//Testa se adicionou ao topo da pilha o número 6.
		pilha.push(6);
		assertEquals("[9, 7, 6]", pilha.toString(), "Deve retornar [9, 7, 6]");	
		
		//Testa se adicionou ao topo da pilha o número 4.
		pilha.push(4);
		assertEquals("[9, 7, 6, 4]", pilha.toString(), "Deve retornar [9, 7, 6, 4]");		
		
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");		

		//Testa se a classe de excessão está funcionando. 
		assertThrows(FullStackException.class, () ->{pilha.push(4);});
	}
	
	@Test
	void testArrayStackStringsCapacityDefined() { //Testa uma pilha de strings com capacidade definida.
		
		//Instancia uma pilha.
		ArrayStack<String> pilha = new ArrayStack<String>(4);
		
		//Testa o estado atual da pilha.
		assertEquals("[]", pilha.toString(), "Deve retornar []");
		
		//Testa se o tamanho da pilha é 0.
		assertEquals(0, pilha.size(), "Deve retornar 0");
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");
		
		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionArrayStack.class, () ->{pilha.top();});
		
		//Testa se adicionou ao topo da pilha a string "Bruce".
		pilha.push("Bruce");
		assertEquals("[Bruce]", pilha.toString(), "Deve retornar [Bruce]");
		
		//Testa se adicionou ao topo da pilha a string "Diana".
		pilha.push("Diana");
		assertEquals("[Bruce, Diana]", pilha.toString(), "Deve retornar [Bruce, Diana]");
		
		//Testa se removeu o topo da pilha "Diana".
		assertEquals("Diana", pilha.pop(), "Deve retornar Diana");
		
		//Testa se adicionou ao topo da pilha a string "Clark".
		pilha.push("Clark");
		assertEquals("[Bruce, Clark]", pilha.toString(), "Deve retornar [Bruce, Clark]");		
		
		//Testa se removeu o topo da pilha "Clark".
		assertEquals("Clark", pilha.pop(), "Deve retornar Clark");
		
		//Testa se o elemento no topo da pilha é "Bruce"
		assertEquals("Bruce", pilha.top(), "Deve retornar Bruce");		
		
		//Testa se removeu o topo da pilha "Bruce".
		assertEquals("Bruce", pilha.pop(), "Deve retornar Bruce");		

		//Testa se a classe de excessão está funcionando. 
		assertThrows(EmptyStackExceptionArrayStack.class, () ->{pilha.pop();});
		
		//Testa se a pilha está vazia.
		assertTrue(pilha.isEmpty(), "Deve retornar true");		
		
		//Testa se adicionou ao topo da pilha a string "Barry".
		pilha.push("Barry");
		assertEquals("[Barry]", pilha.toString(), "Deve retornar [Barry]");		
		
		//Testa se adicionou ao topo da pilha a string "Clark".
		pilha.push("Clark");
		assertEquals("[Barry, Clark]", pilha.toString(), "Deve retornar [Barry, Clark]");
		
		//Testa se adicionou ao topo da pilha a string "Diana".
		pilha.push("Diana");
		assertEquals("[Barry, Clark, Diana]", pilha.toString(), "Deve retornar [Barry, Clark, Diana]");
		
		//Testa se adicionou ao topo da pilha a string "Bruce".
		pilha.push("Bruce");
		assertEquals("[Barry, Clark, Diana, Bruce]", pilha.toString(), "Deve retornar [Barry, Clark, Diana, Bruce]");
				
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");		

		//Testa se a classe de excessão está funcionando. 
		assertThrows(FullStackException.class, () ->{pilha.push("Oliver");});
		
		//Testa se removeu o topo da pilha "Bruce".
		assertEquals("Bruce", pilha.pop(), "Deve retornar Bruce");		
		
		//Testa se adicionou ao topo da pilha a string "Dick".
		pilha.push("Dick");
		assertEquals("[Barry, Clark, Diana, Dick]", pilha.toString(), "Deve retornar [Barry, Clark, Diana, Dick]");		
		
		//Testa se removeu o topo da pilha "Dick".
		assertEquals("Dick", pilha.pop(), "Deve retornar Dick");		
		
		//Testa se removeu o topo da pilha "Diana".
		assertEquals("Diana", pilha.pop(), "Deve retornar Diana");
		
		//Testa se adicionou ao topo da pilha a string "Damian".
		pilha.push("Damian");
		assertEquals("[Barry, Clark, Damian]", pilha.toString(), "Deve retornar [Barry, Clark, Damian]");		
		
		//Testa se adicionou ao topo da pilha a string "Victor".
		pilha.push("Victor");
		assertEquals("[Barry, Clark, Damian, Victor]", pilha.toString(), "Deve retornar [Barry, Clark, Damian, Victor]");
		
		//Testa se a pilha não está vazia.
		assertFalse(pilha.isEmpty(), "Deve retornar false");		
		
		//Testa se o tamanho da pilha é 4.
		assertEquals(4, pilha.size(), "Deve retornar 4");		

		//Testa se a classe de excessão está funcionando. 
		assertThrows(FullStackException.class, () ->{pilha.push("Oliver");});
		
		//Testa o estado atual da pilha.
		assertEquals("[Barry, Clark, Damian, Victor]", pilha.toString(), "Deve retornar [Barry, Clark, Damian, Victor]");
	}

}