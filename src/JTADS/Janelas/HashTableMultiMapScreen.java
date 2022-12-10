package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;
import java.util.Map.Entry;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Dicionario.Fontes.HashTableMultiMap;
import TADS.TAD_Lista.TAD_Lista_Nodos.Fontes.NodePositionList;
import TADS.TAD_Lista.TAD_Lista_Nodos.Interfaces.Position;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class HashTableMultiMapScreen extends JFrame {
	
	HashTableMultiMap<String, JButton> dict;
	NodePositionList<String> keys;
	
	public HashTableMultiMapScreen() {
		
		//Configurações da janela.
		
		super("Dicionário");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Criação do texto de apresentação do TAD.		
		
		String textDict = "\n=================================Introdução==========================================="
				
							+ "\n\nUm Dicionário é um Tipo Abstrato de Dados que permite armazenar elementos que podem ser"
							+ "\nlocalizados usando chaves, as chaves são os endereços de cada elemento, e dentro de um "
							+ "\ndicionário podem existir diversas chaves repetidas. Em caso de chaves repetidas o dicionário "
							+ "\nrealizará as operações de acordo com a ordem de entrada. Podemos perceber que o Dicionário "
							+ "\ntem muito em comum com o Tipo Abstrato de Dados Mapa."

							+ "\n\n==================================Métodos==========================================="
							
							+ "\n\nMétodos básicos do Dicionario:" +							
							
							"\n\nInserir/put: O elemento será inserido no dicionário juntamente com uma chave." + 
							"\n\nRemover/remove: O elemento que tiver a chave correspondente será removido do dicionário." + 
							"\n\nConsultar/get: O elemento que tiver a chave correspondente será encontrado no dicionário." + 

							"\n\n================================Implementação========================================="
							
							+ "\n\nPor questões de desenvolvimento a implementação do Dicionário trata-se de uma dicionário de"
							+ "\nStrings, permitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
							+ "\nnúmeros como Strings. Excerto para as chaves que deverão ser predefinidas.";	
		
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextDict = new JTextArea(textDict);
		
		//Configuração da caixa de texto.
		
		boxTextDict.setEditable(false);
		boxTextDict.setPreferredSize(new Dimension(550, 600));	
		
		//Criação de botoes para a janela.
		
		JButton createDict = new JButton("Criar Dicionário");
		
		//Insercão dos componentes na janela.
		
		add(boxTextDict);
		add(createDict);
		
		//Configuração dos botoes da janela.
		
		createDict.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createDict();}});
	}	
	
	//Cria um dicionario e uma tela de representação para o mesmo.
	public void createDict() {
		
		dict = new HashTableMultiMap<String, JButton>(); //Inicia o dicionario.
		
		keys = new NodePositionList<String>(); //Inicia a lista de chaves.
		
		createRepresentationScreen("Dicionário", dict); //Cria uma janela para o dicionario.				
	}	
	
	//Cria uma janela para representar um dicionário.
	public void createRepresentationScreen(String typeDict, HashTableMultiMap<String, JButton> dict) {
		
		//Cria uma janela para representar o dicionário.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeDict);
		screen.setSize(500, 525);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes do dicionário.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 525));
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem put = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem get = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		put.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {putDict(dict, panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeDict(dict, panel);}});
		get.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getDict(dict);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(put);
		menu.add(remove);
		menu.add(get);		
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);
	}
	
	//Cria uma janela para adiciona elementos ao dicionario.
	public void putDict(HashTableMultiMap<String, JButton> dict, JPanel panel) {		
		
		//Cria uma janela para coletar os elementos que serão adicionados no dicionário.
		
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
				
				//Criação do botão que será inserido no dicionário com o elemento inserido na tela de adição.
				
				JButton elementDict = new JButton(element.getText());
				
				//Configuração dos elementos do painel.
				
				elementDict.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {JOptionPane.showMessageDialog(null, "Chave:["+ key.getText() + "], " + "Elemento [" + element.getText() + "]", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);}});
				
				//Adiciona a chave inserida pelo usuário na lista de chaves
				
				boolean keyFind = false;
				
				for(String keyList : keys) {if(keyList.equals(key.getText())) {keyFind = true;}} //Verifica se já existe uma chave igual na lista de usuário.
				
				if(!keyFind) {keys.addLast(key.getText());}				
				
				//Adiciona o botão criado no dicionário.
				
				dict.put(key.getText(), elementDict);
				
			//Libera uma mensagem sobre o erro.	
			} catch(IllegalArgumentException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}	
			
			refreshScreen(panel);
		}		
	}
	
	//Cria uma janela para consultar elementos do dicionario.
	public void getDict(HashTableMultiMap<String, JButton> dict) {		
		
		//Cria uma janela para coletar os elementos que serão adicionados na fila de prioridade.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
		
		//Criação dos botoes da janela.
		
		panelEntry.add(new JLabel("Índice: "));
		
		JTextField key = new JTextField();
		
		//Configuração dos botoes da janela.
		
		panelEntry.add(key);		
		
		Object[] options = {"OK", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				panelEntry, 
				"Insira o Índice do Elemento:", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);		
		
		if(option == 0) {
			
			try { //Trata uma possivel exceção.
				
				if(!dict.isEmpty()) {
					
					String keyElement = dict.get(key.getText()).getKey();
					String element = dict.get(key.getText()).getValue().getText();
					
					JOptionPane.showMessageDialog(null, "Chave:["+ keyElement + "], " + "Elemento [" + element + "]", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);					

					
				} else {JOptionPane.showMessageDialog(null, "O Dicionário está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);}
				
			//Libera uma mensagem sobre o erro.	
			} catch(IllegalArgumentException error) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);
			
			} catch(Exception error) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}
	
	//Cria uma janela para remover elementos do dicionario.
	public void removeDict(HashTableMultiMap<String, JButton> dict, JPanel panel) {		
		
		//Cria uma janela para coletar a chave do elemento que será removido do dicionario.
		
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
				
				if(!dict.isEmpty()) {
					
					dict.remove(dict.get(key.getText())); //Remove o elemento do dicionário de acordo com a chave inserida pelo usuario.					
					
					//Verifica se o dicinário tem apenas um elemento com a chave informada pelo usuário.

					if(dict.get(key.getText()) == null) {					
						
						//Armazena a primeira posição da lista de chaves.
						
						Position<String> current = keys.first();
						
						//Armazena a posição encontrada na lista de chaves;
						
						Position<String> find = null;
						
						//Percorre a lista de chaves.
						
						while(find == null) {
							
							if(current.element().equals(key.getText())) {find = current; break;} //Coleta a posição encontrada e encerra o loop.
							else if(current != keys.last()){current = keys.next(current);} //Avança a posição.
						}
	
						keys.remove(find); //Remove a chave da lista de chaves.					
					}
						
					//Recarrega a janela.
					
					refreshScreen(panel);
					
				} else {JOptionPane.showMessageDialog(null, "O Dicionário está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);}
				
			//Libera uma mensagem sobre o erro.	
			} catch(IllegalArgumentException error) {JOptionPane.showMessageDialog(null, "A chave não foi encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);}		
		}		
	}	
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel panel) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();
			
		//Cria uma janela para representa os componentes de cada chave do dicionário separada por linhas.
		
		
		JPanel panelElements = new JPanel(new GridLayout(keys.size(), 1));
		
		//Percorre a lista de chaves criada e para cada chave cria uma baia de elementos do dicionário com a mesma chave.
		
		for(String key : keys) {
			
			JPanel panelDict = new JPanel(new GridLayout(2, 1)); //Cria uma janela para a chave e o conjuto de elementos.
			
			panelDict.add(new JLabel(key, SwingConstants.CENTER)); //Adiciona a chave na janela.
			
			JPanel panelElement = new JPanel(); //Cria um painel para agrupar os componentes com a chave referida.
			
			for(Entry<String, JButton> entry : dict.getAll(key)) { //Percorre os elementos de cada chave e insere na janela.
								
				panelElement.add(entry.getValue());
			}

			panelDict.add(panelElement); //Adiciona a janela com os componentes das chaves na janela do conjunto de elementos.
			panelElements.add(panelDict); //Adiciona a janela com a chave e o conjunto na janela principal.			
		}
		
		panel.add(panelElements); //Adiciona a janela principal na janela de representação.		

		//Métodos da janela para recarrega-lá.
		
		panel.revalidate();
		panel.repaint();
	}
	
	public static void main(String[] args) {new MainScreen();}

}