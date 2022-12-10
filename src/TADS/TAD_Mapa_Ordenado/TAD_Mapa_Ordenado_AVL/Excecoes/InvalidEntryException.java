package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção entrada informada invalida.
@SuppressWarnings("serial")
public class InvalidEntryException extends RuntimeException {
	
	public InvalidEntryException(String error) {super(error);}

}