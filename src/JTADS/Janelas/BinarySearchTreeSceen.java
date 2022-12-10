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
		
		//Configurações da janela.
		
		super("Árvore Binária de Busca");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Criação do texto de apresentação do TAD.		
		
		String textTree = "\n=================================Introdução==========================================="
							
							+ "\n\nA Árvore Binária de Busca é um Tipo Abstrato de Dados que tem por sua estrutura uma Árvore "
							+ "\nBinária onde apenas os nodos internos armazenam os elementos juntos com suas chaves. Cada "
							+ "\nnodo dentro da árvore é organizado de acordo com sua chave, os nodos que possuem uma chave "
							+ "\nmenor que a raiz são inseridos na subárvore a esquerda e os nodos que possuem chave maior que "
							+ "\na raiz são inseridos na subarvore a direita, com essa abordagem é possível pesquisar uma chave "
							+ "\ncomparando os nodos internos." 

							+ "\n\n==================================Métodos==========================================="
							
							+ "\n\nMétodos básicos da Árvore Binária de Busca:" +							
							
							"\n\nInserir/put: O elemento será inserido na árvore de acordo com a chave do elemento." + 
							"\n\nRemover/remove: O elemento que tiver a chave correspondente será removido da árvore." + 
							"\n\nConsultar/get: O elemento que tiver a chave correspondente será encontrado na árvore." + 

							"\n\n================================Implementação========================================="
							
							+ "\n\nPor questões de desenvolvimento a implementação da Árvore Binária de Busca trata-se de uma"
							+ "\nárvore de Strings, permitindo uso de todo e qualquer caractere da tabela ASCII e tratando a"
							+ "\nentrada de números como Strings. Excerto para as chaves que deverão ser predefinidas."
							
							+ "\n\nAs operações na Árvore Binária de Busca poderão ser realizadas a partir dos nodos da mesma.";	
		
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextTree = new JTextArea(textTree);
		
		//Configuração da caixa de texto.
		
		boxTextTree.setEditable(false);
		boxTextTree.setPreferredSize(new Dimension(550, 600));	
		
		//Criação de botoes para a janela.
		
		JButton createTree = new JButton("Criar Árvore Binária de Busca");
		
		//Insercão dos componentes na janela.
		
		add(boxTextTree);
		add(createTree);
		
		//Configuração dos botoes da janela.
		
		createTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createBinarySearchTree();}});
	}
	
	@SuppressWarnings("unchecked")
	public void createBinarySearchTree() {		
		
		//Cria à árvore binária de busca segundo o tipo se chave escolhida pelo usuário.
		
		Object[] types = {"Inteiros", "Strings"};			

		//Janela para coletar o tipo de chave que a árvore binária de busca irá armazenar.
		
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
			
			createRepresentationScreenIntegers("Árvore Binária de Busca", ((BinarySearchTree<Integer, ArrayList<JComponent>>)tree));
			
		} else {			
			
			tree = new BinarySearchTree<String, ArrayList<JComponent>>();
			
			createRepresentationScreenStrings("Árvore Binária de Busca", ((BinarySearchTree<String, ArrayList<JComponent>>)tree));			
		}		
	}
	
	//Cria uma janela para representar uma árvore binária de busca.
	public void createRepresentationScreenIntegers(String typeTree, BinarySearchTree<Integer, ArrayList<JComponent>> tree) {		
		
		//Cria uma janela para representar uma árvore binária de busca.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeTree);
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da árvore binária de busca.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(600, 500));
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem put = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem get = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putBinarySearchTreeIntegers(tree, "", panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeBinarySearchTreeIntegers(tree, "", panel);}});
		get.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getBinarySearchTreeIntegers(tree);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);
		menu.add(get);		
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);		
	}	
	
	//Cria uma janela para representar uma árvore binária de busca.
	public void createRepresentationScreenStrings(String typeTree, BinarySearchTree<String, ArrayList<JComponent>> tree) {		
		
		//Cria uma janela para representar uma árvore binária de busca.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeTree);
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da árvore binária de busca.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(600, 500));
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem put = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem get = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putBinarySearchTreeStrings(tree, "/#/", panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeBinarySearchTreeStrings(tree, "/#/", panel);}});
		get.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getBinarySearchTreeStrings(tree);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);
		menu.add(get);		
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);		
	}
	
	//Adiciona um item na árvore binária de busca e na janela de representação da árvore.
	public void putBinarySearchTreeIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Cria uma lista arranjo para conter os componentes de exibição de uma dada posição da árvore binária de busca.
		
		ArrayList<JComponent> components = new ArrayList<JComponent>(3);
		
		//Cria uma janela para coletar os elementos que serão adicionados na árvore binária de busca.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Criação dos botoes da janela.
		
		panelEntry.add(new JLabel("Chave: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField key;
		JTextField element = new JTextField();		
		
		if(keyBinarySearchTree == "") {key = new JTextField();}
		else {key = new JTextField(keyBinarySearchTree);}
		
		//Configuração dos botoes da janela.
		
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
			
			//Criação do painel que será inserido na árvore binária de busca com o painel inserido na tela de adição.
			
			JPanel panelElement = new JPanel(new GridLayout(1, 2));		
			
			//Criação dos elementos do painel, que armazenarão os dados de entrada do usuário.
			
			JButton treeElement = new JButton(String.valueOf(element.getText()));
			JButton treeKey = new JButton(String.valueOf(key.getText()));			
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, treeKey);
			components.add(1, treeElement);
			components.add(2, panelElement);
			
			//Configuração dos elementos do painel.
			
			treeElement.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenIntegers(tree, ((JButton)components.get(0)).getText(), panel);}});
			treeKey.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenIntegers(tree, ((JButton)components.get(0)).getText(), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(treeKey);
			panelElement.add(treeElement);			
			
			try { //Trata uma possivel exceção.				
				
				//Adiciona o painel criado na árvore binária de busca.
				
				tree.put(Integer.parseInt(key.getText()), components);
				
				//Recarrega a janela.
				
				refreshScreenIntegers(tree, panel);
			}
			
			//Libera um mensagem de erro.
			catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inválida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}	
	
	//Adiciona um item na árvore binária de busca e na janela de representação da árvore.
	public void putBinarySearchTreeStrings(BinarySearchTree<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Cria uma lista arranjo para conter os componentes de exibição de uma dada posição da árvore binária de busca.
		
		ArrayList<JComponent> components = new ArrayList<JComponent>(3);
		
		//Cria uma janela para coletar os elementos que serão adicionados na árvore binária de busca.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Criação dos botoes da janela.
		
		panelEntry.add(new JLabel("Chave: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField key;
		JTextField element = new JTextField();		
		
		if(keyBinarySearchTree == "/#/") {key = new JTextField();}
		else {key = new JTextField(keyBinarySearchTree);}
		
		//Configuração dos botoes da janela.
		
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
			
			//Criação do painel que será inserido na árvore binária de busca com o painel inserido na tela de adição.
			
			JPanel panelElement = new JPanel(new GridLayout(1, 2));		
			
			//Criação dos elementos do painel, que armazenarão os dados de entrada do usuário.
			
			JButton treeElement = new JButton(String.valueOf(element.getText()));
			JButton treeKey = new JButton(String.valueOf(key.getText()));			
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, treeKey);
			components.add(1, treeElement);
			components.add(2, panelElement);
			
			//Configuração dos elementos do painel.
			
			treeElement.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenStrings(tree, ((JButton)components.get(0)).getText(), panel);}});
			treeKey.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenStrings(tree, ((JButton)components.get(0)).getText(), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(treeKey);
			panelElement.add(treeElement);			
			
			try { //Trata uma possivel exceção.				
				
				//Adiciona o painel criado na árvore binária de busca.
				
				tree.put(key.getText(), components);
				
				//Recarrega a janela.
				
				refreshScreenStrings(tree, panel);
			}
			
			//Libera um mensagem de erro.
			catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inválida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}
	
	//Remove um item da árvore binária de busca e da janela de representação da árvore.
	public void removeBinarySearchTreeIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Verifica se a árvore está vazia.
		
		if(!tree.isEmpty()) {				
			
			if(keyBinarySearchTree == "") {
	
				//Cria uma janela para coletar os elementos que serão removidos da árvore binária de busca.
				
				JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
				
				//Criação dos botoes da janela.
				
				panelEntry.add(new JLabel("Chave: "));
				
				JTextField key = new JTextField();
				
				//Configuração dos botoes da janela.
				
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
					
					//Trata uma possivel exceção.
					try {
						
						//Remove o componente da árvore binária de busca.
						
						if(tree.remove(Integer.parseInt(key.getText())) != null) {					
							
							//Recarrega a janela.
							
							refreshScreenIntegers(tree, panel);
						
						//Retorna uma mensagem caso a chave não seja encontrada.
						} else {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}				
					}
					
					//Libera um mensagem de erro.
					catch(InvalidEntryException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
					
					//Libera um mensagem de erro.
					catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inválida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
				}
				
			} else {			
				
				//Cria uma janela para coletar os elementos que serão removidos na árvore binária de busca.
				
				Object[] options = {"Continuar", "Cancelar"};
				
				int option = JOptionPane.showOptionDialog(
						
						this.getParent(), 
						"Remover elemento com chave [" + keyBinarySearchTree + "]", 
						"Remoção de Elementos", 
						JOptionPane.OK_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						options,
						options				
				);
				
				//Remove o componente da árvore binária de busca e atualiza a janela.
				
				if(option == 0) {tree.remove(Integer.parseInt(keyBinarySearchTree)); refreshScreenIntegers(tree, panel);}
			}
		
		//Retorna uma mensagem caso à árvore esteja vazia.
		} else {JOptionPane.showMessageDialog(null, "À árvore está vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);}
	}	
	
	//Remove um item da árvore binária de busca e da janela de representação da árvore.
	public void removeBinarySearchTreeStrings(BinarySearchTree<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Verifica se a árvore está vazia.
		
		if(!tree.isEmpty()) {				
			
			if(keyBinarySearchTree == "/#/") {
	
				//Cria uma janela para coletar os elementos que serão removidos da árvore binária de busca.
				
				JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
				
				//Criação dos botoes da janela.
				
				panelEntry.add(new JLabel("Chave: "));
				
				JTextField key = new JTextField();
				
				//Configuração dos botoes da janela.
				
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
					
					//Trata uma possivel exceção.
					try {
						
						//Remove o componente da árvore binária de busca.
						
						if(tree.remove(key.getText()) != null) {					
							
							//Recarrega a janela.
							
							refreshScreenStrings(tree, panel);
						
						//Retorna uma mensagem caso a chave não seja encontrada.
						} else {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}				
					}
					
					//Libera um mensagem de erro.
					catch(InvalidEntryException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
					
					//Libera um mensagem de erro.
					catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inválida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
				}
				
			} else {			
				
				//Cria uma janela para coletar os elementos que serão removidos da árvore binária de busca.
				
				Object[] options = {"Continuar", "Cancelar"};
				
				int option = JOptionPane.showOptionDialog(
						
						this.getParent(), 
						"Remover elemento com chave [" + keyBinarySearchTree + "]", 
						"Remoção de Elementos", 
						JOptionPane.OK_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						options,
						options				
				);
				
				//Remove o componente da árvore binária de busca e atualiza a janela.
				
				if(option == 0) {tree.remove(keyBinarySearchTree); refreshScreenStrings(tree, panel);}
			}
		
		//Retorna uma mensagem caso à árvore esteja vazia.	
		} else {JOptionPane.showMessageDialog(null, "À árvore está vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Exibi uma tela de opções ao clicar nos elementos da árvore.
	public void buttonManagerScreenIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Cria uma janela de opções.
		
		Object[] options = {"Adicionar", "Remover"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Opção: ", 
				"Métodos Árvore Binária de Busca", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual método será chamado de acordo com a opção escolhida.
		
		if(option == 0) {putBinarySearchTreeIntegers(tree, keyBinarySearchTree, panel);} //Adicionar na árvore, usando a mesma chave.
		else {removeBinarySearchTreeIntegers(tree, keyBinarySearchTree, panel);} //Remove da árvore, usando a mesma chave.
	}	
	
	//Exibi uma tela de opções ao clicar nos elementos da árvore.
	public void buttonManagerScreenStrings(BinarySearchTree<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
		//Cria uma janela de opções.
		
		Object[] options = {"Adicionar", "Remover"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Opção: ", 
				"Métodos Árvore Binária de Busca", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual método será chamado de acordo com a opção escolhida.
		
		if(option == 0) {putBinarySearchTreeStrings(tree, keyBinarySearchTree, panel);} //Adicionar na árvore, usando a mesma chave.
		else {removeBinarySearchTreeStrings(tree, keyBinarySearchTree, panel);} //Remove da árvore, usando a mesma chave.
	}
	
	//Busca na árvore o elemento da chave informada e exibe na tela.
	public void getBinarySearchTreeIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree) {
		
		if(!tree.isEmpty()) {
		
			//Cria uma janela para coletar os elementos que serão consultados na árvore binária de busca.
			
			JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
			
			//Criação dos botoes da janela.
			
			panelEntry.add(new JLabel("Chave: "));
			
			JTextField key = new JTextField();
			
			//Configuração dos botoes da janela.
			
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
				
				try { //Trata uma possivel exceção.
					
					//Coleta o elemento da chave inserida.
					
					ArrayList<JComponent> find = tree.get(Integer.parseInt(key.getText()));
					
					if(find != null) {								
					
						//Altera a cor do elemento encontrado.
						
						((JButton)find.get(1)).setBackground(Color.LIGHT_GRAY);
						
						//Exibe a chave e o elemento na tela.
						
						JOptionPane.showMessageDialog(null, "Chave [" + ((JButton)find.get(0)).getText() +"], Elemento[" + ((JButton)find.get(1)).getText() + "]", "Consulta de Elemento", JOptionPane.INFORMATION_MESSAGE);
						
						//Retorna para a cor padrão.
						
						((JButton)find.get(1)).setBackground(null);
					
					//Retorna uma mensagem caso a chave não seja encontrada.	
					} else {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}				
				}	
								
				//Libera um mensagem de erro.
				catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
				
				//Libera um mensagem de erro.
				catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inválida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
			}
			
		//Retorna uma mensagem caso à árvore esteja vazia.	
		} else {JOptionPane.showMessageDialog(null, "À árvore está vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);}
	}	
	
	//Busca na árvore o elemento da chave informada e exibe na tela.
	public void getBinarySearchTreeStrings(BinarySearchTree<String, ArrayList<JComponent>> tree) {
		
		if(!tree.isEmpty()) {
		
			//Cria uma janela para coletar os elementos que serão consultados na árvore binária de busca.
			
			JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
			
			//Criação dos botoes da janela.
			
			panelEntry.add(new JLabel("Chave: "));
			
			JTextField key = new JTextField();
			
			//Configuração dos botoes da janela.
			
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
				
				try { //Trata uma possivel exceção.
					
					//Coleta o elemento da chave inserida.
					
					ArrayList<JComponent> find = tree.get(key.getText());
					
					if(find != null) {								
					
						//Altera a cor do elemento encontrado.
						
						((JButton)find.get(1)).setBackground(Color.LIGHT_GRAY);
						
						//Exibe a chave e o elemento na tela.
						
						JOptionPane.showMessageDialog(null, "Chave [" + ((JButton)find.get(0)).getText() +"], Elemento[" + ((JButton)find.get(1)).getText() + "]", "Consulta de Elemento", JOptionPane.INFORMATION_MESSAGE);
						
						//Retorna para a cor padrão.
						
						((JButton)find.get(1)).setBackground(null);
					
					//Retorna uma mensagem caso a chave não seja encontrada.	
					} else {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}				
				}	
								
				//Libera um mensagem de erro.
				catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
				
				//Libera um mensagem de erro.
				catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inválida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
			}
			
		//Retorna uma mensagem caso à árvore esteja vazia.	
		} else {JOptionPane.showMessageDialog(null, "À árvore está vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);}
	}	

	//Recarrega os componentes da janela.
	public void refreshScreenIntegers(BinarySearchTree<Integer, ArrayList<JComponent>> tree, JPanel panel) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();		
		
		//Cria um painel para conter a tabela da árvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da árvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();		

		//Percorre os nodos da árvore e adiciona na tabela de exibição.
		
		int visits = 0; //Armazena a quantidade de nodos percorridos.
		
		for(Position<Entry<Integer, ArrayList<JComponent>>> position :tree.positionsInorder()) {
			
			if(position.element() != null) {
				
				//Configura a posição da celula da tabela que o componente será inserido.
				
				gridTree.gridx = visits;
				gridTree.gridy = tree.depth(tree, position);
				
				//Adiciona na tabela de exibição.
				
				panelTree.add(position.element().getValue().get(2), gridTree); 
				
				visits++; //Soma-se +1 a quantidade de nodos percorridos.
			}
		}		
		
		//Adiciona a janela com a tabela da árvore na janela de exibição.
		
		panel.add(panelTree);		
		
		//Métodos da janela para recarrega-lá.
		
		panel.revalidate();
		panel.repaint();		
	}	

	//Recarrega os componentes da janela.
	public void refreshScreenStrings(BinarySearchTree<String, ArrayList<JComponent>> tree, JPanel panel) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();		
		
		//Cria um painel para conter a tabela da árvore.
		
		JPanel panelTree = new JPanel(new GridBagLayout());
		
		//Cria a tabela da árvore.
		
		GridBagConstraints gridTree = new GridBagConstraints();		

		//Percorre os nodos da árvore e adiciona na tabela de exibição.
		
		int visits = 0; //Armazena a quantidade de nodos percorridos.
		
		for(Position<Entry<String, ArrayList<JComponent>>> position :tree.positionsInorder()) {
			
			if(position.element() != null) {
				
				//Configura a posição da celula da tabela que o componente será inserido.
				
				gridTree.gridx = visits;
				gridTree.gridy = tree.depth(tree, position);
				
				//Adiciona na tabela de exibição.
				
				panelTree.add(position.element().getValue().get(2), gridTree); 
				
				visits++; //Soma-se +1 a quantidade de nodos percorridos.
			}
		}		
		
		//Adiciona a janela com a tabela da árvore na janela de exibição.
		
		panel.add(panelTree);		
		
		//Métodos da janela para recarrega-lá.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}
	
}