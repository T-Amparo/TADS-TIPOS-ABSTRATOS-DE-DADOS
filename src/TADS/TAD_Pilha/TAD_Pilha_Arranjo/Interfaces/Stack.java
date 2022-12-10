package TADS.TAD_Pilha.TAD_Pilha_Arranjo.Interfaces;

import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes.EmptyStackExceptionArrayStack;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para o TAD PILHA USANDO ARRANJO.
public interface Stack<TYPE> {
	
	//Retorna um inteiro com a quantidade de elementos contidos na PILHA.
	public int size();
	
	//Retorna um valor booleano representando o status da PILHA => True=Vazia, False=Não Vazia.
	public boolean isEmpty();
	
	//Retorna o elemento do topo da PILHA, sem remove-lo.
	public TYPE top() throws EmptyStackExceptionArrayStack;
	
	//Adiciona um 'element=elemento' no topo da PILHA.
	public void push(TYPE element);
	
	//Remove e retorna o elemento do topo da PILHA.
	public TYPE pop() throws EmptyStackExceptionArrayStack;

}