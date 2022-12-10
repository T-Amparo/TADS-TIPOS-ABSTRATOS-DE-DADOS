package TADS.TAD_Lista.TAD_Lista_Arranjo.Fontes;

import TADS.TAD_Lista.TAD_Lista_Arranjo.Interfaces.IndexList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do TAD LISTA ARRANJO.
public class ArrayIndexList<TYPE> implements IndexList<TYPE> {
	
	//Declaração das Variaveis.
	private TYPE array[]; //Arranjo que irá armazenar os elementos da lista.
	private int capacity; //Tamanho inicial do arranjo.
	private int size = 0; //Número total de elementos contidos no arranjo.
	
	private static final int CAPACITY = 15; //Tamanho padrão do arranjo.
	
	//Método Construtor.
	public ArrayIndexList() {this(CAPACITY);} //Inicia o arranjo com o tamanho padrão.
	
	//Método Construtor.
	@SuppressWarnings("unchecked")
	public ArrayIndexList(int capacity) {
		
		this.capacity = capacity; //Inicia a capacidade do arranjo com o valor informado.
		array = (TYPE[]) new Object[capacity]; //Inicia o arranjo com o tamanho inicial.		
	}
	
	//Retorna o número de elementos contidos no arranjo.
	public int size() {return size;}
	
	//Retorna o status do arranjo => True=Vazio, False=Não Vazio.
	public boolean isEmpty() {return size() == 0;}
	
	//Verifica se o 'index=indice' informado é valido.
	protected void validateIndex(int index, int size) throws IndexOutOfBoundsException {		
		if(index < 0 || index >= size) {throw new IndexOutOfBoundsException("Indice [" + index + "] invalido!");} //Realiza a verificação.
	}
	
	//Duplica a capacidade do arranjo.
	protected void duplicateArrangement() {		
			
		capacity *= 2; //Duplica o tamanho inicial do arranjo.
			
		@SuppressWarnings("unchecked")
		TYPE arrangementTemp[] = (TYPE[]) new Object[capacity]; //Cria um arranjo temporario, para armazenar os elementos do arranjo principal.
			
		for(int i=0; i < size(); i++) {arrangementTemp[i] = array[i];} //Percorre o arranjo principal e o arranjo temporario recebe os elementos do mesmo.
		array = arrangementTemp; //Arranjo principal recebe arranjo temporario.
	}
	
	//Retorna o elemento contido no 'index=indice' do arranjo.
	public TYPE get(int index) throws IndexOutOfBoundsException {
		
		validateIndex(index, size()); //Verifica se o 'index=indice' é valido.
		
		return array[index]; //Retorna o elemento.
	}
	
	//Troca o elemento do 'index=indice' informado pelo novo 'element=elemento'.
	public TYPE set(int index, TYPE element) throws IndexOutOfBoundsException {
		
		validateIndex(index, size()); //Verifica se o 'index=indice' é valido.
		
		TYPE elementRemoved = array[index]; //Armazena o elemento removido.
		array[index] = element; //Inseri o novo elemento no arranjo.
		
		return elementRemoved; //Retorna o elemento removido.
	}
	
	//Insere um novo elemento no 'index=indice' informado.
	public void add(int index, TYPE element) throws IndexOutOfBoundsException {
		
		validateIndex(index, (size() + 1)); //Verifica se o 'index=indice' é valido.
		
		if(size() == capacity) {duplicateArrangement();} //Duplica o arranjo, caso atinja a capacidade maxima.
		
		for(int i=(size() - 1); i >= index; i--) {array[i+1] = array[i];} //Desloca os elementos do arranjo para cima.
		array[index] = element; //Inseri o 'element=elemento' no arranjo no 'index=indice' informado.
		size++; //Acrescenta-se +1 ao número total de elementos contidos no arranjo.
	}
	
	//Remove o elemento armazenado no 'index=indice' do arranjo.
	public TYPE remove(int index) throws IndexOutOfBoundsException {
		
		validateIndex(index, size()); //Verifica se o 'index=indice' é valido.
		
		TYPE elementRemoved = array[index]; //Armazena o elemento removido.
		
		for(int i=index; i < (size() - 1); i++) {array[i] = array[i+1];} //Desloca os elementos do arranjo para baixo.
		size--; //Diminui-se 1 do número total de elementos contidos no arranjo.
		
		return elementRemoved; //Retorna o elemento removido.	
	}
	
	//Concatena todos os elementos do arranjo em uma string.
	public String toString() {
		
		String elements = "("; //Inicia a string com parenteses aberto.
		
		if(size() > 0) { //Verifica se há elementos no arranjo.
			
			for(int i=0; i < size; i++) { //Percorre o arranjo.
				
				elements += array[i].toString(); //Concatena os elementos do arranjo com a string.
				
				if(i != (size() - 1)) {elements += ", ";} //Concatena o separador de elementos com a string.				
			}			
		}
		
		elements += ")"; //Fecha a string com parenteses fechado.
		return elements; //Retorna a string de elementos do arranjo.
	}	

}