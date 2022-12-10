package TADS.TAD_Mapa.Interfaces;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para o posicionamento de objetos dentro de uma LISTA DE NODOS.
public interface Position<TYPE> {
	
	//Retorna o elemento armazenado na posição.
	TYPE element();
	
}