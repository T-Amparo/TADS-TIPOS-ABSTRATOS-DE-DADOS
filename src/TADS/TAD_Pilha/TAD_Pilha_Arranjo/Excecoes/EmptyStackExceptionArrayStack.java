package TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção de Pilha vazia.
@SuppressWarnings("serial")
public class EmptyStackExceptionArrayStack extends RuntimeException {
	
	public EmptyStackExceptionArrayStack(String error) {super(error);}

}