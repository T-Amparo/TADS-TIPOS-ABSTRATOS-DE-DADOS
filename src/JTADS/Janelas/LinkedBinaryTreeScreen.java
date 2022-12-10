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
		
		//Configura��es da janela.
		
		super("�rvore Binaria");
		
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
				
				+ "\n\nA estrutura de uma �rvore Bin�ria segue um padr�o, os nodos tem no m�ximo dois filhos, e s�o "
				+ "\nrotulados como filho da direita e filho da esquerda, o filho da esquerda sempre preceder� o filho "
				+ "\nda direita, essas caracter�sticas s�o justamente o que tornam essa �rvore em Bin�ria."
				
				+ "\n\nUtilizando uma �rvore Bin�ria � possivel construir uma �rvore de express�es, aonde o operador"
				+ "\nser� sempre pai de dois n�meros."
				
				+ "\n\nA �rvore Bin�ria tambem possui uma outra variante, a �rvore Bin�ria Completa, que tamb�m dispoe"
				+ "\ndas caracteristica da �rvore Bin�ria comun por�m com caracteristicas novas, para �rvore Bin�ria "
				+ "\nCompleta as inser��es ser�o realizadas da esquerda para a direita, preenchendo sempre um nivel"
				+ "\npor vez."

				+ "\n\n=================================Implementa��o==========================================="

				+ "\n\nAs opera��es na �rvore Bin�ria poder�o ser realizadas a partir dos nodos da mesma.";		
			
		//Inser��o do texto em uma caixa de texto para exibi��o.
		
		JTextArea boxTextTree = new JTextArea(textTree);
		
		//Configura��o da caixa de texto.
		
		boxTextTree.setEditable(false);
		boxTextTree.setPreferredSize(new Dimension(550, 600));	
		
		//Cria��o de botoes para a janela.
		
		JButton createTree = new JButton("Criar �rvore Binaria");		
		
		//Inserc�o dos componentes na janela.
		
		add(boxTextTree);		
		add(createTree);
		
		//Configura��o dos botoes da janela.
		
		createTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createTree();}});
	}
	
	//Cria uma �rvore e uma tela de representa��o para a mesma.
	public void createTree() {
		
		tree = new LinkedBinaryTree<JButton>(); //Inicia a �rvore.
		
		createRepresentationScreen(tree); //Cria uma janela para a �rvore.
	}
	
	//Cria uma janela para representar uma �rvore binaria.
	public void createRepresentationScreen(LinkedBinaryTree<JButton> tree) {		
		
		//Cria uma janela para representar uma �rvore bin�ria.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle("�rvore Binar�a");
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da lista arranjo.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(600, 500));			
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem addRootTree = new JMenuItem("Inserir Raiz", KeyEvent.VK_M);
		JMenuItem treeExpression = new JMenuItem("�rvore de Express�es", KeyEvent.VK_M);
		JMenuItem completeBinary = new JMenuItem("�rvore Completa", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		ActionListener actionInsert;
		
		addRootTree.addActionListener(actionInsert = new ActionListener() {public void actionPerformed(ActionEvent event) {addRootTree(tree, panel);}});
		treeExpression.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {treeExpression(actionInsert, addRootTree, panel);}});
		completeBinary.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {treeCompleteBinary(actionInsert, addRootTree, panel);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(addRootTree);
		menu.add(treeExpression);
		menu.add(completeBinary);
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);				
	}
	
	//Adiciona a raiz inserida pelo usu�rio na �rvore.
	public void addRootTree(LinkedBinaryTree<JButton> tree, JPanel panel) {		
		
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
			
			try { //Trata uma possivel exce��o.
				
				//Cria o componente que ser� inserido na raiz.
				
				JButton element = new JButton(rootElement);
				
				//Insere o componente criado na raiz da �rvore.
				
				tree.addRoot(element);
				
				//Configura o componente criado.
				
				element.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, tree.root(), panel);}});
				
				//Recarrega a janela.
				
				refreshScreen(panel);
			
			//Libera uma mensagem sobre o erro.	
			} catch(NonEmptyTreeException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}
	
	//Cria uma �rvore de express�es e exibe na janela.
	public void treeExpression(ActionListener actionInsert, JMenuItem menuItem, JPanel panel) {		
		
		//Altera para null �s �rvores existentes.
		
		treeCompleteBinary = null;
		tree = null;
		
		//Cria uma janela para coletar o elemento do nodo.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira a Express�o: \n\n -Totalmente Parentizada \n\n -Sem Espa�os \n\n Exemplo: ((1+2)+3)",
				"Inser��o de Express�o",
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				null
		);			
		
		if(element != null && !element.equals("")) {
			
			//Cria uma �rvore de express�o.
			
			treeExpression = new LinkedBinaryTree<String>(); //Cria uma �rvore vazia.
			treeExpression = treeExpression.buildExpression(element); //Cria � �rvore de express�o.		
			
			//Muda �s configura��es do menu.
			
			menuItem.setText("Criar �rvore");
			for(int i = 0; i < menuItem.getActionListeners().length; i++) {menuItem.removeActionListener(menuItem.getActionListeners()[i]);}			
			
			menuItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
				
				//Altera para null � �rvore de express�o criada.
				
				treeExpression = null; 
				
				//Cria uma nova �rvore binaria.
				
				addRootTree((tree = new LinkedBinaryTree<JButton>()), panel);
				
				//Muda �s configura��es do menu.
				
				menuItem.setText("Inserir Raiz");
				menuItem.removeActionListener(menuItem.getActionListeners()[0]);
				menuItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addRootTree(tree, panel);}});				
			}});	
			
			//Remove todos os componentes da janela.
			
			panel.removeAll();		
			
			//Cria uma grade para conter os componentes de exibi��o.
			
			JPanel gridPanel = new JPanel(new GridLayout(2,1));
			
			//Cria um painel para conter a tabela da �rvore.
			
			JPanel panelTree = new JPanel(new GridBagLayout());
			
			//Cria a tabela da �rvore.
			
			GridBagConstraints gridTree = new GridBagConstraints();		

			//Percorre os nodos da �rvore e adiciona na tabela de exibi��o.
			
			int visits = 0; //Armazena a quantidade de nodos percorridos.
			
			for(Position<String> position : treeExpression.positionsInorder()) {
				
				//Configura a posi��o da celula da tabela que o componente ser� inserido.
				
				gridTree.gridx = visits;
				gridTree.gridy = treeExpression.depth(treeExpression, position);
				
				//Adiciona na tabela de exibi��o.
				
				panelTree.add(new JButton(position.element()), gridTree); 
				
				visits++; //Soma-se +1 a quantidade de nodos percorridos.			
			}
			
			//Realiza o calculo da �rvore de express�o.
			
			JLabel evaluateExpression;
			
			//Trata uma possivel exce��o.
			try {evaluateExpression = new JLabel(String.valueOf("C�lculo: " + treeExpression.evaluateExpression(treeExpression, treeExpression.root())));}
			
			//Modifica o valor.
			catch(Exception error) {evaluateExpression = new JLabel("C�lculo : Valor Indisponivel");}

			//Cria um painel para conter �s informa��es de encaminhamento da �rvore.
			
			JPanel panelExpression = new JPanel(new GridLayout(6, 1));
			
			//Adiciona os diversos tipos de encaminhamento no painel.
			
			panelExpression.add(evaluateExpression);
			panelExpression.add(new JLabel("Pr�-Fixado: " + treeExpression.binaryPreorder(treeExpression, treeExpression.root())));
			panelExpression.add(new JLabel("P�s-Fixado: " + treeExpression.binaryPostorder(treeExpression, treeExpression.root())));
			panelExpression.add(new JLabel("Em Ordem: " + treeExpression.inorder(treeExpression, treeExpression.root())));
			panelExpression.add(new JLabel("Caminhamento Euler: " + treeExpression.eulerTour(treeExpression, treeExpression.root())));
			panelExpression.add(new JLabel("Express�o: " + treeExpression.printExpression(treeExpression, treeExpression.root())));
			
			//Adiciona a janela com a tabela da �rvore e os diversos tipos de encaminhamento sobre � arvore.			
			
			gridPanel.add(panelTree);
			gridPanel.add(panelExpression);	
			
			//Adiciona o painel na janela de exibi��o.
			
			panel.add(gridPanel);
			
			//M�todos da janela para recarrega-l�.
			
			panel.revalidate();
			panel.repaint();			
		}		
	}	
	
	//Cria uma �rvore binaria completa e exibe na janela.
	public void treeCompleteBinary(ActionListener actionInsert, JMenuItem menuItem, JPanel panel) {
		
		//Altera para null �s �rvores existentes.
		
		treeExpression = null;
		tree = null;
		
		//Cria uma �rvore binaria completa.
		
		treeCompleteBinary = new ArrayListCompleteBinaryTree<JButton>();		
		
		//Muda �s configura��es do menu.
		
		menuItem.setText("Criar �rvore");
		
		for(int i = 0; i < menuItem.getActionListeners().length; i++) {menuItem.removeActionListener(menuItem.getActionListeners()[i]);}			
		
		menuItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
			
			//Altera para null � �rvore existentes.
			
			treeCompleteBinary = null;
			
			//Cria uma nova �rvore binaria.
			
			addRootTree((tree = new LinkedBinaryTree<JButton>()), panel);
			
			//Muda �s configura��es do menu.
			
			menuItem.setText("Inserir Raiz");
			menuItem.removeActionListener(menuItem.getActionListeners()[0]);
			menuItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addRootTree(tree, panel);}});				
		}});
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();		
		
		//Cria uma grade para conter os componentes de exibi��o.
		
		JPanel gridPanel = new JPanel(new GridLayout(2,1));
		
		JPanel panelTreeBinaryComplete = new JPanel();
		
		//Cria��o do menu para a janela.
		
		JPanel menu = new JPanel();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem add = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);		
		
		//Inserc�o dos botoes no menu.
		
		menu.add(add);
		menu.add(remove);
		
		//Configura��o dos botoes do menu.
		
		add.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addTreeCompleteBinary(treeCompleteBinary, panelTreeBinaryComplete);}});
		
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
			
			try {treeCompleteBinary.remove(); refreshBinaryCompleteScreen(panelTreeBinaryComplete);} 
			catch(Exception error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}});
		
		//Inser��o do menu na grade de exibi��o.		
		
		gridPanel.add(panelTreeBinaryComplete);
		gridPanel.add(menu);
		
		//Inser��o da grade de exibi��o na janela.
		
		panel.add(gridPanel);		
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	//Adiciona um componente na �rvore binaria completa.
	public void addTreeCompleteBinary(ArrayListCompleteBinaryTree<JButton> treeCompleteBinary, JPanel panelTreeBinaryComplete) {		
		
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
			
			//Inseri o componente criado na �rove.
			
			treeCompleteBinary.add(elementNode);
			
			refreshBinaryCompleteScreen(panelTreeBinaryComplete);
		}
	}
	
	//Controla os metodos que podem ser realizados ao clicar nos componentes da �rvore.
	public void buttonManagerScreen(LinkedBinaryTree<JButton> tree, Position<JButton> node, JPanel panel) {		
		
		//Cria uma janela de op��es.
		
		Object[] options = {"Adicionar", "Remover"};
		
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
			
			//Cria uma janela de op��es.
			
			Object[] optionsAdd = {"Direita", "Esquerda"};
			
			option = JOptionPane.showOptionDialog(
					
					this.getParent(), 
					"Selecione uma Op��o: ", 
					"Adicione na �rvore Bin�ria", 
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
					"Inser��o de Nodo",
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					null
			);			
			
			if(element != null) {				
				
				try { //Trata uma possivel exce��o.
					
					//Cria o componente que ser� inserido no nodo.
					
					JButton elementNode = new JButton(element);		
					
					//Inseri o componente criado na �rvore.
					
					if(option == 0) {
						
						//Inseri o componente criado � direita da �rvore.
						
						Position<JButton> newNode = tree.insertRight(node, elementNode); 
						
						//Configura o componente criado.
						
						elementNode.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(tree, newNode, panel);}});				
					}
					
					else {
						
						//Inseri o componente criado � esquerda da �rvore.
						
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
			
			try { //Trata uma possivel exce��o.
				
				//Remove o componente da �rvore.
				
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
		
		//Cria um painel para conter a tabela da �rvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da �rvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();		

		//Percorre os nodos da �rvore e adiciona na tabela de exibi��o.
		
		int visits = 0; //Armazena a quantidade de nodos percorridos.
		
		for(TADS.TAD_Arvore.TAD_Arvore_Binaria_Heap.Interfaces.Position<JButton> position : treeCompleteBinary.positionsInorder()) {
			
			//Configura a posi��o da celula da tabela que o componente ser� inserido.
			
			gridTree.gridx = visits;
			gridTree.gridy = treeCompleteBinary.depth(treeCompleteBinary, position);
			
			//Adiciona na tabela de exibi��o.
			
			panelTree.add(position.element(), gridTree); 
			
			visits++; //Soma-se +1 a quantidade de nodos percorridos.			
		}
		
		//Adiciona a janela com a tabela da �rvore na janela de exibi��o.
		
		panel.add(panelTree);
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel panel) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();		
		
		//Cria um painel para conter a tabela da �rvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da �rvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();		

		//Percorre os nodos da �rvore e adiciona na tabela de exibi��o.
		
		int visits = 0; //Armazena a quantidade de nodos percorridos.
		
		for(Position<JButton> position : tree.positionsInorder()) {
			
			//Configura a posi��o da celula da tabela que o componente ser� inserido.
			
			gridTree.gridx = visits;
			gridTree.gridy = tree.depth(tree, position);
			
			//Adiciona na tabela de exibi��o.
			
			panelTree.add(position.element(), gridTree); 
			
			visits++; //Soma-se +1 a quantidade de nodos percorridos.			
		}		
		
		//Adiciona a janela com a tabela da �rvore na janela de exibi��o.
		
		panel.add(panelTree);		
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}

}