package TADS.TAD_Pilha.TAD_Pilha_LSE.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção de Pilha vazia.
@SuppressWarnings("serial")
public class EmptyStackExceptionNodeStack extends RuntimeException {
	
	public EmptyStackExceptionNodeStack(String error) {super(error);}

}