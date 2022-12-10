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

//Implementa��o do Iterador, para iterar sobre a LISTA DE NODOS.
public class ElementIterator<TYPE> implements Iterator<TYPE> {
	
	//Declara��o de Variaveis.
	protected PositionList<TYPE> list; //Armazena uma LISTA DE NODOS.
	protected Position<TYPE> cursor; //Armazena a posi��o do nodo atual.
	
	//M�todo Construtor.
	public ElementIterator(PositionList<TYPE> list) {
		
		this.list = list; //Recebe a referencia de uma LISTA DE NODOS.
		cursor = (list.isEmpty()) ? null : list.first(); //Se n�o estiver vazia, recebe a primeira posi��o da LISTA DE NODOS.
	}
	
	//Retorna o status do objeto da proxima posi��o => True=Existe, False=N�o Existe. 
	public boolean hasNext() {return (cursor != null);}
	
	//Retorna o proximo nodo apartir de um dado nodo.
	public TYPE next() throws NoSuchElementException {
		
		//Verifica se a posi��o � valida.
		if(cursor == null) throw new NoSuchElementException("N�o existe um proximo elemento!");
		
		TYPE node = cursor.element(); //Armazena o node da LISTA DE NODOS na variavel.
		cursor = (cursor == list.last()) ? null : list.next(cursor); //Caso n�o seja o ultimo nodo, avan�a o cursor.
		return node; //Retorna o nodo da LISTA DE NODOS.
	}
	
	//Lan�a uma exce��o ao tentar realizar a opera��o de remo��o, pois n�o � suportada.
	public void remove() throws UnsupportedOperationException {throw new UnsupportedOperationException("Remove");}
	
}