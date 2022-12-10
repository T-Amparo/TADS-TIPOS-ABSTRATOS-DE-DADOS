package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Testes;

import java.util.Comparator;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Comparador externo para pessoa.
public class ComparatorPeople implements Comparator<People> {
	
	public int compare(People peopleOne, People peopleTwo) {return peopleOne.getNome().compareTo(peopleTwo.getNome());}

}