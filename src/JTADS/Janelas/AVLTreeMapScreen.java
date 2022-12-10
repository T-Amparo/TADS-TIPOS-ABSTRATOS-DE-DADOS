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
		
		//Configurações da janela.
		
		super("Árvore AVL");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Criação do texto de apresentação do TAD.		
		
		String textTree = "\n=================================Introdução==========================================="
				
							+ "\n\nA Árvore AVL é um Tipo Abstrato de Dados semelhante a Árvore Binária de Busca com a diferença\n"
							+ "de uma característica nova, o balanceamento, a árvore AVL tem todos os seus nodos internos"
							+ "\nbalanceados, isso quer dizer que nenhum dos nodos internos da árvore tem diferença maior que"
							+ "\num, entre as alturas de seus filhos."
							
							+ "\n\nPorém sempre que uma ação é realizada na árvore AVL pode acabar violando a sua propriedade de"
							+ "\nbalanceamento, nesse caso é necessário restaurar o balanceamento da árvore, para isso é "
							+ "\nutilizado um algoritmo de reestruturação conhecido como o método de reestruturação de trinodo,"
							+ "\nque restaura o balanceamento da árvore AVL."							

							+ "\n\n==================================Métodos==========================================="
							
							+ "\n\nMétodos básicos da Árvore AVL:" +							
							
							"\n\nInserir/put: O elemento será inserido na árvore de acordo com a chave do elemento." + 
							"\n\nRemover/remove: O elemento que tiver a chave correspondente será removido da árvore." + 
							"\n\nConsultar/get: O elemento que tiver a chave correspondente será encontrado na árvore." + 
							
							"\n\nAo final de cada operação o método de restruturação será chamado para balancear a árvore." +

							"\n\n================================Implementação========================================="
							
							+ "\n\nPor questões de desenvolvimento a implementação da Árvore AVL trata-se de uma árvore de"
							+ "\nStrings, permitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
							+ "\nnúmeros como Strings. Excerto para as chaves que deverão ser predefinidas."
							
							+ "\n\nAs operações na Árvore AVL poderão ser realizadas a partir dos nodos da mesma.";	
		
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextTree = new JTextArea(textTree);
		
		//Configuração da caixa de texto.
		
		boxTextTree.setEditable(false);
		boxTextTree.setPreferredSize(new Dimension(550, 600));	
		
		//Criação de botoes para a janela.
		
		JButton createTree = new JButton("Criar Árvore AVL");
		
		//Insercão dos componentes na janela.
		
		add(boxTextTree);		
		add(createTree);
		
		//Configuração dos botoes da janela.
		
		createTree.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createAVLTreeMap();}});
	}	
	
	@SuppressWarnings("unchecked")
	public void createAVLTreeMap() {		
		
		//Cria à árvore AVL segundo o tipo se chave escolhida pelo usuário.
		
		Object[] types = {"Inteiros", "Strings"};			

		//Janela para coletar o tipo de chave que a árvore AVL irá armazenar.
		
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
			
			createRepresentationScreenIntegers("Árvore AVL", ((AVLTreeMap<Integer, ArrayList<JComponent>>)tree));
			
		} else {			
			
			tree = new AVLTreeMap<String, ArrayList<JComponent>>();
			
			createRepresentationScreenStrings("Árvore AVL", ((AVLTreeMap<String, ArrayList<JComponent>>)tree));			
		}		
	}	
	
	//Cria uma janela para representar uma árvore AVL.
	public void createRepresentationScreenIntegers(String typeTree, AVLTreeMap<Integer, ArrayList<JComponent>> tree) {		
		
		//Cria uma janela para representar uma árvore AVL.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeTree);
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da árvore AVL.
		
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
		
		//Configuração dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putBinarySearchTreeIntegers(tree, "", panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeBinarySearchTreeIntegers(tree, "", panel);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);	
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);		
	}	
	
	//Cria uma janela para representar uma árvore AVL.
	public void createRepresentationScreenStrings(String typeTree, AVLTreeMap<String, ArrayList<JComponent>> tree) {		
		
		//Cria uma janela para representar uma árvore AVL.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeTree);
		screen.setSize(600, 500);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da árvore AVL.
		
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
		
		//Configuração dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putBinarySearchTreeStrings(tree, "/#/", panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeBinarySearchTreeStrings(tree, "/#/", panel);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);	
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);		
	}	
	
	//Adiciona um item na árvore binária de busca e na janela de representação da árvore.
	public void putBinarySearchTreeIntegers(AVLTreeMap<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void putBinarySearchTreeStrings(AVLTreeMap<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void removeBinarySearchTreeIntegers(AVLTreeMap<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void removeBinarySearchTreeStrings(AVLTreeMap<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void buttonManagerScreenIntegers(AVLTreeMap<Integer, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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
	public void buttonManagerScreenStrings(AVLTreeMap<String, ArrayList<JComponent>> tree, String keyBinarySearchTree, JPanel panel) {		
		
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

	//Recarrega os componentes da janela.
	public void refreshScreenIntegers(AVLTreeMap<Integer, ArrayList<JComponent>> tree, JPanel panel) {
		
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
	public void refreshScreenStrings(AVLTreeMap<String, ArrayList<JComponent>> tree, JPanel panel) {
		
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