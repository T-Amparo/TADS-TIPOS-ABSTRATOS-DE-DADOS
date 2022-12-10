package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes.InvalidEntryException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Excecoes.InvalidKeyException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Fontes.AVLTreeMap;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces.Entry;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_AVL.Interfaces.Position;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class AVLTreeMapScreen extends JFrame {	
	
	Object tree;
	
	public AVLTreeMapScreen() {
		
		//Configura��es da janela.
		
		super("�rvore AVL");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Cria��o do texto de apresenta��o do TAD.		
		
		String textTree = "\n=================================Introdu��o==========================================="
				
							+ "\n\nA �rvore AVL � um Tipo Abstrato de Dados semelhante a �rvore Bin�ria de Busca com a diferen�a\n"
							+ "de uma caracter�stica nova, o balanceamento, a �rvore AVL tem todos os seus nodos internos"
							+ "\nbalanceados, isso quer dizer que nenhum dos nodos internos da �rvore tem diferen�a maior que"
							+ "\num, entre as alturas de seus filhos."
							
							+ "\n\nPor�m sempre que uma a��o � realizada na �rvore AVL pode acabar violando a sua propriedade de"
							+ "\nbalanceamento, nesse caso � necess�rio restaurar o balanceamento da �rvore, para isso � "
							+ "\nutilizado um algoritmo de reestrutura��o conhecido como o m�todo de reestrutura��o de trinodo,"
							+ "\nque restaura o balanceamento da �rvore AVL."							

							+ "\n\n==================================M�todos==========================================="
							
							+ "\n\nM�todos b�sicos da �rvore AVL:" +							
							
							"\n\nInserir/put: O elemento ser� inserido na �rvore de acordo com a chave do elemento." + 
							"\n\nRemover/remove: O elemento que tiver a chave correspondente ser� removido da �rvore." + 
							"\n\nConsultar/get: O elemento que tiver a chave correspondente ser� encontrado na �rvore." + 
							
							"\n\nAo final de cada opera��o o m�todo de restrutura��o ser� chamado para balancear a �rvore." +

							"\n\n================================Implementa��o========================================="
							
							+ "\n\nPor quest�es de desenvolvimento a implementa��o da �rvore AVL trata-se de uma �rvore de"
							+ "\nStrings, permitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
							+ "\nn�meros como Strings. Excerto para as chaves que dever�o ser predefinidas."
							
							+ "\n\nAs opera��es na �rvore AVL poder�o ser realizadas a partir dos nodos da mesma.";	
		
		//Inser��o do texto em uma caixa de texto para exibi��o.
		
		JTextArea boxTextTree = new JTextArea(textTree);
		
		//Configura��o da caixa de texto.
		
		boxTextTree.setEditable(false);
		boxTextTree.setPreferredSize(new Dimension(550, 600));	
		
		//Cria��o de botoes para a janela.
		
		JButton createTree = new JButton("Criar �rvore AVL");
		
		//Inserc�o dos componentes na janela.
		
		add(boxTextTree);		
		add(createTree);
		
		//Configura��o dos botoes da janela.
		
		createTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createAVLTreeMap();}});
	}	
	
	@SuppressWarnings("unchecked")
	public void createAVLTreeMap() {		
		
		//Cria � �rvore AVL segundo o tipo se chave escolhida pelo usu�rio.
		
		Object[] types = {"Inteiros", "Strings"};			

		//Janela para coletar o tipo de chave que a �rvore AVL ir� armazenar.
		
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
			
			tree = new AVLTreeMap<Integer, ArrayList<JComponent>>();
			
			createRepresentationScreenIntegers("�rvore AVL", ((AVLTreeMap<Integer, ArrayList<JComponent>>)tree));
			
		} else {			
			
			tree = new AVLTreeMap<String, ArrayList<JComponent>>();
			
			createRepresentationScreenStrings("�rvore AVL", ((AVLTreeMap<String, ArrayList<JComponent>>)tree));			
		}		
	}	
	
	//Cria uma janela para representar uma �rvore AVL.
	public void createRepresentationScreenIntegers(String typeTree, AVLTreeMap<Integer, ArrayList<JComponent>> tree) {		
		
		//Cria uma janela para representar uma �rvore AVL.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeTree);
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da �rvore AVL.
		
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
		
		//Configura��o dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putBinarySearchTreeIntegers(tree, "", panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeBinarySearchTreeIntegers(tree, "", panel);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);	
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);		
	}	
	
	//Cria uma janela para representar uma �rvore AVL.
	public void createRepresentationScreenStrings(String typeTree, AVLTreeMap<String, ArrayList<JComponent>> tree) {		
		
		//Cria uma janela para representar uma �rvore AVL.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeTree);
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da �rvore AVL.
		
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
		
		//Configura��o dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putBinarySearchTreeStrings(tree, "/#/", panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeBinarySearchTreeStrings(tree, "/#/", panel);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);	
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);		
	}	
	
	//Adiciona um item na �rvore bin�ria de busca e na janela de representa��o da �rvore.
	public void putBinarySearchTreeIntegers(AVLTreeMap<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void putBinarySearchTreeStrings(AVLTreeMap<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void removeBinarySearchTreeIntegers(AVLTreeMap<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void removeBinarySearchTreeStrings(AVLTreeMap<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void buttonManagerScreenIntegers(AVLTreeMap<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void buttonManagerScreenStrings(AVLTreeMap<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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

	//Recarrega os componentes da janela.
	public void refreshScreenIntegers(AVLTreeMap<Integer, ArrayList<JComponent>> tree, JPanel panel) {
		
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
	public void refreshScreenStrings(AVLTreeMap<String, ArrayList<JComponent>> tree, JPanel panel) {
		
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