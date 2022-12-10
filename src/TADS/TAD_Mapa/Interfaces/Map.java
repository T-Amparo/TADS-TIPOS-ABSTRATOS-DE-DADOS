package TADS.TAD_Mapa.Interfaces;

import TADS.TAD_Mapa.Excecoes.InvalidKeyException;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Interface para o TAD MAPA.
public interface Map<KEY, VALUE> {
	
	//Retorna um inteiro com a quantidade de elementos contidos no MAPA.
	public int size();

	//Retorna um valor booleano representando o status do MAPA => True=Vazia, False=Não Vazia.
	public boolean isEmpty();

	//Adiciona uma entrada(chave, valor) ao MAPA, substituindo o atual caso exista e retornando a entrada substituida. 
	public VALUE put(KEY key, VALUE value) throws InvalidKeyException;

	//Retorna o valor pertecente a chave informada.
	public VALUE get(KEY key) throws InvalidKeyException;

	//Remove uma entrada(chave, valor) de uma dada chave.
	public VALUE remove(KEY key) throws InvalidKeyException;

	//Retorna uma coleção iteravel de todas as chaves do mapa.
	public Iterable<KEY> keySet();

	//Retorna uma coleção iteravel de todos os valores do mapa.
	public Iterable<VALUE> values();

	//Retorna uma coleção iteravel de todas as entradas do mapa.
	public Iterable<Entry<KEY,VALUE>> entrySet();
	
}