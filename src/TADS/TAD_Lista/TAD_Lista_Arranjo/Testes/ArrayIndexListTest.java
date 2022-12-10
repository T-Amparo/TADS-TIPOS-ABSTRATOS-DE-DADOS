package TADS.TAD_Lista.TAD_Lista_Arranjo.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Lista.TAD_Lista_Arranjo.Fontes.ArrayIndexList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD LISTA ARRANJO.
class ArrayIndexListTest {

	@Test
	void testArrayIndexListCapacityNotDefined() { //Testa uma lista de inteiros com capacidade não definida.
		
		//Instancia e cria uma LISTA ARRANJO.
		ArrayIndexList<Integer> lista = new ArrayIndexList<Integer>();
		
		assertTrue(lista.isEmpty()); //Testa se a lista está vazia.
		
		assertEquals(0, lista.size(), "Deve retornar 0"); //Testa se a lista tem 0 elementos.
		
		assertEquals("()", lista.toString(), "Deve imprimir ()"); //Testa se a impressão da lista é "()".		
		
		lista.add(0, 7); //Inseri um elemento na lista.
		assertEquals("(7)", lista.toString(), "Deve imprimir (7)"); //Testa se o elemento foi inserido corretamente.	
		
		lista.add(0, 4); //Inseri um elemento na lista.
		assertEquals("(4, 7)", lista.toString(), "Deve imprimir (4, 7)"); //Testa se o elemento foi inserido corretamente.
		
		assertEquals(7, (int)lista.get(1), "Deve retornar 7"); //Testa se o elemento no indice 1 da lista é o número 7. 
		
		lista.add(2, 2); //Inseri um elemento na lista.
		assertEquals("(4, 7, 2)", lista.toString(), "Deve imprimir (4, 7, 2)"); //Testa se o elemento foi inserido corretamente.
		
		assertThrows(IndexOutOfBoundsException.class, () -> {lista.get(3);}); //Testa a exceção ao tentar acessa um indice fora da lista.
		
		assertEquals(7, (int)lista.remove(1), "Deve retornar 7"); //Testa se removeu o elemento corretamente da lista
		
		lista.add(1, 5); //Inseri um elemento na lista.
		assertEquals("(4, 5, 2)", lista.toString(), "Deve imprimir (4, 5, 2)"); //Testa se o elemento foi inserido corretamente.		
		
		lista.add(1, 3); //Inseri um elemento na lista.
		assertEquals("(4, 3, 5, 2)", lista.toString(), "Deve imprimir (4, 3, 5, 2)"); //Testa se o elemento foi inserido corretamente.
		
		lista.add(4, 9); //Inseri um elemento na lista.
		assertEquals("(4, 3, 5, 2, 9)", lista.toString(), "Deve imprimir (4, 3, 5, 2, 9)"); //Testa se o elemento foi inserido corretamente.

		assertEquals(5, (int)lista.get(2), "Deve retornar 5"); //Testa se o elemento no indice 2 da lista é o número 5. 
		
		assertEquals(2, (int)lista.set(3, 8), "Deve retornar 2"); //Testa se o elemento no indice 3 foi trocado pelo número 8.
		
		assertEquals(8, (int)lista.remove(3), "Deve retornar 8"); //Testa se removeu o elemento corretamente da lista
		
		assertEquals("(4, 3, 5, 9)", lista.toString(), "Deve imprimir (4, 3, 5, 9)");	//Testa se a impressão da lista está completa.	
	}	

	@Test
	void testArrayIndexListCapacityDefined() { //Testa uma lista de inteiros com capacidade definida.
		
		//Instancia e cria uma LISTA ARRANJO.
		ArrayIndexList<Integer> lista = new ArrayIndexList<Integer>(5);
		
		assertTrue(lista.isEmpty()); //Testa se a lista está vazia.
		
		assertEquals(0, lista.size(), "Deve retornar 0"); //Testa se a lista tem 0 elementos.
		
		assertEquals("()", lista.toString(), "Deve imprimir ()"); //Testa se a impressão da lista é "()".		
		
		lista.add(0, 7); //Inseri um elemento na lista.
		assertEquals("(7)", lista.toString(), "Deve imprimir (7)"); //Testa se o elemento foi inserido corretamente.	
		
		lista.add(0, 4); //Inseri um elemento na lista.
		assertEquals("(4, 7)", lista.toString(), "Deve imprimir (4, 7)"); //Testa se o elemento foi inserido corretamente.
		
		assertEquals(7, (int)lista.get(1), "Deve retornar 7"); //Testa se o elemento no indice 1 da lista é o número 7. 
		
		lista.add(2, 2); //Inseri um elemento na lista.
		assertEquals("(4, 7, 2)", lista.toString(), "Deve imprimir (4, 7, 2)"); //Testa se o elemento foi inserido corretamente.
		
		assertThrows(IndexOutOfBoundsException.class, () -> {lista.get(3);}); //Testa a exceção ao tentar acessa um indice fora da lista.
		
		assertEquals(7, (int)lista.remove(1), "Deve retornar 7"); //Testa se removeu o elemento corretamente da lista
		
		lista.add(1, 5); //Inseri um elemento na lista.
		assertEquals("(4, 5, 2)", lista.toString(), "Deve imprimir (4, 5, 2)"); //Testa se o elemento foi inserido corretamente.		
		
		lista.add(1, 3); //Inseri um elemento na lista.
		assertEquals("(4, 3, 5, 2)", lista.toString(), "Deve imprimir (4, 3, 5, 2)"); //Testa se o elemento foi inserido corretamente.
		
		lista.add(4, 9); //Inseri um elemento na lista.
		assertEquals("(4, 3, 5, 2, 9)", lista.toString(), "Deve imprimir (4, 3, 5, 2, 9)"); //Testa se o elemento foi inserido corretamente.

		assertEquals(5, (int)lista.get(2), "Deve retornar 5"); //Testa se o elemento no indice 2 da lista é o número 5. 
		
		assertEquals(2, (int)lista.set(3, 8), "Deve retornar 2"); //Testa se o elemento no indice 3 foi trocado pelo número 8.
		
		assertEquals(8, (int)lista.remove(3), "Deve retornar 8"); //Testa se removeu o elemento corretamente da lista
		
		assertEquals("(4, 3, 5, 9)", lista.toString(), "Deve imprimir (4, 3, 5, 9)");	//Testa se a impressão da lista está completa.	
	}

}