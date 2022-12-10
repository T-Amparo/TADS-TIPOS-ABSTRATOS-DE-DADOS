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

//Implementa��o do TAD DICION�RIO.
public class HashTableMultiMap<KEY, VALUE> implements MultiMap<KEY, VALUE> {
	
	//Declara��o das Variaveis.
	private Map<KEY, LinkedList<Map.Entry<KEY, VALUE>>> map; //Mapa das chaves para entradas.
	private int size; //N�mero total de elementos contidos no dicion�rio.
	
	//M�todo Construtor.
	public HashTableMultiMap() {

		map = new HashMap<KEY, LinkedList<Map.Entry<KEY, VALUE>>>(); //Inicializa o map com um mapa vazio.
		size = 0; //Inicializa o size com 0.
	}	
	
	//Retorna o n�mero de elementos contidos no dicion�rio.
	public int size() {return size;}
	
	//Retorna o status do dicion�rio => True=Vazio, False=N�o Vazio.
	public boolean isEmpty() {return size == 0;}	
	
	//Retorna a entrada da chave informada, se n�o houver retorna null.
	public Map.Entry<KEY, VALUE> get(KEY key) throws IllegalArgumentException {
	
		LinkedList<Map.Entry<KEY, VALUE>> list; //Instancia uma lista.
	
		if (key == null) throw new IllegalArgumentException(); //Verifica se a chave informada � null.	
		if ((list = map.get(key)) == null) return null; //Verifica se existe uma mesma chave no dicion�rio, caso n�o exista retorna null.
	
		return list.peekFirst(); //Retorna a primeira entrada encontrada com a chave informada.
	}
	
	//Adiciona um item no dicion�rio e retorna a entrada recentemente criada.
	public Map.Entry<KEY, VALUE> put(KEY key, VALUE value) throws IllegalArgumentException {
	
		LinkedList<Map.Entry<KEY, VALUE>> list; //Instancia uma lista.
	
		if (key == null) throw new IllegalArgumentException(); //Verifica se a chave informada � null.
		
		if ((list = map.get(key)) == null) { //Verifica se existe uma mesma chave no dicion�rio.
		
			list = new LinkedList<Map.Entry<KEY, VALUE>>(); //Cria um nova lista para a chave informada.	
			map.put(key, list);	//Adiciona a lista da chave criada no mapa de chvaes.
		}
	
		Map.Entry<KEY, VALUE> entry = new AbstractMap.SimpleEntry<KEY, VALUE>(key, value); //Cria uma entrada com os valores informados.	
		list.add(entry); //Adiciona a nova entrada a lista da chave informada.
	
		size++; //Acrescenta-se +1 ao n�mero total de elementos contidos no dicion�rio.
	
		return entry;
	}

	//Remove e retorna a entrada informada do dicion�rio.
	public Map.Entry<KEY, VALUE> remove(Map.Entry<KEY, VALUE> entry) throws IllegalArgumentException {
	
		LinkedList<Map.Entry<KEY, VALUE>> list; //Instancia uma lista.
	
		if (entry == null) throw new IllegalArgumentException(); //Verifica se a entrada informada � null.
	
		KEY key = entry.getKey(); //Armazena a chave da entrada.	
		list = map.get(key); //Armazena a lista de chaves de acordo com a chave informada. 
	
		if (list == null) throw new IllegalArgumentException(); //Verifica se a chave est� em map, caso n�o esteja lan�a uma exce��o.
	
		if (list.remove(entry)) { //Verifica se a entrada foi removida.
		
			size--; //Diminui-se 1 do n�mero total de elementos contidos no dicion�rio.
		
			if (list.isEmpty()) map.remove(key); //Remove a lista da chave.		
			return entry; //Retorna a entrada removida
	
		} else throw new IllegalArgumentException(); //Caso a entrada n�o esteja na lista lan�a uma exce��o.
	}
	
	// Retorna um iterador contendo todas as entradas contidas em uma chave do dicion�rio.
	public Iterable<Map.Entry<KEY, VALUE>> getAll(KEY key) throws IllegalArgumentException {
	
		LinkedList<Map.Entry<KEY, VALUE>> list; //Instancia uma lista.
	
		if (key == null) throw new IllegalArgumentException(); //Verifica se a chave informada � null.	
		if ((list = map.get(key)) == null) return null; //Verifica se existe uma mesma chave no dicion�rio, caso n�o exista lan�a uma exce��o.
	
		return list; //Retorna a cole��o de entradas da chave informada.
	}
	
	//Retorna uma cole��o iter�vel de todos os elementos do dicion�rio.
	public Iterable<Map.Entry<KEY, VALUE>> entrySet() {
	
		LinkedList<Map.Entry<KEY, VALUE>> list = new LinkedList<Map.Entry<KEY, VALUE>>(); //Instancia e cria uma lista vazia.
	
		for (LinkedList<Map.Entry<KEY, VALUE>> entry : map.values()) list.addAll(entry); //Percorre a lista de chaves e para cada chave adiciona na lista vazia criada.
	
		return list; //Retorna a cole��o de todas as entradas do dicion�rio.
	}	

}