package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exceção Posição Fora do Intervalo da LISTA DE NODOS.
@SuppressWarnings("serial")
public class BoundaryViolationException extends RuntimeException {
	
	public BoundaryViolationException(String error) {super(error);}

}