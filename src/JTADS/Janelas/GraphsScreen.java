package JTADS.Janelas;

import java.awt.*;

import javax.swing.*;

import JTADS.Menu.MainScreen;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class GraphsScreen extends JFrame {	
	
	public GraphsScreen() {
		
		//Configurações da janela.
		
		super("Grafos");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Criação do texto de apresentação do TAD.
		
		String textStack = "\n=================================Introdução====================================="
				
				+ "\n\nGrafos são uma forma de organizar e representar dados, mais especificamente os relacionamentos "
				+ "\nou conexões existentes entre diversos pares de vértices em um conjunto de arestas e vértices."
				
				+ "\n\nUm grafo pode ser considerado dirigido quando todas as suas arestas são dirigidas, isto é, "
				+ "\napresentam uma única direção, ou seja, o primeiro ponto final da aresta é sua origem e o "
				+ "\nseguinte o seu destino."
				
				+ "\n\nNaturalmente um grafo será considerado não dirigido se todas as suas arestas forem não "
				+ "\ndirigidas. Ou caso um grafo tenha arestas dirigidas e não dirigidas ele será considerado um grafo "
				+ "\nmisto."
				
				+ "\n\n=================================Definições====================================="
				
				+ "\n\nAlgumas definições:"
				
				+ "\n\nVértice: Um objeto que pode assumir diversos papeis, como por exemplo: Estados de um país, "
				+ "\nRestaurantes em um mapa ou qualquer outro tipo de papel relevante."
				
				+ "\n\nAresta: A conexão entre dois vértices diferentes, como por exemplo: O caminho da escola para casa, "
				+ "\nA fronteira entre dois estados, O caminho de uma casa até um restaurante e assim por diante."
				
				+ "\n\nGrafos: Um conjunto de vértices e arestas, que podem ser formados por diversas conexões."
				
				+ "\n\n=================================Aplicações====================================="
				
				+ "\n\nAlgumas aplicações:"
				
				+ "\n\n-Linhas de Metrô;"
				+ "\n-Distância entre pontos de um mapa;"
				+ "\n-Distância entre cidades;"
				+ "\n-Conexões de redes de computadores;"
				+ "\n-Rota de aviões.";		
							
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextStack = new JTextArea(textStack);
		
		//Configuração da caixa de texto.
		
		boxTextStack.setEditable(false);
		boxTextStack.setPreferredSize(new Dimension(550, 600));
		
		//Insercão da caixa de texto na janela.
		
		add(boxTextStack);
	}
	
	public static void main(String[] args) {new MainScreen();}

}