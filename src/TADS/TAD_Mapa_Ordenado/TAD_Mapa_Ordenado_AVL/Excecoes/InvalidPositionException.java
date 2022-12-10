package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção posição invalida.
@SuppressWarnings("serial")
public class InvalidPositionException extends RuntimeException {
	
	public InvalidPositionException(String error) {super(error);}

}