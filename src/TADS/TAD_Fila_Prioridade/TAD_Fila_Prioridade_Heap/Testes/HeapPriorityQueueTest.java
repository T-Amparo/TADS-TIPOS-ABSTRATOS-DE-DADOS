package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Fontes.HeapPriorityQueue;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD FILA DE PRIORIDADE USANDO HEAP.
class HeapPriorityQueueTest {

	@Test
	void testHeapPriorityQueueStrings() { //Testa uma fila de prioridade com chaves inteiras e valores Strings.
		
		//Instancia uma fila.
		HeapPriorityQueue<Integer, String> fila = new HeapPriorityQueue<Integer, String>();
		
		//Adiciona uma sequencia entradas a fila de prioridade.
		fila.insert(4, "C");
		fila.insert(5, "A");
		fila.insert(6, "Z");
		fila.insert(15, "K");
		fila.insert(9, "F");
		fila.insert(7, "Q");
		fila.insert(20, "B");
		fila.insert(16, "X");
		fila.insert(25, "J");
		fila.insert(14, "E");
		fila.insert(12, "H");
		fila.insert(11, "S");
		fila.insert(8, "W");
		
		//Testa o estado atual da fila.
		assertEquals("[null, [(4,C),1], [(5,A),2], [(6,Z),3], [(15,K),4], "
				+ "[(9,F),5], [(7,Q),6], [(20,B),7], [(16,X),8], [(25,J),9], "
				+ "[(14,E),10], [(12,H),11], [(11,S),12], [(8,W),13]]", fila.toString());
		
		//Adiciona uma entrada a fila de prioridade.
		fila.insert(2, "T");
		
		//Testa o estado atual da fila.
		assertEquals("[null, [(2,T),1], [(5,A),2], [(4,C),3], [(15,K),4], [(9,F),5], "
				+ "[(7,Q),6], [(6,Z),7], [(16,X),8], [(25,J),9], [(14,E),10], [(12,H),11], "
				+ "[(11,S),12], [(8,W),13], [(20,B),14]]", fila.toString());
		
		//Remove a menor entrada da fila de prioridade.
		fila.removeMin();
		
		//Testa o estado atual da fila.
		assertEquals("[null, [(4,C),1], [(5,A),2], [(6,Z),3], [(15,K),4], [(9,F),5], [(7,Q),6], [(20,B),7], "
				+ "[(16,X),8], [(25,J),9], [(14,E),10], [(12,H),11], [(11,S),12], [(8,W),13]]", fila.toString());
	}
	
}