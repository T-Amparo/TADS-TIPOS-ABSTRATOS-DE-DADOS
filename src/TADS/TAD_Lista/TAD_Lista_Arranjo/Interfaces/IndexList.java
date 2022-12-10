package TADS.TAD_Lista.TAD_Lista_Arranjo.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para o TAD LISTA ARRANJO.
public interface IndexList<TYPE> {
	
	//Retorna um inteiro com a quantidade de elementos contidos na LISTA ARRANJO.
	public int size();
	
	//Retorna um valor booleano representando o status da LISTA ARRANJO => True=Vazia, False=Não Vazia.
	public boolean isEmpty();
	
	//Adiciona um 'element = elemento' no 'index = indice' da LISTA ARRANJO, deslocando todos elementos para trás do novo elemento.
	public void add(int index, TYPE element) throws IndexOutOfBoundsException;
	
	//Remove e retorna o elemento no 'index = indice' da LISTA ARRANJO, deslocando os elementos posteriores ao elemento removido para frente.
	public TYPE remove(int index) throws IndexOutOfBoundsException;
	
	//Retorna o elemento contido no 'index = indice' da LISTA ARRANJO, sem removê-lo.
	public TYPE get(int index) throws IndexOutOfBoundsException;
	
	//Troca o elemento contido no 'index = indice' por 'element = elemento' e retorna o elemento que estava em 'index'.
	public TYPE set(int index, TYPE element) throws IndexOutOfBoundsException;

}