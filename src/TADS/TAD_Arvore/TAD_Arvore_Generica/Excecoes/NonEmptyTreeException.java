package TADS.TAD_Arvore.TAD_Arvore_Generica.Excecoes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Exce��o �rvore n�o est� vazia, n�o � possivel criar uma raiz.
@SuppressWarnings("serial")
public class NonEmptyTreeException extends RuntimeException {
	
	public NonEmptyTreeException(String error) {super(error);}

}