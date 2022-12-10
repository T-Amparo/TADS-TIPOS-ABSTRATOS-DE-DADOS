package TADS.TAD_Pilha.TAD_Pilha_Arranjo.Fontes;

import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes.EmptyStackExceptionArrayStack;
import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes.FullStackException;
import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Interfaces.Stack;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementa��o do TAD PILHA USANDO ARRANJO.
public class ArrayStack<TYPE> implements Stack<TYPE> {	
	
	//Declara��o das Variaveis.
	private TYPE stack[]; //Arranjo que ir� armazenar os elementos da pilha.
	private int capacity; //Tamanho inicial do arranjo stack.
	private int top = -1; //Indice para o topo da pilha.
	
	private static final int CAPACITY = 15; //Tamanho padr�o do arranjo.	
	
	//M�todo Construtor.
	public ArrayStack() {this(CAPACITY);} //Inicia o arranjo com o tamanho padr�o.	
	
	//M�todo Construtor.
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		
		this.capacity = capacity; //Inicia a capacidade do arranjo com o valor informado.
		stack = (TYPE[]) new Object[capacity]; //Inicia o arranjo com o tamanho inicial.		
	}
	
	//Retorna o n�mero de elementos contidos na pilha.
	public int size() {return top + 1;}
	
	//Retorna o status da pilha => (True=Vazio, False=N�o Vazio).
	public boolean isEmpty() {return top == -1;}	
	
	//Verifica se n�o � possivel realizar alguma opera��o na pilha => (0=top, remove e 1=push).
	protected void validateStack(int operation) throws EmptyStackExceptionArrayStack, FullStackException {
		
		if(operation == 0) { //Verifica qual o tipo de opera��o que est� sendo realizada.
			if(isEmpty()) throw new EmptyStackExceptionArrayStack("A pilha est� vazia!"); //Realiza a verifica��o se a pilha est� vazia.
		
		} else if(operation == 1) { //Verifica qual o tipo de opera��o que est� sendo realizada.
			if(size() == capacity) throw new FullStackException("A pilha est� cheia!"); //Realiza a verifica��o se a pilha est� cheia.
		}
	}
	
	//Retorna o elemento do topo da pilha, sem remove-lo.
	public TYPE top() throws EmptyStackExceptionArrayStack {
		
		validateStack(0); //Verifica se � possivel realizar a opera��o.
		
		return stack[top]; //Retorna o elemento que est� no topo da pilha.		
	}	
	
	//Insere um novo elemento no topo da pilha.
	public void push(TYPE element) throws FullStackException {
		
		validateStack(1); //Verifica se � possivel realizar a opera��o.
		
		stack[++top] = element; //Adiciona o elemento ao topo da pilha.		
	}	
	
	//Remove e retorna o elemento do topo da pilha.
	public TYPE pop() throws EmptyStackExceptionArrayStack {
		
		validateStack(0); //Verifica se � possivel realizar a opera��o.
		
		TYPE elementRemoved = stack[top]; //Armazena o elemento removido do topo da pilha.
		stack[top--] = null; //Remove a referencia ao elemento do topo da pilha.
		
		return elementRemoved; //Retorna o elemento removido.
	}	
	
	//Concatena todos os elementos do arranjo em uma string.
	public String toString() {
		
		String elements = "["; //Inicia a string com chaves aberta.
		
		for(int i=0; i < size(); i++) { //Percorre o arranjo stack.
			
			elements += stack[i]; //Concatena os elementos do arranjo stack com a string.
			if(i != (size() - 1)) {elements += ", ";} //Concatena o separador de elementos com a string.
		}
		
		elements += "]"; //Fecha a string com chaves.
		return elements; //Retorna a string de elementos do arranjo stack.		
	}

}