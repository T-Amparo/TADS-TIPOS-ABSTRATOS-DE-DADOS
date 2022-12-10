package TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Testes;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

//Classe para testa o TAD FILA DE PRIORIDADE.
public class People implements Comparable<People> {
	
	//Declaração das Variaveis.
	private String name; //Armazena um nome.
	private int age; //Armazena uma idade.
	
	//Método Construtor
	public People(String name, int age) {

		this.name = name; //Inicializa o nome.
		this.age = age; //Inicializa a idade.
	}
	
	//Retorna o nome da pessoa.
	public String getNome() {return name;}

	//Retorna a idade da pessoa.
	public int getIdade() {return age;}
	
	//Retorna uma string com nome e idade da pessoa concatenada.
	public String toString() {return "Pessoa {nome=" + name + ", idade=" + age + "}";}
	
	//Comparador padrão para idade.
	public int compareTo(People people) {

		if (this.age < people.age) {return -1;} //Caso a idade seja menor retorna -1.
		if (this.age > people.age) {return 1;} //Caso a idade seja maior retorna 1.

		return 0; //Caso não entre em nenhuma condição retorna 0.
	}

}