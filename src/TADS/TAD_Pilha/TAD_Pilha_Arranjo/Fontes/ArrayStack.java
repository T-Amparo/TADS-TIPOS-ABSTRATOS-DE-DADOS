package TADS.TAD_Pilha.TAD_Pilha_Arranjo.Fontes;

import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes.EmptyStackExceptionArrayStack;
import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes.FullStackException;
import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Interfaces.Stack;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do TAD PILHA USANDO ARRANJO.
public class ArrayStack<TYPE> implements Stack<TYPE> {	
	
	//Declaração das Variaveis.
	private TYPE stack[]; //Arranjo que irá armazenar os elementos da pilha.
	private int capacity; //Tamanho inicial do arranjo stack.
	private int top = -1; //Indice para o topo da pilha.
	
	private static final int CAPACITY = 15; //Tamanho padrão do arranjo.	
	
	//Método Construtor.
	public ArrayStack() {this(CAPACITY);} //Inicia o arranjo com o tamanho padrão.	
	
	//Método Construtor.
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		
		this.capacity = capacity; //Inicia a capacidade do arranjo com o valor informado.
		stack = (TYPE[]) new Object[capacity]; //Inicia o arranjo com o tamanho inicial.		
	}
	
	//Retorna o número de elementos contidos na pilha.
	public int size() {return top + 1;}
	
	//Retorna o status da pilha => (True=Vazio, False=Não Vazio).
	public boolean isEmpty() {return top == -1;}	
	
	//Verifica se não é possivel realizar alguma operação na pilha => (0=top, remove e 1=push).
	protected void validateStack(int operation) throws EmptyStackExceptionArrayStack, FullStackException {
		
		if(operation == 0) { //Verifica qual o tipo de operação que está sendo realizada.
			if(isEmpty()) throw new EmptyStackExceptionArrayStack("A pilha está vazia!"); //Realiza a verificação se a pilha está vazia.
		
		} else if(operation == 1) { //Verifica qual o tipo de operação que está sendo realizada.
			if(size() == capacity) throw new FullStackException("A pilha está cheia!"); //Realiza a verificação se a pilha está cheia.
		}
	}
	
	//Retorna o elemento do topo da pilha, sem remove-lo.
	public TYPE top() throws EmptyStackExceptionArrayStack {
		
		validateStack(0); //Verifica se é possivel realizar a operação.
		
		return stack[top]; //Retorna o elemento que está no topo da pilha.		
	}	
	
	//Insere um novo elemento no topo da pilha.
	public void push(TYPE element) throws FullStackException {
		
		validateStack(1); //Verifica se é possivel realizar a operação.
		
		stack[++top] = element; //Adiciona o elemento ao topo da pilha.		
	}	
	
	//Remove e retorna o elemento do topo da pilha.
	public TYPE pop() throws EmptyStackExceptionArrayStack {
		
		validateStack(0); //Verifica se é possivel realizar a operação.
		
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