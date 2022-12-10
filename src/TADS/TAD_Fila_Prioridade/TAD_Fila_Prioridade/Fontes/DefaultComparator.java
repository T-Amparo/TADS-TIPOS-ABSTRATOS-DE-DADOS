package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Fontes;

import java.util.Comparator;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Comparador padrão para o TAD FILA DE PRIORIDADE.
public class DefaultComparator<TYPE> implements Comparator<TYPE> {
	
	@SuppressWarnings("unchecked")
	public int compare(TYPE keyOne, TYPE keyTwo) throws ClassCastException {		
		return ((Comparable<TYPE>) keyOne).compareTo(keyTwo);
	}
	
}