package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Fontes;

import java.util.Comparator;

import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Excecoes.EmptyPriorityQueueException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Excecoes.InvalidKeyException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Interfaces.Entry;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Interfaces.Position;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Interfaces.PositionList;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Interfaces.PriorityQueue;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do TAD FILA DE PRIORIDADE.
public class SortedListPriorityQueue<KEY, VALUE> implements PriorityQueue<KEY, VALUE> {
	
	//Declaração das Variaveis.
	protected PositionList<Entry<KEY, VALUE>> entries; //Armazena uma lista de todas as entradas da fila.
	protected Comparator<KEY> comparator; //Armazena um comparador.
	protected Position<Entry<KEY, VALUE>> position; //Armazena uma posição da fila, utilizada pela sub classe.
	
	//Subclasse.
	protected static class MyEntry<KEY, VALUE> implements Entry<KEY, VALUE> {
		
		//Declaração das Variaveis da Subclasse.
		protected KEY key; //Armazena uma chave.
		protected VALUE value; //Armazena um valor.
		
		//Método Construtor da Subclasse.
		public MyEntry(KEY key, VALUE value) {
			
			this.key = key; //Inicializa a chave com o valor informado.
			this.value = value; //Inicializa o valor com o valor informado.		
		}
		
		//Retorna a chave pertecente a entrada.
		public KEY getKey() {return key;}

		//Retorna o valor pertecente a entrada.
		public VALUE getValue() {return value;}
		
		//Retorna uma string (chave, valor).
		public String toString() {return "(" + key + "," + value + ")";}		
	}
	
	//Método Construtor.
	public SortedListPriorityQueue() {
		
		this.entries = new NodePositionList<Entry<KEY, VALUE>>(); //Cria uma lista de entradas vazia.
		this.comparator = new DefaultComparator<KEY>(); //Inicializa com o comparador padrão.
	}	
	
	//Método Construtor.
	public SortedListPriorityQueue(Comparator<KEY> comparator) {
		
		this.entries = new NodePositionList<Entry<KEY, VALUE>>(); //Cria uma lista de entradas vazia.
		this.comparator = comparator; //Inicializa com o comparador informado.
	}	
	
	//Método Construtor.
	public SortedListPriorityQueue(PositionList<Entry<KEY, VALUE>> entries, Comparator<KEY> comparator) {
		
		this.entries = entries; //Inicializa com a lista de entradas informadas.
		this.comparator = comparator; //Inicializa com o comparador informado.
	}
	
	//Altera o comparador da fila de prioridade.
	public void setComparator(Comparator<KEY> comparator) throws IllegalStateException {
		
		if (!isEmpty()) {throw new IllegalStateException("A fila de prioridade não está vazia!");} //Verifica se fila está vazia, caso não esteja libera a exceção.
		this.comparator = comparator; //Altera o comparador.		
	}	
	
	//Retorna o número de elementos contidos na fila.
	public int size() {return entries.size();}
	
	//Retorna o status da fila => True=Vazio, False=Não Vazio.
	public boolean isEmpty() {return entries.isEmpty();}
	
	//Retorna uma entrada com a menor chave, sem remove-la.
	public Entry<KEY, VALUE> min() throws EmptyPriorityQueueException {
		
		if (entries.isEmpty()) {throw new EmptyPriorityQueueException("A fila de prioridade está vazia!");} //Verifica se fila está vazia, caso esteje libera a exceção.
		else {return entries.first().element();} //Retorna o primeiro elemento da fila.	
	}
	
	public Entry<KEY, VALUE> insert(KEY key, VALUE value) throws InvalidKeyException {

		validateKey(key); //Verifica se a chave informada é valida.

		Entry<KEY, VALUE> entry = new MyEntry<KEY, VALUE>(key, value); //Cria a chave para a fila com os parametros informados.

		insertEntry(entry); //Método que realizará à inserção da entrada criada.
		
		return entry; //Retorna à entrada criada.
	}
	
	//Remove e retorna uma entrada(key, value) com a menor chave.
	public Entry<KEY, VALUE> removeMin() throws EmptyPriorityQueueException {

		if (entries.isEmpty()) throw new EmptyPriorityQueueException("A fila de prioridade está vazia!"); //Verifica se fila está vazia, caso esteje libera a exceção.
		else return entries.remove(entries.first()); //Remove e retorna a entrada.
	}
	
	//Método que realizará à inserção de uma entrada.
	protected void insertEntry(Entry<KEY, VALUE> entry) {

		if (entries.isEmpty()) { //Verifica se a fila está vazia.
	
			entries.addFirst(entry); //Adiciona a entrada na frente da fila.	
			position = entries.first(); //Armazena a posição da entrada inserida.

		} else if (comparator.compare(entry.getKey(), entries.last().element().getKey()) > 0) { //Usando o comparador verifica se a chave é valida para está situação.
	
			entries.addLast(entry); //Adiciona a entrada no final da fila.	
			position = entries.last(); //Armazena a posição da entrada inserida.

		} else { //Caso contrario.
	
			Position<Entry<KEY, VALUE>> current = entries.first();
	
			while (comparator.compare(entry.getKey(), current.element().getKey()) > 0) { //Percorre a lista, até a chave ser valida.

				current = entries.next(current); //Avança para a proxima posição de inserção.
			}
	
			entries.addBefore(current, entry); //Adiciona a entrada depois do elemento encontrado pelo laço.	
			position = entries.prev(current); //Armazena a posição da entrada inserida.
		}
	}
	
	//Verifica se a chave informada é valida.
	protected boolean validateKey(KEY key) throws InvalidKeyException {

		boolean valid; //Armazena o resultado da validação.

		try {valid = (comparator.compare(key, key) == 0);} //Realiza à comparação.
		catch (ClassCastException error) {throw new InvalidKeyException("A chave não pode ser comparada!");} //Libera a exceção caso à chave não possa ser comparada.

		return valid; //Retorna o resultado da validação.
	}
	
	public PositionList<Entry<KEY, VALUE>> getEntries(){return entries;}
	
	//Retorna uma string (chave, valor).
	public String toString() {return entries.toString();}
	
}