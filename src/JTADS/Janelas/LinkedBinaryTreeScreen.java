package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Arvore.TAD_Arvore_Binaria.Excecoes.InvalidPositionException;
import TADS.TAD_Arvore.TAD_Arvore_Binaria.Excecoes.NonEmptyTreeException;
import TADS.TAD_Arvore.TAD_Arvore_Binaria.Fontes.LinkedBinaryTree;
import TADS.TAD_Arvore.TAD_Arvore_Binaria.Interfaces.Position;
import TADS.TAD_Arvore.TAD_Arvore_Binaria_Heap.Fontes.ArrayListCompleteBinaryTree;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class LinkedBinaryTreeScreen extends JFrame {	
	
	LinkedBinaryTree<JButton> tree;
	LinkedBinaryTree<String> treeExpression;
	ArrayListCompleteBinaryTree<JButton> treeCompleteBinary;
	
	public LinkedBinaryTreeScreen() {
		
		//Configurações da janela.
		
		super("Árvore Binaria");
		
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
				
				+ "\n\nA estrutura de uma Árvore Binária segue um padrão, os nodos tem no máximo dois filhos, e são "
				+ "\nrotulados como filho da direita e filho da esquerda, o filho da esquerda sempre precederá o filho "
				+ "\nda direita, essas características são justamente o que tornam essa Árvore em Binária."
				
				+ "\n\nUtilizando uma Árvore Binária é possivel construir uma árvore de expressões, aonde o operador"
				+ "\nserá sempre pai de dois números."
				
				+ "\n\nA Árvore Binária tambem possui uma outra variante, a Árvore Binária Completa, que também dispoe"
				+ "\ndas caracteristica da Árvore Binária comun porém com caracteristicas novas, para Árvore Binária "
				+ "\nCompleta as inserções serão realizadas da esquerda para a direita, preenchendo sempre um nivel"
				+ "\npor vez."

				+ "\n\n=================================Implementação==========================================="

				+ "\n\nAs operações na Árvore Binária poderão ser realizadas a partir dos nodos da mesma.";		
			
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextTree = new JTextArea(textTree);
		
		//Configuração da caixa de texto.
		
		boxTextTree.setEditable(false);
		boxTextTree.setPreferredSize(new Dimension(550, 600));	
		
		//Criação de botoes para a janela.
		
		JButton createTree = new JButton("Criar Árvore Binaria");		
		
		//Insercão dos componentes na janela.
		
		add(boxTextTree);		
		add(createTree);
		
		//Configuração dos botoes da janela.
		
		createTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createTree();}});
	}
	
	//Cria uma árvore e uma tela de representação para a mesma.
	public void createTree() {
		
		tree = new LinkedBinaryTree<JButton>(); //Inicia a árvore.
		
		createRepresentationScreen(tree); //Cria uma janela para a árvore.
	}
	
	//Cria uma janela para representar uma árvore binaria.
	public void createRepresentationScreen(LinkedBinaryTree<JButton> tree) {		
		
		//Cria uma janela para representar uma árvore binária.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle("Árvore Binaría");
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da lista arranjo.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(600, 500));			
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem addRootTree = new JMenuItem("Inserir Raiz", KeyEvent.VK_M);
		JMenuItem treeExpression = new JMenuItem("Árvore de Expressões", KeyEvent.VK_M);
		JMenuItem completeBinary = new JMenuItem("Árvore Completa", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		ActionListener actionInsert;
		
		addRootTree.addActionListener(actionInsert = new ActionListener() {public void actionPerformed(ActionEvent event) {addRootTree(tree, panel);}});
		treeExpression.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {treeExpression(actionInsert, addRootTree, panel);}});
		completeBinary.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {treeCompleteBinary(actionInsert, addRootTree, panel);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(addRootTree);
		menu.add(treeExpression);
		menu.add(completeBinary);
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);				
	}
	
	//Adiciona a raiz inserida pelo usuário na árvore.
	public void addRootTree(LinkedBinaryTree<JButton> tree, JPanel panel) {		
		
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
			
			try { //Trata uma possivel exceção.
				
				//Cria o componente que será inserido na raiz.
				
				JButton element = new JButton(rootElement);
				
				//Insere o componente criado na raiz da árvore.
				
				tree.addRoot(element);
				
				//Configura o componente criado.
				
				element.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, tree.root(), panel);}});
				
				//Recarrega a janela.
				
				refreshScreen(panel);
			
			//Libera uma mensagem sobre o erro.	
			} catch(NonEmptyTreeException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}
	
	//Cria uma árvore de expressões e exibe na janela.
	public void treeExpression(ActionListener actionInsert, JMenuItem menuItem, JPanel panel) {		
		
		//Altera para null ás árvores existentes.
		
		treeCompleteBinary = null;
		tree = null;
		
		//Cria uma janela para coletar o elemento do nodo.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira a Expressão: \n\n -Totalmente Parentizada \n\n -Sem Espaços \n\n Exemplo: ((1+2)+3)",
				"Inserção de Expressão",
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				null
		);			
		
		if(element != null && !element.equals("")) {
			
			//Cria uma árvore de expressão.
			
			treeExpression = new LinkedBinaryTree<String>(); //Cria uma árvore vazia.
			treeExpression = treeExpression.buildExpression(element); //Cria à árvore de expressão.		
			
			//Muda às configurações do menu.
			
			menuItem.setText("Criar Árvore");
			for(int i = 0; i < menuItem.getActionListeners().length; i++) {menuItem.removeActionListener(menuItem.getActionListeners()[i]);}			
			
			menuItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
				
				//Altera para null à árvore de expressão criada.
				
				treeExpression = null; 
				
				//Cria uma nova árvore binaria.
				
				addRootTree((tree = new LinkedBinaryTree<JButton>()), panel);
				
				//Muda às configurações do menu.
				
				menuItem.setText("Inserir Raiz");
				menuItem.removeActionListener(menuItem.getActionListeners()[0]);
				menuItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addRootTree(tree, panel);}});				
			}});	
			
			//Remove todos os componentes da janela.
			
			panel.removeAll();		
			
			//Cria uma grade para conter os componentes de exibição.
			
			JPanel gridPanel = new JPanel(new GridLayout(2,1));
			
			//Cria um painel para conter a tabela da árvore.
			
			JPanel panelTree = new JPanel(new GridBagLayout());
			
			//Cria a tabela da árvore.
			
			GridBagConstraints gridTree = new GridBagConstraints();		

			//Percorre os nodos da árvore e adiciona na tabela de exibição.
			
			int visits = 0; //Armazena a quantidade de nodos percorridos.
			
			for(Position<String> position : treeExpression.positionsInorder()) {
				
				//Configura a posição da celula da tabela que o componente será inserido.
				
				gridTree.gridx = visits;
				gridTree.gridy = treeExpression.depth(treeExpression, position);
				
				//Adiciona na tabela de exibição.
				
				panelTree.add(new JButton(position.element()), gridTree); 
				
				visits++; //Soma-se +1 a quantidade de nodos percorridos.			
			}
			
			//Realiza o calculo da árvore de expressão.
			
			JLabel evaluateExpression;
			
			//Trata uma possivel exceção.
			try {evaluateExpression = new JLabel(String.valueOf("Cálculo: " + treeExpression.evaluateExpression(treeExpression, treeExpression.root())));}
			
			//Modifica o valor.
			catch(Exception error) {evaluateExpression = new JLabel("Cálculo : Valor Indisponivel");}

			//Cria um painel para conter ás informações de encaminhamento da árvore.
			
			JPanel panelExpression = new JPanel(new GridLayout(6, 1));
			
			//Adiciona os diversos tipos de encaminhamento no painel.
			
			panelExpression.add(evaluateExpression);
			panelExpression.add(new JLabel("Pré-Fixado: " + treeExpression.binaryPreorder(treeExpression, treeExpression.root())));
			panelExpression.add(new JLabel("Pós-Fixado: " + treeExpression.binaryPostorder(treeExpression, treeExpression.root())));
			panelExpression.add(new JLabel("Em Ordem: " + treeExpression.inorder(treeExpression, treeExpression.root())));
			panelExpression.add(new JLabel("Caminhamento Euler: " + treeExpression.eulerTour(treeExpression, treeExpression.root())));
			panelExpression.add(new JLabel("Expressão: " + treeExpression.printExpression(treeExpression, treeExpression.root())));
			
			//Adiciona a janela com a tabela da árvore e os diversos tipos de encaminhamento sobre à arvore.			
			
			gridPanel.add(panelTree);
			gridPanel.add(panelExpression);	
			
			//Adiciona o painel na janela de exibição.
			
			panel.add(gridPanel);
			
			//Métodos da janela para recarrega-lá.
			
			panel.revalidate();
			panel.repaint();			
		}		
	}	
	
	//Cria uma árvore binaria completa e exibe na janela.
	public void treeCompleteBinary(ActionListener actionInsert, JMenuItem menuItem, JPanel panel) {
		
		//Altera para null ás árvores existentes.
		
		treeExpression = null;
		tree = null;
		
		//Cria uma árvore binaria completa.
		
		treeCompleteBinary = new ArrayListCompleteBinaryTree<JButton>();		
		
		//Muda às configurações do menu.
		
		menuItem.setText("Criar Árvore");
		
		for(int i = 0; i < menuItem.getActionListeners().length; i++) {menuItem.removeActionListener(menuItem.getActionListeners()[i]);}			
		
		menuItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
			
			//Altera para null à árvore existentes.
			
			treeCompleteBinary = null;
			
			//Cria uma nova árvore binaria.
			
			addRootTree((tree = new LinkedBinaryTree<JButton>()), panel);
			
			//Muda às configurações do menu.
			
			menuItem.setText("Inserir Raiz");
			menuItem.removeActionListener(menuItem.getActionListeners()[0]);
			menuItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addRootTree(tree, panel);}});				
		}});
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();		
		
		//Cria uma grade para conter os componentes de exibição.
		
		JPanel gridPanel = new JPanel(new GridLayout(2,1));
		
		JPanel panelTreeBinaryComplete = new JPanel();
		
		//Criação do menu para a janela.
		
		JPanel menu = new JPanel();
		
		//Criação dos botoes do menu.
		
		JMenuItem add = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);		
		
		//Insercão dos botoes no menu.
		
		menu.add(add);
		menu.add(remove);
		
		//Configuração dos botoes do menu.
		
		add.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addTreeCompleteBinary(treeCompleteBinary, panelTreeBinaryComplete);}});
		
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
			
			try {treeCompleteBinary.remove(); refreshBinaryCompleteScreen(panelTreeBinaryComplete);} 
			catch(Exception error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}});
		
		//Inserção do menu na grade de exibição.		
		
		gridPanel.add(panelTreeBinaryComplete);
		gridPanel.add(menu);
		
		//Inserção da grade de exibição na janela.
		
		panel.add(gridPanel);		
		
		//Métodos da janela para recarrega-lá.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	//Adiciona um componente na árvore binaria completa.
	public void addTreeCompleteBinary(ArrayListCompleteBinaryTree<JButton> treeCompleteBinary, JPanel panelTreeBinaryComplete) {		
		
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
			
			//Inseri o componente criado na árove.
			
			treeCompleteBinary.add(elementNode);
			
			refreshBinaryCompleteScreen(panelTreeBinaryComplete);
		}
	}
	
	//Controla os metodos que podem ser realizados ao clicar nos componentes da árvore.
	public void buttonManagerScreen(LinkedBinaryTree<JButton> tree, Position<JButton> node, JPanel panel) {		
		
		//Cria uma janela de opções.
		
		Object[] options = {"Adicionar", "Remover"};
		
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
			
			//Cria uma janela de opções.
			
			Object[] optionsAdd = {"Direita", "Esquerda"};
			
			option = JOptionPane.showOptionDialog(
					
					this.getParent(), 
					"Selecione uma Opção: ", 
					"Adicione na Árvore Binária", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					optionsAdd, 
					optionsAdd
			);			
			
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
				
				try { //Trata uma possivel exceção.
					
					//Cria o componente que será inserido no nodo.
					
					JButton elementNode = new JButton(element);		
					
					//Inseri o componente criado na árvore.
					
					if(option == 0) {
						
						//Inseri o componente criado à direita da árvore.
						
						Position<JButton> newNode = tree.insertRight(node, elementNode); 
						
						//Configura o componente criado.
						
						elementNode.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, newNode, panel);}});				
					}
					
					else {
						
						//Inseri o componente criado à esquerda da árvore.
						
						Position<JButton> newNode = tree.insertLeft(node, elementNode);
						
						//Configura o componente criado.
						
						elementNode.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, newNode, panel);}});					
					}
					
					//Recarrega a janela.
					
					refreshScreen(panel);
				
				//Libera uma mensagem sobre o erro.
				} catch(InvalidPositionException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			}
			
		} else if(option == 1) {
			
			try { //Trata uma possivel exceção.
				
				//Remove o componente da árvore.
				
				tree.remove(node);
				
				//Recarrega a janela.
				
				refreshScreen(panel);
			
			//Libera uma mensagem sobre o erro.
			} catch(InvalidPositionException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
		}
	}
	
	public void refreshBinaryCompleteScreen(JPanel panel) {		
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();		
		
		//Cria um painel para conter a tabela da árvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da árvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();		

		//Percorre os nodos da árvore e adiciona na tabela de exibição.
		
		int visits = 0; //Armazena a quantidade de nodos percorridos.
		
		for(TADS.TAD_Arvore.TAD_Arvore_Binaria_Heap.Interfaces.Position<JButton> position : treeCompleteBinary.positionsInorder()) {
			
			//Configura a posição da celula da tabela que o componente será inserido.
			
			gridTree.gridx = visits;
			gridTree.gridy = treeCompleteBinary.depth(treeCompleteBinary, position);
			
			//Adiciona na tabela de exibição.
			
			panelTree.add(position.element(), gridTree); 
			
			visits++; //Soma-se +1 a quantidade de nodos percorridos.			
		}
		
		//Adiciona a janela com a tabela da árvore na janela de exibição.
		
		panel.add(panelTree);
		
		//Métodos da janela para recarrega-lá.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel panel) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();		
		
		//Cria um painel para conter a tabela da árvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da árvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();		

		//Percorre os nodos da árvore e adiciona na tabela de exibição.
		
		int visits = 0; //Armazena a quantidade de nodos percorridos.
		
		for(Position<JButton> position : tree.positionsInorder()) {
			
			//Configura a posição da celula da tabela que o componente será inserido.
			
			gridTree.gridx = visits;
			gridTree.gridy = tree.depth(tree, position);
			
			//Adiciona na tabela de exibição.
			
			panelTree.add(position.element(), gridTree); 
			
			visits++; //Soma-se +1 a quantidade de nodos percorridos.			
		}		
		
		//Adiciona a janela com a tabela da árvore na janela de exibição.
		
		panel.add(panelTree);		
		
		//Métodos da janela para recarrega-lá.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}

}