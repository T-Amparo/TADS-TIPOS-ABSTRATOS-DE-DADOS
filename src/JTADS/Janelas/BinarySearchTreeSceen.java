package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Excecoes.InvalidEntryException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Excecoes.InvalidKeyException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Fontes.BinarySearchTree;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces.Entry;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces.Position;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class BinarySearchTreeSceen extends JFrame {
	
	Object tree;
	
	public BinarySearchTreeSceen() {
		
		//Configura��es da janela.
		
		super("�rvore Bin�ria de Busca");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Cria��o do texto de apresenta��o do TAD.		
		
		String textTree = "\n=================================Introdu��o==========================================="
							
							+ "\n\nA �rvore Bin�ria de Busca � um Tipo Abstrato de Dados que tem por sua estrutura uma �rvore "
							+ "\nBin�ria onde apenas os nodos internos armazenam os elementos juntos com suas chaves. Cada "
							+ "\nnodo dentro da �rvore � organizado de acordo com sua chave, os nodos que possuem uma chave "
							+ "\nmenor que a raiz s�o inseridos na sub�rvore a esquerda e os nodos que possuem chave maior que "
							+ "\na raiz s�o inseridos na subarvore a direita, com essa abordagem � poss�vel pesquisar uma chave "
							+ "\ncomparando os nodos internos." 

							+ "\n\n==================================M�todos==========================================="
							
							+ "\n\nM�todos b�sicos da �rvore Bin�ria de Busca:" +							
							
							"\n\nInserir/put: O elemento ser� inserido na �rvore de acordo com a chave do elemento." + 
							"\n\nRemover/remove: O elemento que tiver a chave correspondente ser� removido da �rvore." + 
							"\n\nConsultar/get: O elemento que tiver a chave correspondente ser� encontrado na �rvore." + 

							"\n\n================================Implementa��o========================================="
							
							+ "\n\nPor quest�es de desenvolvimento a implementa��o da �rvore Bin�ria de Busca trata-se de uma"
							+ "\n�rvore de Strings, permitindo uso de todo e qualquer caractere da tabela ASCII e tratando a"
							+ "\nentrada de n�meros como Strings. Excerto para as chaves que dever�o ser predefinidas."
							
							+ "\n\nAs opera��es na �rvore Bin�ria de Busca poder�o ser realizadas a partir dos nodos da mesma.";	
		
		//Inser��o do texto em uma caixa de texto para exibi��o.
		
		JTextArea boxTextTree = new JTextArea(textTree);
		
		//Configura��o da caixa de texto.
		
		boxTextTree.setEditable(false);
		boxTextTree.setPreferredSize(new Dimension(550, 600));	
		
		//Cria��o de botoes para a janela.
		
		JButton createTree = new JButton("Criar �rvore Bin�ria de Busca");
		
		//Inserc�o dos componentes na janela.
		
		add(boxTextTree);
		add(createTree);
		
		//Configura��o dos botoes da janela.
		
		createTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createBinarySearchTree();}});
	}
	
	@SuppressWarnings("unchecked")
	public void createBinarySearchTree() {		
		
		//Cria � �rvore bin�ria de busca segundo o tipo se chave escolhida pelo usu�rio.
		
		Object[] types = {"Inteiros", "Strings"};			

		//Janela para coletar o tipo de chave que a �rvore bin�ria de busca ir� armazenar.
		
		int type = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione o Tipo da Chave:", 
				"Tipos de Dados", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				types, 
				types
		);		
		
		if(type == 0) {
			
			tree = new BinarySearchTree<Integer, ArrayList<JComponent>>();
			
			createRepresentationScreenIntegers("�rvore Bin�ria de Busca", ((BinarySearchTree<Integer, ArrayList<JComponent>>)tree));
			
		} else {			
			
			tree = new BinarySearchTree<String, ArrayList<JComponent>>();
			
			createRepresentationScreenStrings("�rvore Bin�ria de Busca", ((BinarySearchTree<String, ArrayList<JComponent>>)tree));			
		}		
	}
	
	//Cria uma janela para representar uma �rvore bin�ria de busca.
	public void createRepresentationScreenIntegers(String typeTree, BinarySearchTree<Integer, ArrayList<JComponent>> tree) {		
		
		//Cria uma janela para representar uma �rvore bin�ria de busca.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeTree);
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da �rvore bin�ria de busca.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(600, 500));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem put = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem get = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putBinarySearchTreeIntegers(tree, "", panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeBinarySearchTreeIntegers(tree, "", panel);}});
		get.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getBinarySearchTreeIntegers(tree);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);
		menu.add(get);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);		
	}	
	
	//Cria uma janela para representar uma �rvore bin�ria de busca.
	public void createRepresentationScreenStrings(String typeTree, BinarySearchTree<String, ArrayList<JComponent>> tree) {		
		
		//Cria uma janela para representar uma �rvore bin�ria de busca.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeTree);
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da �rvore bin�ria de busca.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(600, 500));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem put = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem get = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putBinarySearchTreeStrings(tree, "/#/", panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeBinarySearchTreeStrings(tree, "/#/", panel);}});
		get.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getBinarySearchTreeStrings(tree);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);
		menu.add(get);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);		
	}
	
	//Adiciona um item na �rvore bin�ria de busca e na janela de representa��o da �rvore.
	public void putBinarySearchTreeIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Cria uma lista arranjo para conter os componentes de exibi��o de uma dada posi��o da �rvore bin�ria de busca.
		
		ArrayList<JComponent> components = new ArrayList<JComponent>(3);
		
		//Cria uma janela para coletar os elementos que ser�o adicionados na �rvore bin�ria de busca.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Cria��o dos botoes da janela.
		
		panelEntry.add(new JLabel("Chave: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField key;
		JTextField element = new JTextField();		
		
		if(keyBinarySearchTree == "") {key = new JTextField();}
		else {key = new JTextField(keyBinarySearchTree);}
		
		//Configura��o dos botoes da janela.
		
		panelEntry.add(key);
		panelEntry.add(element);		
		
		Object[] options = {"OK", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				panelEntry, 
				"Insira um Novo Elemento:", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);
		
		if(option == 0) {			
			
			//Cria��o do painel que ser� inserido na �rvore bin�ria de busca com o painel inserido na tela de adi��o.
			
			JPanel panelElement = new JPanel(new GridLayout(1, 2));		
			
			//Cria��o dos elementos do painel, que armazenar�o os dados de entrada do usu�rio.
			
			JButton treeElement = new JButton(String.valueOf(element.getText()));
			JButton treeKey = new JButton(String.valueOf(key.getText()));			
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, treeKey);
			components.add(1, treeElement);
			components.add(2, panelElement);
			
			//Configura��o dos elementos do painel.
			
			treeElement.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenIntegers(tree, ((JButton)components.get(0)).getText(), panel);}});
			treeKey.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenIntegers(tree, ((JButton)components.get(0)).getText(), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(treeKey);
			panelElement.add(treeElement);			
			
			try { //Trata uma possivel exce��o.				
				
				//Adiciona o painel criado na �rvore bin�ria de busca.
				
				tree.put(Integer.parseInt(key.getText()), components);
				
				//Recarrega a janela.
				
				refreshScreenIntegers(tree, panel);
			}
			
			//Libera um mensagem de erro.
			catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}	
	
	//Adiciona um item na �rvore bin�ria de busca e na janela de representa��o da �rvore.
	public void putBinarySearchTreeStrings(BinarySearchTree<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Cria uma lista arranjo para conter os componentes de exibi��o de uma dada posi��o da �rvore bin�ria de busca.
		
		ArrayList<JComponent> components = new ArrayList<JComponent>(3);
		
		//Cria uma janela para coletar os elementos que ser�o adicionados na �rvore bin�ria de busca.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Cria��o dos botoes da janela.
		
		panelEntry.add(new JLabel("Chave: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField key;
		JTextField element = new JTextField();		
		
		if(keyBinarySearchTree == "/#/") {key = new JTextField();}
		else {key = new JTextField(keyBinarySearchTree);}
		
		//Configura��o dos botoes da janela.
		
		panelEntry.add(key);
		panelEntry.add(element);		
		
		Object[] options = {"OK", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				panelEntry, 
				"Insira um Novo Elemento:", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);
		
		if(option == 0) {			
			
			//Cria��o do painel que ser� inserido na �rvore bin�ria de busca com o painel inserido na tela de adi��o.
			
			JPanel panelElement = new JPanel(new GridLayout(1, 2));		
			
			//Cria��o dos elementos do painel, que armazenar�o os dados de entrada do usu�rio.
			
			JButton treeElement = new JButton(String.valueOf(element.getText()));
			JButton treeKey = new JButton(String.valueOf(key.getText()));			
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, treeKey);
			components.add(1, treeElement);
			components.add(2, panelElement);
			
			//Configura��o dos elementos do painel.
			
			treeElement.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenStrings(tree, ((JButton)components.get(0)).getText(), panel);}});
			treeKey.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenStrings(tree, ((JButton)components.get(0)).getText(), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(treeKey);
			panelElement.add(treeElement);			
			
			try { //Trata uma possivel exce��o.				
				
				//Adiciona o painel criado na �rvore bin�ria de busca.
				
				tree.put(key.getText(), components);
				
				//Recarrega a janela.
				
				refreshScreenStrings(tree, panel);
			}
			
			//Libera um mensagem de erro.
			catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}
	
	//Remove um item da �rvore bin�ria de busca e da janela de representa��o da �rvore.
	public void removeBinarySearchTreeIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Verifica se a �rvore est� vazia.
		
		if(!tree.isEmpty()) {				
			
			if(keyBinarySearchTree == "") {
	
				//Cria uma janela para coletar os elementos que ser�o removidos da �rvore bin�ria de busca.
				
				JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
				
				//Cria��o dos botoes da janela.
				
				panelEntry.add(new JLabel("Chave: "));
				
				JTextField key = new JTextField();
				
				//Configura��o dos botoes da janela.
				
				panelEntry.add(key);		
				
				Object[] options = {"OK", "Cancelar"};
				
				int option = JOptionPane.showOptionDialog(
						
						this.getParent(), 
						panelEntry, 
						"Insira a Chave do Elemento:", 
						JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						options,
						options				
				);
				
				if(option == 0) {
					
					//Trata uma possivel exce��o.
					try {
						
						//Remove o componente da �rvore bin�ria de busca.
						
						if(tree.remove(Integer.parseInt(key.getText())) != null) {					
							
							//Recarrega a janela.
							
							refreshScreenIntegers(tree, panel);
						
						//Retorna uma mensagem caso a chave n�o seja encontrada.
						} else {JOptionPane.showMessageDialog(null, "A chave n�o foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}				
					}
					
					//Libera um mensagem de erro.
					catch(InvalidEntryException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
					
					//Libera um mensagem de erro.
					catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
				}
				
			} else {			
				
				//Cria uma janela para coletar os elementos que ser�o removidos na �rvore bin�ria de busca.
				
				Object[] options = {"Continuar", "Cancelar"};
				
				int option = JOptionPane.showOptionDialog(
						
						this.getParent(), 
						"Remover elemento com chave [" + keyBinarySearchTree + "]", 
						"Remo��o de Elementos", 
						JOptionPane.OK_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						options,
						options				
				);
				
				//Remove o componente da �rvore bin�ria de busca e atualiza a janela.
				
				if(option == 0) {tree.remove(Integer.parseInt(keyBinarySearchTree)); refreshScreenIntegers(tree, panel);}
			}
		
		//Retorna uma mensagem caso � �rvore esteja vazia.
		} else {JOptionPane.showMessageDialog(null, "� �rvore est� vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);}
	}	
	
	//Remove um item da �rvore bin�ria de busca e da janela de representa��o da �rvore.
	public void removeBinarySearchTreeStrings(BinarySearchTree<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Verifica se a �rvore est� vazia.
		
		if(!tree.isEmpty()) {				
			
			if(keyBinarySearchTree == "/#/") {
	
				//Cria uma janela para coletar os elementos que ser�o removidos da �rvore bin�ria de busca.
				
				JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
				
				//Cria��o dos botoes da janela.
				
				panelEntry.add(new JLabel("Chave: "));
				
				JTextField key = new JTextField();
				
				//Configura��o dos botoes da janela.
				
				panelEntry.add(key);		
				
				Object[] options = {"OK", "Cancelar"};
				
				int option = JOptionPane.showOptionDialog(
						
						this.getParent(), 
						panelEntry, 
						"Insira a Chave do Elemento:", 
						JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						options,
						options				
				);
				
				if(option == 0) {
					
					//Trata uma possivel exce��o.
					try {
						
						//Remove o componente da �rvore bin�ria de busca.
						
						if(tree.remove(key.getText()) != null) {					
							
							//Recarrega a janela.
							
							refreshScreenStrings(tree, panel);
						
						//Retorna uma mensagem caso a chave n�o seja encontrada.
						} else {JOptionPane.showMessageDialog(null, "A chave n�o foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}				
					}
					
					//Libera um mensagem de erro.
					catch(InvalidEntryException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
					
					//Libera um mensagem de erro.
					catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
				}
				
			} else {			
				
				//Cria uma janela para coletar os elementos que ser�o removidos da �rvore bin�ria de busca.
				
				Object[] options = {"Continuar", "Cancelar"};
				
				int option = JOptionPane.showOptionDialog(
						
						this.getParent(), 
						"Remover elemento com chave [" + keyBinarySearchTree + "]", 
						"Remo��o de Elementos", 
						JOptionPane.OK_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						options,
						options				
				);
				
				//Remove o componente da �rvore bin�ria de busca e atualiza a janela.
				
				if(option == 0) {tree.remove(keyBinarySearchTree); refreshScreenStrings(tree, panel);}
			}
		
		//Retorna uma mensagem caso � �rvore esteja vazia.	
		} else {JOptionPane.showMessageDialog(null, "� �rvore est� vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Exibi uma tela de op��es ao clicar nos elementos da �rvore.
	public void buttonManagerScreenIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Cria uma janela de op��es.
		
		Object[] options = {"Adicionar", "Remover"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Op��o: ", 
				"M�todos �rvore Bin�ria de Busca", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual m�todo ser� chamado de acordo com a op��o escolhida.
		
		if(option == 0) {putBinarySearchTreeIntegers(tree, keyBinarySearchTree, panel);} //Adicionar na �rvore, usando a mesma chave.
		else {removeBinarySearchTreeIntegers(tree, keyBinarySearchTree, panel);} //Remove da �rvore, usando a mesma chave.
	}	
	
	//Exibi uma tela de op��es ao clicar nos elementos da �rvore.
	public void buttonManagerScreenStrings(BinarySearchTree<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Cria uma janela de op��es.
		
		Object[] options = {"Adicionar", "Remover"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Op��o: ", 
				"M�todos �rvore Bin�ria de Busca", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual m�todo ser� chamado de acordo com a op��o escolhida.
		
		if(option == 0) {putBinarySearchTreeStrings(tree, keyBinarySearchTree, panel);} //Adicionar na �rvore, usando a mesma chave.
		else {removeBinarySearchTreeStrings(tree, keyBinarySearchTree, panel);} //Remove da �rvore, usando a mesma chave.
	}
	
	//Busca na �rvore o elemento da chave informada e exibe na tela.
	public void getBinarySearchTreeIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree) {
		
		if(!tree.isEmpty()) {
		
			//Cria uma janela para coletar os elementos que ser�o consultados na �rvore bin�ria de busca.
			
			JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
			
			//Cria��o dos botoes da janela.
			
			panelEntry.add(new JLabel("Chave: "));
			
			JTextField key = new JTextField();
			
			//Configura��o dos botoes da janela.
			
			panelEntry.add(key);		
			
			Object[] options = {"OK", "Cancelar"};
			
			int option = JOptionPane.showOptionDialog(
					
					this.getParent(), 
					panelEntry, 
					"Insira a Chave do Elemento:", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					options,
					options				
			);
			
			if(option == 0) {
				
				try { //Trata uma possivel exce��o.
					
					//Coleta o elemento da chave inserida.
					
					ArrayList<JComponent> find = tree.get(Integer.parseInt(key.getText()));
					
					if(find != null) {								
					
						//Altera a cor do elemento encontrado.
						
						((JButton)find.get(1)).setBackground(Color.LIGHT_GRAY);
						
						//Exibe a chave e o elemento na tela.
						
						JOptionPane.showMessageDialog(null, "Chave [" + ((JButton)find.get(0)).getText() +"], Elemento[" + ((JButton)find.get(1)).getText() + "]", "Consulta de Elemento", JOptionPane.INFORMATION_MESSAGE);
						
						//Retorna para a cor padr�o.
						
						((JButton)find.get(1)).setBackground(null);
					
					//Retorna uma mensagem caso a chave n�o seja encontrada.	
					} else {JOptionPane.showMessageDialog(null, "A chave n�o foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}				
				}	
								
				//Libera um mensagem de erro.
				catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
				
				//Libera um mensagem de erro.
				catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
			}
			
		//Retorna uma mensagem caso � �rvore esteja vazia.	
		} else {JOptionPane.showMessageDialog(null, "� �rvore est� vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);}
	}	
	
	//Busca na �rvore o elemento da chave informada e exibe na tela.
	public void getBinarySearchTreeStrings(BinarySearchTree<String, ArrayList<JComponent>> tree) {
		
		if(!tree.isEmpty()) {
		
			//Cria uma janela para coletar os elementos que ser�o consultados na �rvore bin�ria de busca.
			
			JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
			
			//Cria��o dos botoes da janela.
			
			panelEntry.add(new JLabel("Chave: "));
			
			JTextField key = new JTextField();
			
			//Configura��o dos botoes da janela.
			
			panelEntry.add(key);		
			
			Object[] options = {"OK", "Cancelar"};
			
			int option = JOptionPane.showOptionDialog(
					
					this.getParent(), 
					panelEntry, 
					"Insira a Chave do Elemento:", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					options,
					options				
			);
			
			if(option == 0) {
				
				try { //Trata uma possivel exce��o.
					
					//Coleta o elemento da chave inserida.
					
					ArrayList<JComponent> find = tree.get(key.getText());
					
					if(find != null) {								
					
						//Altera a cor do elemento encontrado.
						
						((JButton)find.get(1)).setBackground(Color.LIGHT_GRAY);
						
						//Exibe a chave e o elemento na tela.
						
						JOptionPane.showMessageDialog(null, "Chave [" + ((JButton)find.get(0)).getText() +"], Elemento[" + ((JButton)find.get(1)).getText() + "]", "Consulta de Elemento", JOptionPane.INFORMATION_MESSAGE);
						
						//Retorna para a cor padr�o.
						
						((JButton)find.get(1)).setBackground(null);
					
					//Retorna uma mensagem caso a chave n�o seja encontrada.	
					} else {JOptionPane.showMessageDialog(null, "A chave n�o foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}				
				}	
								
				//Libera um mensagem de erro.
				catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
				
				//Libera um mensagem de erro.
				catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
			}
			
		//Retorna uma mensagem caso � �rvore esteja vazia.	
		} else {JOptionPane.showMessageDialog(null, "� �rvore est� vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);}
	}	

	//Recarrega os componentes da janela.
	public void refreshScreenIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree, JPanel panel) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();		
		
		//Cria um painel para conter a tabela da �rvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da �rvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();		

		//Percorre os nodos da �rvore e adiciona na tabela de exibi��o.
		
		int visits = 0; //Armazena a quantidade de nodos percorridos.
		
		for(Position<Entry<Integer, ArrayList<JComponent>>> position :tree.positionsInorder()) {
			
			if(position.element() != null) {
				
				//Configura a posi��o da celula da tabela que o componente ser� inserido.
				
				gridTree.gridx = visits;
				gridTree.gridy = tree.depth(tree, position);
				
				//Adiciona na tabela de exibi��o.
				
				panelTree.add(position.element().getValue().get(2), gridTree); 
				
				visits++; //Soma-se +1 a quantidade de nodos percorridos.
			}
		}		
		
		//Adiciona a janela com a tabela da �rvore na janela de exibi��o.
		
		panel.add(panelTree);		
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();		
	}	

	//Recarrega os componentes da janela.
	public void refreshScreenStrings(BinarySearchTree<String, ArrayList<JComponent>> tree, JPanel panel) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();		
		
		//Cria um painel para conter a tabela da �rvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da �rvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();		

		//Percorre os nodos da �rvore e adiciona na tabela de exibi��o.
		
		int visits = 0; //Armazena a quantidade de nodos percorridos.
		
		for(Position<Entry<String, ArrayList<JComponent>>> position :tree.positionsInorder()) {
			
			if(position.element() != null) {
				
				//Configura a posi��o da celula da tabela que o componente ser� inserido.
				
				gridTree.gridx = visits;
				gridTree.gridy = tree.depth(tree, position);
				
				//Adiciona na tabela de exibi��o.
				
				panelTree.add(position.element().getValue().get(2), gridTree); 
				
				visits++; //Soma-se +1 a quantidade de nodos percorridos.
			}
		}		
		
		//Adiciona a janela com a tabela da �rvore na janela de exibi��o.
		
		panel.add(panelTree);		
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}
	
}