package TADS.TAD_Arvore.TAD_Arvore_Binaria.Interfaces;

import java.util.Iterator;

import TADS.TAD_Arvore.TAD_Arvore_Binaria.Excecoes.BoundaryViolationException;
import TADS.TAD_Arvore.TAD_Arvore_Binaria.Excecoes.InvalidPositionException;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para uma LISTA DE NODOS.
public interface PositionList<TYPE> extends Iterable<TYPE> {
	
	//Retorna um inteiro com a quantidade de elementos contidos na LISTA DE NODOS.
	public int size();
	
	//Retorna um valor booleano representando o status da LISTA DE NODOS => True=Vazia, False=Não Vazia.
	public boolean isEmpty();
	
	//Retorna o primeiro nodo da LISTA DE NODOS.
	public Position<TYPE> first();
	
	//Retorna o ultimo nodo da LISTA DE NODOS.
	public Position<TYPE> last();
	
	//Retorna o nodo que segue um dado nodo da LISTA DE NODOS.
	public Position<TYPE> next(Position<TYPE> node) throws InvalidPositionException, BoundaryViolationException;
	
	//Retorna o nodo que antecede um dado nodo da LISTA DE NODOS.
	public Position<TYPE> prev(Position<TYPE> node) throws InvalidPositionException, BoundaryViolationException;
	
	//Adiciona um 'element=elemento' no inicio da LISTA DE NODOS.
	public void addFirst(TYPE element);
	
	//Adiciona um 'element=elemento' no final da LISTA DE NODOS.
	public void addLast(TYPE element);
	
	//Adiciona um 'element=elemento' após um dado elemento da LISTA DE NODOS.
	public void addAfter(Position<TYPE> node, TYPE element) throws InvalidPositionException;
	
	//Adiciona um 'element=elemento' antes de um dado elemento da LISTA DE NODOS.
	public void addBefore(Position<TYPE> node, TYPE element) throws InvalidPositionException;
	
	//Remove um nodo da lista e retorna o elemento armazenado nele.
	public TYPE remove(Position<TYPE> node) throws InvalidPositionException;
	
	//Troca o elemento armazenado em um determinado nodo e retorna o elemento que estava armazenado nele.
	public TYPE set(Position<TYPE> node, TYPE element) throws InvalidPositionException;
	
	//Retorna um iterador sobre todos os elementos da lista.
	public Iterator<TYPE> iterator();

}