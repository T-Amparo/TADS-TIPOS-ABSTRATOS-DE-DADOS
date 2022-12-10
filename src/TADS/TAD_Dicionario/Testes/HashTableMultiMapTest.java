package TADS.TAD_Dicionario.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Dicionario.Fontes.HashTableMultiMap;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD DICIONÁRIO.
class HashTableMultiMapTest {

	@Test
	void testHashTableMultiMapStrings() { //Testa uma dicionário de strings.
		
		//Instancia e cria um dicionário.
		HashTableMultiMap<Integer, String> dict = new HashTableMultiMap<Integer, String>();
		
		//Testa o estado atual do dicionário.
		assertEquals("[]", dict.entrySet().toString());
		
		//Testa se o dicionário está vazio.
		assertTrue(dict.isEmpty());
		
		//Testa se a quantidade de elemetos no dicionário é 0.
		assertEquals(0, dict.size());
		
		//Testa se adicionou a entrada (5, A) no diconário.
		assertEquals("5=A", dict.put(5, "A").toString());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[5=A]", dict.entrySet().toString());

		//Testa se adicionou a entrada (7, B) no diconário.
		assertEquals("7=B", dict.put(7, "B").toString());

		//Testa o estado atual do dicionário.
		assertEquals("[5=A, 7=B]", dict.entrySet().toString());

		//Testa se adicionou a entrada (2, C) no diconário.
		assertEquals("2=C", dict.put(2, "C").toString());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=C, 5=A, 7=B]", dict.entrySet().toString());

		//Testa se adicionou a entrada (8, D) no diconário.
		assertEquals("8=D", dict.put(8, "D").toString());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=C, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se adicionou a entrada (2, E) no diconário.
		assertEquals("2=E", dict.put(2, "E").toString());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se retornou a entrada correspondente a chave.
		assertEquals("7=B", dict.get(7).toString());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se não existe chave com número 4.
		assertNull(dict.get(4));

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se retornou a entrada correspondente a chave.
		assertEquals("2=C", dict.get(2).toString());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se retornou todas as entradas do dicionário com chave 2.
		assertEquals("[2=C, 2=E]", dict.getAll(2).toString());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se a quantidade de elemetos no dicionário é 5.
		assertEquals(5, dict.size());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=C, 2=E, 5=A, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se removeu a entrada (5, A) do diconário.
		assertEquals("5=A", dict.remove(dict.get(5)).toString());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=C, 2=E, 7=B, 8=D]", dict.entrySet().toString());

		//Testa se removeu a entrada (2, C) do diconário.
		assertEquals("2=C", dict.remove(dict.get(2)).toString());

		//Testa se retornou todas as entradas do dicionário.
		assertEquals("[2=E, 7=B, 8=D]", dict.entrySet().toString());		
	}

}