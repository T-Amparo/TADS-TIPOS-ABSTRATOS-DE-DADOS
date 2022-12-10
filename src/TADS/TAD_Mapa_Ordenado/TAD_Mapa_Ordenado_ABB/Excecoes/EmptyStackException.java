package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção de Pilha vazia.
@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException {
	
	public EmptyStackException(String error) {super(error);}

}