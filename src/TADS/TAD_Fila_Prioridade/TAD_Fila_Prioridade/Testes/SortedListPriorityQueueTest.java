package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Excecoes.EmptyPriorityQueueException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Fontes.SortedListPriorityQueue;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Interfaces.Entry;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Realiza o teste da implemetação do TAD FILA DE PRIORIDADE.
class SortedListPriorityQueueTest {

	@Test
	void testSortedListPriorityQueueStrings() { //Testa uma fila de prioridade com chaves inteiras e valores Strings.
		
		//Instancia uma fila.
		SortedListPriorityQueue<Integer, String> fila = new SortedListPriorityQueue<Integer, String>();
		
		//Variavel para armazenar uma entrada da fila.
		Entry<Integer, String> entrada;
		
		//Adiciona à fila a entrada (5, A) e armazena na variavel entrada.
		entrada = fila.insert(5, "A");

		//Testa se a variavel entrada é (5, A).
		assertEquals("(5,A)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(5,A)]", fila.toString());

		//Adiciona à fila a entrada (9, C) e armazena na variavel entrada.
		entrada = fila.insert(9, "C");

		//Testa se a variavel entrada é (9, C).
		assertEquals("(9,C)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(5,A), (9,C)]", fila.toString());

		//Adiciona à fila a entrada (3, B) e armazena na variavel entrada.
		entrada = fila.insert(3, "B");

		//Testa se a variavel entrada é (3, B).
		assertEquals("(3,B)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(3,B), (5,A), (9,C)]", fila.toString());

		//Adiciona à fila a entrada (7, D) e armazena na variavel entrada.
		entrada = fila.insert(7, "D");

		//Testa se a variavel entrada é (7, D).
		assertEquals("(7,D)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(3,B), (5,A), (7,D), (9,C)]", fila.toString());

		//Armazena a menor entrada da fila.
		entrada = fila.min();

		//Testa se a menor entrada da fila é (3, B).
		assertEquals("(3,B)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(3,B), (5,A), (7,D), (9,C)]", fila.toString());

		//Remove e armazena a menor entrada da fila.
		entrada = fila.removeMin();

		//Testa se a entrada removida foi (3, B).
		assertEquals("(3,B)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(5,A), (7,D), (9,C)]", fila.toString());

		//Testa se o tamanho da file é 3.
		assertEquals(3, fila.size());

		//Remove e armazena a menor entrada da fila.
		entrada = fila.removeMin();

		//Testa se a entrada removida foi (5, A).
		assertEquals("(5,A)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(7,D), (9,C)]", fila.toString());

		//Remove e armazena a menor entrada da fila.
		entrada = fila.removeMin();

		//Testa se a entrada removida foi (7, D).
		assertEquals("(7,D)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(9,C)]", fila.toString());

		//Remove e armazena a menor entrada da fila.
		entrada = fila.removeMin();

		//Testa se a entrada removida foi (9, C).
		assertEquals("(9,C)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[]", fila.toString());

		//Testa se a exceção está funcionado.
		assertThrows(EmptyPriorityQueueException.class, () -> { fila.removeMin(); });
	}
	
	@Test
	void testPeopleComparatorInternal() { //Testa o comparador padrão da classe People.
		
		//Instancia uma fila.
		SortedListPriorityQueue<People, People> fila = new SortedListPriorityQueue<People, People>();

		//Variavel para armazenar uma entrada da fila.
		Entry<People, People> entrada;

		//Armazena uma pessoa.
		People people;
		
		//Cria uma pessoa e armazena na variavel people.
		people = new People("João", 20);

		//Adiciona a pessoa criada na fila.
		entrada = fila.insert(people, null);

		//Testa a variavel entrada.
		assertEquals("(Pessoa {nome=João, idade=20},null)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(Pessoa {nome=João, idade=20},null)]", fila.toString());
		
		//Cria uma pessoa e armazena na variavel people.
		people = new People("Marcos", 30);

		//Adiciona a pessoa criada na fila.
		entrada = fila.insert(people, null);

		//Testa a variavel entrada.
		assertEquals("(Pessoa {nome=Marcos, idade=30},null)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(Pessoa {nome=João, idade=20},null), (Pessoa {nome=Marcos, idade=30},null)]", fila.toString());
		
		//Cria uma pessoa e armazena na variavel people.
		people = new People("Fabio", 25);

		//Adiciona a pessoa criada na fila.
		entrada = fila.insert(people, null);

		//Testa a variavel entrada.
		assertEquals("(Pessoa {nome=Fabio, idade=25},null)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(Pessoa {nome=João, idade=20},null), (Pessoa {nome=Fabio, idade=25},null), (Pessoa {nome=Marcos, idade=30},null)]", fila.toString());
	}	
	
	@Test
	void testPeopleComparatorExternal() { //Testa o comparador externo para a classe People.
		
		//Armazena um comparador externo para a classe pessoa.
		Comparator<People> comparator = new ComparatorPeople();
		
		//Instancia uma fila.
		SortedListPriorityQueue<People, People> fila = new SortedListPriorityQueue<People, People>(comparator);

		//Variavel para armazenar uma entrada da fila.
		Entry<People, People> entrada;

		//Armazena uma pessoa.
		People people;
		
		//Cria uma pessoa e armazena na variavel people.
		people = new People("João", 20);

		//Adiciona a pessoa criada na fila.
		entrada = fila.insert(people, null);

		//Testa a variavel entrada.
		assertEquals("(Pessoa {nome=João, idade=20},null)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(Pessoa {nome=João, idade=20},null)]", fila.toString());
		
		//Cria uma pessoa e armazena na variavel people.
		people = new People("Marcos", 30);

		//Adiciona a pessoa criada na fila.
		entrada = fila.insert(people, null);

		//Testa a variavel entrada.
		assertEquals("(Pessoa {nome=Marcos, idade=30},null)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(Pessoa {nome=João, idade=20},null), (Pessoa {nome=Marcos, idade=30},null)]", fila.toString());
		
		//Cria uma pessoa e armazena na variavel people.
		people = new People("Fabio", 25);

		//Adiciona a pessoa criada na fila.
		entrada = fila.insert(people, null);

		//Testa a variavel entrada.
		assertEquals("(Pessoa {nome=Fabio, idade=25},null)", entrada.toString());

		//Testa o estado atual da fila.
		assertEquals("[(Pessoa {nome=Fabio, idade=25},null), (Pessoa {nome=João, idade=20},null), (Pessoa {nome=Marcos, idade=30},null)]", fila.toString());
	}

}