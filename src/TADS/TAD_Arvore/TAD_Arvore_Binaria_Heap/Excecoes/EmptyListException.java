package TADS.TAD_Arvore.TAD_Arvore_Binaria_Heap.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exce��o LISTA DE NODOS vazia.
@SuppressWarnings("serial")
public class EmptyListException extends RuntimeException {
	
	public EmptyListException(String error) {super(error);}

}