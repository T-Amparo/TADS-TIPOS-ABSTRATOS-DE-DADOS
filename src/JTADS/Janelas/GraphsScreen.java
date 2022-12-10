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
		
		//Configura��es da janela.
		
		super("Grafos");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Cria��o do texto de apresenta��o do TAD.
		
		String textStack = "\n=================================Introdu��o====================================="
				
				+ "\n\nGrafos s�o uma forma de organizar e representar dados, mais especificamente os relacionamentos "
				+ "\nou conex�es existentes entre diversos pares de v�rtices em um conjunto de arestas e v�rtices."
				
				+ "\n\nUm grafo pode ser considerado dirigido quando todas as suas arestas s�o dirigidas, isto �, "
				+ "\napresentam uma �nica dire��o, ou seja, o primeiro ponto final da aresta � sua origem e o "
				+ "\nseguinte o seu destino."
				
				+ "\n\nNaturalmente um grafo ser� considerado n�o dirigido se todas as suas arestas forem n�o "
				+ "\ndirigidas. Ou caso um grafo tenha arestas dirigidas e n�o dirigidas ele ser� considerado um grafo "
				+ "\nmisto."
				
				+ "\n\n=================================Defini��es====================================="
				
				+ "\n\nAlgumas defini��es:"
				
				+ "\n\nV�rtice: Um objeto que pode assumir diversos papeis, como por exemplo: Estados de um pa�s, "
				+ "\nRestaurantes em um mapa ou qualquer outro tipo de papel relevante."
				
				+ "\n\nAresta: A conex�o entre dois v�rtices diferentes, como por exemplo: O caminho da escola para casa, "
				+ "\nA fronteira entre dois estados, O caminho de uma casa at� um restaurante e assim por diante."
				
				+ "\n\nGrafos: Um conjunto de v�rtices e arestas, que podem ser formados por diversas conex�es."
				
				+ "\n\n=================================Aplica��es====================================="
				
				+ "\n\nAlgumas aplica��es:"
				
				+ "\n\n-Linhas de Metr�;"
				+ "\n-Dist�ncia entre pontos de um mapa;"
				+ "\n-Dist�ncia entre cidades;"
				+ "\n-Conex�es de redes de computadores;"
				+ "\n-Rota de avi�es.";		
							
		//Inser��o do texto em uma caixa de texto para exibi��o.
		
		JTextArea boxTextStack = new JTextArea(textStack);
		
		//Configura��o da caixa de texto.
		
		boxTextStack.setEditable(false);
		boxTextStack.setPreferredSize(new Dimension(550, 600));
		
		//Inserc�o da caixa de texto na janela.
		
		add(boxTextStack);
	}
	
	public static void main(String[] args) {new MainScreen();}

}