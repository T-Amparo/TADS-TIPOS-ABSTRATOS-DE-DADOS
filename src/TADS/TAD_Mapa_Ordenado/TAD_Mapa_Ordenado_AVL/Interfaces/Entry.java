package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para a entrada do TAD MAPA.
public interface Entry<KEY, VALUE> {
	
	//Retorna a chave pertecente uma dada entrada.
	public KEY getKey();
	
	//Retorna o valor pertecente uma dada entrada.
	public VALUE getValue();
	
}