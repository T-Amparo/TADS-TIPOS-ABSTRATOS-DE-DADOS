package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção chave informada invalida.
@SuppressWarnings("serial")
public class InvalidKeyException extends RuntimeException {
	
	public InvalidKeyException(String error) {super(error);}
	
}