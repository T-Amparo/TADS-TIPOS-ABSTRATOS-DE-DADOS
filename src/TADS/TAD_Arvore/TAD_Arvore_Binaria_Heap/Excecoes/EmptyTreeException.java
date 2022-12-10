package TADS.TAD_Arvore.TAD_Arvore_Binaria_Heap.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção árvore está vazia.
@SuppressWarnings("serial")
public class EmptyTreeException extends RuntimeException {
	
	public EmptyTreeException(String error) {super(error);}

}