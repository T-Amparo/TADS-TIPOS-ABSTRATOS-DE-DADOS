package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Fontes.LinkedTree;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Fontes.NodePositionList;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Fontes.TreeNode;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.Position;
import TADS.TAD_Arvore.TAD_Arvore_Generica.Interfaces.PositionList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class LinkedTreeScreen extends JFrame {
	
	LinkedTree<JButton> tree;
	
	public LinkedTreeScreen() {
		
		//Configurações da janela.
		
		super("Árvore Genérica");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Criação do texto de apresentação do TAD.
		
		String textTree = "\n=================================Introdução==========================================="
				
				+ "\n\nUma Árvore é um dos Tipos Abstratos de Dados mais importantes da computação, "
				+ "\npermitindo a implementação de uma série de algoritmos mais rápidos do que em estruturas de "
				+ "\ndados lineares."
				
				+ "\n\nEm uma Árvore as posições se referem aos seus nodos, e o posicionamento de um nodo dentro"
				+ "\nda árvore em relação ao seu pai ou seu filho é o que define uma árvore válida."
				
				+ "\n\nA estrutura de um Árvore Genérica é totalmente hierárquica, sendo um conjunto de nodos que "
				+ "\narmazenam elementos e se relacionam com outros nodos da árvore, iniciando pela raiz cada "
				+ "\nnodo que compõe a árvore terá um pai e poderá ter diversos filhos, com exceção da raiz da árvore "
				+ "\nque não tem outro nodo acima dela, ou seja a raiz não terá um pai."
				
				+ "\n\n=================================Implementação==========================================="
				
				+ "\n\nPara está implementação de Árvore Generica somente foi implementado o método adicionar. "
				+ "\n\nAs inserções na Árvore Generica poderão ser realizadas a partir do nodo raiz.";		
			
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextTree = new JTextArea(textTree);
		
		//Configuração da caixa de texto.
		
		boxTextTree.setEditable(false);
		boxTextTree.setPreferredSize(new Dimension(550, 600));	
		
		//Criação de botoes para a janela.
		
		JButton createTree = new JButton("Criar Árvore Genérica");
		
		//Insercão dos componentes na janela.
		
		add(boxTextTree);
		add(createTree);
		
		//Configuração dos botoes da janela.
		
		createTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createTree();}});
	}
	
	//Cria uma árvore e uma janela para exibi-la.
	public void createTree() {
		
		//Cria uma janela para coletar a raiz da árvore.
		
		String rootElement = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira a Raiz da Árvore Genérica:",
				"Inserção da Raiz",
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				null
		);
		
		if(rootElement != null) { //Verifica se uma raiz foi informada.			
			
			//Cria uma janela para representar uma lista arranjo.
			
			JFrame screen = new JFrame();
			
			//Configurações da janela criada.
			
			screen.setLayout(new BorderLayout());
			screen.setTitle("Árvore Genérica");
			screen.setSize(500, 645);
			screen.setResizable(false);
			screen.setVisible(true);
			
			//Cria um painel para conter os componentes da lista arranjo.
			
			JPanel panel = new JPanel();
			
			//Cria um painel com scroll.
			
			JScrollPane scroll = new JScrollPane(panel);
			
			//Configuração do scroll da janela.
			
			scroll.setPreferredSize(new Dimension(500, 145));			
			
			//Criação do menu para a janela.
			
			JMenuBar menu = new JMenuBar();
			
			//Criação dos botoes do menu.
			
			JMenuItem createNewTree = new JMenuItem("Criar Nova Árvore", KeyEvent.VK_M);
			
			//Configuração dos botoes do menu.
			
			createNewTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createNewTree(panel);}});
			
			//Insercão dos botoes no menu.
			
			menu.add(createNewTree);			
			
			//Insercão do menu na janela.
			
			screen.setJMenuBar(menu);
			
			//Inserção do scroll na janela.
			
			screen.add(scroll);
			
			//Cria uma árvore genérica e suas configurações.
			
			tree = new LinkedTree<JButton>(); //Cria uma árvore genérica.
			
			//Cria o componente que será inserido na raiz.
			
			JButton element = new JButton(rootElement);
			
			//Insere o componente criado na raiz da árvore.
			
			tree.addRoot(element);			
	
			TreeNode<JButton> root = (TreeNode<JButton>) tree.root(); //Armazena a raiz da árvore.
			root.setChildren(new NodePositionList<Position<JButton>>()); //Cria uma lista para os filhos da raiz da árvore.
			
			//Configura o componente criado.
			
			element.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, root, panel);}});
			
			refreshScreen(panel);
		}		
	}	
	
	//O Método irá criar os nodos filhos de um dado nodo.
	private TreeNode<JButton> createNodeChildren(TreeNode<JButton> node, JButton element) {

		PositionList<Position<JButton>> children; //Instancia uma lista de posições.
		TreeNode<JButton> newNode; //Novo nodo que será criado.

		children = node.getChildren(); //Armazena os filhos da raiz.

		newNode = new TreeNode<JButton>(); //Cria um nodo vazio.

		newNode.setElement(element); //Define o elemento do nodo.
		newNode.setParent(node); //Define o pai do nodo.
		newNode.setChildren(new NodePositionList<Position<JButton>>()); //Cria uma lista de posições para os filhos do nodo.
		children.addLast(newNode); //Adiciona o nodo criado a lista de filhos da raiz.

		return newNode; //Retorna o nodo criado.
	}
	
	public void buttonManagerScreen(LinkedTree<JButton> tree, TreeNode<JButton> node, JPanel panel) {		
		
		//Cria uma janela de opções.
		
		Object[] options = {"Adicionar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Opção: ", 
				"Métodos da Árvore Genérica", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		if(option == 0) {			
			
			//Cria uma janela para coletar o elemento do nodo.
			
			String element = (String) JOptionPane.showInputDialog(
					
					this.getParent(),
					"Insira o Valor do Elemento:",
					"Inserção de Nodo",
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					null
			);
			
			if(element != null) {				
				
				//Cria o componente que será inserido no nodo.
				
				JButton elementNode = new JButton(element);				
				
				//Configura o componente criado.
				
				TreeNode<JButton> newNode = createNodeChildren(node, elementNode);
				
				elementNode.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, newNode, panel);}});
				
				refreshScreen(panel);
			}			
		}		
	}
	
	//Cria uma árvore nova.
	public void createNewTree(JPanel panel) {		
		
		//Cria uma janela de confirmação.
		
		Object[] options = {"Continuar", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Deseja Criar Uma Nova Árvore?", 
				"Criar Nova Árvore Generica", 
				JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);
		
		if(option == 0) {			
			
			//Cria uma janela para coletar a raiz da árvore.
			
			String rootElement = (String) JOptionPane.showInputDialog(
					
					this.getParent(),
					"Insira a Raiz da Árvore Genérica:",
					"Inserção da Raiz",
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					null
			);
			
			if(rootElement != null) {				
				
				//Cria uma árvore genérica e suas configurações.
				
				tree = new LinkedTree<JButton>(); //Cria uma árvore genérica.
				
				//Cria o componente que será inserido na raiz.
				
				JButton element = new JButton(rootElement);
				
				//Insere o componente criado na raiz da árvore.
				
				tree.addRoot(element);			
		
				TreeNode<JButton> root = (TreeNode<JButton>) tree.root(); //Armazena a raiz da árvore.
				root.setChildren(new NodePositionList<Position<JButton>>()); //Cria uma lista para os filhos da raiz da árvore.				
				
				//Configura o componente criado.
				
				element.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, root, panel);}});
				
				refreshScreen(panel);
			}
		}
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel panel) {
		
		panel.removeAll();
		
		//Instancia uma lista de nodos, para armazenar os nodos da árvore.
		
		PositionList<Position<JButton>> positions = new NodePositionList<Position<JButton>>();
		
		//Percorre à árvore e preenche a lista com os nodos da mesma.
		
		for(Position<JButton> position : tree.positions()) {positions.addLast(position);}
		
		//Cria um painel para conter a tabela da árvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da árvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();

		//Percorre a árvore e preenche a tabela.
		
		int visits = 0;
		for(Position<JButton> position : positions) {

			gridTree.fill = GridBagConstraints.HORIZONTAL;
			gridTree.gridx = tree.depth(tree, position);
			gridTree.gridy = visits;
			
			panelTree.add(position.element(), gridTree);
			visits++;
		}
		
		//Adiciona a janela com a tabela da árvore na janela de representação.
		
		panel.add(panelTree);		
		
		//Métodos da janela para recarrega-lá.
		
		panel.revalidate();
		panel.repaint();
	}
	
	public static void main(String[] args) {new MainScreen();}

}