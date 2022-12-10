package TADS.TAD_Dicionario.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Dicionario.Fontes.HashTableMultiMap;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemeta��o do TAD DICION�RIO.
class HashTableMultiMapTest {

	@Test
	void testHashTableMultiMapStrings() { //Testa uma dicion�rio de strings.
		
		//Instancia e cria um dicion�rio.
		HashTableMultiMap<Integer, String> dict = new HashTableMultiMap<Integer, String>();
		
		//Testa o estado atual do dicion�rio.
		assertEquals("[]", dict.entrySet().toString());
		
		//Testa se o dicion�rio est� vazio.
		assertTrue(dict.isEmpty());
		
		//Testa se a quantidade de elemetos no dicion�rio � 0.
		assertEquals(0, dict.size());
		
		//Testa se adicionou a entrada (5, A) no dicon�rio.
		assertEquals("5=A", dict.put(5, "A").toString());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[5=A]", dict.entrySet().toString());

		//Testa se adicionou a entrada (7, B) no dicon�rio.
		assertEquals("7=B", dict.put(7, "B").toString());

		//Testa o estado atual do dicion�rio.
		assertEquals("[5=A, 7=B]", dict.entrySet().toString());

		//Testa se adicionou a entrada (2, C) no dicon�rio.
		assertEquals("2=C", dict.put(2, "C").toString());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=C, 5=A, 7=B]", dict.entrySet().toString());

		//Testa se adicionou a entrada (8, D) no dicon�rio.
		assertEquals("8=D", dict.put(8, "D").toString());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=C, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se adicionou a entrada (2, E) no dicon�rio.
		assertEquals("2=E", dict.put(2, "E").toString());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se retornou a entrada correspondente a chave.
		assertEquals("7=B", dict.get(7).toString());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se n�o existe chave com n�mero 4.
		assertNull(dict.get(4));

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se retornou a entrada correspondente a chave.
		assertEquals("2=C", dict.get(2).toString());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se retornou todas as entradas do dicion�rio com chave 2.
		assertEquals("[2=C, 2=E]", dict.getAll(2).toString());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se a quantidade de elemetos no dicion�rio � 5.
		assertEquals(5, dict.size());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se removeu a entrada (5, A) do dicon�rio.
		assertEquals("5=A", dict.remove(dict.get(5)).toString());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=C, 2=E, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se removeu a entrada (2, C) do dicon�rio.
		assertEquals("2=C", dict.remove(dict.get(2)).toString());

		//Testa se retornou todas as entradas do dicion�rio.
		assertEquals("[2=E, 7=B, 8=D]", dict.entrySet().toString());		
	}

}