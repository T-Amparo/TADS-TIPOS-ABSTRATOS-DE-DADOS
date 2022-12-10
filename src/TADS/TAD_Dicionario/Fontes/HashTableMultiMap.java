package TADS.TAD_Dicionario.Fontes;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import TADS.TAD_Dicionario.Interfaces.MultiMap;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementação do TAD DICIONÁRIO.
public class HashTableMultiMap<KEY, VALUE> implements MultiMap<KEY, VALUE> {
	
	//Declaração das Variaveis.
	private Map<KEY, LinkedList<Map.Entry<KEY, VALUE>>> map; //Mapa das chaves para entradas.
	private int size; //Número total de elementos contidos no dicionário.
	
	//Método Construtor.
	public HashTableMultiMap() {

		map = new HashMap<KEY, LinkedList<Map.Entry<KEY, VALUE>>>(); //Inicializa o map com um mapa vazio.
		size = 0; //Inicializa o size com 0.
	}	
	
	//Retorna o número de elementos contidos no dicionário.
	public int size() {return size;}
	
	//Retorna o status do dicionário => True=Vazio, False=Não Vazio.
	public boolean isEmpty() {return size == 0;}	
	
	//Retorna a entrada da chave informada, se não houver retorna null.
	public Map.Entry<KEY, VALUE> get(KEY key) throws IllegalArgumentException {
	
		LinkedList<Map.Entry<KEY, VALUE>> list; //Instancia uma lista.
	
		if (key == null) throw new IllegalArgumentException(); //Verifica se a chave informada é null.	
		if ((list = map.get(key)) == null) return null; //Verifica se existe uma mesma chave no dicionário, caso não exista retorna null.
	
		return list.peekFirst(); //Retorna a primeira entrada encontrada com a chave informada.
	}
	
	//Adiciona um item no dicionário e retorna a entrada recentemente criada.
	public Map.Entry<KEY, VALUE> put(KEY key, VALUE value) throws IllegalArgumentException {
	
		LinkedList<Map.Entry<KEY, VALUE>> list; //Instancia uma lista.
	
		if (key == null) throw new IllegalArgumentException(); //Verifica se a chave informada é null.
		
		if ((list = map.get(key)) == null) { //Verifica se existe uma mesma chave no dicionário.
		
			list = new LinkedList<Map.Entry<KEY, VALUE>>(); //Cria um nova lista para a chave informada.	
			map.put(key, list);	//Adiciona a lista da chave criada no mapa de chvaes.
		}
	
		Map.Entry<KEY, VALUE> entry = new AbstractMap.SimpleEntry<KEY, VALUE>(key, value); //Cria uma entrada com os valores informados.	
		list.add(entry); //Adiciona a nova entrada a lista da chave informada.
	
		size++; //Acrescenta-se +1 ao número total de elementos contidos no dicionário.
	
		return entry;
	}

	//Remove e retorna a entrada informada do dicionário.
	public Map.Entry<KEY, VALUE> remove(Map.Entry<KEY, VALUE> entry) throws IllegalArgumentException {
	
		LinkedList<Map.Entry<KEY, VALUE>> list; //Instancia uma lista.
	
		if (entry == null) throw new IllegalArgumentException(); //Verifica se a entrada informada é null.
	
		KEY key = entry.getKey(); //Armazena a chave da entrada.	
		list = map.get(key); //Armazena a lista de chaves de acordo com a chave informada. 
	
		if (list == null) throw new IllegalArgumentException(); //Verifica se a chave está em map, caso não esteja lança uma exceção.
	
		if (list.remove(entry)) { //Verifica se a entrada foi removida.
		
			size--; //Diminui-se 1 do número total de elementos contidos no dicionário.
		
			if (list.isEmpty()) map.remove(key); //Remove a lista da chave.		
			return entry; //Retorna a entrada removida
	
		} else throw new IllegalArgumentException(); //Caso a entrada não esteja na lista lança uma exceção.
	}
	
	// Retorna um iterador contendo todas as entradas contidas em uma chave do dicionário.
	public Iterable<Map.Entry<KEY, VALUE>> getAll(KEY key) throws IllegalArgumentException {
	
		LinkedList<Map.Entry<KEY, VALUE>> list; //Instancia uma lista.
	
		if (key == null) throw new IllegalArgumentException(); //Verifica se a chave informada é null.	
		if ((list = map.get(key)) == null) return null; //Verifica se existe uma mesma chave no dicionário, caso não exista lança uma exceção.
	
		return list; //Retorna a coleção de entradas da chave informada.
	}
	
	//Retorna uma coleção iterável de todos os elementos do dicionário.
	public Iterable<Map.Entry<KEY, VALUE>> entrySet() {
	
		LinkedList<Map.Entry<KEY, VALUE>> list = new LinkedList<Map.Entry<KEY, VALUE>>(); //Instancia e cria uma lista vazia.
	
		for (LinkedList<Map.Entry<KEY, VALUE>> entry : map.values()) list.addAll(entry); //Percorre a lista de chaves e para cada chave adiciona na lista vazia criada.
	
		return list; //Retorna a coleção de todas as entradas do dicionário.
	}	

}