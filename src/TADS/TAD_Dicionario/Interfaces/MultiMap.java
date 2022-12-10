package TADS.TAD_Dicionario.Interfaces;

import java.util.Map;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para o TAD DICIONÁRIO.
public interface MultiMap<KEY, VALUE> {	
	
	//Retorna um inteiro com a quantidade de elementos contidos no DICIONÁRIO.
	public int size();
	
	//Retorna um valor booleano representando o status do DICIONÁRIO => True=Vazio, False=Não Vazio.
	public boolean isEmpty();
	
	//Retorna o elemento com a 'key=chave' informada, se não houver retorna null.
	public Map.Entry<KEY,VALUE> get(KEY key) throws IllegalArgumentException;
	
	//Adiciona um elemento com 'key=chave' e 'value=valor' no dicionário.
	public Map.Entry<KEY,VALUE> put(KEY key, VALUE value) throws IllegalArgumentException;
	
	//Remove e retorna um elemento do DICIONÁRIO, se não houver retorna null.
	public Map.Entry<KEY,VALUE> remove(Map.Entry<KEY,VALUE> element) throws IllegalArgumentException;
	
	//Retorna uma coleção iterável contendo todos os elementos do DICIONÁRIO com a 'chave=key' informada.
	public Iterable<Map.Entry<KEY,VALUE>> getAll(KEY key) throws IllegalArgumentException;
	
	//Retorna uma coleção iterável de todos os elementos do DICIONÁRIO.
	public Iterable<Map.Entry<KEY,VALUE>> entrySet();

}