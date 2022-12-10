package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Fila.TAD_Fila_Arranjo.Excecoes.EmptyQueueExceptionArrayQueue;
import TADS.TAD_Fila.TAD_Fila_Arranjo.Excecoes.FullQueueException;
import TADS.TAD_Fila.TAD_Fila_Arranjo.Fontes.ArrayQueue;
import TADS.TAD_Fila.TAD_Fila_LSE.Excecoes.EmptyQueueExceptionNodeQueue;
import TADS.TAD_Fila.TAD_Fila_LSE.Fontes.NodeQueue;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class QueueScreen extends JFrame {
	
	NodeQueue<JButton> nodeQueue;
	ArrayQueue<JButton> arrayQueue;
	
	public QueueScreen() {
		
		//Configurações da janela.
		
		super("Fila");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);
		
		//Criação do texto de apresentação do TAD.		
		
		String textQueue = "\n=================================Introdução==========================================="+
		
							"\n\nUma fila é um Tipo Abstrato de Dados que permite armazena uma coleção de elementos de \n"
							+ "forma linear, aonde os acessos na fila são realizados em apenas uma de suas extremidades, \no inicio da fila."
							
							+ "\n\nA estrutura de uma fila se define pelo padrão FIFO (Primeiro a entrar, primeiro a sair), "
							+ "\nesse padrão é justamente o que define a fila, pois a fila só permiti acesso a uma de suas "
							+ "\nextremidades o inicio da fila, nesse caso quando se necessita de um elemento da fila, o \nmesmo será "
							+ "removido do inicio da fila e quando for necessário inserir um novo elemento o \nmesmo será "
							+ "inserido no final da fila podendo se tornar o novo inicio da fila."
							
							+ "\n\n==================================Métodos==========================================="
							+ "\n\nMétodos básicos da Fila:" +
							
							"\n\nInserir/Enqueue: O elemento que for inserido na fila receberá uma posição no final da fila." + 
							"\n\nRemover/Dequeue: O elemento que estiver localizado no inicioa da fila será removido." + 
							"\n\nConsultar/Front: O Elemento será encontrada no inicio da fila." +							
							
							"\n\n================================Implementação=========================================" +
							
							"\n\nPara esta implementação foi implementada duas variantes da fila, uma que utiliza a "
							+ "\nLista Simplesmente Encadeada e outra que utiliza arranjo." + 
							
							"\n\nPor questões de desenvolvimento a implementação da Fila trata-se de uma fila de Strings, "
							+ "\npermitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
							+ "\nnúmeros como Strings.";	
			
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextQueue = new JTextArea(textQueue);
		
		//Configuração da caixa de texto.
		
		boxTextQueue.setEditable(false);
		boxTextQueue.setPreferredSize(new Dimension(550, 600));	
		
		//Criação de botoes para a janela.
		
		JButton createQueue = new JButton("Criar Fila");
		
		//Insercão dos componentes na janela.
		
		add(boxTextQueue);
		add(createQueue);
		
		//Configuração dos botoes da janela.
		
		createQueue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createQueue();}});
	}
	
	//Cria uma fila e uma tela de representação para à fila.
	public void createQueue() {		

		//Janela de criação da fila.
		
		Object[] options = {"Fila com LSE", "Fila com Arranjo"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione o Tipo de Fila:", 
				"Tipos de Filas", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Cria à fila escolhida pelo usuário.
		
		switch(option) {
		
			case 0: //Cria uma fila LSE.
				
				nodeQueue = new NodeQueue<JButton>(); //Inicia a fila LSE.
				
				createRepresentationScreen("Fila LSE", nodeQueue); //Cria uma janela para a fila.
				
				break;
			
			case 1: //Cria uma fila de arranjos.				
				
				try { //Trata uma possivel exceção.
					
					//Cria uma janela para coletar o tamanho da fila de arranjos.
					
					int size = Integer.parseInt((String) JOptionPane.showInputDialog(
							
							this.getParent(),
							"Defina o Tamanho do Arranjo:",
							"Definação de Tamanho",
							JOptionPane.PLAIN_MESSAGE,
							null,
							null,
							null
					));
					
					if (size == 0) { 
						
						arrayQueue = new ArrayQueue<JButton>(); //Inicia a fila de arranjos com o tamanho padrão.
						
						JOptionPane.showMessageDialog(null, "A fila foi iniciada com o valor padrão!", "Aviso", JOptionPane.WARNING_MESSAGE); //Mensagem de aviso.						
					}
						
					else {arrayQueue = new ArrayQueue<JButton>(size);} //Inicia a fila de arranjos com o tamanho fornecido pelo usuário.					
					
					createRepresentationScreen("Fila de Arranjos", arrayQueue); //Cria uma janela para a fila.
				
				}catch(Exception error) {}
				
				break;		
		}		
	}	
	
	//Cria uma janela para representar uma fila.
	public void createRepresentationScreen(String typeQueue, NodeQueue<JButton> queue) {
		
		//Cria uma janela para representar uma fila LSE.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeQueue);
		screen.setSize(500, 115);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da fila.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 115));
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem enqueue = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem dequeue = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem front = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		enqueue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {enqueueQueue(queue, panel);}});
		dequeue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {dequeueQueue(queue, panel);}});
		front.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {frontQueue(queue);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(enqueue);
		menu.add(dequeue);
		menu.add(front);		
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);
	}	
	
	//Cria uma janela para representar uma fila.
	public void createRepresentationScreen(String typeQueue, ArrayQueue<JButton> queue) {
		
		//Cria uma janela para representar uma fila LSE.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeQueue);
		screen.setSize(500, 115);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da fila.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 115));
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem enqueue = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem dequeue = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem front = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		enqueue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {enqueueQueue(queue, panel);}});
		dequeue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {dequeueQueue(queue, panel);}});
		front.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {frontQueue(queue);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(enqueue);
		menu.add(dequeue);
		menu.add(front);		
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);
	}
	
	//Adiciona um item na fila LSE e na janela de representação da fila.
	public void enqueueQueue(NodeQueue<JButton> queue, JPanel panel) {		
		
		//Cria uma janela para coletar o elemento que será adicionado na fila.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira um Novo Elemento:",
				"Inserção de Elementos",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
		);
		
		if (element != null) {
			
			//Criação do botão que será inserido na fila com o elemento inserido na tela de adição.
			
			JButton elementQueue = new JButton(element);
			
			//Adiciona o botão criado na fila.
			
			queue.enqueue(elementQueue);
			
			//Adiciona o botão criado na janela.
			panel.add(elementQueue);
			
			//Recarrega a janela.
			refreshScreen(panel);
		}
	}	
	
	//Adiciona um item na fila de arranjos e na janela de representação da fila.
	public void enqueueQueue(ArrayQueue<JButton> queue, JPanel panel) {		
		
		//Cria uma janela para coletar o elemento que será adicionado na fila.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira um Novo Elemento:",
				"Inserção de Elementos",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
		);
		
		//Criação do botão que será inserido na fila com o elemento inserido na tela de adição.
		
		JButton elementQueue = new JButton(element);
		
		try { //Trata uma possivel exceção.
			
			if (element != null) {
				
				//Adiciona o botão criado na fila.
				
				queue.enqueue(elementQueue);
				
				//Adiciona o botão criado na janela.
				panel.add(elementQueue);
				
				//Recarrega a janela.
				refreshScreen(panel);
			}
		
		//Libera uma mensagem sobre o erro.
		} catch(FullQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Remove um item da fila LSE e da janela de representação da fila.
	public void dequeueQueue(NodeQueue<JButton> queue, JPanel panel) {
		
		try { //Trata uma possivel exceção.
			
			//Remove o botão do início da fila.
			panel.remove(queue.dequeue());
			
			//Recarrega a janela.
			refreshScreen(panel);
			
		//Libera uma mensagem sobre o erro.	
		} catch(EmptyQueueExceptionNodeQueue error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Remove um item da fila de arranjos e da janela de representação da fila.
	public void dequeueQueue(ArrayQueue<JButton> queue, JPanel panel) {
		
		try { //Trata uma possivel exceção.
			
			//Remove o botão do início da fila.
			panel.remove(queue.dequeue());
			
			//Recarrega a janela.
			refreshScreen(panel);
		
		//Libera uma mensagem sobre o erro.
		} catch(EmptyQueueExceptionArrayQueue error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Apresenta em uma janela o elemento do início da fila.
	public void frontQueue(NodeQueue<JButton> queue) {
		
		//Trata uma possivel exceção.
		try {JOptionPane.showMessageDialog(null, "Elemento do Início: (" + queue.front().getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento do início da fila.
		catch(EmptyQueueExceptionNodeQueue error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
	}	
	
	//Apresenta em uma janela o elemento do início da fila.
	public void frontQueue(ArrayQueue<JButton> queue) {
		
		//Trata uma possivel exceção.
		try {JOptionPane.showMessageDialog(null, "Elemento do Início: (" + queue.front().getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento do início da fila.
		catch(EmptyQueueExceptionArrayQueue error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.	
	}	
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel screen) {
		
		//Métodos da janela para recarrega-lá.
		screen.revalidate();
		screen.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}

}