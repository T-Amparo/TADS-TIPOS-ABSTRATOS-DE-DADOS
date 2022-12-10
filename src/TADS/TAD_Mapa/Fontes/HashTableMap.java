package TADS.TAD_Mapa.Fontes;

import TADS.TAD_Mapa.Excecoes.InvalidKeyException;
import TADS.TAD_Mapa.Interfaces.Entry;
import TADS.TAD_Mapa.Interfaces.Map;
import TADS.TAD_Mapa.Interfaces.PositionList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Implementa��o do TAD MAPA.
public class HashTableMap<KEY, VALUE> implements Map<KEY, VALUE> {	
	
	//Declara��o das Variaveis.	
	protected int size = 0; //N�mero total de entradas contidas na mapa.
	protected int prime, capacity; //Capacidade e fator primo do arranjo de buckets.
	protected Entry<KEY, VALUE>[] bucket; //Arranjo de buckets.
	protected long scale, shift; //Fator de escala e de mudan�a.
	
	protected Entry<KEY, VALUE> AVAILABLE = new HashEntry<KEY, VALUE>(null, null);
	
	//Subclasse.
	public static class HashEntry<KEY, VALUE> implements Entry<KEY, VALUE> {
		
		//Declara��o das Variaveis da Subclasse.
		protected KEY key; //Armazena uma chave.
		protected VALUE value; //Armazena um valor.

		//M�todo Construtor da Subclasse.
		public HashEntry(KEY key, VALUE value) {
			
			this.key = key; //Inicializa a chave com o valor informado.
			this.value = value; //Inicializa o valor com o valor informado.
		}
		
		//Retorna o valor pertecente a entrada.
		public VALUE getValue() {return value;}

		//Retorna a chave pertecente a entrada.
		public KEY getKey() {return key;}
		
		//Altera o valor da entrada pelo novo valor informado.
		public VALUE setValue(VALUE value) {

			VALUE exchanged = this.value; //Armazena o valor atual da entrada.
			this.value = value; //Altera o valor da entrada para o novo valor.
			
			return exchanged; //Retorna o valor removido.
		}
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object object) { //Verifica a igualdade entre a entrada atual e uma entrada informada.

			HashEntry<KEY, VALUE> entry; //Espa�o para armazenar o objeto convertido para HashEntry.

			//Verifica e trata uma possivel exce��o.
			try {entry = (HashEntry<KEY, VALUE>) object;} //Converte o objeto para HashEntry.
			catch (ClassCastException error) {return false;} //Caso uma exce��o seja liberada, retorna false.

			//Compara a chave e o valor da entrada atual com a chave e valor do objeto informado, e retorna o resultado.
			return (entry.getKey() == key) && (entry.getValue() == value);
		}
		
