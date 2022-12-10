package TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção de Pilha cheia.
@SuppressWarnings("serial")
public class FullStackException extends RuntimeException {
	
	public FullStackException(String error) {super(error);}

}