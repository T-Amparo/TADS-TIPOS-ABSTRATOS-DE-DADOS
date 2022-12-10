package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes;

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