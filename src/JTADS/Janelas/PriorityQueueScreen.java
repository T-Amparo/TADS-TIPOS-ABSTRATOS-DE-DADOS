package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Excecoes.EmptyPriorityQueueException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Excecoes.InvalidKeyException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Fontes.SortedListPriorityQueue;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Interfaces.Entry;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Interfaces.Position;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade.Interfaces.PositionList;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Excecoes.EmptyHeapPriorityQueueException;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Fontes.HeapPriorityQueue;
import TADS.TAD_Fila_Prioridade.TAD_Fila_Prioridade_Heap.Interfaces.EntryHeapPriorityQueue;
import TADS.TAD_Lista.TAD_Lista_Arranjo.Fontes.ArrayIndexList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class PriorityQueueScreen extends JFrame {
	
	Object priorityQueue;
	Object priorityQueueHeap;
	
	public PriorityQueueScreen() {
		
		//Configura��es da janela.
		
		super("Fila de Prioridade");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);	
		
		//Cria��o do texto de apresenta��o do TAD.		
		
		String textPriorityQueue = "\n=================================Introdu��o==========================================="
							
							+ "\n\nUma Fila de Prioridade � um Tipo Abstrato de Dados que permite que um conjunto de elementos "
							+ "\nseja armazenado conforme as suas prioridades, aonde cada elemento ter� sua pr�pria chave que "
							+ "\nser� atribu�da no momento que o elemento for inserido."
							
							+ "\n\nA Fila de Prioridade trabalha com chaves-valor que s�o usadas para identificar a prioridade de um "
							+ "\ndeterminado elemento, como por exemplo uma fila de prioridade de uma determinada loja, o cliente "
							+ "\nem espera recebe uma senha e � inserido na fila, caso a caixa esteja livre, o cliente com a menor "
							+ "\nsenha ser� removido da fila para ser atendido."

							+ "\n\n==================================M�todos==========================================="
							
							+ "\n\nM�todos b�sicos da Fila de Prioridade:" +						
							
							"\n\nInserir/insert: O elemento ser� inserido na fila juntamente com uma chave, que ir� determinar o seu\nnivel de prioridade." + 
							"\n\nRemover/removeMin: O elemento que tiver a chave com a menor prioridade ser� removido da fila." + 
							"\n\nConsultar/min: O elemento que tiver a chave com a menor prioridade ser� encontrado na fila." + 

							"\n\n================================Implementa��o=========================================" +
							
							"\n\nPara esta implementa��o foi implementada duas variantes da fila de prioridade, uma que utiliza a "
							+ "\nLista Simplesmente Encadeada e outra que utiliza Heap." + 
							
							"\n\nPor quest�es de desenvolvimento a implementa��o da Fila de Prioridade trata-se de uma fila de"
							+ "\nStrings, permitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
							+ "\nn�meros como Strings. Excerto para as chaves que dever�o ser predefinidas.";	
			
		//Inser��o do texto em uma caixa de texto para exibi��o.
		
		JTextArea boxTextPriorityQueue = new JTextArea(textPriorityQueue);
		
		//Configura��o da caixa de texto.
		
		boxTextPriorityQueue.setEditable(false);
		boxTextPriorityQueue.setPreferredSize(new Dimension(550, 600));	
		
		//Cria��o de botoes para a janela.
		
		JButton createQueue = new JButton("Criar Fila de Prioridade");
		
		//Inserc�o dos componentes na janela.
		
		add(boxTextPriorityQueue);
		add(createQueue);
		
		//Configura��o dos botoes da janela.
		
		createQueue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createPriorityQueue();}});
	}
	
	//Cria uma fila de prioridade e uma tela de representa��o para � fila de prioridade.
	@SuppressWarnings("unchecked")
	public void createPriorityQueue() {		

		//Janela de cria��o da fila de prioridade.
		
		Object[] options = {"Fila de Prioridade", "Fila de Prioridade com Heap"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione o Tipo de Fila de Prioridade:", 
				"Tipos de Filas de Prioridade", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Cria � fila de prioridade escolhida pelo usu�rio.
		
		Object[] types = {"Inteiros", "Strings"};
		
		switch(option) {
		
			case 0: //Cria uma fila de prioridade.				

				//Janela para coletar o tipo de chave que a fila ir� armazenar.
				
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
					
					priorityQueue = new SortedListPriorityQueue<Integer, ArrayIndexList<JComponent>>(); //Cria uma fila de prioridade com chaves inteiras.
					
					createRepresentationScreenIntegers("Fila de Prioridade", ((SortedListPriorityQueue<Integer, ArrayIndexList<JComponent>>)priorityQueue)); //Cria uma janela para a fila de prioridade.
					
				} else {
					
					priorityQueue = new SortedListPriorityQueue<String, ArrayIndexList<JComponent>>(); //Cria uma fila de prioridade com chaves strings.
					
					createRepresentationScreenStrings("Fila de Prioridade", ((SortedListPriorityQueue<String, ArrayIndexList<JComponent>>)priorityQueue)); //Cria uma janela para a fila de prioridade.					
				} 
				
				
				
				break;
			
			case 1: //Cria uma fila de prioridade com heap.				

				//Janela para coletar o tipo de chave que a fila ir� armazenar.
				
				type = JOptionPane.showOptionDialog(
						
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
					
					priorityQueueHeap = new HeapPriorityQueue<Integer, ArrayIndexList<JComponent>>(); //Cria uma fila de prioridade heap com chaves inteiras.
					
					createRepresentationScreenIntegers("Fila de Prioridade com Heap", ((HeapPriorityQueue<Integer, ArrayIndexList<JComponent>>)priorityQueueHeap)); //Cria uma janela para a fila de prioridade com heap.
					
				} else {
					
					priorityQueueHeap = new HeapPriorityQueue<String, ArrayIndexList<JComponent>>(); //Cria uma fila de prioridade heap com chaves strings.
					
					createRepresentationScreenStrings("Fila de Prioridade com Heap", ((HeapPriorityQueue<String, ArrayIndexList<JComponent>>)priorityQueueHeap)); //Cria uma janela para a fila de prioridade heap.					
				}
				
				break;		
		}		
	}
	
	//Cria uma janela para representar uma fila de prioridade.
	public void createRepresentationScreenIntegers(String typeQueue, SortedListPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueue) {		
		
		//Cria uma janela para representar uma fila LSE.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeQueue);
		screen.setSize(500, 145);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da fila.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 145));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem insert = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem removeMin = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem min = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		insert.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {insertPriorityQueueIntegers(priorityQueue, -1, panel);}});
		removeMin.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeMinPriorityQueueIntegers(priorityQueue, panel);}});
		min.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {minPriorityQueueIntegers(priorityQueue);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(insert);
		menu.add(removeMin);
		menu.add(min);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);		
	}	
	
	//Cria uma janela para representar uma fila de prioridade com heap.
	public void createRepresentationScreenIntegers(String typeQueue, HeapPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueueHeap) {		
		
		//Cria uma janela para representar uma fila LSE.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeQueue);
		screen.setSize(500, 145);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da fila.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 145));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem insert = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem removeMin = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem min = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		insert.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {insertPriorityQueueIntegers(priorityQueueHeap, -1, panel);}});
		removeMin.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeMinPriorityQueueIntegers(priorityQueueHeap, panel);}});
		min.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {minPriorityQueueIntegers(priorityQueueHeap);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(insert);
		menu.add(removeMin);
		menu.add(min);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);		
	}	
	
	//Cria uma janela para representar uma fila de prioridade.
	public void createRepresentationScreenStrings(String typeQueue, SortedListPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueue) {		
		
		//Cria uma janela para representar uma fila LSE.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeQueue);
		screen.setSize(500, 145);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da fila.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 145));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem insert = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem removeMin = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem min = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		insert.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {insertPriorityQueueStrings(priorityQueue, "", panel);}});
		removeMin.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeMinPriorityQueueStrings(priorityQueue, panel);}});
		min.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {minPriorityQueueStrings(priorityQueue);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(insert);
		menu.add(removeMin);
		menu.add(min);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);		
	}	
	
	//Cria uma janela para representar uma fila de prioridade com heap.
	public void createRepresentationScreenStrings(String typeQueue, HeapPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueueHeap) {		
		
		//Cria uma janela para representar uma fila LSE.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeQueue);
		screen.setSize(500, 145);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da fila.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 145));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem insert = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem removeMin = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem min = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		insert.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {insertPriorityQueueStrings(priorityQueueHeap, "", panel);}});
		removeMin.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeMinPriorityQueueStrings(priorityQueueHeap, panel);}});
		min.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {minPriorityQueueStrings(priorityQueueHeap);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(insert);
		menu.add(removeMin);
		menu.add(min);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);		
	}
	
	//Adiciona um item na fila de prioridade e na janela de representa��o da fila.
	public void insertPriorityQueueIntegers(SortedListPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueue, int keyPriorityQueue, JPanel panel) {		
		
		//Cria uma lista arranjo para conter os componentes de exibi��o de uma dada posi��o da fila de prioridade.
		
		ArrayIndexList<JComponent> components = new ArrayIndexList<JComponent>(4);
		
		//Cria uma janela para coletar os elementos que ser�o adicionados na fila de prioridade.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Cria��o dos botoes da janela.
		
		panelEntry.add(new JLabel("Chave: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField key;
		JTextField element = new JTextField();
		
		if(keyPriorityQueue == -1) {key = new JTextField();}
		else {key = new JTextField(String.valueOf(keyPriorityQueue));}
		
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
		
		if (option == 0) {			
			
			//Cria��o do painel que ser� inserido na fila de prioriade com o painel inserido na tela de adi��o.
			
			JPanel panelElement = new JPanel(new GridLayout(2, 1));		
			
			//Cria��o dos elementos do painel, que armazenar�o os dados de entrada do usu�rio.
			
			JButton priorityQueueElement = new JButton(String.valueOf(element.getText()));
			JButton priorityQueueKey = new JButton(String.valueOf(key.getText()));
			JLabel priorityQueueIndex = new JLabel(String.valueOf(priorityQueue.size() + 1), SwingConstants.RIGHT);
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, priorityQueueKey);
			components.add(1, priorityQueueElement);
			components.add(2, priorityQueueIndex);
			components.add(3, panelElement);
			
			//Configura��o dos elementos do painel.
			
			priorityQueueElement.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenIntegers(priorityQueue, Integer.parseInt(((JButton)components.get(0)).getText()), panel);}});
			priorityQueueKey.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenIntegers(priorityQueue, Integer.parseInt(((JButton)components.get(0)).getText()), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(priorityQueueElement);
			panelElement.add(priorityQueueKey);
			panelElement.add(priorityQueueIndex);
			
			try { //Trata uma possivel exce��o.
				
				//Adiciona o painel criado na fila.
				
				priorityQueue.insert(Integer.parseInt(key.getText()), components);
				
				//Adiciona o painel criado na janela.
				
				panel.add(panelElement);
				
				//Recarrega a janela.
				
				refreshScreenIntegers(panel, priorityQueue);
			}
			
			//Libera um mensagem de erro.
			catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}	
	
	//Adiciona um item na fila de prioridade com heap e na janela de representa��o da fila.
	public void insertPriorityQueueIntegers(HeapPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueueHeap, int keyPriorityQueueHeap, JPanel panel) {		
		
		//Cria uma lista arranjo para conter os componentes de exibi��o de uma dada posi��o da fila de prioridade.
		
		ArrayIndexList<JComponent> components = new ArrayIndexList<JComponent>(4);
		
		//Cria uma janela para coletar os elementos que ser�o adicionados na fila de prioridade.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Cria��o dos botoes da janela.
		
		panelEntry.add(new JLabel("Chave: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField key;
		JTextField element = new JTextField();
		
		if(keyPriorityQueueHeap == -1) {key = new JTextField();}
		else {key = new JTextField(String.valueOf(keyPriorityQueueHeap));}
		
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
		
		if (option == 0) {			
			
			//Cria��o do painel que ser� inserido na fila de prioriade com o painel inserido na tela de adi��o.
			
			JPanel panelElement = new JPanel(new GridLayout(2, 1));		
			
			//Cria��o dos elementos do painel, que armazenar�o os dados de entrada do usu�rio.
			
			JButton priorityQueueElement = new JButton(String.valueOf(element.getText()));
			JButton priorityQueueKey = new JButton(String.valueOf(key.getText()));
			JLabel priorityQueueIndex = new JLabel(String.valueOf(priorityQueueHeap.size() + 1), SwingConstants.RIGHT);
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, priorityQueueKey);
			components.add(1, priorityQueueElement);
			components.add(2, priorityQueueIndex);
			components.add(3, panelElement);
			
			//Configura��o dos elementos do painel.
			
			priorityQueueElement.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenIntegers(priorityQueueHeap, Integer.parseInt(((JButton)components.get(0)).getText()), panel);}});
			priorityQueueKey.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenIntegers(priorityQueueHeap, Integer.parseInt(((JButton)components.get(0)).getText()), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(priorityQueueElement);
			panelElement.add(priorityQueueKey);
			panelElement.add(priorityQueueIndex);
			
			try { //Trata uma possivel exce��o.
				
				//Adiciona o painel criado na fila.
				
				priorityQueueHeap.insert(Integer.parseInt(key.getText()), components);
				
				//Adiciona o painel criado na janela.
				
				panel.add(panelElement);
				
				//Recarrega a janela.
				
				
			}
			
			//Libera um mensagem de erro.
			catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}	
			
			refreshScreenIntegers(panel, priorityQueueHeap);
		}		
	}	
	
	//Adiciona um item na fila de prioridade e na janela de representa��o da fila.
	public void insertPriorityQueueStrings(SortedListPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueue, String keyPriorityQueue, JPanel panel) {		
		
		//Cria uma lista arranjo para conter os componentes de exibi��o de uma dada posi��o da fila de prioridade.
		
		ArrayIndexList<JComponent> components = new ArrayIndexList<JComponent>(4);
		
		//Cria uma janela para coletar os elementos que ser�o adicionados na fila de prioridade.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Cria��o dos botoes da janela.
		
		panelEntry.add(new JLabel("Chave: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField key;
		JTextField element = new JTextField();
		
		if(keyPriorityQueue == "") {key = new JTextField();}
		else {key = new JTextField(String.valueOf(keyPriorityQueue));}
		
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
		
		if (option == 0) {			
			
			//Cria��o do painel que ser� inserido na fila de prioriade com o painel inserido na tela de adi��o.
			
			JPanel panelElement = new JPanel(new GridLayout(2, 1));		
			
			//Cria��o dos elementos do painel, que armazenar�o os dados de entrada do usu�rio.
			
			JButton priorityQueueElement = new JButton(String.valueOf(element.getText()));
			JButton priorityQueueKey = new JButton(String.valueOf(key.getText()));
			JLabel priorityQueueIndex = new JLabel(String.valueOf(priorityQueue.size() + 1), SwingConstants.RIGHT);
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, priorityQueueKey);
			components.add(1, priorityQueueElement);
			components.add(2, priorityQueueIndex);
			components.add(3, panelElement);
			
			//Configura��o dos elementos do painel.
			
			priorityQueueElement.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenString(priorityQueue, String.valueOf(((JButton)components.get(0)).getText()), panel);}});
			priorityQueueKey.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenString(priorityQueue, String.valueOf(((JButton)components.get(0)).getText()), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(priorityQueueElement);
			panelElement.add(priorityQueueKey);
			panelElement.add(priorityQueueIndex);
			
			try { //Trata uma possivel exce��o.
				
				//Adiciona o painel criado na fila.
				
				priorityQueue.insert(key.getText(), components);
				
				//Adiciona o painel criado na janela.
				
				panel.add(panelElement);
				
				//Recarrega a janela.
				
				refreshScreenStrings(panel, priorityQueue);
			}
			
			//Libera um mensagem de erro.
			catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}
	
	//Adiciona um item na fila de prioridade e na janela de representa��o da fila.
	public void insertPriorityQueueStrings(HeapPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueueHeap, String keyPriorityQueue, JPanel panel) {		
		
		//Cria uma lista arranjo para conter os componentes de exibi��o de uma dada posi��o da fila de prioridade.
		
		ArrayIndexList<JComponent> components = new ArrayIndexList<JComponent>(4);
		
		//Cria uma janela para coletar os elementos que ser�o adicionados na fila de prioridade.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Cria��o dos botoes da janela.
		
		panelEntry.add(new JLabel("Chave: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField key;
		JTextField element = new JTextField();
		
		if(keyPriorityQueue == "") {key = new JTextField();}
		else {key = new JTextField(String.valueOf(keyPriorityQueue));}
		
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
		
		if (option == 0) {			
			
			//Cria��o do painel que ser� inserido na fila de prioriade com o painel inserido na tela de adi��o.
			
			JPanel panelElement = new JPanel(new GridLayout(2, 1));		
			
			//Cria��o dos elementos do painel, que armazenar�o os dados de entrada do usu�rio.
			
			JButton priorityQueueElement = new JButton(String.valueOf(element.getText()));
			JButton priorityQueueKey = new JButton(String.valueOf(key.getText()));
			JLabel priorityQueueIndex = new JLabel(String.valueOf(priorityQueueHeap.size() + 1), SwingConstants.RIGHT);
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, priorityQueueKey);
			components.add(1, priorityQueueElement);
			components.add(2, priorityQueueIndex);
			components.add(3, panelElement);
			
			//Configura��o dos elementos do painel.
			
			priorityQueueElement.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenString(priorityQueueHeap, String.valueOf(((JButton)components.get(0)).getText()), panel);}});
			priorityQueueKey.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreenString(priorityQueueHeap, String.valueOf(((JButton)components.get(0)).getText()), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(priorityQueueElement);
			panelElement.add(priorityQueueKey);
			panelElement.add(priorityQueueIndex);
			
			try { //Trata uma possivel exce��o.
				
				//Adiciona o painel criado na fila.
				
				priorityQueueHeap.insert(key.getText(), components);
				
				//Adiciona o painel criado na janela.
				
				panel.add(panelElement);
				
				//Recarrega a janela.
				
				refreshScreenStrings(panel, priorityQueueHeap);
			}
			
			//Libera um mensagem de erro.
			catch(InvalidKeyException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Chave Inv�lida!", "Aviso", JOptionPane.WARNING_MESSAGE);}			
		}		
	}
	
	public void removeMinPriorityQueueIntegers(SortedListPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueue, JPanel panel) {
		
		//Trata uma possivel exce��o.
		try {priorityQueue.removeMin(); refreshScreenIntegers(panel, priorityQueue);} //Remove o elemento da fila de prioridade e atualiza a janela.
		
		//Libera um mensagem de erro.
		catch(EmptyPriorityQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}	
	
	public void removeMinPriorityQueueIntegers(HeapPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueueHeap, JPanel panel) {
		
		//Trata uma possivel exce��o.
		try {priorityQueueHeap.removeMin(); refreshScreenIntegers(panel, priorityQueueHeap);} //Remove o elemento da fila de prioridade e atualiza a janela.
		
		//Libera um mensagem de erro.
		catch(EmptyHeapPriorityQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}	
	
	public void removeMinPriorityQueueStrings(SortedListPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueue, JPanel panel) {
		
		//Trata uma possivel exce��o.
		try {priorityQueue.removeMin(); refreshScreenStrings(panel, priorityQueue);} //Remove o elemento da fila de prioridade e atualiza a janela.
		
		//Libera um mensagem de erro.
		catch(EmptyPriorityQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}	
	
	public void removeMinPriorityQueueStrings(HeapPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueueHeap, JPanel panel) {
		
		//Trata uma possivel exce��o.
		try {priorityQueueHeap.removeMin(); refreshScreenStrings(panel, priorityQueueHeap);} //Remove o elemento da fila de prioridade e atualiza a janela.
		
		//Libera um mensagem de erro.
		catch(EmptyHeapPriorityQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Apresenta em uma janela o elemento com a menor chave da fila.
	public void minPriorityQueueIntegers(SortedListPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueue) {
		
		//Trata uma possivel exce��o.
		try {			
			
			//Recupera a chave e o elemento da posi��o da fila.
			
			String key = String.valueOf(priorityQueue.min().getKey());
			String element = String.valueOf(((JButton)priorityQueue.min().getValue().get(1)).getText());
			
			JOptionPane.showMessageDialog(null, "Chave: (" + key +"), Elemento: (" + element + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento com a menor chave da fila.
		
		catch(EmptyPriorityQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
	}		
	
	//Apresenta em uma janela o elemento com a menor chave da fila de prioridade com heap.
	public void minPriorityQueueIntegers(HeapPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueueHeap) {
		
		//Trata uma possivel exce��o.
		try {			
			
			//Recupera a chave e o elemento da posi��o da fila.
			
			String key = String.valueOf(priorityQueueHeap.min().getKey());
			String element = String.valueOf(((JButton)priorityQueueHeap.min().getValue().get(1)).getText());
			
			JOptionPane.showMessageDialog(null, "Chave: (" + key +"), Elemento: (" + element + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento com a menor chave da fila.
		
		catch(EmptyHeapPriorityQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
	}
	
	//Apresenta em uma janela o elemento com a menor chave da fila.
	public void minPriorityQueueStrings(SortedListPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueue) {
		
		//Trata uma possivel exce��o.
		try {			
			
			//Recupera a chave e o elemento da posi��o da fila.
			
			String key = String.valueOf(priorityQueue.min().getKey());
			String element = String.valueOf(((JButton)priorityQueue.min().getValue().get(1)).getText());
			
			JOptionPane.showMessageDialog(null, "Chave: (" + key +"), Elemento: (" + element + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento com a menor chave da fila.
		
		catch(EmptyPriorityQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
	}	
	
	//Apresenta em uma janela o elemento com a menor chave da fila.
	public void minPriorityQueueStrings(HeapPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueue) {
		
		//Trata uma possivel exce��o.
		try {			
			
			//Recupera a chave e o elemento da posi��o da fila.
			
			String key = String.valueOf(priorityQueue.min().getKey());
			String element = String.valueOf(((JButton)priorityQueue.min().getValue().get(1)).getText());
			
			JOptionPane.showMessageDialog(null, "Chave: (" + key +"), Elemento: (" + element + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento com a menor chave da fila.
		
		catch(EmptyHeapPriorityQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
	}
	
	//Exibi uma tela de op��es ao clicar no elementos da fila.
	public void buttonManagerScreenIntegers(SortedListPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueue, int keyPriorityQueue, JPanel panel) {		
		
		//Cria uma janela de op��es.
		
		Object[] options = {"Adicionar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Op��o: ", 
				"M�todos Fila de Prioridade", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual m�todo ser� chamado de acordo com a op��o escolhida.
		
		if (option == 0) {insertPriorityQueueIntegers(priorityQueue,  keyPriorityQueue, panel);} //Adicionar na fila, usando a mesma chave.
	}		
	
	//Exibi uma tela de op��es ao clicar no elementos da fila de prioridade com heap.
	public void buttonManagerScreenIntegers(HeapPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueueHeap, int keyPriorityQueue, JPanel panel) {		
		
		//Cria uma janela de op��es.
		
		Object[] options = {"Adicionar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Op��o: ", 
				"M�todos Fila de Prioridade com Heap", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual m�todo ser� chamado de acordo com a op��o escolhida.
		
		if (option == 0) {insertPriorityQueueIntegers(priorityQueueHeap,  keyPriorityQueue, panel);} //Adicionar na fila, usando a mesma chave.
	}
	
	//Exibi uma tela de op��es ao clicar no elementos da fila.
	public void buttonManagerScreenString(SortedListPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueue, String keyPriorityQueue, JPanel panel) {		
		
		//Cria uma janela de op��es.
		
		Object[] options = {"Adicionar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Op��o: ", 
				"M�todos Fila de Prioridade", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual m�todo ser� chamado de acordo com a op��o escolhida.
		
		if (option == 0) {insertPriorityQueueStrings(priorityQueue,  keyPriorityQueue, panel);} //Adicionar na fila, usando a mesma chave.
	}
	
	//Exibi uma tela de op��es ao clicar no elementos da fila.
	public void buttonManagerScreenString(HeapPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueue, String keyPriorityQueue, JPanel panel) {		
		
		//Cria uma janela de op��es.
		
		Object[] options = {"Adicionar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Op��o: ", 
				"M�todos Fila de Prioridade com Heap", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual m�todo ser� chamado de acordo com a op��o escolhida.
		
		if (option == 0) {insertPriorityQueueStrings(priorityQueue,  keyPriorityQueue, panel);} //Adicionar na fila, usando a mesma chave.
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreenIntegers(JPanel panel, SortedListPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueue) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();
		
		if(priorityQueue.size() != 0) {
			
			//Recupera a fila de prioridade contendo todos os elementos.
			
			PositionList<Entry<Integer, ArrayIndexList<JComponent>>> entries = priorityQueue.getEntries();
			
			//Recupera a primeira posi��o da fila.
			
			Position<Entry<Integer, ArrayIndexList<JComponent>>> current = entries.first();
			
			//Percorre a lista arranjo e adiciona na janela os componentes contidos na lista arranjo.
			
			for(int i = 0; i < entries.size(); i++) {
	
				((JLabel)current.element().getValue().get(2)).setText(String.valueOf(i));
				
				JPanel panelElement = ((JPanel)current.element().getValue().get(3));			
				
				panel.add(panelElement);
				
				if(i < entries.size()-1) {current = entries.next(current);}			
			}
		}
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();	
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreenIntegers(JPanel panel, HeapPriorityQueue<Integer, ArrayIndexList<JComponent>> priorityQueue) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();
		
		if(priorityQueue.size() != 0) {
			
			//Recupera a fila de prioridade contendo todos os elementos.
			
			ArrayList<EntryHeapPriorityQueue<Integer, ArrayIndexList<JComponent>>> entries = priorityQueue.positions();
			
			//Percorre a lista arranjo e adiciona na janela os componentes contidos na lista arranjo.
			
			for(int i = 0; i < entries.size(); i++) {
				
				((JLabel)entries.get(i).getValue().get(2)).setText(String.valueOf(i));
				
				JPanel panelElement = ((JPanel)entries.get(i).getValue().get(3));			
				
				panel.add(panelElement);		
			}
		}
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();	
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreenStrings(JPanel panel, SortedListPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueue) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();
		
		if(priorityQueue.size() != 0) {
			
			//Recupera a fila de prioridade contendo todos os elementos.
			
			PositionList<Entry<String, ArrayIndexList<JComponent>>> entries = priorityQueue.getEntries();
			
			//Recupera a primeira posi��o da fila.
			
			Position<Entry<String, ArrayIndexList<JComponent>>> current = entries.first();
			
			//Percorre a lista arranjo e adiciona na janela os componentes contidos na lista arranjo.
			
			for(int i = 0; i < entries.size(); i++) {
	
				((JLabel)current.element().getValue().get(2)).setText(String.valueOf(i));
				
				JPanel panelElement = ((JPanel)current.element().getValue().get(3));			
				
				panel.add(panelElement);
				
				if(i < entries.size()-1) {current = entries.next(current);}			
			}
		}
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();	
	}	
	
	//Recarrega os componentes da janela.
	public void refreshScreenStrings(JPanel panel, HeapPriorityQueue<String, ArrayIndexList<JComponent>> priorityQueue) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();
		
		if(priorityQueue.size() != 0) {
			
			//Recupera a fila de prioridade contendo todos os elementos.
			
			ArrayList<EntryHeapPriorityQueue<String, ArrayIndexList<JComponent>>> entries = priorityQueue.positions();
			
			//Percorre a lista arranjo e adiciona na janela os componentes contidos na lista arranjo.
			
			for(int i = 0; i < entries.size(); i++) {
				
				((JLabel)entries.get(i).getValue().get(2)).setText(String.valueOf(i));
				
				JPanel panelElement = ((JPanel)entries.get(i).getValue().get(3));			
				
				panel.add(panelElement);		
			}
		}
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();	
	}
	
	public static void main(String[] args) {new MainScreen();}

}