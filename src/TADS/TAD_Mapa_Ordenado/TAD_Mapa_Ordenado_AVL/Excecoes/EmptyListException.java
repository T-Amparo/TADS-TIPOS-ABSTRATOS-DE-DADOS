package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção LISTA DE NODOS vazia.
@SuppressWarnings("serial")
public class EmptyListException extends RuntimeException {
	
	public EmptyListException(String error) {super(error);}

}