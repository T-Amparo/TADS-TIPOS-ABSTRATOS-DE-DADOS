package TADS.TAD_Mapa.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Fontes.HeapPriorityQueue;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces.EntryHeapPriorityQueue;
import TADS.TAD_Mapa.Fontes.HashTableMap;
import TADS.TAD_Mapa.Interfaces.Entry;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD MAPA.
class HashTableMapTest {

	@Test
	void testHashTableMapStrings() { //Testa um mapa com chaves inteiras e valores Strings.		
		
		//Instancia um mapa.
		HashTableMap<Integer, String> mapa = new HashTableMap<Integer, String>();
		
		//Testa se o mapa está vazio.
		assertTrue(mapa.isEmpty());

		//Testa se adicionou a entrada(5, A) no mapa.
		assertNull(mapa.put(5, "A"));
		assertEquals("{(5,A)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se adicionou a entrada(7, B) no mapa.
		assertNull(mapa.put(7, "B"));
		assertEquals("{(5,A), (7,B)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se adicionou a entrada(2, C) no mapa.
		assertNull(mapa.put(2, "C"));
		assertEquals("{(2,C), (5,A), (7,B)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se adicionou a entrada(8, D) no mapa.
		assertNull(mapa.put(8, "D"));
		assertEquals("{(2,C), (5,A), (7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se adicionou a entrada(2, E) no mapa.
		assertNotNull(mapa.put(2, "E"));
		assertEquals("{(2,E), (5,A), (7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se o valor da chave 7 é B.
		assertEquals("B", mapa.get(7));
		assertEquals("{(2,E), (5,A), (7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se o valor da chave 4 é null, pois não existe.
		assertNull(mapa.get(4));

		assertEquals("{(2,E), (5,A), (7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se o valor da chave 2 é E.
		assertEquals("E", mapa.get(2));
		assertEquals("{(2,E), (5,A), (7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se a quantidade de entradas no mapa é 4.
		assertEquals(4, mapa.size());

		assertEquals("{(2,E), (5,A), (7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se removeu a chave 5 com elemento A.
		assertEquals("A", mapa.remove(5));
		assertEquals("{(2,E), (7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se removeu a chave 2 com elemento E.
		assertEquals("E", mapa.remove(2));
		assertEquals("{(7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.

		//Testa se o valor da chave 2 é null, pois não existe.
		assertNull(mapa.get(2));

		//Testa se o mapa está vazio.
		assertFalse(mapa.isEmpty());

		assertEquals("{(7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.
		assertEquals("{7, 8}", ordenarKeys(mapa.keySet())); //Testa a coleção de chaves do mapa.
		
		assertEquals("{(7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.
		assertEquals("{B, D}", ordenarValues(mapa.values())); //Testa a coleção de valores do mapa.
		
		assertEquals("{(7,B), (8,D)}", ordenar(mapa.entrySet())); //Testa o estado atual do mapa.
	}
	
	private Object ordenarValues(Iterable<String> values) {

		String saida = "{";

		HeapPriorityQueue<String, String> P = new HeapPriorityQueue<String, String>();

		for (String value : values) { P.insert(value, value); }

		EntryHeapPriorityQueue<String, String> entry;

		while (!P.isEmpty()) {

			entry = P.removeMin();
			saida += entry.getKey() + ", ";
		}

		return saida.substring(0, saida.length() - 2) + "}";
	}
	
	private Object ordenarKeys(Iterable<Integer> keySet) {

		String saida = "{";

		HeapPriorityQueue<Integer, Integer> P = new HeapPriorityQueue<Integer, Integer>();

		for (Integer key : keySet) { P.insert(key, key); }

		EntryHeapPriorityQueue<Integer, Integer> entry;

		while (!P.isEmpty()) {

			entry = P.removeMin();
			saida += entry.getKey() + ", ";
		}

		return saida.substring(0, saida.length() - 2) + "}";
	}
	
	protected String ordenar(Iterable<Entry<Integer, String>> iterable) {

		String saida = "{";

		HeapPriorityQueue<Integer, String> P = new HeapPriorityQueue<Integer, String>();

		for (Entry<Integer, String> entry : iterable) { P.insert(entry.getKey(), entry.getValue()); }

		EntryHeapPriorityQueue<Integer, String> entry;

		while (!P.isEmpty()) {

			entry = P.removeMin();
			saida += "(" + entry.getKey() + "," + entry.getValue() + "), ";
		}

		return saida.substring(0, saida.length() - 2) + "}";
	}

}