		//Retorna uma string (chave, valor).
		public String toString() {return "(" + key + "," + value + ")";}		
	}
	
	//M�todo Construtor.
	public HashTableMap() {this(109345121, 1000);} //Inicia uma tabela Hash com um n�mero para o fator primo e a capacidade.
	
	//M�todo Construtor.
	public HashTableMap(int capacity) {this(109345121, capacity);}  //Inicia uma tabela Hash com um n�mero para o fator primo e com a capacidade informada.
	
	//M�todo Construtor.
	@SuppressWarnings("unchecked")
	public HashTableMap(int prime, int capacity) { //Inicia uma tabela Hash com o n�mero do fator primo e a capacidade informados.

		this.prime = prime;	//Defini o valor primo de acordo com o valor informado.
		this.capacity = capacity; //Defini a capacidade de acordo com o valor informado.
		this.bucket = (Entry<KEY, VALUE>[]) new Entry[capacity]; //Converte um arranjo de entradas.
	
		java.util.Random random = new java.util.Random(); //Instancia a biblioteca random.
	
		this.scale = random.nextInt(prime - 1) + 1; //Gera um n�mero aleatorio para a escala a partir do valor primo.
		this.shift = random.nextInt(prime); //Gera um n�mero aleatorio para o fator de mudan�a a partir do valor primo.
	}
	
	//Verifica se a chave informada � valida.
	protected void validateKey(KEY key) {if (key == null) throw new InvalidKeyException("Chave inv�lida!");}
	
	//O m�todo aplica o m�todo MAD para o c�digo hash padr�o.
	public int hashValue(KEY key) {return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);}
	
	//Retorna o n�mero de elementos contidos no mapa.
	public int size() {return size;}
	
	//Retorna o status do mapa => True=Vazio, False=N�o Vazio.
	public boolean isEmpty() {return (size == 0);}
	
	//Retorna uma cole��o iteravel contendo todas �s chaves do mapa.
	public Iterable<KEY> keySet() {

		PositionList<KEY> keys = new NodePositionList<KEY>(); //Cria uma lista vazia para �s chaves.

		//Percorre os buckets e adiciona �s chaves na lista criada.
		for (int i = 0; i < capacity; i++)if ((bucket[i] != null) && (bucket[i] != AVAILABLE)) keys.addLast(bucket[i].getKey());

		return keys; //Retorna a lista de chaves criadas.
	}
	
	//Busca e retorna o indice de uma chave.
	protected int findEntry(KEY key) throws InvalidKeyException{
		
		validateKey(key); //Verifica se a chave informada � valida.
		
		int avaliabre = -1;
		
		int hash = hashValue(key); //Armazena o c�digo hash da chave.
		int init = hash; //Armazena o momento inicial do c�digo hash.
		
		do {

			Entry<KEY, VALUE> entrys = bucket[hash]; //Armazena o bucket da chave.

			if (entrys == null) {if (avaliabre < 0) avaliabre = hash; break;} //Verifica se a chave est� no bucket.
			if (key.equals(entrys.getKey())) return hash; //Caso a chave seja encontrada retorna o indice.
			if (entrys == AVAILABLE) {if (avaliabre < 0) avaliabre = hash;} //Verifica se o slot est� vazio.

			hash = (hash + 1) % capacity; //Indice dentro do arranjo de buckets.
			
		} while (hash != init); //Condi��o do la�o.
		
		return -(avaliabre + 1); //Retorna o indice encontrado.
	}
	
	//Retorna o valor da chave informada.
	public VALUE get(KEY key) throws InvalidKeyException {

		int index = findEntry(key); //Armazena o indice da chave.
		if (index < 0) return null; //Verifica se o indice � valido, caso n�o seja retorna null.

		return bucket[index].getValue(); //Retorna o valor da chave encontrado.
	}
	
	//Adiciona uma nova entrada no mapa, caso a entrada j� exista substitui.
	public VALUE put(KEY key, VALUE value) throws InvalidKeyException {

		int index = findEntry(key); //Armazena o indice da chave.

		if (index >= 0) return ((HashEntry<KEY, VALUE>) bucket[index]).setValue(value); //Verifica se j� existe uma entrada, caso exista substitui pelo nova entrada.
		if (size >= capacity / 2) {rehash(); index = findEntry(key);} //Mantem o fator de carga menor que 0.5 e encontra o local para entrada informada.

		bucket[-index - 1] = new HashEntry<KEY, VALUE>(key, value); //Converte a entrada para o indice apropriado.
		size++; //Acrescenta-se +1 ao n�mero total de entradas contidas no mapa.

		return null; //Retorna null caso n�o exista entradas anteriores no mapa.
	}
	
	@SuppressWarnings("unchecked")
	protected void rehash() { //Duplica a capacidade da tabela hash.

		capacity = 2 * capacity; //Duplica a capacidade.

		Entry<KEY, VALUE>[] old = bucket; //Armazena o bucket inicial.
		bucket = (Entry<KEY, VALUE>[]) new Entry[capacity]; //Cria um bucket com a nova capacidade, duas vezes maior.

		java.util.Random random = new java.util.Random(); //Instancia a biblioteca random.

		scale = random.nextInt(prime - 1) + 1; //Define o novo fator de escala.
		shift = random.nextInt(prime); //Define o novo fator de mudan�a.

		for (int i = 0; i < old.length; i++) { //Percorre o bucket armazenado.

			Entry<KEY, VALUE> entry = old[i]; //Armazena as entradas do bucket.

			if ((entry != null) && (entry != AVAILABLE)) { //Verifica se a entrada � valida.

				int index = -1 - findEntry(entry.getKey()); //Encontra um indice valido para a entrada.
				bucket[index] = entry; //Adiciona a entrada ao bucket.
			}
		}
	}
	
	//Retorna e remove a entrada com a chave informada.
	public VALUE remove(KEY key) throws InvalidKeyException {

		int index = findEntry(key); //Armazena o indice da chave.
		if (index < 0) return null; //Verifica se o indice � valido, caso n�o seja retorna null.

		VALUE elementRemoved = bucket[index].getValue(); //Armazena o elemento removido.
		bucket[index] = AVAILABLE; //Marca o espa�o do bucket como vazio.
		size--; //Diminui-se 1 do n�mero total de entradas contidas no mapa.

		return elementRemoved; //Retorna o elemento removido.
	}
	
	//Retorna uma cole��o iteravel contendo todas as entradas do mapa.
	public Iterable<Entry<KEY, VALUE>> entrySet() {

		PositionList<Entry<KEY, VALUE>> entries = new NodePositionList<Entry<KEY, VALUE>>(); //Cria uma lista vazia.

		for (int i = 0; i < capacity; i++) //Percorre o buckets do mapa.

			if ((bucket[i] != null) && (bucket[i] != AVAILABLE)) entries.addLast(bucket[i]); //Adiciona as entradas na lista criada.

		return entries; //Retorna lista de entradas.
	}
	
	//Retorna uma cole��o iteravel contendo todos os valores do mapa.
	public Iterable<VALUE> values() {

		PositionList<VALUE> values = new NodePositionList<VALUE>(); //Cria uma lista vazia.

		for (int i = 0; i < capacity; i++) //Percorre o buckets do mapa.

			if ((bucket[i] != null) && (bucket[i] != AVAILABLE)) values.addLast(bucket[i].getValue()); //Adiciona os valores na lista criada.

		return values; //Retorna lista de valores.
	}
	
}