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
		
		//Configura��es da janela.
		
		super("�rvore Gen�rica");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Cria��o do texto de apresenta��o do TAD.
		
		String textTree = "\n=================================Introdu��o==========================================="
				
				+ "\n\nUma �rvore � um dos Tipos Abstratos de Dados mais importantes da computa��o, "
				+ "\npermitindo a implementa��o de uma s�rie de algoritmos mais r�pidos do que em estruturas de "
				+ "\ndados lineares."
				
				+ "\n\nEm uma �rvore as posi��es se referem aos seus nodos, e o posicionamento de um nodo dentro"
				+ "\nda �rvore em rela��o ao seu pai ou seu filho � o que define uma �rvore v�lida."
				
				+ "\n\nA estrutura de um �rvore Gen�rica � totalmente hier�rquica, sendo um conjunto de nodos que "
				+ "\narmazenam elementos e se relacionam com outros nodos da �rvore, iniciando pela raiz cada "
				+ "\nnodo que comp�e a �rvore ter� um pai e poder� ter diversos filhos, com exce��o da raiz da �rvore "
				+ "\nque n�o tem outro nodo acima dela, ou seja a raiz n�o ter� um pai."
				
				+ "\n\n=================================Implementa��o==========================================="
				
				+ "\n\nPara est� implementa��o de �rvore Generica somente foi implementado o m�todo adicionar. "
				+ "\n\nAs inser��es na �rvore Generica poder�o ser realizadas a partir do nodo raiz.";		
			
		//Inser��o do texto em uma caixa de texto para exibi��o.
		
		JTextArea boxTextTree = new JTextArea(textTree);
		
		//Configura��o da caixa de texto.
		
		boxTextTree.setEditable(false);
		boxTextTree.setPreferredSize(new Dimension(550, 600));	
		
		//Cria��o de botoes para a janela.
		
		JButton createTree = new JButton("Criar �rvore Gen�rica");
		
		//Inserc�o dos componentes na janela.
		
		add(boxTextTree);
		add(createTree);
		
		//Configura��o dos botoes da janela.
		
		createTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createTree();}});
	}
	
	//Cria uma �rvore e uma janela para exibi-la.
	public void createTree() {
		
		//Cria uma janela para coletar a raiz da �rvore.
		
		String rootElement = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira a Raiz da �rvore Gen�rica:",
				"Inser��o da Raiz",
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				null
		);
		
		if(rootElement != null) { //Verifica se uma raiz foi informada.			
			
			//Cria uma janela para representar uma lista arranjo.
			
			JFrame screen = new JFrame();
			
			//Configura��es da janela criada.
			
			screen.setLayout(new BorderLayout());
			screen.setTitle("�rvore Gen�rica");
			screen.setSize(500, 645);
			screen.setResizable(false);
			screen.setVisible(true);
			
			//Cria um painel para conter os componentes da lista arranjo.
			
			JPanel panel = new JPanel();
			
			//Cria um painel com scroll.
			
			JScrollPane scroll = new JScrollPane(panel);
			
			//Configura��o do scroll da janela.
			
			scroll.setPreferredSize(new Dimension(500, 145));			
			
			//Cria��o do menu para a janela.
			
			JMenuBar menu = new JMenuBar();
			
			//Cria��o dos botoes do menu.
			
			JMenuItem createNewTree = new JMenuItem("Criar Nova �rvore", KeyEvent.VK_M);
			
			//Configura��o dos botoes do menu.
			
			createNewTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createNewTree(panel);}});
			
			//Inserc�o dos botoes no menu.
			
			menu.add(createNewTree);			
			
			//Inserc�o do menu na janela.
			
			screen.setJMenuBar(menu);
			
			//Inser��o do scroll na janela.
			
			screen.add(scroll);
			
			//Cria uma �rvore gen�rica e suas configura��es.
			
			tree = new LinkedTree<JButton>(); //Cria uma �rvore gen�rica.
			
			//Cria o componente que ser� inserido na raiz.
			
			JButton element = new JButton(rootElement);
			
			//Insere o componente criado na raiz da �rvore.
			
			tree.addRoot(element);			
	
			TreeNode<JButton> root = (TreeNode<JButton>) tree.root(); //Armazena a raiz da �rvore.
			root.setChildren(new NodePositionList<Position<JButton>>()); //Cria uma lista para os filhos da raiz da �rvore.
			
			//Configura o componente criado.
			
			element.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, root, panel);}});
			
			refreshScreen(panel);
		}		
	}	
	
	//O M�todo ir� criar os nodos filhos de um dado nodo.
	private TreeNode<JButton> createNodeChildren(TreeNode<JButton> node, JButton element) {

		PositionList<Position<JButton>> children; //Instancia uma lista de posi��es.
		TreeNode<JButton> newNode; //Novo nodo que ser� criado.

		children = node.getChildren(); //Armazena os filhos da raiz.

		newNode = new TreeNode<JButton>(); //Cria um nodo vazio.

		newNode.setElement(element); //Define o elemento do nodo.
		newNode.setParent(node); //Define o pai do nodo.
		newNode.setChildren(new NodePositionList<Position<JButton>>()); //Cria uma lista de posi��es para os filhos do nodo.
		children.addLast(newNode); //Adiciona o nodo criado a lista de filhos da raiz.

		return newNode; //Retorna o nodo criado.
	}
	
	public void buttonManagerScreen(LinkedTree<JButton> tree, TreeNode<JButton> node, JPanel panel) {		
		
		//Cria uma janela de op��es.
		
		Object[] options = {"Adicionar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Op��o: ", 
				"M�todos da �rvore Gen�rica", 
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
					"Inser��o de Nodo",
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					null
			);
			
			if(element != null) {				
				
				//Cria o componente que ser� inserido no nodo.
				
				JButton elementNode = new JButton(element);				
				
				//Configura o componente criado.
				
				TreeNode<JButton> newNode = createNodeChildren(node, elementNode);
				
				elementNode.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, newNode, panel);}});
				
				refreshScreen(panel);
			}			
		}		
	}
	
	//Cria uma �rvore nova.
	public void createNewTree(JPanel panel) {		
		
		//Cria uma janela de confirma��o.
		
		Object[] options = {"Continuar", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Deseja Criar Uma Nova �rvore?", 
				"Criar Nova �rvore Generica", 
				JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);
		
		if(option == 0) {			
			
			//Cria uma janela para coletar a raiz da �rvore.
			
			String rootElement = (String) JOptionPane.showInputDialog(
					
					this.getParent(),
					"Insira a Raiz da �rvore Gen�rica:",
					"Inser��o da Raiz",
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					null
			);
			
			if(rootElement != null) {				
				
				//Cria uma �rvore gen�rica e suas configura��es.
				
				tree = new LinkedTree<JButton>(); //Cria uma �rvore gen�rica.
				
				//Cria o componente que ser� inserido na raiz.
				
				JButton element = new JButton(rootElement);
				
				//Insere o componente criado na raiz da �rvore.
				
				tree.addRoot(element);			
		
				TreeNode<JButton> root = (TreeNode<JButton>) tree.root(); //Armazena a raiz da �rvore.
				root.setChildren(new NodePositionList<Position<JButton>>()); //Cria uma lista para os filhos da raiz da �rvore.				
				
				//Configura o componente criado.
				
				element.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, root, panel);}});
				
				refreshScreen(panel);
			}
		}
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel panel) {
		
		panel.removeAll();
		
		//Instancia uma lista de nodos, para armazenar os nodos da �rvore.
		
		PositionList<Position<JButton>> positions = new NodePositionList<Position<JButton>>();
		
		//Percorre � �rvore e preenche a lista com os nodos da mesma.
		
		for(Position<JButton> position : tree.positions()) {positions.addLast(position);}
		
		//Cria um painel para conter a tabela da �rvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da �rvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();

		//Percorre a �rvore e preenche a tabela.
		
		int visits = 0;
		for(Position<JButton> position : positions) {

			gridTree.fill = GridBagConstraints.HORIZONTAL;
			gridTree.gridx = tree.depth(tree, position);
			gridTree.gridy = visits;
			
			panelTree.add(position.element(), gridTree);
			visits++;
		}
		
		//Adiciona a janela com a tabela da �rvore na janela de representa��o.
		
		panel.add(panelTree);		
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();
	}
	
	public static void main(String[] args) {new MainScreen();}

}