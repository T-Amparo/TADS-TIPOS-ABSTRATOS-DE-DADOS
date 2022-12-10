package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Fontes.HeapPriorityQueue;
import TADS.TAD_Lista.TAD_Lista_Arranjo.Fontes.ArrayIndexList;
import TADS.TAD_Mapa.Excecoes.InvalidKeyException;
import TADS.TAD_Mapa.Fontes.HashTableMap;
import TADS.TAD_Mapa.Interfaces.Entry;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class HashTableMapScreen extends JFrame {
	
	HashTableMap<String, ArrayIndexList<JComponent>> map;
	
	public HashTableMapScreen() {
		
		//Configurações da janela.
		
		super("Mapa");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Criação do texto de apresentação do TAD.		
		
		String textMap = "\n=================================Introdução==========================================="
					
							+ "\n\nUm Mapa é um Tipo Abstrato de Dados que permite armazenar elementos que podem ser "
							+ "\nlocalizados usando chaves, as chaves são os endereços de cada elemento, "
							+ "\ne dentro de um mapa não podem existir chaves repetidas. Para garantir a unicidade "
							+ "\nde cada chave  o mapa faz uso de uma tabela hash."


							+ "\n\n==================================Métodos==========================================="
							
							+ "\n\nMétodos básicos do Mapa:" +						
							
							"\n\nInserir/put: O elemento será inserido no mapa juntamente com uma chave." + 
							"\n\nRemover/remove: O elemento que tiver a chave correspondente será removido do mapa." + 
							"\n\nConsultar/get: O elemento que tiver a chave correspondente será encontrado no mapa." + 

							"\n\n================================Implementação========================================="
							
							+ "\n\nPor questões de desenvolvimento a implementação do Mapa trata-se de uma mapa de"
							+ "\nStrings, permitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
							+ "\nnúmeros como Strings. Excerto para as chaves que deverão ser predefinidas.";	

		
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextMap = new JTextArea(textMap);
		
		//Configuração da caixa de texto.
		
		boxTextMap.setEditable(false);
		boxTextMap.setPreferredSize(new Dimension(550, 600));	
		
		//Criação de botoes para a janela.
		
		JButton createMap = new JButton("Criar Mapa");
		
		//Insercão dos componentes na janela.
		
		add(boxTextMap);
		add(createMap);
		
		//Configuração dos botoes da janela.
		
		createMap.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createMap();}});
	}	
	
	//Cria um mapa e uma tela de representação para o mesmo.
	public void createMap() {
		
		map = new HashTableMap<String, ArrayIndexList<JComponent>>(); //Inicia o mapa.
		
		createRepresentationScreen("Mapa", map); //Cria uma janela para o mapa.				
	}	
	
	//Cria uma janela para representar um mapa.
	public void createRepresentationScreen(String typeMap, HashTableMap<String, ArrayIndexList<JComponent>> map) {
		
		//Cria uma janela para representar o mapa.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeMap);
		screen.setSize(500, 145);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes do mapa.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 145));
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem put = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem get = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putMap(map, panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeMap(map, panel);}});
		get.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getMap(map, panel);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);
		menu.add(get);		
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);
	}	
	
	//Cria uma janela para adiciona elementos ao mapa.
	public void putMap(HashTableMap<String, ArrayIndexList<JComponent>> map, JPanel panel) {		
		
		//Cria uma janela para coletar os elementos que serão adicionados no mapa.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Criação dos botoes da janela.
		
		panelEntry.add(new JLabel("Chave: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField key = new JTextField();
		JTextField element = new JTextField();
		
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
			
			try { //Trata uma possivel exceção.
				
				//Cria uma lista arranjo para conter os componentes de exibição de uma dada posição da fila de prioridade.
				
				ArrayIndexList<JComponent> components = new ArrayIndexList<JComponent>();
				
				//Criação dos componentes que serão inseridos no mapa com os elementos inseridos na tela de adição.
				
				JButton elementMap = new JButton(element.getText());				
				JLabel keyMap = new JLabel(key.getText(), SwingConstants.CENTER);
				
				JPanel panelMap = new JPanel(new GridLayout(2, 1));
				
				//Configuração dos componentes criados.
				
				panelMap.add(elementMap);
				panelMap.add(keyMap);				
				
				//Configuração dos elementos do painel.
				
				elementMap.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(map, key.getText(), panel);}});
				
				//Adiciona os componentes criado na lista arranjo de componentes.
				
				components.add(0, elementMap);
				components.add(1, keyMap);
				components.add(2, panelMap);
				
				//Adiciona a lista de componentes no mapa.
				
				map.put(key.getText(), components);
				
			//Libera uma mensagem sobre o erro.	
			} catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
			
			//Libera uma mensagem sobre o erro.
			} catch(IllegalArgumentException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}	
			
			refreshScreen(panel);
		}		
	}	
	
	//Cria uma janela para consultar elementos do mapa.
	public void getMap(HashTableMap<String, ArrayIndexList<JComponent>> map, JPanel panel) {		
		
		//Cria uma janela para coletar a chave que será consultada no mapa.
		
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
				
				if(!map.isEmpty()) {
					
					String keyElement = key.getText();
					String element = ((JButton)map.get(keyElement).get(0)).getText();
					
					JOptionPane.showMessageDialog(null, "Chave:["+ keyElement + "], " + "Elemento [" + element + "]", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);				

					
				} else {JOptionPane.showMessageDialog(null, "O mapa está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);}
				
			//Libera uma mensagem sobre o erro.	
			} catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
			
			//Libera uma mensagem sobre o erro.	
			} catch(IllegalArgumentException error) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);
			
			//Libera uma mensagem sobre o erro.	
			} catch(Exception error) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}	
	
	//Cria uma janela para remover elementos do mapa.
	public void removeMap(HashTableMap<String, ArrayIndexList<JComponent>> map, JPanel panel) {		
		
		//Cria uma janela para coletar a chave do elemento que será removido do mapa.
		
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
				
				if(!map.isEmpty()) {
					
					//Remove o elemento da lista de acordo com a chave inserida pelo usuario.
					
					if(map.remove(key.getText()) == null) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);} 
					
					//Recarrega a janela.
					
					refreshScreen(panel);
					
				} else {JOptionPane.showMessageDialog(null, "O mapa está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);}
				
			//Libera uma mensagem sobre o erro.	
			} catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
			
			//Libera uma mensagem sobre o erro.	
			} catch(IllegalArgumentException error) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);
			
			//Libera uma mensagem sobre o erro.	
			} catch(Exception error) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}		
		}		
	}
	
	//Exibi uma tela de opções ao clicar no elementos da lista.
	public void buttonManagerScreen(HashTableMap<String, ArrayIndexList<JComponent>> map, String key, JPanel panel) {		
		
		//Cria uma janela de opções.
		
		Object[] options = {"Adicionar", "Remover"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Opção: ", 
				"Métodos Lista Arranjo", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		if(option == 0) {			
			
			//Cria uma janela para coletar o novo elemento que será removido no lugar da chave do elemento atual.
			
			JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
			
			//Criação dos botoes da janela.
			
			panelEntry.add(new JLabel("Insira um Novo Elemento: "));
			
			JTextField element = new JTextField();
			
			//Configuração dos botoes da janela.
			
			panelEntry.add(element);		
			
			Object[] optionsAdd = {"OK", "Cancelar"};
			
			option = JOptionPane.showOptionDialog(
					
					this.getParent(), 
					panelEntry, 
					"Insira um Novo Elemento:", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					optionsAdd,
					optionsAdd				
			);
			
			if(option == 0) {
				
				try { //Trata uma possivel exceção.
					
					//Cria uma lista arranjo para conter os componentes de exibição de uma dada posição da fila de prioridade.
					
					ArrayIndexList<JComponent> components = new ArrayIndexList<JComponent>();
					
					//Criação dos componentes que serão inseridos no mapa com os elementos inseridos na tela de adição.
					
					JButton elementMap = new JButton(element.getText());				
					JLabel keyMap = new JLabel(key, SwingConstants.CENTER);
					
					JPanel panelMap = new JPanel(new GridLayout(2, 1));
					
					//Configuração dos componentes criados.
					
					panelMap.add(elementMap);
					panelMap.add(keyMap);				
					
					//Configuração dos elementos do painel.
					
					elementMap.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(map, key, panel);}});
					
					//Adiciona os componentes criado na lista arranjo de componentes.
					
					components.add(0, elementMap);
					components.add(1, keyMap);
					components.add(2, panelMap);
					
					//Adiciona a lista de componentes no mapa.
					
					map.put(key, components);				
				
				//Libera uma mensagem sobre o erro.	
				} catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
				
				//Libera uma mensagem sobre o erro.
				} catch(IllegalArgumentException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}				
			}
			
		} else {
			
			try { //Trata uma possivel exceção.
				
				if(!map.isEmpty()) {
					
					//Remove o elemento da lista de acordo com a chave inserida pelo usuario.
					
					if(map.remove(key) == null) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}
					
				} else {JOptionPane.showMessageDialog(null, "O mapa está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);}
				
			//Libera uma mensagem sobre o erro.	
			} catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
			
			//Libera uma mensagem sobre o erro.	
			} catch(IllegalArgumentException error) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);
			
			//Libera uma mensagem sobre o erro.	
			} catch(Exception error) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}		
		}		
		
		//Recarrega a janela.
		
		refreshScreen(panel);
	}
	
	//Ordena o mapa usando uma fila de prioridade.
	public HeapPriorityQueue<String, ArrayIndexList<JComponent>> sortMap(HashTableMap<String, ArrayIndexList<JComponent>> map) {
		
		//Cria uma fila de prioridade vazia.
		
		HeapPriorityQueue<String, ArrayIndexList<JComponent>> queue = new HeapPriorityQueue<String, ArrayIndexList<JComponent>>();
		
		//Percorre o mapa adicionando os elementos na fila de prioridade.
		
		for(Entry<String, ArrayIndexList<JComponent>> entry : map.entrySet()) {queue.insert(entry.getKey(), entry.getValue());}
		
		return queue; //Retorna a fila de prioridade.
	}

	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel panel) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();
		
		//Ordena o mapa usando uma fila de prioridade.
		HeapPriorityQueue<String, ArrayIndexList<JComponent>> queue = sortMap(map);
		
		//Percorre a fila de prioridade.
		
		while(!queue.isEmpty()) {panel.add(((JPanel)queue.removeMin().getValue().get(2)));} //Adiciona os elementos do mapa na tela.
		
		//Métodos da janela para recarrega-lá.
		panel.revalidate();
		panel.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}
	
}