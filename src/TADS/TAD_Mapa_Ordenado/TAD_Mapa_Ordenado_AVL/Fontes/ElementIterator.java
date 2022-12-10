package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Fontes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces.Position;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces.PositionList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do Iterador, para iterar sobre a LISTA DE NODOS.
public class ElementIterator<TYPE> implements Iterator<TYPE> {
	
	//Declaração de Variaveis.
	protected PositionList<TYPE> list; //Armazena uma LISTA DE NODOS.
	protected Position<TYPE> cursor; //Armazena a posição do nodo atual.
	
	//Método Construtor.
	public ElementIterator(PositionList<TYPE> list) {
		
		this.list = list; //Recebe a referencia de uma LISTA DE NODOS.
		cursor = (list.isEmpty()) ? null : list.first(); //Se não estiver vazia, recebe a primeira posição da LISTA DE NODOS.
	}
	
	//Retorna o status do objeto da proxima posição => True=Existe, False=Não Existe. 
	public boolean hasNext() {return (cursor != null);}
	
	//Retorna o proximo nodo apartir de um dado nodo.
	public TYPE next() throws NoSuchElementException {
		
		//Verifica se a posição é valida.
		if(cursor == null) throw new NoSuchElementException("Não existe um proximo elemento!");
		
		TYPE node = cursor.element(); //Armazena o node da LISTA DE NODOS na variavel.
		cursor = (cursor == list.last()) ? null : list.next(cursor); //Caso não seja o ultimo nodo, avança o cursor.
		return node; //Retorna o nodo da LISTA DE NODOS.
	}
	
	//Lança uma exceção ao tentar realizar a operação de remoção, pois não é suportada.
	public void remove() throws UnsupportedOperationException {throw new UnsupportedOperationException("Remove");}
	
}