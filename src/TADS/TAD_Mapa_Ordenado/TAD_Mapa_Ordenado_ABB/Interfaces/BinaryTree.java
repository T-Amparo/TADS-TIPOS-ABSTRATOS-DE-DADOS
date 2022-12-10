package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces;

import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Excecoes.BoundaryViolationException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Excecoes.InvalidPositionException;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para à ÁRVORE BINÁRIA.
public interface BinaryTree<TYPE> extends Tree<TYPE> {
	
	//Retorna o filho esquerdo do nodo.
	public Position<TYPE> left(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException;
	
	//Retorna o filho direito do nodo.
	public Position<TYPE> right(Position<TYPE> position) throws InvalidPositionException, BoundaryViolationException;
	
	//Retorna o valor booleano representando se o nodo tem filho a esquerda.
	public boolean hasLeft(Position<TYPE> position) throws InvalidPositionException;
	
	//Retorna o valor booleano representando se o nodo tem filho a direita.
	public boolean hasRight(Position<TYPE> position) throws InvalidPositionException;
	
}