package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Fontes;

import java.util.Comparator;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

public class DefaultComparator<TYPE> implements Comparator<TYPE> {
	
	@SuppressWarnings("unchecked")
	public int compare(TYPE keyOne, TYPE keyTwo) throws ClassCastException {		
		return ((Comparable<TYPE>) keyOne).compareTo(keyTwo);
	}
	
}